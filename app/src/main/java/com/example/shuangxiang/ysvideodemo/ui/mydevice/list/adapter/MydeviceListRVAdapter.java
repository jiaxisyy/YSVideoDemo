package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by shuang.xiang on 2017/4/24.
 */

public class MydeviceListRVAdapter extends RecyclerView.Adapter {
    private Map<String, String> map;
    private Context mContext;

    public MydeviceListRVAdapter(Map<String, String> map, Context context) {
        this.map = map;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_mydevice_list, parent, false);
        return new MyViewHolder(inflate);
    }

    public void setData(Map<String, String> map) {
        this.map = map;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        Set<String> set = map.keySet();
        List<String> names = new ArrayList<>();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            names.add(it.next());
        }
        viewHolder.mTitle.setText(names.get(position).toString());
        if (map.get(names.get(position)).equals("OFFLINE")) {
            viewHolder.mTitle.setTextColor(Color.parseColor("#c9d3dc"));
        }
    }

    @Override
    public int getItemCount() {
        return map == null ? 0 : map.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_mydevice_listName);

        }
    }
}
