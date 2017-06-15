package com.jinfukeji.taqu.activity.hot;

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
 * 时间:"11:50"
 * 包名:com.jinfukeji.taqu.activity.hot
 * 描述:中间部分随便改的界面适配器
 */

public class SuibianGaiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void OnItemClickListen(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater inflater;
    private List<Integer> mData;
    private String[] mTitles;

    public SuibianGaiAdapter(Context context, List<Integer> mData, String[] mTitles) {
        this.inflater = LayoutInflater.from(context);
        this.mData = mData;
        this.mTitles = mTitles;
    }

    private static class HotViewHolder extends RecyclerView.ViewHolder{
        ImageView sbg_img;
        TextView sbg_tv_new;
        HotViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_suibiangai,parent,false);
        HotViewHolder hotViewHolder=new HotViewHolder(view);
        hotViewHolder.sbg_img= (ImageView) view.findViewById(R.id.sbg_img);
        hotViewHolder.sbg_tv_new= (TextView) view.findViewById(R.id.sbg_tv_new);
        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HotViewHolder){
            ((HotViewHolder) holder).sbg_img.setImageResource(mData.get(position));
            ((HotViewHolder) holder).sbg_tv_new.setText(mTitles[position]);

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
