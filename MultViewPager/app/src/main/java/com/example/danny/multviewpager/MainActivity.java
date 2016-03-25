package com.example.danny.multviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.danny.multviewpager.utils.DeviceUtils;
import com.example.danny.multviewpager.utils.PixelUtil;


public class MainActivity extends Activity implements ViewPager.OnPageChangeListener{

    private RelativeLayout viewpager_container;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // 将viewpager父控件的触摸事件传给viewpager
        viewpager_container = (RelativeLayout) findViewById(R.id.viewpager_container);
        /****** 动态设置viewpager的宽度，屏幕适配 ******/
        RelativeLayout.LayoutParams contanerParams = (android.widget.RelativeLayout.LayoutParams) viewPager.getLayoutParams();
        int screenWidth = DeviceUtils.getScreenWidth(this);
        if (contanerParams != null) {
            contanerParams.width = (int) (screenWidth * 0.8F);
            viewPager.setLayoutParams(contanerParams);
        }

        viewPager.setPageMargin(PixelUtil.dip2px(this, 8)); // 设置页与页之间的间距
        viewPager.setAdapter(new MyPagerAdapter(this));

        viewPager.setOffscreenPageLimit(5);
        viewPager.setOnPageChangeListener(this);
    }

    /**
     * ********************viewPager的滑动监听*******************************************
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        viewpager_container.invalidate();
    }

    @Override
    public void onPageSelected(int position) {
    }
}
