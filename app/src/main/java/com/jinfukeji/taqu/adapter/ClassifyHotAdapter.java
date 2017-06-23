package com.jinfukeji.taqu.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.utils.GuideUtil;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"15:31"
 * 包名:com.jinfukeji.taqu.adapter
 * 描述:社区热帖适配器
 */

public class ClassifyHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater inflater;
    private List<String> mListData;
    private static final int ITEM_HEADER = 0;
    private static final int ITEM_ITEM = 1;
    private static final int ITEM_LOAD_FOOTER = 2;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE=0;
    //正在加载...
    public static final int ISLOADING=1;
    //上拉加载的显示状态，初始为0
    private int load_more_status=0;

    public ClassifyHotAdapter(Context context, List<String> mListData) {
        this.inflater =LayoutInflater.from(context);
        this.mListData = mListData;
    }

    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER){
            View view=inflater.inflate(R.layout.classify_hot_listview_headview,parent,false);
            return new HeadViewHolder(view);
        }else if (viewType == ITEM_ITEM){
            View view=inflater.inflate(R.layout.item_classify_hot,parent,false);
            return new ClassifyHotItemViewHolder(view);
        }else if (viewType == ITEM_LOAD_FOOTER){
            View view=inflater.inflate(R.layout.item_classify_hot_end,parent,false);
            return new LoadFooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ClassifyHotItemViewHolder){
            ((ClassifyHotItemViewHolder) holder).classify_hot_item_name.setText(mListData.get(position-1));
        }else if (holder instanceof LoadFooterViewHolder){
            LoadFooterViewHolder viewHolder= (LoadFooterViewHolder) holder;
            switch (load_more_status){
                case PULLUP_LOAD_MORE:
                    viewHolder.loarmore_progressbar.setVisibility(View.GONE);
                    viewHolder.loadmore_text.setText("上拉加载更多");
                    break;
                case ISLOADING:
                    viewHolder.loarmore_progressbar.setVisibility(View.VISIBLE);
                    viewHolder.loadmore_text.setText("正在加载......");
                    break;
            }
        }
        //列表数据的点击事件监听，注意：这里的position是比正常的position+1的，因为多加了一个头部View
        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView,position+1);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size()+2;
    }

    //方便Activity调用改变加载状态来显示不同的加载信息（上拉加载更多，加载中…）
    public void changeMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position ==0){
            return ITEM_HEADER;
        }else if (position  == mListData.size()+1){
            return ITEM_LOAD_FOOTER;
        }else {
            return ITEM_ITEM;
        }
    }

    //头部轮播
    private static class HeadViewHolder extends RecyclerView.ViewHolder{
        private int[] shequ_imgs;
        private LinearLayout shequ_hot_ll;
        private ViewPager shequ_hot_vp;
        private Handler classify_hot_Handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                GuideUtil.handleMessage1(classify_hot_Handler,shequ_hot_vp);
            }
        };
        HeadViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            shequ_imgs=new int[]{R.mipmap.index_guide_1,R.mipmap.index_tttt_guide_1,R.mipmap.index_qqfs_guide_1};
            shequ_hot_ll= (LinearLayout) itemView.findViewById(R.id.classify_hot_lunbotu_ll);
            GuideUtil.initDot(shequ_imgs,context,shequ_hot_ll);
            shequ_hot_vp= (ViewPager) itemView.findViewById(R.id.classify_hot_lunbotu_vp);
            GuideAdapter shequHot_guideAdapter=new GuideAdapter(shequ_imgs,context);
            shequ_hot_vp.setAdapter(shequHot_guideAdapter);
            shequ_hot_vp.setCurrentItem(shequ_imgs.length*10000);
            GuideUtil.updateDescAndDot(shequ_hot_vp,shequ_imgs,shequ_hot_ll);
            classify_hot_Handler.sendEmptyMessageDelayed(0,3000);
            GuideUtil.setOnTouchListener(shequ_hot_vp,classify_hot_Handler);
            GuideUtil.setOnPageChangeListener(shequ_hot_vp,shequ_imgs,shequ_hot_ll);
        }
    }

    //中间item
    private static class ClassifyHotItemViewHolder extends RecyclerView.ViewHolder{
        TextView classify_hot_item_name;
        ClassifyHotItemViewHolder(View itemView) {
            super(itemView);
            classify_hot_item_name= (TextView) itemView.findViewById(R.id.classify_hot_item_name);
        }
    }

    //底部加载更多
    private static class LoadFooterViewHolder extends RecyclerView.ViewHolder{
        ProgressBar loarmore_progressbar;
        TextView loadmore_text;
        LoadFooterViewHolder(View itemView) {
            super(itemView);
            loarmore_progressbar= (ProgressBar) itemView.findViewById(R.id.loarmore_progressbar);
            loadmore_text= (TextView) itemView.findViewById(R.id.loadmore_text);
        }
    }
}
