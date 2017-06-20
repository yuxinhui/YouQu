package com.jinfukeji.taqu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinfukeji.taqu.R;

/**
 * Created by "于志渊"
 * 时间:"11:25"
 * 包名:com.jinfukeji.taqu.adapter
 * 描述:套套天堂详情里筛选界面分类适配器
 */

public class TtxqXuanFenleiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private String[] mTitle;
    private LayoutInflater inflater;

    public TtxqXuanFenleiAdapter(String[] mTitle, Context context) {
        this.mTitle = mTitle;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_ttxq_xuan_fenlei,parent,false);
        TtxqXuanFenleiViewHolder viewHolder=new TtxqXuanFenleiViewHolder(view);
        viewHolder.item_ttxq_xuan_fenlei_tit= (TextView) view.findViewById(R.id.item_ttxq_xuan_fenlei_tit);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TtxqXuanFenleiViewHolder){
            ((TtxqXuanFenleiViewHolder) holder).item_ttxq_xuan_fenlei_tit.setText(mTitle[position]);
        }
        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTitle.length;
    }

    private static class TtxqXuanFenleiViewHolder extends RecyclerView.ViewHolder{
        TextView item_ttxq_xuan_fenlei_tit;
        TtxqXuanFenleiViewHolder(View itemView) {
            super(itemView);
        }
    }
}
