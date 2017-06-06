package com.jinfukeji.taqu.activity.hot;

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
 * 时间:"11:50"
 * 包名:com.jinfukeji.taqu.activity.hot
 * 描述:中间部分随便改的界面适配器
 */

public class SuibianGaiAdapter extends BaseAdapter{
    private int[] mIconIDs;
    private String[] mTitles;
    private Context mContext;
    private LayoutInflater mInflater;
    private Bitmap thumBitmap;

    public SuibianGaiAdapter(int[] mIconIDs, String[] mTitles, Context mContext) {
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
        if(view==null){
            holder = new viewHolder();
            view = mInflater.inflate(R.layout.item_suibiangai, null);
            holder.xsq_img=(ImageView)view.findViewById(R.id.syg_img);
            holder.xsq_jiage_new=(TextView)view.findViewById(R.id.syg_tv_new);
            view.setTag(holder);
        }else{
            holder=(viewHolder) view.getTag();
        }
        holder.xsq_jiage_new.setText(mTitles[i]);
        Bitmap iconBitmap = getPropThumnail(mIconIDs[i]);
        holder.xsq_img.setImageBitmap(iconBitmap);
        return view;
    }

    private class viewHolder{
        private ImageView xsq_img;
        private TextView xsq_jiage_new,xsq_jiage_old;
    }

    private Bitmap getPropThumnail(int id){
        Drawable d = mContext.getResources().getDrawable(id);
        Bitmap b = BitmapUtil.drawableToBitmap(d);
//      Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
        int w = mContext.getResources().getDimensionPixelOffset(R.dimen.suiyigai_width);
        int h = mContext.getResources().getDimensionPixelSize(R.dimen.suiyigai_heigth);

        thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);

        return thumBitmap;
    }
}
