package com.chencha.hourglassoftime.ui.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.chencha.hourglassoftime.R;
import com.chencha.hourglassoftime.ui.base.BaseActivity2;
import com.chencha.hourglassoftime.util.Constants;
import com.chencha.hourglassoftime.util.NoteEditor;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chencha.hourglassoftime.util.Constants.EXTRA_WEAC_SHARE;

/**
 * Desc:   添加笔记
 * Author: chencha
 * Date: 16/12/16
 */

public class AddNoteActivity extends BaseActivity2 implements ColorChooserDialog.ColorCallback {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.note_text_layout)
    CoordinatorLayout mNoteTextLayout;
    @BindView(R.id.line)
    View mLine;
    @BindView(R.id.note_bg_edit)
    NoteEditor mNoteBgEdit;

    //选择的背景色
    private int mSelectColor;
    private String mBackColor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notes_layout);
        StatusBarUtil.setTranslucent(this,22);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        initToobar("创建");
        /**
         * 初始化背景色
         */
        mNoteTextLayout.setBackgroundColor(Color.parseColor("#FFB805"));
        mToolbar.setBackgroundColor(Color.parseColor("#FFB805"));
    }

    /**
     * toolbar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 重写onOptionsItemSelected
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                break;
            case R.id.action_select:
                break;
            case R.id.action_bg:
                new ColorChooserDialog.Builder(AddNoteActivity.this, R.string.select_bg)
                        .titleSub(R.string.colors)
                        .doneButton(R.string.ok)
                        .cancelButton(R.string.cancel)
                        .backButton(R.string.back)
                        .customButton(R.string.custom_define)
                        .presetsButton(R.string.back)
                        .show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 颜色值回调
     *
     * @param dialog
     * @param selectedColor
     */
    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        mSelectColor = selectedColor;
        operateColor(selectedColor, Constants.BACK_COLOR);
        /**
         * color int转换
         */
        mBackColor = "#" + Integer.toHexString(mSelectColor);
        mNoteTextLayout.setBackgroundColor(Color.parseColor(mBackColor));
        mToolbar.setBackgroundColor(Color.parseColor(mBackColor));
        mNoteBgEdit.setBackgroundColor(Color.parseColor(mBackColor));
        StatusBarUtil.setTranslucent(this, 22);

        Snackbar.make(mNoteTextLayout, "修改了混沌背景", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();

    }

    /**
     * @param selectedColor
     * @param saveColorKey
     */
    private void operateColor(int selectedColor, String saveColorKey) {
        SharedPreferences preferences = getSharedPreferences(EXTRA_WEAC_SHARE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(saveColorKey, selectedColor);
        editor.apply();

    }


}
