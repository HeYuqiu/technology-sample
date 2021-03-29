package com.hyq.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yuqiu.He
 * @date 2021/3/28
 */
public class SocketBIO {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9090);
        while (true) {
            Socket client = server.accept(); // 接受客户端-阻塞1
            new Thread(()->{
                try {
                    BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String str = bufferedReader.readLine(); // 读取IO数据-阻塞2
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
