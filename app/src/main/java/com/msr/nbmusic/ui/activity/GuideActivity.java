package com.msr.nbmusic.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.msr.nbmusic.R;
import com.msr.nbmusic.ui.base.BaseActivity;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.guide_banner)
    ConvenientBanner guideBanner;

    private Integer[] guideImgs = new Integer[]{R.drawable.img_guide1,
            R.drawable.img_guide2,
            R.drawable.img_guide3,
            R.drawable.img_guide4};

    @Override
    public int returnLayoutID() {
        return R.layout.activity_guide;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        guideBanner.setPages(new CBViewHolderCreator<GuideBannerHolderView>() {

            @Override
            public GuideBannerHolderView createHolder() {
                return new GuideBannerHolderView();
            }
        }, Arrays.asList(guideImgs))
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        guideBanner.setcurrentitem(0);
        guideBanner.setCanLoop(false);
    }

    public class GuideBannerHolderView implements Holder<Integer> {

        private RelativeLayout bgImg;
        private TextView goInNow;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_guide, null);
            bgImg = (RelativeLayout) view.findViewById(R.id.view_guide_bg);
            goInNow = (TextView) view.findViewById(R.id.view_guide_in);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            if (position == guideImgs.length - 1) {
                goInNow.setVisibility(View.VISIBLE);
                goInNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseStartActivity(MainActivity.class);
                        finish();
                    }
                });
            } else {
                goInNow.setVisibility(View.GONE);
            }
            bgImg.setBackgroundResource(data);
        }
    }
}
