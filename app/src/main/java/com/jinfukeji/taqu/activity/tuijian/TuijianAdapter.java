package com.jinfukeji.taqu.activity.tuijian;

import android.content.Context;
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
 * 时间:"14:51"
 * 包名:com.jinfukeji.taqu.activity.tuijian
 * 描述:良心推荐界面适配器
 */

public class TuijianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater inflater;
    private List<Integer> mData;
    private String[] mTitles;

    public TuijianAdapter(Context context, List<Integer> mData, String[] mTitles) {
        this.inflater = LayoutInflater.from(context);
        this.mData = mData;
        this.mTitles = mTitles;
    }

    private static class TuijianViewHolder extends RecyclerView.ViewHolder{
        TuijianViewHolder(View itemView) {
            super(itemView);
        }
        ImageView tuijian_item_img;
        TextView tuijian_item_name_tv;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_tuijian,parent,false);
        TuijianViewHolder viewHolder=new TuijianViewHolder(view);
        viewHolder.tuijian_item_img= (ImageView) view.findViewById(R.id.tuijian_item_img);
        viewHolder.tuijian_item_name_tv= (TextView) view.findViewById(R.id.tuijian_item_name_tv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView,position);
                }
            });
        }
        if (holder instanceof TuijianViewHolder){
            ((TuijianViewHolder) holder).tuijian_item_img.setImageResource(mData.get(position));
            ((TuijianViewHolder) holder).tuijian_item_name_tv.setText(mTitles[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
