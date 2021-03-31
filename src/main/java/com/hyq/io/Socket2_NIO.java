package com.hyq.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * 模拟NIO（同步非阻塞）
 * @author Yuqiu.He
 * @date 2021/3/27
 */
public class Socket2_NIO {
    public static void main(String[] args) throws Exception {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false); // 内核级别非阻塞，只让接受客户端不阻塞

        // 单线程：即处理连接客户端，也处理遍历每个连接
        while (true) {
            // 接受客户端连接
            // accept 调用内核了：没有客户端时，阻塞模式下回一直卡着，非阻塞下回直接返回-1；
            // 来客户端连接时，accept返回的是这个客户端的fd（文件描述符）
            SocketChannel client = ss.accept();
            if (client == null) {

            } else {
                client.configureBlocking(false); // 设置对client的连接也是非阻塞的（内核级别）
                int port = client.socket().getPort();
                System.out.println("client..port:"+port);
                clients.add(client);
            }

            ByteBuffer buffer =ByteBuffer.allocateDirect(4096); // buffer缓冲区
            // 遍历所有客户端，处理IO数据
            for (SocketChannel c : clients) {
                // 获取socket连接中的数据
                // 关键点：不会阻塞，BIO下这里会阻塞，所以没法用一个线程遍历所有socket连接
                int num = c.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    String b = new String(buffer.array());
                    System.out.println(c.socket().getPort()+" : "+b);
                    buffer.clear();
                }
            }

            // 思考：这种模式有什么弊端？
            // 轮询客户端里，如果1万个客户端只有一个发来数据，需要白遍历9999次？--> 事件通知
            // 并且99%情况都是没有数据来的，但是他一直在空转浪费cpu

            // 由此引出多路复用之select：
            // 调用内核的select方法，传入1万个FD，内核会阻塞的监听1万个客户端是否有数据，当有数据时内核会通知具体是哪个客户端

            // 弊端：select有个弊端就是每次调select获取哪个连接有数据时都要传1万个FD

            // 由此引出多路复用之epoll（poll和select差不多）
            // ....

            // select、poll、epoll都是内核的实现，java里是用Selector对内核api进行的封装来实现多路复用；
            // netty的多路复用也是靠selector实现的；
        }
    }
}
