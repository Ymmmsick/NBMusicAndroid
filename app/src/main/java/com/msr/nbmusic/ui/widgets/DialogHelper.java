package com.msr.nbmusic.ui.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.msr.nbmusic.R;
import com.msr.nbmusic.event.BADialogListener;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class DialogHelper {

    /**
     * 开启首页广告dialog
     *
     * @param context
     * @param adOnclickListener
     */
    public static void openAdsDialog(Context context, final BADialogListener adOnclickListener) {
        View layout = View.inflate(context, R.layout.dialog_ads, null);
        final Dialog dialog = new Dialog(context, R.style.BADialog);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        ImageView adsImg = (ImageView) layout.findViewById(R.id.ads_img);
        ImageView adsClose = (ImageView) layout.findViewById(R.id.ads_close);
        if (adOnclickListener != null)
            adsImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adOnclickListener.onClick(view);
                    dialog.dismiss();
                }
            });
        adsClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
