package com.msr.nbmusic.ui.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义实现一个可控制滑动的ViewPager
 *
 * @author xue
 * @version 1.0
 * @time 2014-06-20 11:44:37
 */
public class BanSlideViewPager extends ViewPager {
    private boolean isCanScroll = true;

    public BanSlideViewPager(Context context) {
        super(context);
    }

    public BanSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不可滑动
     *
     * @param isCanScroll
     */
    public void setScrollEnable(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    // 触摸没有反应就可以了
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.isCanScroll) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.isCanScroll) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        if (this.isCanScroll)
            super.setCurrentItem(item);
        else
            setCurrentItem(item, false);
    }
}