package com.chencha.hourglassoftime.ui.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.chencha.hourglassoftime.R;
import com.chencha.hourglassoftime.util.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Desc:  activity 基类
 * Author: chencha
 * Date: 16/12/14
 */

public abstract class BaseActivity extends AppCompatActivity {
    //布局文件
    protected abstract int getLayoutId();

    protected abstract void initView();

    // Context
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isTranslucentStatusBar()) {
                Window window = getWindow();
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

        StatusBarUtil statusBarUtil = new StatusBarUtil();
        statusBarUtil.StatusBarLightMode(this);
        statusBarUtil.setStatusBarColor(this, R.color.colorAccent);

        setContentView(getLayoutId());

        mContext = this;
        ButterKnife.bind(this);
        initView();
    }

    //判断是否 statusBar 状态栏为透明
    protected boolean isTranslucentStatusBar() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
