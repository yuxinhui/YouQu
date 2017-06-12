package com.jinfukeji.taqu.activity.tuijian;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinfukeji.taqu.R;
import com.jinfukeji.taqu.utils.BitmapUtil;

/**
 * Created by "于志渊"
 * 时间:"14:51"
 * 包名:com.jinfukeji.taqu.activity.tuijian
 * 描述:良心推荐界面适配器
 */

public class TuijianAdapter extends BaseAdapter{
    private int[] mIconIDs;
    private String[] mTitles;
    private Context mContext;
    private LayoutInflater mInflater;
    private Bitmap thumBitmap;

    public TuijianAdapter(int[] mIconIDs, String[] mTitles, Context mContext) {
        this.mIconIDs = mIconIDs;
        this.mTitles = mTitles;
        this.mContext = mContext;
        mInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mIconIDs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder holder;
        if (view == null){
            holder=new viewHolder();
            view=mInflater.inflate(R.layout.item_tuijian,null);
            holder.tuijian_item_img= (ImageView) view.findViewById(R.id.tuijian_item_img);
            holder.tuijian_item_name_tv= (TextView) view.findViewById(R.id.tuijian_item_name_tv);
            view.setTag(holder);
        }else {
            holder= (viewHolder) view.getTag();
        }
        holder.tuijian_item_name_tv.setText(mTitles[i]);
        Bitmap iconBitmap = getPropThumnail(mIconIDs[i]);
        holder.tuijian_item_img.setImageBitmap(iconBitmap);
        return view;
    }

    private class viewHolder{
        ImageView tuijian_item_img;
        TextView tuijian_item_name_tv;
    }

    private Bitmap getPropThumnail(int id){
        Drawable d = mContext.getResources().getDrawable(id);
        Bitmap b = BitmapUtil.drawableToBitmap(d);
        //Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
        int w = mContext.getResources().getDimensionPixelOffset(R.dimen.qqfs_width);
        int h = mContext.getResources().getDimensionPixelSize(R.dimen.qqfs_heigth);
        thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);
        return thumBitmap;
    }
}
