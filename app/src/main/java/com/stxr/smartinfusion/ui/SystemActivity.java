package com.stxr.smartinfusion.ui;/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.ui
 *  文件名:   SystemActivity
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 17:23
 *  描述：    系统界面
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stxr.smartinfusion.R;
import com.stxr.smartinfusion.base.BackActivity;

public class SystemActivity extends BackActivity implements View.OnClickListener {
    private Button btn_wifi;
    private Button btn_update;
    private Button btn_about;
    private Button btn_motor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        initView();
        initData();
    }

    private void initData() {
        btn_about.setOnClickListener(this);
        btn_wifi.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_motor.setOnClickListener(this);
    }


    private void initView() {
        btn_about = (Button) findViewById(R.id.btn_aboutUs);
        btn_motor = (Button) findViewById(R.id.btn_motorReset);
        btn_update = (Button) findViewById(R.id.btn_systemUpdate);
        btn_wifi = (Button) findViewById(R.id.btn_wifiConnect);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_aboutUs:

                break;
            case R.id.btn_wifiConnect:
                startActivity(new Intent(SystemActivity.this,WifiActivity.class));
                break;
            case R.id.btn_motorReset:

                break;
            case R.id.btn_systemUpdate:

                break;
        }
    }
}
