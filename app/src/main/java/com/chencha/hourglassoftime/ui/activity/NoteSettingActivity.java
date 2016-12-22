package com.chencha.hourglassoftime.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

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
    @BindView(R.id.setting_frm)
    FrameLayout mSettingFrm;

//    private SettingFragment mSettingFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.note_setting;
    }

    @Override
    protected void initView() {
        initToobar("设置");

        mToolbar.setBackgroundColor(Color.parseColor("#FFB805"));

//        mSettingFragment = SettingFragment.newInstance();
//        getFragmentManager().beginTransaction().replace(R.id.setting_frm, mSettingFragment).commit();

    }

}
