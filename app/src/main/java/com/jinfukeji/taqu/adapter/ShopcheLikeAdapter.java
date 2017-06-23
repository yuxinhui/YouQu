package com.jinfukeji.taqu.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.utils.FlowLayout;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"14:28"
 * 包名:com.jinfukeji.taqu.adapter
 * 描述:购物车界面下面猜你喜欢适配器
 */

public class ShopcheLikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<Integer> mData;

    public ShopcheLikeAdapter(Context context, List<Integer> mData) {
        this.inflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_gouwu_like,parent,false);
        return new ShopcheLikeViewholder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShopcheLikeViewholder){
            ((ShopcheLikeViewholder) holder).gouwu_item_like_img.setImageResource(mData.get(position));
            ((ShopcheLikeViewholder) holder).gouwu_item_like_shopche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showShopche();//加入购物车
                }
            });
        }
    }

    //加入购物车
    private View popShopche;
    AlertDialog dialog;
    private void showShopche() {
        dialog=new AlertDialog.Builder(inflater.getContext()).create();
        dialog.show();
        Window window=dialog.getWindow();
        assert window != null;
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.shaixuan_popup);
        popShopche=View.inflate(inflater.getContext(),R.layout.pop_shopche,null);
        window.setContentView(popShopche);
        WindowManager windowManager= (WindowManager) inflater.getContext().getSystemService(Context.WINDOW_SERVICE);
        int popShopcheHeight=(windowManager.getDefaultDisplay().getHeight()/3)*2;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,popShopcheHeight);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        popShopcheView();//购物车弹窗控件
    }

    //购物车弹窗控件
    private ImageView pop_shopche_img;
    private TextView pop_shopche_jiage,pop_shopche_guige_juti,pop_gouwu_num;
    private FlowLayout pop_shopche_danxuan_fl;
    private Button pop_gouwu_num_jian,pop_gouwu_num_jia,pop_shopche_ok_btn;
    private String[] names={"小二坏","从你我098764765","王嘉","想你今年467","五日今年","哈话u艾灸盒不能出票据"};
    private void popShopcheView() {
        pop_shopche_img= (ImageView) popShopche.findViewById(R.id.pop_shopche_img);
        pop_shopche_jiage= (TextView) popShopche.findViewById(R.id.pop_shopche_jiage);
        pop_shopche_guige_juti= (TextView) popShopche.findViewById(R.id.pop_shopche_guige_juti);
        pop_gouwu_num= (TextView) popShopche.findViewById(R.id.pop_gouwu_num);
        pop_gouwu_num_jian= (Button) popShopche.findViewById(R.id.pop_gouwu_num_jian);
        pop_gouwu_num_jia= (Button) popShopche.findViewById(R.id.pop_gouwu_num_jia);
        pop_shopche_ok_btn= (Button) popShopche.findViewById(R.id.pop_shopche_ok_btn);
        //第一步：初始化FlowLayout
        pop_shopche_danxuan_fl= (FlowLayout) popShopche.findViewById(R.id.pop_shopche_danxuan_fl);
        //第二步：移除FlowLayout中的所有子布局
        pop_shopche_danxuan_fl.removeAllViews();
        //第三步：循环创建子View，添加到FlowLayout中
        for (int x=0;x<names.length;x++){
            //3.1初始化子view
            CheckBox checkBox= (CheckBox) View.inflate(popShopche.getContext(),R.layout.item_flowlayout,null);
            checkBox.setText(names[x]);

            final int finalX = x;
            //3.2设置子view的点击事件
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //遍历FlowLayout中的所有view，如果是当前选中的view，设置为选中状态，其他设置为未选中状态
                    refreshCheckBox(names[finalX]);
                }
            });
            //3.3将子view添加到FlowLayout中
            pop_shopche_danxuan_fl.addView(checkBox);
        }
    }

    //遍历FlowLayout中的所有view，如果是当前选中的view，设置为选中状态，其他设置为未选中状态
    private void refreshCheckBox(String name) {
        for(int y=0;y<pop_shopche_danxuan_fl.getChildCount();y++){
            CheckBox radio = (CheckBox) pop_shopche_danxuan_fl.getChildAt(y);

            if(name.equals(radio.getText())){
                radio.setChecked(true);
            }else {
                radio.setChecked(false);
            }

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager=recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            GridLayoutManager layoutManager= (GridLayoutManager) manager;
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
        }
    }

    private static class ShopcheLikeViewholder extends RecyclerView.ViewHolder{
        ImageView gouwu_item_like_img,gouwu_item_like_shopche;
        TextView gouwu_item_like_tit,gouwu_item_like_jiage;
        ShopcheLikeViewholder(View itemView) {
            super(itemView);
            gouwu_item_like_img= (ImageView) itemView.findViewById(R.id.gouwu_item_like_img);
            gouwu_item_like_shopche= (ImageView) itemView.findViewById(R.id.gouwu_item_like_shopche);
            gouwu_item_like_tit= (TextView) itemView.findViewById(R.id.gouwu_item_like_tit);
            gouwu_item_like_jiage= (TextView) itemView.findViewById(R.id.gouwu_item_like_jiage);
        }
    }
}
