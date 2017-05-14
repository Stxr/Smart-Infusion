package com.stxr.smartinfusion.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.stxr.smartinfusion.utils.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.stxr.smartinfusion.statics.Const.ACTION;
import static com.stxr.smartinfusion.statics.Const.PORT;

/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.base
 *  文件名:   BaseActivity
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 14:57
 *  描述：
 */
public class BaseActivity extends AppCompatActivity {
    private Socket socket;
    private ServerSocket mServerSocket = null;
    private boolean running = false;
    private AcceptThread mAcceptThread;
    private ReceiveThread mReceiveThread;
    private Handler mHandler = null;
    private String receiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData1();
    }

    private void initData1() {
        mHandler = new MyHandler();
        mAcceptThread = new AcceptThread();
        mAcceptThread.start();//开始监听
        running = true;
    }

    //定义监听客户端连接的线程
    private class AcceptThread extends Thread {
        @Override
        public void run() {
//            while (running) {
            try {
                mServerSocket = new ServerSocket(PORT);//建立一个ServerSocket服务器端
                socket = mServerSocket.accept();//阻塞直到有socket客户端连接
//                System.out.println("连接成功");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = mHandler.obtainMessage();
                msg.what = 0;
                msg.obj = socket.getInetAddress().getHostAddress();//获取客户端IP地址
                mHandler.sendMessage(msg);//返回连接成功的信息
                //开启mReceiveThread线程接收数据
                mReceiveThread = new ReceiveThread(socket);
                mReceiveThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            }
        }
    }

    private class ReceiveThread extends Thread {
        private InputStream is = null;
        private String read;

        //建立构造函数来获取socket对象的输入流
        public ReceiveThread(Socket sk) {
            try {
                is = sk.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String buff = null;
            while (running) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                byte[] byteBuffer = new byte[1024];
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    int temp = 0;
                    while ((temp = is.read(byteBuffer)) != -1) {
                        buff = new String(byteBuffer, 0, temp);
                        Message msg = mHandler.obtainMessage();
                        msg.what = 1;
                        msg.obj = buff;
                        mHandler.sendMessage(msg);
                        L.e(buff);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    //读服务器端发来的数据，阻塞直到收到结束符\n或\r
                    read = br.readLine();
                    L.e(read);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    running = false;//防止服务器端关闭导致客户端读到空指针而导致程序崩溃
                    Message msg2 = mHandler.obtainMessage();
                    msg2.what = 2;
                    mHandler.sendMessage(msg2);//发送信息通知用户客户端已关闭
                    e.printStackTrace();
                    break;
                }
                //用Handler把读取到的信息发到主线程
//                Message msg = mHandler.obtainMessage();
//                msg.what = 1;
//                msg.obj = buff;
//                mHandler.sendMessage(msg);

            }
        }
    }

    public void send(String content) {
        OutputStream os = null;
        try {
            os = socket.getOutputStream();//获得socket的输出流
//            String msg = et.getText().toString().trim();
//                    System.out.println(msg);
            os.write(content.getBytes("utf-8"));//输出EditText的内容
//            et.setText("");//发送后输入框清0
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            displayToast("未连接不能输出");//防止服务器端关闭导致客户端读到空指针而导致程序崩溃
        }
    }

    protected void displayToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public String getReceiveData() {
        return receiveData;
    }

    public void setReceiveData(String receiveData) {
        this.receiveData = receiveData;
    }
    class MyHandler extends Handler {//在主线程处理Handler传回来的message

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    receiveData = (String) msg.obj;
                    L.e("接收到了" + receiveData);
                    Intent intent = new Intent(ACTION);
                    intent.putExtra("data", receiveData);
                    sendBroadcast(intent);
                    break;
                case 0:
                    displayToast("客户端" + msg.obj + "已连接");
                    displayToast("连接成功");
                    break;
                case 2:
                    displayToast("客户端已断开");
                    //清空TextView
//                    tv.setText(null);//
//                    IPtv.setText(null);
                    try {
                        socket.close();
                        mServerSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
