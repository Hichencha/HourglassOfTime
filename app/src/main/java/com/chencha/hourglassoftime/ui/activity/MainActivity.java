package com.chencha.hourglassoftime.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.chencha.hourglassoftime.R;
import com.chencha.hourglassoftime.ui.base.BaseActivity;
import com.chencha.hourglassoftime.ui.fab.FloatingActionButton;
import com.chencha.hourglassoftime.ui.fab.FloatingActionMenu;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.list_note)
    RecyclerView mListNote;
    @BindView(R.id.menu_labels_right)
    FloatingActionMenu mMenuLabelsRight;
    @BindView(R.id.note_layout)
    CoordinatorLayout mNoteLayout;
    @BindView(R.id.input_btn)
    FloatingActionButton mInputBtn;
    @BindView(R.id.speech_btn)
    FloatingActionButton mSpeechBtn;
    @BindView(R.id.seeting_btn)
    FloatingActionButton mSeetingBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mMenuLabelsRight.setClosedOnTouchOutside(true);
        mMenuLabelsRight.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMenuLabelsRight.toggle(true);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenuLabelsRight.showMenuButton(true);
    }

    @OnClick({R.id.menu_labels_right, R.id.input_btn, R.id.speech_btn, R.id.seeting_btn})
    public void onClick(View view) {
        Intent intent = null;
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.input_btn:
                startActivity(new Intent(mContext, AddNoteActivity.class));
                mMenuLabelsRight.toggle(true);
                mMenuLabelsRight.hideMenuButton(true);
                break;
            case R.id.speech_btn:
                break;
            case R.id.seeting_btn:
                startActivity(new Intent(mContext, NoteSettingActivity.class));
                mMenuLabelsRight.toggle(true);
                mMenuLabelsRight.hideMenuButton(true);
                break;
        }
    }


    //返回键
    private long firstTime = 0;//第一次返回按钮计时

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Snackbar.make(mNoteLayout, "离开混沌世界线", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    firstTime = secondTime;
                } else {
                    finish();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


//    private void showSetting() {
//        final Dialog dialog = new Dialog(mContext, R.style.AnimBottom);
//        dialog.setContentView(R.layout.dialog_setting);
//        //dialog 宽度设置全屏
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.CENTER_HORIZONTAL);
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        window.setAttributes(lp);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//
//    }

}
