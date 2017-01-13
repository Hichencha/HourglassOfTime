package com.chencha.hourglassoftime;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;

/**
 * Desc:
 * Author: chencha
 * Date: 16/12/14
 */

public class NoteApplication extends Application {
    private static NoteApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始
//        GreenDaoManager.getInstance();
        FreelineCore.init(this);

    }
}
