package com.msr.nbmusic.ui.widgets;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.msr.nbmusic.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ymmmsick on 9/12/17.
 */

public class DiscView extends FrameLayout {

    private ObjectAnimator discObjectAnimator;
    private CircleImageView discImg;
    private FrameLayout discRootview;

    public DiscView(Context context) {
        super(context);
        initView();
    }

    public DiscView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DiscView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 加载布局
        LayoutInflater.from(getContext()).inflate(R.layout.view_disc, this);
        discImg = (CircleImageView) findViewById(R.id.disc_img);
        discRootview = (FrameLayout) findViewById(R.id.disc_rootview);

        discObjectAnimator = ObjectAnimator.ofFloat(discRootview, "rotation", 0, 360);
        discObjectAnimator.setDuration(20000);
        //使ObjectAnimator动画匀速平滑旋转
        discObjectAnimator.setInterpolator(new LinearInterpolator());
        //无限循环旋转
        discObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        discObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
    }

    public ImageView getDiscImg() {
        return discImg;
    }

    public void startRotation() {
        discObjectAnimator.start();
    }

    public void cancelRotation() {
        discObjectAnimator.cancel();
    }
}
