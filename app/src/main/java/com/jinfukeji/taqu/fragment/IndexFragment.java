package com.jinfukeji.taqu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.activity.hot.SuibianGaiAdapter;
import com.jinfukeji.taqu.activity.qingqufushi.QingquFushiAdapter;
import com.jinfukeji.taqu.activity.taotaotiantang.TaoTaoTTAdapter;
import com.jinfukeji.taqu.activity.xianshiqiang.HorizontalListView;
import com.jinfukeji.taqu.activity.xianshiqiang.HorizontalListViewAdapter;
import com.jinfukeji.taqu.adapter.GuideAdapter;
import com.jinfukeji.taqu.utils.GuideUtil;

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
        initBanner();//轮播图
        flashSale();//限时抢购
        suiYiGai();//中间随便改
        taoTTT();//套套天堂
        qingQuFushi();//情趣服饰
        return view;
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

    GridView qqfsGridView;
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
        qqfsGridView= (GridView) view.findViewById(R.id.qqfs_gv);
        String[] titles={"超级好人", "就是一个", "小二坏坏", "真的"};
        final int[] ids = {R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4};
        fushiAdapter=new QingquFushiAdapter(ids,titles,getContext());
        qqfsGridView.setAdapter(fushiAdapter);
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

    GridView ttttGridView;
    private TaoTaoTTAdapter taoTTAdapter;
    //套套天堂
    private void taoTTT() {
        tttt_imgs=new int[]{R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3};
        tttt_lunbotu_ll= (LinearLayout) view.findViewById(R.id.tttt_lunbotu_ll);
        GuideUtil.initDot(tttt_imgs,getContext(),tttt_lunbotu_ll);
        tttt_lunbotu_vp= (ViewPager) view.findViewById(R.id.tttt_lunbotu_vp);
        GuideAdapter tttt_guideAdapter=new GuideAdapter(tttt_imgs,getContext());
        tttt_lunbotu_vp.setAdapter(tttt_guideAdapter);
        tttt_lunbotu_vp.setCurrentItem(tttt_imgs.length*10000);
        GuideUtil.updateDescAndDot(tttt_lunbotu_vp,tttt_imgs,tttt_lunbotu_ll);
        ttttHandler.sendEmptyMessageDelayed(0,3000);
        GuideUtil.setOnTouchListener(tttt_lunbotu_vp,ttttHandler);
        GuideUtil.setOnPageChangeListener(tttt_lunbotu_vp,tttt_imgs,tttt_lunbotu_ll);
        ttttGridView= (GridView) view.findViewById(R.id.tttt_gv);
        String[] titles={"小二坏坏", "就是一个", "超级好人", "真的"};
        final int[] ids = {R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4};
        taoTTAdapter=new TaoTaoTTAdapter(ids,titles,getContext());
        ttttGridView.setAdapter(taoTTAdapter);
    }

    //中间随便改控件
    SuibianGaiAdapter gaiAdapter;
    HorizontalListView gListView;
    //中间随便改
    private void suiYiGai() {
        gListView= (HorizontalListView) view.findViewById(R.id.suiyigai_hlistview);
        String[] titles={"梦雷", "就是一个", "傻逼", "真的"};
        final int[] ids = {R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4};
        gaiAdapter=new SuibianGaiAdapter(ids,titles,getContext());
        gListView.setAdapter(gaiAdapter);
    }

    //限时抢购控件
    HorizontalListView hListView;
    HorizontalListViewAdapter hListViewAdapter;
    //限时抢购
    private void flashSale() {
        hListView= (HorizontalListView) view.findViewById(R.id.horizon_listview);
        String[] titles={"怀师", "南怀瑾军校", "闭关", "南怀瑾"};
        final int[] ids = {R.mipmap.buffer,R.mipmap.buffer2,R.mipmap.buffer3,R.mipmap.buffer4};
        hListViewAdapter=new HorizontalListViewAdapter(ids,titles,getContext());
        hListView.setAdapter(hListViewAdapter);
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
}
