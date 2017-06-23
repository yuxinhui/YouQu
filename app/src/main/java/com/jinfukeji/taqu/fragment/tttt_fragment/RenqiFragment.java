package com.jinfukeji.taqu.fragment.tttt_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.adapter.TtxqRenqiAdapter;
import com.jinfukeji.taqu.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"14:25"
 * 包名:com.jinfukeji.taqu.fragment.tttt_fragment
 * 描述:套套天堂人气界面
 */

public class RenqiFragment extends Fragment{
    View view;

    private RecyclerView renqi_rv;
    private TtxqRenqiAdapter renqiAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_ttxq_renqi,container,false);
        initView();//控件初始化
        return view;
    }

    //控件初始化
    private void initView() {
        final List<Integer> ids =new ArrayList<Integer>(Arrays.asList(R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan,
                R.mipmap.kenan,R.mipmap.kenan,R.mipmap.kenan));
        String[] mTitle={"【我也不知道说的啥】我就是水边打折玩的不要介意哈咋嫩也就是四边搞搞","【我也不知道说的啥】我就是水边打折玩的不要介意哈咋嫩也就是四边搞搞",
                "【我也不知道说的啥】我就是水边打折玩的不要介意哈咋嫩也就是四边搞搞","【我也不知道说的啥】我就是水边打折玩的不要介意哈咋嫩也就是四边搞搞",
                "【我也不知道说的啥】我就是水边打折玩的不要介意哈咋嫩也就是四边搞搞","【我也不知道说的啥】我就是水边打折玩的不要介意哈咋嫩也就是四边搞搞",
                "【我也不知道说的啥】我就是水边打折玩的不要介意哈咋嫩也就是四边搞搞","【我也不知道说的啥】我就是水边打折玩的不要介意哈咋嫩也就是四边搞搞"};
        String[] mJiage={"￥13.13","￥8.13","￥25.00","￥19.78","￥25.26","￥65.00","￥00.00","￥13.13"};
        String[] mNum={"100","345","346","321","987","900","650","340"};
        renqi_rv= (RecyclerView) view.findViewById(R.id.renqi_rv);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        renqi_rv.setLayoutManager(layoutManager);
        int spanCount=2;
        int spacing=10;
        renqi_rv.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing, false));
        renqiAdapter=new TtxqRenqiAdapter(getContext(),ids,mJiage,mNum,mTitle);
        renqiAdapter.setmOnItemClickLitener(new TtxqRenqiAdapter.OnItemClickLitener() {
            @Override
            public void OnItemClick(View view, int position) {

            }
        });
        renqi_rv.setAdapter(renqiAdapter);
    }
}
