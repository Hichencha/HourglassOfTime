package com.chencha.hourglassoftime.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chencha.hourglassoftime.R;
import com.chencha.hourglassoftime.ui.base.BaseActivity2;

import butterknife.BindView;

/**
 * Desc:  设置
 * Author: chencha
 * Date: 16/12/21
 */

public class NoteSettingActivity extends BaseActivity2 {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.list_item)
    RecyclerView mListItem;


    private static String[] settingItems = {"界面展示", "夜间模式", "关于我"};


    @Override
    protected int getLayoutId() {
        return R.layout.note_setting;
    }

    @Override
    protected void initView() {
        initToobar("设置");

        mToolbar.setBackgroundColor(Color.parseColor("#FFB805"));
    }


}
