package com.qxf;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.nio.charset.Charset;

/**
 * @ClassName ZipTest
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/11/24 22:53
 **/
public class ZipTest {
    private static final String rootPath="E:\\项目需求文档\\粤通物流系统设计说明书";

    public static void main(String[] args) {
        int len = "".split(",").length;
        System.out.println(len);
//        File file = new File(rootPath + File.separator + "粤通物流系统设计说明书.zip");
//        for (int i=0;i<10;i++){
//            for (int j=0;j<10;j++){
//                for (int k=0;k<10;k++){
//                    for (int m=0;m<10;m++){
//                        try {
//                            unzip(file,rootPath,""+i+j+k+m);
//                            System.out.println("密码正确："+i+j+k+m);
//                            return;
//                        } catch (ZipException e) {
//                            System.out.println("密码错误");
//                            e.getMessage();
//                        }
//                    }
//                }
//            }
//        }
    }

    public static void unzip(File file,String toFolder,String password) throws ZipException {
        ZipFile zipFile = new ZipFile(file);
        zipFile.setCharset(Charset.forName("GBK"));

        if (!zipFile.isValidZipFile()) {
            throw new ZipException("压缩文件不合法,可能被损坏.");
        }
        File destDir = new File(toFolder);
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdirs();
        }
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(password.toCharArray());
        }
        zipFile.extractAll(toFolder);
    }
}
