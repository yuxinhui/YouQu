package com.jinfukeji.taqu.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfukeji.taqu.R;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"17:07"
 * 包名:com.jinfukeji.taqu.adapter
 * 描述:限时抢购倒计时适配器
 */

public class XsqxqTimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public interface OnItemClickListener{
        void OnItemClick(View view,int posion);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private LayoutInflater inflater;
    private List<Integer> mDatas;

    public XsqxqTimeAdapter(Context context, List<Integer> mDatas) {
        this.inflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_xsqxq_time,parent,false);
        return new XsqxqTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof XsqxqTimeViewHolder){
            ((XsqxqTimeViewHolder) holder).xsqxq_time_img.setImageResource(mDatas.get(position));
            ((XsqxqTimeViewHolder) holder).xsqxq_jiage_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
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
        return mDatas.size();
    }

    private static class XsqxqTimeViewHolder extends RecyclerView.ViewHolder{
        private ImageView xsqxq_time_img;
        private TextView xsqxq_time_title,xsqxq_time_dazhe,xsqxq_jiage_old,xsqxq_jiage_new,xsqxq_time_liji;
        XsqxqTimeViewHolder(View itemView) {
            super(itemView);
            xsqxq_time_img= (ImageView) itemView.findViewById(R.id.xsqxq_time_img);
            xsqxq_time_title= (TextView) itemView.findViewById(R.id.xsqxq_time_title);
            xsqxq_time_dazhe= (TextView) itemView.findViewById(R.id.xsqxq_time_dazhe);
            xsqxq_jiage_old= (TextView) itemView.findViewById(R.id.xsqxq_jiage_old);
            xsqxq_jiage_new= (TextView) itemView.findViewById(R.id.xsqxq_jiage_new);
            xsqxq_time_liji= (TextView) itemView.findViewById(R.id.xsqxq_time_liji);
        }
    }
}
