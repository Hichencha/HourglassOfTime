package com.chencha.hourglassoftime.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chencha.hourglassoftime.R;

/**
 * Desc:   设置
 * Author: chencha
 * Date: 17/1/12
 */

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] mSettingInfos;

    public SettingAdapter(Context context, String[] mSettingInfos) {
        this.mContext = context;
        this.mSettingInfos = mSettingInfos;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.seting_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nameInfo = mSettingInfos[position];
        holder.mInfoName.setText(nameInfo);

    }

    @Override
    public int getItemCount() {
        return mSettingInfos.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mInfoName;

        public ViewHolder(View itemView) {
            super(itemView);
            mInfoName = (TextView) itemView.findViewById(R.id.info_name);
        }
    }
}
