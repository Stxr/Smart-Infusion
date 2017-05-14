package com.stxr.smartinfusion.udp;

import com.stxr.smartinfusion.utils.L;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.udp
 *  文件名:   Server
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 12:12
 *  描述：    
 */
public class Server implements Runnable{
    private int localport;

    public Server(int localport) {
        this.localport = localport;
    }
    /**
     *
     * @return 返回本机的ip地址
     */
    public static String getIp(){
        try{
            for(Enumeration<NetworkInterface> en= NetworkInterface.getNetworkInterfaces();en.hasMoreElements();){
                NetworkInterface intf = en.nextElement();
                for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();){
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if(!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address){
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(getIp());
                L.e("\nServer: Start connecting\n");
                DatagramSocket socket = new DatagramSocket(localport);
                L.e("Server: Receiving\n");
                try {
                    while (true) {
                        byte[] buf = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);
                        L.e("Server: Message received: ‘"
                                + new String(packet.getData()) + "’\n");
                        L.e("Server: Succeed!\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                L.e("Server: Error!\n");
            }
    }
}
