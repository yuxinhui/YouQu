package com.jinfukeji.taqu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.adapter.ShopcheLiebiaoAdapter;
import com.jinfukeji.taqu.adapter.ShopcheLikeAdapter;
import com.jinfukeji.taqu.utils.GridSpacingItemDecoration;
import com.jinfukeji.taqu.utils.LinearlayoutSpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"14:38"
 * 包名:com.jinfukeji.taqu.fragment
 * 描述:购物车界面
 */

public class ShoppingcartFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_shopping,container,false);
        initView();//初始化控件
        shopcheLiebiao();//配置购物车列表
        shopcheLike();//购物界面下面猜你喜欢配置
        return view;
    }

    //初始化控件
    private ImageView gouwu_xiaoxi,gouwu_quanxuan_img;
    private TextView gouwu_zongjia_tv;
    private Button gouwu_jiesuan_btn;
    private RecyclerView gouwu_liebiao,gouwu_like;
    private void initView() {
        gouwu_xiaoxi= (ImageView) view.findViewById(R.id.gouwu_xiaoxi);
        gouwu_quanxuan_img= (ImageView) view.findViewById(R.id.gouwu_quanxuan_img);
        gouwu_zongjia_tv= (TextView) view.findViewById(R.id.gouwu_zongjia_tv);
        gouwu_jiesuan_btn= (Button) view.findViewById(R.id.gouwu_jiesuan_btn);
        gouwu_liebiao= (RecyclerView) view.findViewById(R.id.gouwu_liebiao);
        gouwu_like= (RecyclerView) view.findViewById(R.id.gouwu_like);
    }

    //配置购物车列表
    private ShopcheLiebiaoAdapter liebiaoAdapter;
    private List<Integer> mDataLiebiao=new ArrayList<Integer>(Arrays.asList(R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan));
    private void shopcheLiebiao(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gouwu_liebiao.setLayoutManager(layoutManager);
        gouwu_liebiao.addItemDecoration(new LinearlayoutSpacesItemDecoration(10));
        liebiaoAdapter=new ShopcheLiebiaoAdapter(mDataLiebiao,getContext());
        gouwu_liebiao.setAdapter(liebiaoAdapter);
    }

    //购物界面下面猜你喜欢配置
    private ShopcheLikeAdapter likeAdapter;
    private List<Integer> mDataLike=new ArrayList<Integer>(Arrays.asList(R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan,
            R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan));
    private void shopcheLike(){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        gouwu_like.setLayoutManager(gridLayoutManager);
        int spant=2;
        int spice=10;
        gouwu_like.addItemDecoration(new GridSpacingItemDecoration(spant,spice,false));
        likeAdapter=new ShopcheLikeAdapter(getContext(),mDataLike);
        gouwu_like.setAdapter(likeAdapter);
    }
}
