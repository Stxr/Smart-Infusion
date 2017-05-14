package com.stxr.smartinfusion.base;

import android.os.Bundle;
import android.view.MenuItem;

/*
 *  项目名：  MyApplication
 *  包名：    com.stxr.smartinfusion.base
 *  文件名:   BackActivity
 *  创建者:   Stxr
 *  创建时间:  2017/5/13 18:48
 *  描述：    有返回键
 */
public class BackActivity extends BaseActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示返回键
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //按了返回键就关掉界面
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
