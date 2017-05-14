package com.stxr.smartinfusion.ui;/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.ui
 *  文件名:   WifiActivity
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 18:38
 *  描述：    
 */

import android.os.Bundle;
import android.widget.TextView;

import com.stxr.smartinfusion.R;
import com.stxr.smartinfusion.base.BackActivity;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import static com.stxr.smartinfusion.statics.Const.PORT;

public class WifiActivity extends BackActivity {
    private TextView tv_ip;
    private TextView tv_port;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        initView();
        initData();
    }

    private void initData() {
        tv_ip.setText("服务器的ip地址："+getIp());
        tv_port.setText("端口号："+PORT);
    }

    private void initView() {
        tv_ip = (TextView) findViewById(R.id.tv_localIp);
        tv_port = (TextView) findViewById(R.id.tv_localPort);
    }
    //得到本机的ip地址
    public static String getIp(){
        try{
            for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
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
}
