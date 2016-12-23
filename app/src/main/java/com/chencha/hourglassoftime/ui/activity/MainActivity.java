package com.chencha.hourglassoftime.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.chencha.hourglassoftime.R;
import com.chencha.hourglassoftime.event.DayNight;
import com.chencha.hourglassoftime.ui.base.BaseActivity;
import com.chencha.hourglassoftime.ui.fab.FloatingActionButton;
import com.chencha.hourglassoftime.ui.fab.FloatingActionMenu;
import com.chencha.hourglassoftime.util.SwitchDayMightHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 夜间模式
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

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
    @BindView(R.id.switch_box)
    CheckBox mSwitchBox;
    @BindView(R.id.layout_note)
    RelativeLayout mLayoutNote;
//    @BindView(R.id.seeting_btn)
//    FloatingActionButton mSeetingBtn;

    private SwitchDayMightHelper mSwitchDayMightHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mSwitchDayMightHelper = new SwitchDayMightHelper(this);
        initTheme();
        mMenuLabelsRight.setClosedOnTouchOutside(true);
        mMenuLabelsRight.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMenuLabelsRight.toggle(true);
            }
        });
        mSwitchBox.setOnCheckedChangeListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenuLabelsRight.showMenuButton(true);
    }


    private void initTheme() {
        if (mSwitchDayMightHelper.isDay()) {
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int mViewId = compoundButton.getId();
        if (mViewId == R.id.switch_box) {
            if (mSwitchBox.isChecked()) {
                mSwitchBox.setBackgroundResource(R.mipmap.switch_on);
                changeTheme();
            } else {
                mSwitchBox.setBackgroundResource(R.mipmap.switch_off);
                changeTheme();
            }
        }
    }


    @OnClick({R.id.menu_labels_right, R.id.input_btn, R.id.speech_btn})
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
        }
    }

    /**
     * 改变主题
     */

    private void changeTheme() {
        showAnimation();
        toggleThemeSetting();
        refreshUI();
    }

    /**
     * 切换主题设置
     */
    private void toggleThemeSetting() {
        if (mSwitchDayMightHelper.isDay()) {
            mSwitchDayMightHelper.setMode(DayNight.NIGHT);
            setTheme(R.style.NightTheme);
        } else {
            mSwitchDayMightHelper.setMode(DayNight.DAY);
            setTheme(R.style.DayTheme);
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


    /**
     * 刷新UI界面
     */
    private void refreshUI() {
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.clockBackground, background, true);
        theme.resolveAttribute(R.attr.clockTextColor, textColor, true);

        mLayoutNote.setBackgroundResource(background.resourceId);

        refreshStatusBar();
    }


    /**
     * 刷新 StatusBar
     */
    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }

    /**
     * 展示一个切换动画
     */
    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }


    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }


}
