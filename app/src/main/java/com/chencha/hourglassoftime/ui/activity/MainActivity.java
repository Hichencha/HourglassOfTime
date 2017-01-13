package com.chencha.hourglassoftime.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.chencha.hourglassoftime.R;
import com.chencha.hourglassoftime.ui.activity.adapter.SettingAdapter;
import com.chencha.hourglassoftime.ui.base.BaseActivity;
import com.chencha.hourglassoftime.ui.fab.FloatingActionButton;
import com.chencha.hourglassoftime.ui.fab.FloatingActionMenu;
import com.chencha.hourglassoftime.util.SwitchDayMightHelper;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 夜间模式
 */
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
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.action_grid)
    RadioButton mActionGrid;
    @BindView(R.id.action_row)
    RadioButton mActionRow;
    @BindView(R.id.action_setting)
    ImageView mActionSetting;

//    @BindView(R.id.switch_box)
//    CheckBox mSwitchBox;

    private SwitchDayMightHelper mSwitchDayMightHelper;

    //设置
    private SettingAdapter mSettingAdapter;
    private static final String[] mSettingInfos = {"夜间模式", "废纸篓", "设置"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        mToolbar.setBackgroundColor(Color.parseColor("#FFFFFF"));

//        mSwitchDayMightHelper = new SwitchDayMightHelper(this);
//        initTheme();
        mMenuLabelsRight.setClosedOnTouchOutside(true);
        mMenuLabelsRight.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMenuLabelsRight.toggle(true);
            }
        });
//        mSwitchBox.setOnCheckedChangeListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mMenuLabelsRight.showMenuButton(true);
    }


//    private void initTheme() {
//        if (mSwitchDayMightHelper.isDay()) {
//            setTheme(R.style.DayTheme);
//        } else {
//            setTheme(R.style.NightTheme);
//        }
//    }

//    @Override
//    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//        int mViewId = compoundButton.getId();
//        if (mViewId == R.id.switch_box) {
//            if (mSwitchBox.isChecked()) {
//                mSwitchBox.setBackgroundResource(R.mipmap.switch_on);
//                changeTheme();
//            } else {
//                mSwitchBox.setBackgroundResource(R.mipmap.switch_off);
//                changeTheme();
//            }
//        }
//    }


    @OnClick({R.id.menu_labels_right, R.id.input_btn, R.id.speech_btn, R.id.action_grid, R.id.action_row, R.id.action_setting})
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
            case R.id.action_grid:
                break;
            case R.id.action_row:
                break;
            case R.id.action_setting:
                showSetting();
                break;


        }
    }

//    /**
//     * 改变主题
//     */
//
//    private void changeTheme() {
//        showAnimation();
//        toggleThemeSetting();
//        refreshUI();
//    }

//    /**
//     * 切换主题设置
//     */
//    private void toggleThemeSetting() {
//        if (mSwitchDayMightHelper.isDay()) {
//            mSwitchDayMightHelper.setMode(DayNight.NIGHT);
//            setTheme(R.style.NightTheme);
//        } else {
//            mSwitchDayMightHelper.setMode(DayNight.DAY);
//            setTheme(R.style.DayTheme);
//        }
//    }


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


    private void showSetting() {
        final Dialog dialog = new Dialog(mContext, R.style.AnimBottom);
        dialog.setContentView(R.layout.setting_layout);
        //dialog 宽度设置全屏
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.RIGHT | Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);


        RecyclerView mSettingList = (RecyclerView) dialog.findViewById(R.id.setting_item);
        mSettingList.setLayoutManager(new LinearLayoutManager(mContext));
        mSettingAdapter = new SettingAdapter(mContext, mSettingInfos);
        mSettingList.setAdapter(mSettingAdapter);
        mSettingAdapter.notifyDataSetChanged();

    }


//    /**
//     * 刷新UI界面
//     */
//    private void refreshUI() {
//        TypedValue background = new TypedValue();//背景色
//        TypedValue textColor = new TypedValue();//字体颜色
//        Resources.Theme theme = getTheme();
//        theme.resolveAttribute(R.attr.clockBackground, background, true);
//        theme.resolveAttribute(R.attr.clockTextColor, textColor, true);
//
//        mLayoutNote.setBackgroundResource(background.resourceId);
//
//        refreshStatusBar();
//    }
//
//
//    /**
//     * 刷新 StatusBar
//     */
//    private void refreshStatusBar() {
//        if (Build.VERSION.SDK_INT >= 21) {
//            TypedValue typedValue = new TypedValue();
//            Resources.Theme theme = getTheme();
//            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
//            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
//        }
//    }
//
//    /**
//     * 展示一个切换动画
//     */
//    private void showAnimation() {
//        final View decorView = getWindow().getDecorView();
//        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
//        if (decorView instanceof ViewGroup && cacheBitmap != null) {
//            final View view = new View(this);
//            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
//            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT);
//            ((ViewGroup) decorView).addView(view, layoutParam);
//            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
//            objectAnimator.setDuration(300);
//            objectAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    ((ViewGroup) decorView).removeView(view);
//                }
//            });
//            objectAnimator.start();
//        }
//    }
//
//
//    /**
//     * 获取一个 View 的缓存视图
//     *
//     * @param view
//     * @return
//     */
//    private Bitmap getCacheBitmapFromView(View view) {
//        final boolean drawingCacheEnabled = true;
//        view.setDrawingCacheEnabled(drawingCacheEnabled);
//        view.buildDrawingCache(drawingCacheEnabled);
//        final Bitmap drawingCache = view.getDrawingCache();
//        Bitmap bitmap;
//        if (drawingCache != null) {
//            bitmap = Bitmap.createBitmap(drawingCache);
//            view.setDrawingCacheEnabled(false);
//        } else {
//            bitmap = null;
//        }
//        return bitmap;
//    }
//

}
