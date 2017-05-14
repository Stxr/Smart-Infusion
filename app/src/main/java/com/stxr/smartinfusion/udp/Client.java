package com.stxr.smartinfusion.udp;

import com.stxr.smartinfusion.utils.L;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static android.R.attr.start;
import static android.R.attr.thickness;
import static android.R.id.input;

/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.udp
 *  文件名:   Client
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 9:45
 *  描述：    
 */
public class Client implements Runnable {
    private String serverIp;
    private int serverPort;
    private String content;
    public Client(String serverIp, int serverPort, String content) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.content = content;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            InetAddress serverAddr = InetAddress.getByName(serverIp);
            L.e("Client: Start connecting\n");
            DatagramSocket socket = new DatagramSocket();
            byte[] buf;
            if (!content.isEmpty()) {
                buf = content.getBytes();
            } else {
                buf = ("Default message").getBytes();
            }
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, serverPort);
            L.e("Client: Sending ‘" + new String(buf) + "’\n");
            socket.send(packet);
            L.e("Client: Message sent\n");
            L.e("Client: Succeed!\n");
        } catch (Exception e) {
            L.e("Client: Error!\n");
        }
    }
}
