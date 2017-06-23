package com.jinfukeji.taqu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfukeji.taqu.R;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"14:05"
 * 包名:com.jinfukeji.taqu.adapter
 * 描述:购物车界面里添加到购物车的适配器
 */

public class ShopcheLiebiaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> integers;
    private LayoutInflater inflater;

    public ShopcheLiebiaoAdapter(List<Integer> integers, Context context) {
        this.integers = integers;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_gouwu_liebiao,parent,false);
        return new ShopcheLiebiaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShopcheLiebiaoViewHolder){
            ((ShopcheLiebiaoViewHolder) holder).gouwu_item_shangpin_img.setImageResource(integers.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return integers.size();
    }

    private static class ShopcheLiebiaoViewHolder extends RecyclerView.ViewHolder{
        ImageView gouwu_item_xuan_img,gouwu_item_shangpin_img;
        TextView gouwu_item_shangpin_tit,gouwu_item_shangpin_guige_juti,gouwu_item_shangpin_jiage,gouwu_num;
        Button gouwu_num_jian,gouwu_num_jia;
        ShopcheLiebiaoViewHolder(View itemView) {
            super(itemView);
            gouwu_item_xuan_img= (ImageView) itemView.findViewById(R.id.gouwu_item_xuan_img);
            gouwu_item_shangpin_img= (ImageView) itemView.findViewById(R.id.gouwu_item_shangpin_img);
            gouwu_item_shangpin_tit= (TextView) itemView.findViewById(R.id.gouwu_item_shangpin_tit);
            gouwu_item_shangpin_guige_juti= (TextView) itemView.findViewById(R.id.gouwu_item_shangpin_guige_juti);
            gouwu_item_shangpin_jiage= (TextView) itemView.findViewById(R.id.gouwu_item_shangpin_jiage);
            gouwu_num= (TextView) itemView.findViewById(R.id.gouwu_num);
            gouwu_num_jian= (Button) itemView.findViewById(R.id.gouwu_num_jian);
            gouwu_num_jia= (Button) itemView.findViewById(R.id.gouwu_num_jia);
        }
    }
}
