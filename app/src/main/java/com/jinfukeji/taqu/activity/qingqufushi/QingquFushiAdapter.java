package com.jinfukeji.taqu.activity.qingqufushi;

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
 * 时间:"16:25"
 * 包名:com.jinfukeji.taqu.activity.qingqufushi
 * 描述:情趣服饰适配器
 */

public class QingquFushiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener=mOnItemClickLitener;
    }

    private LayoutInflater inflater;
    private List<Integer> mData;
    private String[] mTitles;

    public QingquFushiAdapter(Context context, List<Integer> mData, String[] mTitles) {
        this.inflater = LayoutInflater.from(context);
        this.mData = mData;
        this.mTitles = mTitles;
    }

    private static class QQFSViewHolder extends RecyclerView.ViewHolder{
        TextView qqfs_item_name_tv;
        ImageView qqfs_item_img;
        QQFSViewHolder(View itemView){
            super(itemView);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_qqfs,parent,false);
        QQFSViewHolder viewHolder=new QQFSViewHolder(view);
        viewHolder.qqfs_item_name_tv= (TextView) view.findViewById(R.id.qqfs_item_name_tv);
        viewHolder.qqfs_item_img= (ImageView) view.findViewById(R.id.qqfs_item_img);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof QQFSViewHolder){
            ((QQFSViewHolder) holder).qqfs_item_name_tv.setText(mTitles[position]);
            ((QQFSViewHolder) holder).qqfs_item_img.setImageResource(mData.get(position));
        }
        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
