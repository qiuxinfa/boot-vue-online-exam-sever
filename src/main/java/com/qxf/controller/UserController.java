package com.qxf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.dto.JwtDto;
import com.qxf.entity.User;
import com.qxf.security.config.TokenProvider;
import com.qxf.security.property.SecurityProperties;
import com.qxf.service.UserService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


/**
 * @ClassName UserController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 20:37
 **/
@RestController
@RequestMapping("user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private  final static String rootPath = "D:/attachment/";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String username){
        PageHelper.startPage(startPage,pageSize);
        //查询自己的考试记录
        List<User> list = userService.getListByPage(username);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,pageInfo.getTotal());
    }

    @PostMapping("/add")
    public ResultUtil addUser(@RequestBody User user){
        String msg = "新增失败！";
        Integer cnt = userService.addUser(user);
        if (cnt > 0){
            msg = "新增成功！";
        }
        return new ResultUtil(EnumCode.OK.getValue(),msg);
    }

    @PostMapping("/update")
    public ResultUtil updateUser(@RequestBody User user){
        String msg = "修改失败！";
        Integer cnt = userService.updateUser(user);
        if (cnt > 0){
            msg = "修改成功！";
        }
        return new ResultUtil(EnumCode.OK.getValue(),msg);
    }

    @PostMapping("/delete")
    public ResultUtil deleteUser(String id){
        String msg = "0";
        Integer cnt = userService.deleteUser(id);
        if (cnt > 0){
            msg = "1";
        }
        return new ResultUtil(EnumCode.OK.getValue(),msg);
    }
    //用户登录
    @PostMapping("/login")
    public ResultUtil login(@RequestBody User user, HttpServletRequest request,
                                        HttpServletResponse response) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        //认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String jwtToken = tokenProvider.createToken(authentication);
        // 返回 token 与 用户信息
        Map<String,Object> authInfo = new HashMap<String,Object>(3){{
            put("token", securityProperties.getTokenStartWith() + jwtToken);
            put("tokenExpiredTime",new Date().getTime() + securityProperties.getTokenValidityInSeconds());
            put("user",authentication.getPrincipal());
        }};
        logger.info(user.getUsername()+" ：登录成功");
        return new ResultUtil(EnumCode.OK.getValue(),"登录成功！",authInfo);
    }

    @GetMapping("/info")
    public ResultUtil getUserInfo(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String,Object> authInfo = new HashMap<String,Object>(2){{
            put("name",userDetails == null ? "未知用户" : userDetails.getUsername());
            put("avatar", "");
        }};
        return new ResultUtil(EnumCode.OK.getValue(),"获取用户信息成功",authInfo);
    }

    //刷新token
    @PostMapping("/refreshToken")
    public ResultUtil refreshToken(@RequestBody JwtDto jwtDto) throws JsonProcessingException {
        Map<String,Object> authInfo = new HashMap<>(3);
        String token = jwtDto.getToken();
        String username = jwtDto.getUsername();
        //检查token格式
        if (token != null && token.startsWith(securityProperties.getTokenStartWith())){
            //去掉头部
            token = token.replace(securityProperties.getTokenStartWith(),"");
            logger.info("开始刷新token当前时间 {} ms",new Date().getTime() + securityProperties.getTokenValidityInSeconds());
            //返回新的token
//            try {
//                tokenProvider.validateRefreshToken(token);
//            }catch (ExpiredJwtException e){
//                //token过期
//                logger.info("refreshToken，token已过期");
//
//            }
            //认证
//            User user = userService.getUserByUsername(username);
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),"123456");
//            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // 生成新的令牌
            String jwtToken = tokenProvider.createToken(authentication);

            authInfo.put("token", securityProperties.getTokenStartWith() + jwtToken);
            authInfo.put("tokenExpiredTime",new Date().getTime() + securityProperties.getTokenValidityInSeconds());
            authInfo.put("user",authentication.getPrincipal());
        }else {
            logger.info("请求刷新的token无效："+token);
            authInfo.put("token","");
            authInfo.put("tokenExpiredTime","");
            authInfo.put("user","");
        }

        return new ResultUtil(EnumCode.OK.getValue(),"刷新token成功！",authInfo);
    }

    //文件上传
    @RequestMapping("/upload")
    public Object uploadFile(MultipartFile[] multipartFiles){
        String fileName = "";
        File fileDir = new File(rootPath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
            if (multipartFiles != null && multipartFiles.length > 0) {
                for(int i = 0;i<multipartFiles.length;i++){
                    try {
                        //原文件名
                        fileName = multipartFiles[i].getOriginalFilename();
                        String suffixName = fileName.substring(fileName.lastIndexOf("."));
                        //用UUID命名
                        fileName = UUID.randomUUID().toString()+suffixName;
                        String storagePath = rootPath + fileName;
                        logger.info("上传的文件：" + multipartFiles[i].getName() + "," + multipartFiles[i].getContentType() + "," + multipartFiles[i].getOriginalFilename()
                                +"，保存的路径为：" + storagePath);
                        // 3种方法： 第1种
//                        Streams.copy(multipartFiles[i].getInputStream(), new FileOutputStream(storagePath), true);
                        // 第2种
//                        Path path = Paths.get(storagePath);
//                        Files.write(path,multipartFiles[i].getBytes());
                        // 第3种
                        multipartFiles[i].transferTo(new File(storagePath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //前端可以通过状态码，判断文件是否上传成功
        String imgURL = "http://localhost:8888/api/file/" + fileName;
        return new ResultUtil(EnumCode.OK.getValue(),"文件上传成功",imgURL);
    }

    /**
     *
     * @param fileName 文件名
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public Object downloadFile(@RequestParam String fileName, HttpServletResponse response){
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=utf-8");
            //Content-Disposition中指定的类型是文件的扩展名，并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，文件以filename的值命名，
            // 保存类型以Content中设置的为准。注意：在设置Content-Disposition头字段之前，一定要设置Content-Type头字段。
            //把文件名按UTF-8取出，并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"),"ISO8859-1"));
            //读取流
            File f = new File(rootPath+fileName);
            is = new FileInputStream(f);
            if (is == null) {
                logger.info("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"下载附件失败，请检查文件“" + fileName + "”是否存在");
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"下载附件失败,error:"+e.getMessage());
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //其实，这个返回什么都不重要
        return new ResultUtil(EnumCode.OK.getValue(),"下载成功");
    }


}
