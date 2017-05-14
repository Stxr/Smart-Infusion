package com.stxr.smartinfusion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stxr.smartinfusion.base.BaseActivity;
import com.stxr.smartinfusion.utils.L;

import static com.stxr.smartinfusion.statics.Const.ACTION;

public class TestActivity extends BaseActivity {
    private TextView tv = null;
    private EditText et = null;
    private Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //注册广播
        IntentFilter filter = new IntentFilter(ACTION);
        registerReceiver(broadcastReceiver, filter);
        tv = (TextView) findViewById(R.id.tv);
        et = (EditText) findViewById(R.id.etSend);
        btnSend = (Button) findViewById(R.id.btnSend);
        //发送数据按钮
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send(et.getText().toString().trim());
            }
        });
    }
    //接收广播
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            L.e("收到广播了");
            tv.setText(intent.getExtras().getString("data"));
        }
    };
    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
    }
}
