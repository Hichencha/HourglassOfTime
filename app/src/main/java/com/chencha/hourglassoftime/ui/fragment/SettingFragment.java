package com.chencha.hourglassoftime.ui.fragment;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;

import com.chencha.hourglassoftime.R;


/**
 * Desc:  设置
 * Author: chencha
 * Date: 16/12/21
 */

public class SettingFragment extends PreferenceFragment {

    private CheckBoxPreference mGrid_list,mNight_mode;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_select);

        /**
         * 更加key 定位控件id
         */

//        mGrid_list = (CheckBoxPreference) findPreference("GRID_LIST_KEY");
//        mNight_mode = (CheckBoxPreference) findPreference("NIGHT_MODE_SELECT");

        /**
         * 监听事件
         */
//        mGrid_list.setOnPreferenceClickListener((Preference.OnPreferenceClickListener) this);
//        mNight_mode.setOnPreferenceClickListener((Preference.OnPreferenceClickListener) this);
//
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences();

    }
//
//    @Override
//    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
//        String key = preference.getKey();
//        if (TextUtils.equals(key, getString(getActivity(), GRID_LIST_KEY))) {
//
//        }
//
//
//        return super.onPreferenceTreeClick(preferenceScreen, preference);
//    }
//
//    private CharSequence getString(Activity activity, int gridListKey) {
//        if (activity != null) {
//            return activity.getString(gridListKey);
//        }
//        return "";
//    }
}
