package com.stxr.smartinfusion.ui;/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.ui
 *  文件名:   ShueyeActivity
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 18:55
 *  描述：    
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.stxr.smartinfusion.R;
import com.stxr.smartinfusion.base.BackActivity;

import java.util.Objects;


public class ShueyeActivity extends BackActivity {
    private EditText et_volume1;
    private EditText et_volume2;
    private EditText et_volume3;
    private EditText et_volume4;

    private EditText et_speed11;
    private EditText et_speed12;
    private EditText et_speed21;
    private EditText et_speed22;
    private EditText et_speed31;
    private EditText et_speed32;
    private EditText et_speed41;
    private EditText et_speed42;

    private EditText et_time11;
    private EditText et_time12;
    private EditText et_time21;
    private EditText et_time22;
    private EditText et_time31;
    private EditText et_time32;
    private EditText et_time41;
    private EditText et_time42;

    String[][] shuyeData = new String[4][5];
    private boolean flagListener = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuye);
        initView();
        initData();
    }

    private void initData() {
        et_volume1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    et_speed11.setText(null);
                    et_speed12.setText(null);
                    et_time11.setText(null);
                    et_time12.setText(null);
                    flagListener = true;
                }
            }
        });
        et_volume2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    et_speed21.setText("");
                    et_speed22.setText("");
                    et_time21.setText("");
                    et_time22.setText("");
                    flagListener = true;
                }
            }
        });
        et_volume3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    et_speed31.setText("");
                    et_speed32.setText("");
                    et_time31.setText("");
                    et_time32.setText("");
                    flagListener = true;
                }

            }
        });
        et_volume4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    et_speed41.setText("");
                    et_speed42.setText("");
                    et_time41.setText("");
                    et_time42.setText("");
                    flagListener = true;
                }
            }
        });

        et_speed11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume1.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed11.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume1.getText().toString().trim());
                        double v = Double.parseDouble(et_speed11.getText().toString().trim());
                        et_time11.setText(c/v+"");
                    }else{
                        et_time11.setText(null);
                        et_time12.setText(null);
                        et_speed12.setText(null);
                    }
                    flagListener = true;
                }
            }
        });
        et_time11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume1.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed11.getText().toString().trim(), "")&&
                            !Objects.equals(et_time11.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume1.getText().toString().trim());
                        double v = Double.parseDouble(et_speed11.getText().toString().trim());
                        double t = Double.parseDouble(et_time11.getText().toString().trim());
                        if (v * t > c) {
                            et_time11.setText(c / v + "");
                            et_speed12.setText(null);
                            et_time12.setText(null);
                        }else{
                            et_speed12.setText(5+"");
                            et_time12.setText((c-v*t)/5+"");
                        }
                    }else if(Objects.equals(et_time11.getText().toString().trim(), "")){
                        et_speed12.setText(null);
                        et_time12.setText(null);
                    }
                    flagListener = true;
                }
            }
        });
        et_speed12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume1.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed11.getText().toString().trim(), "")&&
                            !Objects.equals(et_time11.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed12.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume1.getText().toString().trim());
                        double v = Double.parseDouble(et_speed11.getText().toString().trim());
                        double t = Double.parseDouble(et_time11.getText().toString().trim());
                        double v2 = Double.parseDouble(et_speed12.getText().toString().trim());
                        et_time12.setText((c-v*t)/v2+"");
                    }else if(Objects.equals(et_speed12.getText().toString().trim(), "")){
                        et_time12.setText(null);
                    }
                    flagListener = true;
                }
            }
        });

        et_speed21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume2.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed21.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume2.getText().toString().trim());
                        double v = Double.parseDouble(et_speed21.getText().toString().trim());
                        et_time21.setText(c/v+"");
                    }else{
                        et_time21.setText(null);
                        et_time21.setText(null);
                        et_time22.setText(null);
                        et_speed22.setText(null);
                    }
                    flagListener = true;
                }
            }
        });
        et_time21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume2.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed21.getText().toString().trim(), "")&&
                            !Objects.equals(et_time21.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume2.getText().toString().trim());
                        double v = Double.parseDouble(et_speed21.getText().toString().trim());
                        double t = Double.parseDouble(et_time21.getText().toString().trim());
                        if (v * t > c) {
                            et_time21.setText(c / v + "");
                            et_speed22.setText(null);
                            et_time22.setText(null);
                        }else{
                            et_speed22.setText(5+"");
                            et_time22.setText((c-v*t)/5+"");
                        }
                    } else if (Objects.equals(et_time21.getText().toString().trim(), "")) {
                        et_speed22.setText(null);
                        et_time22.setText(null);
                    }
                    flagListener = true;
                }
            }
        });
        et_speed22.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume2.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed21.getText().toString().trim(), "")&&
                            !Objects.equals(et_time21.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed22.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume2.getText().toString().trim());
                        double v = Double.parseDouble(et_speed21.getText().toString().trim());
                        double t = Double.parseDouble(et_time21.getText().toString().trim());
                        double v2 = Double.parseDouble(et_speed22.getText().toString().trim());
                        et_time22.setText((c-v*t)/v2+"");
                    }else if(Objects.equals(et_speed22.getText().toString().trim(), "")){
                        et_time22.setText(null);
                    }
                    flagListener = true;
                }
            }
        });

        et_speed31.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume3.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed31.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume3.getText().toString().trim());
                        double v = Double.parseDouble(et_speed31.getText().toString().trim());
                        et_time31.setText(c/v+"");
                    }else{
                        et_time31.setText(null);
                        et_time31.setText(null);
                        et_time32.setText(null);
                        et_speed32.setText(null);
                    }
                    flagListener = true;
                }
            }
        });
        et_time31.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume3.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed31.getText().toString().trim(), "")&&
                            !Objects.equals(et_time31.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume3.getText().toString().trim());
                        double v = Double.parseDouble(et_speed31.getText().toString().trim());
                        double t = Double.parseDouble(et_time31.getText().toString().trim());
                        if (v * t > c) {
                            et_time31.setText(c / v + "");
                            et_speed32.setText(null);
                            et_time32.setText(null);
                        }else{
                            et_speed32.setText(5+"");
                            et_time32.setText((c-v*t)/5+"");
                        }
                    } else if (Objects.equals(et_time31.getText().toString().trim(), "")) {
                        et_speed32.setText(null);
                        et_time32.setText(null);
                    }
                    flagListener = true;
                }
            }
        });
        et_speed32.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume3.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed31.getText().toString().trim(), "")&&
                            !Objects.equals(et_time31.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed32.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume3.getText().toString().trim());
                        double v = Double.parseDouble(et_speed31.getText().toString().trim());
                        double t = Double.parseDouble(et_time31.getText().toString().trim());
                        double v2 = Double.parseDouble(et_speed32.getText().toString().trim());
                        et_time32.setText((c-v*t)/v2+"");
                    }else if(Objects.equals(et_speed32.getText().toString().trim(), "")){
                        et_time32.setText(null);
                    }
                    flagListener = true;
                }
            }
        });

        et_speed41.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume4.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed41.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume4.getText().toString().trim());
                        double v = Double.parseDouble(et_speed41.getText().toString().trim());
                        et_time41.setText(c/v+"");
                    }else{
                        et_time41.setText(null);
                        et_time41.setText(null);
                        et_time42.setText(null);
                        et_speed42.setText(null);
                    }
                    flagListener = true;
                }
            }
        });
        et_time41.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume4.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed41.getText().toString().trim(), "")&&
                            !Objects.equals(et_time41.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume4.getText().toString().trim());
                        double v = Double.parseDouble(et_speed41.getText().toString().trim());
                        double t = Double.parseDouble(et_time41.getText().toString().trim());
                        if (v * t > c) {
                            et_time41.setText(c / v + "");
                            et_speed42.setText("");
                            et_time42.setText("");
                        }else{
                            et_speed42.setText(5+"");
                            et_time42.setText((c-v*t)/5+"");
                        }
                    } else if (Objects.equals(et_time41.getText().toString().trim(), "")) {
                        et_speed42.setText("");
                        et_time42.setText("");
                    }
                    flagListener = true;
                }
            }
        });
        et_speed42.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flagListener) {
                    flagListener = false;
                    if (!Objects.equals(et_volume4.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed41.getText().toString().trim(), "")&&
                            !Objects.equals(et_time41.getText().toString().trim(), "") &&
                            !Objects.equals(et_speed42.getText().toString().trim(), "")) {
                        double c = Double.parseDouble(et_volume4.getText().toString().trim());
                        double v = Double.parseDouble(et_speed41.getText().toString().trim());
                        double t = Double.parseDouble(et_time41.getText().toString().trim());
                        double v2 = Double.parseDouble(et_speed42.getText().toString().trim());
                        et_time42.setText((c-v*t)/v2+"");
                    }else if(Objects.equals(et_speed42.getText().toString().trim(), "")){
                        et_time42.setText(null);
                    }
                    flagListener = true;
                }
            }
        });
    }

    private void initView() {
        et_volume1 = (EditText) findViewById(R.id.et_volume1);
        et_volume2 = (EditText) findViewById(R.id.et_volume2);
        et_volume3 = (EditText) findViewById(R.id.et_volume3);
        et_volume4 = (EditText) findViewById(R.id.et_volume4);

        et_speed11 = (EditText) findViewById(R.id.et_speed11);
        et_speed12 = (EditText) findViewById(R.id.et_speed12);
        et_speed21 = (EditText) findViewById(R.id.et_speed21);
        et_speed22 = (EditText) findViewById(R.id.et_speed22);
        et_speed31 = (EditText) findViewById(R.id.et_speed31);
        et_speed32 = (EditText) findViewById(R.id.et_speed32);
        et_speed41 = (EditText) findViewById(R.id.et_speed41);
        et_speed42 = (EditText) findViewById(R.id.et_speed42);

        et_time11 = (EditText) findViewById(R.id.et_time11);
        et_time12 = (EditText) findViewById(R.id.et_time12);
        et_time21 = (EditText) findViewById(R.id.et_time21);
        et_time22 = (EditText) findViewById(R.id.et_time22);
        et_time31 = (EditText) findViewById(R.id.et_time31);
        et_time32 = (EditText) findViewById(R.id.et_time32);
        et_time41 = (EditText) findViewById(R.id.et_time41);
        et_time42 = (EditText) findViewById(R.id.et_time42);
    }

    private void getData() {
        shuyeData[0][0] = et_volume1.getText().toString().trim();
        shuyeData[1][0] = et_volume2.getText().toString().trim();
        shuyeData[2][0] = et_volume3.getText().toString().trim();
        shuyeData[3][0] = et_volume4.getText().toString().trim();

        shuyeData[0][1] = et_speed11.getText().toString().trim();
        shuyeData[0][2] = et_speed12.getText().toString().trim();
        shuyeData[1][1] = et_speed21.getText().toString().trim();
        shuyeData[1][2] = et_speed22.getText().toString().trim();
        shuyeData[2][1] = et_speed31.getText().toString().trim();
        shuyeData[2][2] = et_speed32.getText().toString().trim();
        shuyeData[3][1] = et_speed41.getText().toString().trim();
        shuyeData[3][2] = et_speed42.getText().toString().trim();

        shuyeData[0][3] = et_time11.getText().toString().trim();
        shuyeData[0][4] = et_time12.getText().toString().trim();
        shuyeData[1][3] = et_time21.getText().toString().trim();
        shuyeData[1][4] = et_time22.getText().toString().trim();
        shuyeData[2][3] = et_time31.getText().toString().trim();
        shuyeData[2][4] = et_time32.getText().toString().trim();
        shuyeData[3][3] = et_time41.getText().toString().trim();
        shuyeData[3][4] = et_time42.getText().toString().trim();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_confirm:
                Intent intent = new Intent(this, DisplayActivity.class);
                Bundle bundle = new Bundle();
                getData();
                bundle.putStringArray("bottle1",shuyeData[0]);
                bundle.putStringArray("bottle2",shuyeData[1]);
                bundle.putStringArray("bottle3",shuyeData[2]);
                bundle.putStringArray("bottle4",shuyeData[3]);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
}
