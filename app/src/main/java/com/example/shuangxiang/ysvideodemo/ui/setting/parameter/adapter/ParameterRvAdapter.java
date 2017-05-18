package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class ParameterRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> names;
    private List<String> values;

    public ParameterRvAdapter(Context context, List<String> names, List<String> values) {
        mContext = context;
        this.names = names;
        this.values = values;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_setting_parameter, parent, false);
        return new MyViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.mTitle.setText(names.get(position));
        viewHolder.mEditText.setText(values.get(position));


    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        EditText mEditText;


        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_system_setting);
            mEditText = (EditText) itemView.findViewById(R.id.et_item_system_setting);
        }
    }
}
