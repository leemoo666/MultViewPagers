package com.example.danny.multviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPagerAdapter extends PagerAdapter {

    private int[] imageIDs = {R.drawable.aa,R.drawable.bb,R.drawable.cc,R.drawable.dd,R.drawable.ee,R.drawable.ff};
    private ImageView ivPicture;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public MyPagerAdapter(Context context) {
        this.context = context;
    }

    private LayoutInflater getLayoutInflater() {
        if (null == mLayoutInflater) {
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return mLayoutInflater;
    }

    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = getLayoutInflater().inflate(R.layout.item_viewpager, container, false);
        ivPicture = (ImageView) view.findViewById(R.id.item_imageview);
        ivPicture.setBackgroundResource(imageIDs[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
