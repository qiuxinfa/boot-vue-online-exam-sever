package com.qxf.task;

import cn.hutool.core.util.StrUtil;
import com.qxf.websocket.WebSocketServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SendMsgToClient
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/6/1 20:30
 **/
@Component
//@EnableScheduling
public class SendMsgToClient {

    //从第10秒开始，每隔5秒发送一次
    @Scheduled(cron="10/5 * * * * ?")
    public void sendMsg() {
        try {
            ConcurrentHashMap<String, WebSocketServer> webSocketMap = WebSocketServer.getWebSocketMap();
            ConcurrentHashMap.KeySetView<String, WebSocketServer> userIds = webSocketMap.keySet();
            if (userIds.size() <= 0){
                System.out.println("当前没有用户连接，不发送消息");
                return;
            }

            String toUserId = null;
            int count = new Random(System.currentTimeMillis()).nextInt(userIds.size());
            //取一个发送消息
            for (int i = 0; i < userIds.size();i++){
                Iterator<String> iterator = userIds.iterator();
                if (iterator.hasNext()){
                    if (i == count){
                        toUserId = iterator.next();
                    }else {
                        iterator.next();
                    }

                }
            }
            if (StrUtil.isNotBlank(toUserId)){
                WebSocketServer.sendInfo("这是服务端主动推送的消息："+new Date(),"67eb71f1091911eab9205c93a27933da");
            }else {
                System.out.println("当前没有用户连接，不发送消息");
            }

        }catch (IOException e){
            System.out.println("定时推送消息失败...");
        }

    }

}