package com.jinfukeji.taqu.activity.xianshiqiang;

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
 * 时间:"15:15"
 * 包名:com.jinfukeji.taqu.activity.xianshiqiang
 * 描述:限时抢购部分的适配器
 */

public class XianshiQiangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    /**
     * item监听
     * */
    public interface OnItemClickLitener{
        void onItemClick(View view,int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void OnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater inflater;
    private List<Integer> mData;
    private String[] mTitles;

    public XianshiQiangAdapter(Context context, List<Integer> mData, String[] mTitles) {
        this.inflater = LayoutInflater.from(context);
        this.mData = mData;
        this.mTitles = mTitles;
    }

    private static class XianshiViewHolder extends RecyclerView.ViewHolder{
        ImageView xsq_img;
        TextView xsq_jiage_new;
        XianshiViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_horizontallistview,parent,false);
        XianshiViewHolder viewHolder=new XianshiViewHolder(view);
        viewHolder.xsq_img= (ImageView) view.findViewById(R.id.xsq_img);
        viewHolder.xsq_jiage_new= (TextView) view.findViewById(R.id.xsq_jiage_new);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof XianshiViewHolder){
            ((XianshiViewHolder) holder).xsq_img.setImageResource(mData.get(position));
            ((XianshiViewHolder) holder).xsq_jiage_new.setText(mTitles[position]);
            //如果设置了回调，则设置点击事件
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
