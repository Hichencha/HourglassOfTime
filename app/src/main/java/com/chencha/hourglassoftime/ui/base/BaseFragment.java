package com.chencha.hourglassoftime.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Desc:   fragment 基类
 * Author: chencha
 * Date: 16/12/14
 */

public abstract class BaseFragment extends Fragment {
    protected View mView;

    protected abstract void initView();

    //获取 fragment
    protected abstract int getLayoutId();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false);
        }
        initView();
        return mView;
    }


}
