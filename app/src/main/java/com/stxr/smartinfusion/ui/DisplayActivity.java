package com.stxr.smartinfusion.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stxr.smartinfusion.R;
import com.stxr.smartinfusion.base.BaseActivity;

import cn.iwgang.countdownview.CountdownView;

/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.ui
 *  文件名:   DisplayActivity
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 18:58
 *  描述：    
 */
public class DisplayActivity extends BaseActivity {
    private TextView tv_speed;
    private Button[] buttons= new Button[4];
    private CountdownView cd_used;
    private CountdownView cd_left;
    private double shuyeData[][] = new double[4][5];
    String[] shuyeData1;
    String[] shuyeData2;
    String[] shuyeData3;
    String[] shuyeData4;
    //退出计时
    private long firstTime;
    private int id_bottle;

    private int speed;
    private int valume;
    private long timeUsed;
    private long timeLeft;
    private int lastBottle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        initView();
        initData();
    }

    private void initData() {
        Bundle data = getIntent().getExtras();
        shuyeData1 = data.getStringArray("bottle1");
        shuyeData2 = data.getStringArray("bottle2");
        shuyeData3 = data.getStringArray("bottle3");
        shuyeData4 = data.getStringArray("bottle4");
        //遍历4个 转换
        for (int i = 0; i < 5; i++) {
            if (shuyeData1[i].equals("")) {
                shuyeData1[i] = "0";
            }
            if (shuyeData2[i].equals("")) {
                shuyeData2[i] = "0";
            }
            if (shuyeData3[i].equals("")) {
                shuyeData3[i] = "0";
            }
            if (shuyeData4[i].equals("")) {
                shuyeData4[i] = "0";
            }
            if (i == 3 || i == 4) {
                shuyeData[0][i] = Double.parseDouble(shuyeData1[i])*60;
                shuyeData[1][i] = Double.parseDouble(shuyeData2[i])*60;
                shuyeData[2][i] = Double.parseDouble(shuyeData3[i])*60;
                shuyeData[3][i] = Double.parseDouble(shuyeData4[i])*60;
            }else{
                shuyeData[0][i] = Double.parseDouble(shuyeData1[i]);
                shuyeData[1][i] = Double.parseDouble(shuyeData2[i]);
                shuyeData[2][i] = Double.parseDouble(shuyeData3[i]);
                shuyeData[3][i] = Double.parseDouble(shuyeData4[i]);
            }
        }
        //计算最后一瓶的id
        for(int i=0;i<4;i++) {
            if (shuyeData[i][0] == 0) {
                buttons[i].setVisibility(View.GONE);
            }
//            lastBottle = i;
        }
        for(int i=0;i<4;i++) {
            if (shuyeData[i][3] != 0) {
                timeLeft = (long) shuyeData[i][3];
                speed = (int) shuyeData[i][1];
                break;
            }
        }
//        timeLeft=50000;
        cd_left.start(timeLeft*1000);
        cd_left.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                if (shuyeData[id_bottle][4] != 0) { //如果还有第二速率
                    speed = (int) shuyeData[id_bottle][2];
                    timeLeft = (long) shuyeData[id_bottle][4];
                    cd_left.start(timeLeft*1000);
                    buttons[id_bottle].setBackgroundColor(getResources().getColor(R.color.btn_second));
                }else{  //否则下一瓶
                    buttons[id_bottle].setBackgroundColor(getResources().getColor(R.color.btn_stop));
                    while (shuyeData[id_bottle][0] == 0 || (shuyeData[id_bottle][3] <= 0 && shuyeData[id_bottle][4] <= 0)){
                        id_bottle++;
                        if (id_bottle >= 4) return;
                    }
                    speed = (int) shuyeData[id_bottle][1];
                    cd_left.start((long) (shuyeData[id_bottle][3]*1000));
                }
            }
        });
        cd_left.setOnCountdownIntervalListener(1000, new CountdownView.OnCountdownIntervalListener() {
            @Override
            public void onInterval(CountdownView cv, long remainTime) {
                tv_speed.setText(speed+"");
                if (cv.getSecond()%2 == 0) {
                    buttons[id_bottle].setVisibility(View.VISIBLE);
                } else {
                    buttons[id_bottle].setVisibility(View.INVISIBLE);
                }
                if (shuyeData[id_bottle][4] == 0) {  //如果没有速率2了，自动变蓝
                    buttons[id_bottle].setBackgroundColor(getResources().getColor(R.color.btn_second));
                }
                if (shuyeData[id_bottle][3] != 0) { //速率1
                    shuyeData[id_bottle][3] = ((int) (remainTime / 1000));
                }else{
                    shuyeData[id_bottle][4] = ((int) (remainTime / 1000));
                }
//                timeUsed += timeLeft * 1000 - remainTime;
                timeUsed = timeUsed+1;
                cd_used.updateShow(timeUsed*1000);
            }
        });

    }

    private void initView() {
        tv_speed = (TextView) findViewById(R.id.tv_speed);
        buttons[0]=(Button) findViewById(R.id.btn_one);
        buttons[1] = (Button) findViewById(R.id.btn_two);
        buttons[2] = (Button) findViewById(R.id.btn_three);
        buttons[3] = (Button) findViewById(R.id.btn_four);
        cd_left = (CountdownView) findViewById(R.id.cd_left);
        cd_used = (CountdownView) findViewById(R.id.cd_used);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
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

//    private void showWindow(boolean isShow, int id) {
//        switch (id) {
//            case 0:
//                if (isShow) {
//                    btn_one.setVisibility(View.VISIBLE);
//                }else{
//                    btn_one.setVisibility(View.INVISIBLE);
//                }
//                break;
//            case 1:
//                if (isShow) {
//                    btn_two.setVisibility(View.VISIBLE);
//                }else{
//                    btn_two.setVisibility(View.INVISIBLE);
//                }
//                break;
//            case 2:
//                if (isShow) {
//                    btn_three.setVisibility(View.VISIBLE);
//                }else{
//                    btn_three.setVisibility(View.INVISIBLE);
//                }
//                break;
//            case 3:
//                if (isShow) {
//                    btn_four.setVisibility(View.VISIBLE);
//                }else{
//                    btn_four.setVisibility(View.INVISIBLE);
//                }
//                break;
//        }
//    }
}
