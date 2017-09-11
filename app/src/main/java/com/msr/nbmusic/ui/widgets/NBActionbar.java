package com.msr.nbmusic.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.msr.nbmusic.R;

/**
 * Created by Ymmmsick on 2017-09-11.
 */

public class NBActionbar extends RelativeLayout {

    private ImageView play;

    public NBActionbar(Context context) {
        super(context);
        initView();
    }

    public NBActionbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NBActionbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 加载布局
        LayoutInflater.from(getContext()).inflate(R.layout.view_actionbar, this);
        play = (ImageView) findViewById(R.id.view_play);
        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

}
