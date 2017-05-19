package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
    private List<String> units;
    private MyItemClickListener mItemClickListener;

    public ParameterRvAdapter(Context context, List<String> names, List<String> values,List<String> units) {
        mContext = context;
        this.names = names;
        this.values = values;
        this.units=units;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_setting_parameter, parent, false);
        return new MyViewHolder(inflate, mItemClickListener);
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //解决参数设置界面错误问题
        holder.setIsRecyclable(false);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.mTitle.setText(names.get(position));
        viewHolder.mEditText.setText(values.get(position)+units.get(position));

    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            TextView.OnEditorActionListener {
        TextView mTitle;
        EditText mEditText;
        private MyItemClickListener mListener;

        public MyViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_system_setting);
            mEditText = (EditText) itemView.findViewById(R.id.et_item_system_setting);
            mEditText.setOnEditorActionListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getPosition());
            }
        }
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (mListener != null) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    if (mListener != null) {
                        mListener.onItemEditTextAction(mEditText.getText().toString(), getPosition());
                    }
                    //点击关闭键盘
                    InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context
                            .INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(textView.getApplicationWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
            return false;
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int postion);

        void onItemEditTextAction(String value, int position);
    }
}
