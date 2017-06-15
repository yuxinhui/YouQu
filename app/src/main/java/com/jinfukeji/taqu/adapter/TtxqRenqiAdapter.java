package com.jinfukeji.taqu.adapter;

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
 * 时间:"16:21"
 * 包名:com.jinfukeji.taqu.adapter
 * 描述:套套详情里人气界面适配器
 */

public class TtxqRenqiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public interface OnItemClickLitener{
        void OnItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater inflater;
    private List<Integer> mListData;
    private String[] mJiage;
    private String[] mTitle;
    private String[] mNum;

    public TtxqRenqiAdapter(Context context, List<Integer> mListData, String[] mJiage, String[] mNum,String[] mTitle) {
        this.inflater = LayoutInflater.from(context);
        this.mListData = mListData;
        this.mJiage = mJiage;
        this.mNum = mNum;
        this.mTitle =mTitle;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view=inflater.inflate(R.layout.item_renqi,parent,false);
            return new ContentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ContentViewHolder){
            ((ContentViewHolder) holder).renqi_item_img.setImageResource(mListData.get(position));
            ((ContentViewHolder) holder).renqi_item_title.setText(mTitle[position]);
            ((ContentViewHolder) holder).renqi_item_jiage.setText(mJiage[position]);
            ((ContentViewHolder) holder).renqi_item_num.setText(mNum[position]);
        }

        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.OnItemClick(holder.itemView,position+1);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    private static class ContentViewHolder extends RecyclerView.ViewHolder{
        ImageView renqi_item_img;
        TextView renqi_item_title,renqi_item_jiage,renqi_item_num;
        ContentViewHolder(View itemView) {
            super(itemView);
            renqi_item_img= (ImageView) itemView.findViewById(R.id.renqi_item_img);
            renqi_item_title= (TextView) itemView.findViewById(R.id.renqi_item_title);
            renqi_item_jiage= (TextView) itemView.findViewById(R.id.renqi_item_jiage);
            renqi_item_num= (TextView) itemView.findViewById(R.id.renqi_item_num);
        }
    }
}
