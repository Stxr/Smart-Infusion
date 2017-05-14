package com.stxr.smartinfusion.tcp;

import com.stxr.smartinfusion.utils.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.tcp
 *  文件名:   TcpServer
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 13:00
 *  描述：    
 */
public class TcpServer implements Runnable {
    @Override
    public void run() {
        try {
            Boolean endFlag = false;
            ServerSocket ss = new ServerSocket(8080);
            while (!endFlag) {
                // 等待客户端连接
                Socket s = ss.accept();
                OutputStream os=null;
//                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                InputStream inputStream=s.getInputStream();
                os = s.getOutputStream();
                os.write("hello".getBytes("utf-8"));
                os.flush();
//                s.sendUrgentData();
                //注意第二个参数据为true将会自动flush，否则需要需要手动操作output.flush()
                byte []byteBuffer=new byte[1024];
                PrintWriter output = new PrintWriter(s.getOutputStream(),true);
                int temp = 0;
                while((temp=inputStream.read(byteBuffer))!=-1)
                    L.e(new String(byteBuffer,0,temp));
//                String message = input.readLine();
//                L.e("message from Client:"+message);
                output.println("message received!");
                //output.flush();
//                if("shutDown".equals(message)){
//                    endFlag=true;
//                }
                s.close();
            }
            ss.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
