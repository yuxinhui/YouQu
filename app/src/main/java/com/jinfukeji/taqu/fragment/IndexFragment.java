package com.jinfukeji.taqu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.activity.hot.SuibianGaiAdapter;
import com.jinfukeji.taqu.activity.qingqufushi.QingquFushiAdapter;
import com.jinfukeji.taqu.activity.taotaotiantang.TaoTaoTTAdapter;
import com.jinfukeji.taqu.activity.taotaotiantang.TaoTaoTianTangActivity;
import com.jinfukeji.taqu.activity.tuijian.TuijianAdapter;
import com.jinfukeji.taqu.activity.xianshiqiang.FlashSalectivity;
import com.jinfukeji.taqu.activity.xianshiqiang.XianshiQiangAdapter;
import com.jinfukeji.taqu.adapter.GuideAdapter;
import com.jinfukeji.taqu.utils.GridSpacingItemDecoration;
import com.jinfukeji.taqu.utils.GuideUtil;
import com.jinfukeji.taqu.utils.LinearlayoutSpacesItemDecoration;
import com.jinfukeji.taqu.utils.ScrollViewUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"14:27"
 * 包名:com.jinfukeji.taqu.fragment
 * 描述:首页界面
 */

public class IndexFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_index,container,false);
        biaoTi();//滑动显示标题
        initBanner();//轮播图
        flashSale();//限时抢购
        suiYiGai();//中间随便改
        taoTTT();//套套天堂
        qingQuFushi();//情趣服饰
        tuiJian();//良心推荐
        onClick();//点击事件
        return view;
    }

    //各种点击事件
    private void onClick() {
        view.findViewById(R.id.index_banner1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TaoTaoTianTangActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 11:
                String ttxq=data.getExtras().getString("index");
                break;
            case 22:
                String xsq_qubu=data.getExtras().getString("index");
                break;
            default:
                break;
        }
    }

    //良心推荐
    RecyclerView tuijian_rv;
    TuijianAdapter tuijianAdapter;
    private void tuiJian() {
        tuijian_rv= (RecyclerView) view.findViewById(R.id.tuijian_rv);
        String[] titles={"小二坏坏", "好人","才认识","你个loser"};
        final List<Integer> ids =new ArrayList<Integer>(Arrays.asList(R.mipmap.buffer3,R.mipmap.buffer4,R.mipmap.buffer5,R.mipmap.buffer));
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        tuijian_rv.setLayoutManager(layoutManager);
        int spanCont=2;
        int space=15;
        boolean includeEdge = false;
        tuijian_rv.addItemDecoration(new GridSpacingItemDecoration(spanCont,space,includeEdge));
        tuijianAdapter=new TuijianAdapter(getContext(),ids,titles);
        tuijian_rv.setAdapter(tuijianAdapter);
    }

    //情趣服饰控件
    private TextView qqfs_more_txt;
    private int[] qqfs_imgs;
    private LinearLayout qqfs_lunbotu_ll;
    private ViewPager qqfs_lunbotu_vp;
    private Handler qqfsHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            GuideUtil.handleMessage1(qqfsHandler,qqfs_lunbotu_vp);
        }
    };

    RecyclerView qqfs_rv;
    private QingquFushiAdapter fushiAdapter;
    //情趣服饰
    private void qingQuFushi() {
        qqfs_imgs=new int[]{R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4};
        qqfs_lunbotu_ll= (LinearLayout) view.findViewById(R.id.qqfs_lunbotu_ll);
        GuideUtil.initDot(qqfs_imgs,getContext(),qqfs_lunbotu_ll);
        qqfs_lunbotu_vp= (ViewPager) view.findViewById(R.id.qqfs_lunbotu_vp);
        GuideAdapter qqfs_guideAdapter=new GuideAdapter(qqfs_imgs,getContext());
        qqfs_lunbotu_vp.setAdapter(qqfs_guideAdapter);
        qqfs_lunbotu_vp.setCurrentItem(qqfs_imgs.length*10000);
        GuideUtil.updateDescAndDot(qqfs_lunbotu_vp,qqfs_imgs,qqfs_lunbotu_ll);
        qqfsHandler.sendEmptyMessageDelayed(0,3000);
        GuideUtil.setOnTouchListener(qqfs_lunbotu_vp,qqfsHandler);
        GuideUtil.setOnPageChangeListener(qqfs_lunbotu_vp,qqfs_imgs,qqfs_lunbotu_ll);
        String[] titles={"超级好人", "就是一个", "小二坏坏", "真的"};
        final List<Integer> ids =new ArrayList<Integer>(Arrays.asList(R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4));
        qqfs_rv= (RecyclerView) view.findViewById(R.id.qqfs_rv);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        qqfs_rv.setLayoutManager(layoutManager);
        int spanCount=2;
        int spacing=10;
        boolean includeEdge = false;
        qqfs_rv.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,includeEdge));
        fushiAdapter=new QingquFushiAdapter(getContext(),ids,titles);
        fushiAdapter.setOnItemClickLitener(new QingquFushiAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        qqfs_rv.setAdapter(fushiAdapter);
    }

    //套套天堂控件
    private TextView tttt_more_txt;
    private int[] tttt_imgs;
    private LinearLayout tttt_lunbotu_ll;
    private ViewPager tttt_lunbotu_vp;
    private Handler ttttHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            GuideUtil.handleMessage1(ttttHandler,tttt_lunbotu_vp);
        }
    };

    RecyclerView tttt_rv;
    private TaoTaoTTAdapter taoTTAdapter;
    //套套天堂
    private void taoTTT() {
        ttttView();//控件初始化
        ttttClick();//点击事件
        tttt_imgs=new int[]{R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3};
        GuideUtil.initDot(tttt_imgs,getContext(),tttt_lunbotu_ll);
        GuideAdapter tttt_guideAdapter=new GuideAdapter(tttt_imgs,getContext());
        tttt_lunbotu_vp.setAdapter(tttt_guideAdapter);
        tttt_lunbotu_vp.setCurrentItem(tttt_imgs.length*10000);
        GuideUtil.updateDescAndDot(tttt_lunbotu_vp,tttt_imgs,tttt_lunbotu_ll);
        ttttHandler.sendEmptyMessageDelayed(0,3000);
        GuideUtil.setOnTouchListener(tttt_lunbotu_vp,ttttHandler);
        GuideUtil.setOnPageChangeListener(tttt_lunbotu_vp,tttt_imgs,tttt_lunbotu_ll);

        String[] titles={"小二坏坏", "就是一个", "超级好人", "真的","梦雷", "就是一个", "傻逼", "真的"};
        final List<Integer> ids =new ArrayList<Integer>(Arrays.asList(R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4,
                R.mipmap.buffer5,R.mipmap.buffer6,R.mipmap.buffer7,R.mipmap.buffer8));
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),4,GridLayoutManager.VERTICAL,false);
        tttt_rv.setLayoutManager(layoutManager);
        int spanCount=4;
        int spacing=10;
        boolean includeEdge = false;
        tttt_rv.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,includeEdge));
        taoTTAdapter=new TaoTaoTTAdapter(getContext(),ids,titles);
        taoTTAdapter.OnItemClickLitener(new TaoTaoTTAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        tttt_rv.setAdapter(taoTTAdapter);
    }

    private void ttttClick() {
        tttt_more_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),TaoTaoTianTangActivity.class));
            }
        });
    }

    private void ttttView() {
        tttt_lunbotu_ll= (LinearLayout) view.findViewById(R.id.tttt_lunbotu_ll);
        tttt_lunbotu_vp= (ViewPager) view.findViewById(R.id.tttt_lunbotu_vp);
        tttt_rv= (RecyclerView) view.findViewById(R.id.tttt_rv);
        tttt_more_txt= (TextView) view.findViewById(R.id.tttt_more_txt);
    }

    //中间随便改控件
    SuibianGaiAdapter gaiAdapter;
    RecyclerView sbg_rv;
    //中间随便改
    private void suiYiGai() {
        String[] titles={"梦雷", "就是一个", "傻逼", "真的"};
        final List<Integer> ids =new ArrayList<Integer>(Arrays.asList(R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4));
        sbg_rv= (RecyclerView) view.findViewById(R.id.suiyigai_rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sbg_rv.setLayoutManager(linearLayoutManager);
        sbg_rv.addItemDecoration(new LinearlayoutSpacesItemDecoration(10));
        gaiAdapter=new SuibianGaiAdapter(getContext(),ids,titles);
        gaiAdapter.OnItemClickListen(new SuibianGaiAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        sbg_rv.setAdapter(gaiAdapter);
    }

    //限时抢购控件
    private TextView xsq_changci_tv,xsq_time_tv,xsq_qubu_tv;
    private RecyclerView xsqg_rv;
    private XianshiQiangAdapter xianshiQiangAdapter;
    //限时抢购
    private void flashSale() {
        flashSaleView();//初始化里面控件
        String[] titles={"13.14", "15.26", "14.23", "55.55"};
        final List<Integer> ids =new ArrayList<Integer>(Arrays.asList(R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        xsqg_rv.setLayoutManager(linearLayoutManager);
        xsqg_rv.addItemDecoration(new LinearlayoutSpacesItemDecoration(15));
        xianshiQiangAdapter=new XianshiQiangAdapter(getContext(),ids,titles);
        xianshiQiangAdapter.OnItemClickLitener(new XianshiQiangAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        xsqg_rv.setAdapter(xianshiQiangAdapter);
        flashSaleClick();//点击事件
    }

    private void flashSaleClick() {
        xsq_qubu_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FlashSalectivity.class));
            }
        });
    }

    private void flashSaleView() {
        xsqg_rv= (RecyclerView) view.findViewById(R.id.xsqg_rv);
        xsq_changci_tv= (TextView) view.findViewById(R.id.xsq_changci_tv);
        xsq_time_tv= (TextView) view.findViewById(R.id.xsq_time_tv);
        xsq_qubu_tv= (TextView) view.findViewById(R.id.xsq_qubu_tv);
    }

    //轮播图控件
    private int[] imgsId;
    private LinearLayout lunbotu_dots_ll;
    private ViewPager lunbotu_guide_vp;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            lunbotu_guide_vp.setCurrentItem(lunbotu_guide_vp.getCurrentItem()+1,true);
            handler.sendEmptyMessageDelayed(0,2000);
        }
    };
    private ImageView index_sousuo_img,index_xinxi_img;
    //轮播图
    public void initBanner() {
        imgsId=new int[]{R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4};
        lunbotu_dots_ll= (LinearLayout) view.findViewById(R.id.lunbotu_ll);
        GuideUtil.initDot(imgsId,getContext(),lunbotu_dots_ll);
        lunbotu_guide_vp= (ViewPager) view.findViewById(R.id.lunbotu_vp);
        GuideAdapter index_guideAdapter=new GuideAdapter(imgsId,getContext());
        lunbotu_guide_vp.setAdapter(index_guideAdapter);
        lunbotu_guide_vp.setCurrentItem(imgsId.length*10000);
        GuideUtil.updateDescAndDot(lunbotu_guide_vp,imgsId,lunbotu_dots_ll);
        handler.sendEmptyMessageDelayed(0,2000);
        GuideUtil.setOnTouchListener(lunbotu_guide_vp,handler);
        GuideUtil.setOnPageChangeListener(lunbotu_guide_vp,imgsId,lunbotu_dots_ll);
    }

    //滑动显示标题控件
    private ImageView biaoti_xiaoxi_img,biaoti_sousuo_img;
    private ScrollViewUtil myScrollView;
    private View layout;
    //滑动显示标题
    private void biaoTi(){
        biaotiView();//标题里面的控件初始化
        layout.setAlpha(0);
        myScrollView.setFadingView(layout);
        myScrollView.setFadingHeightView(view.findViewById(R.id.lunbotu_vp));
    }

    //标题里面的控件初始化
    private void biaotiView() {
        layout=view.findViewById(R.id.nac_layout);
        myScrollView= (ScrollViewUtil) view.findViewById(R.id.index_scrollview);
    }
}