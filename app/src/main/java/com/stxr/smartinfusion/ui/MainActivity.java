package com.stxr.smartinfusion.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stxr.smartinfusion.R;
import com.stxr.smartinfusion.base.BaseActivity;

/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.ui
 *  文件名:   MainActivity
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 17:09
 *  描述：    
 */
public class MainActivity extends BaseActivity {
    private Button btn_sys;
    private Button btn_shuye;
    private long firstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        btn_sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SystemActivity.class));
            }
        });
        btn_shuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShueyeActivity.class));
            }
        });
    }

    private void initView() {
        btn_shuye = (Button) findViewById(R.id.shuye_setting);
        btn_sys = (Button) findViewById(R.id.system_setting);
    }
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {   //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出输液", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {                                                    //两次按键小于2秒时，退出应用
//                    System.exit(0);
                    finish();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
