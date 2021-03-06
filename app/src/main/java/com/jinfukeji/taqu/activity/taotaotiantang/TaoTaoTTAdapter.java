package com.jinfukeji.taqu.activity.taotaotiantang;

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
 * 时间:"14:29"
 * 包名:com.jinfukeji.taqu.activity.taotaotiantang
 * 描述:套套天堂的适配器
 */

public class TaoTaoTTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void OnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater inflater;
    private List<Integer> mData;
    private String[] mTitles;

    public TaoTaoTTAdapter(Context context, List<Integer> mData, String[] mTitles) {
        this.inflater =LayoutInflater.from(context);
        this.mData = mData;
        this.mTitles = mTitles;
    }

    private static class TTTTViewHolder extends RecyclerView.ViewHolder{
        ImageView tttt_item_img;
        TextView tttt_item_name_tv;
        TTTTViewHolder(View itemView) {
            super(itemView);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_taotaott,parent,false);
        TTTTViewHolder viewHolder=new TTTTViewHolder(view);
        viewHolder.tttt_item_img= (ImageView) view.findViewById(R.id.tttt_item_img);
        viewHolder.tttt_item_name_tv= (TextView) view.findViewById(R.id.tttt_item_name_tv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TTTTViewHolder){
            ((TTTTViewHolder) holder).tttt_item_img.setImageResource(mData.get(position));
            ((TTTTViewHolder) holder).tttt_item_name_tv.setText(mTitles[position]);

            if (mOnItemClickLitener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickLitener.onItemClick(holder.itemView,position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
