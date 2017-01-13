package com.chencha.hourglassoftime.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.chencha.hourglassoftime.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Desc:  activity 基类
 * Author: chencha
 * Date: 16/12/14
 */

public abstract class BaseActivity2 extends AppCompatActivity implements SwipeBackActivityBase {
    // Context
    protected Context mContext;

    private Toolbar mToolbar;


    //手势返回
    private SwipeBackActivityHelper mBackActivityHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mBackActivityHelper = new SwipeBackActivityHelper(this);
        mBackActivityHelper.onActivityCreate();

    }

    /**
     * toobar
     *
     * @return
     */
    public void initToobar(String title) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_gray));
        mToolbar.setSubtitleTextColor(getResources().getColor(R.color.color868686));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.back);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (null == v && mBackActivityHelper != null) {
            return mBackActivityHelper.findViewById(id);
        }
        return v;
    }


    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }


    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
