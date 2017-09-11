package com.msr.nbmusic.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.msr.nbmusic.R;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.comm.NBGlideManager;
import com.msr.nbmusic.ui.base.BaseAdapter;
import com.msr.nbmusic.ui.widgets.font.NBTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ymmmsick on 9/11/17.
 */

public class NewMusicAdapter extends BaseAdapter<NewMusicBean.PagebeanBean.SonglistBean> {

    public NewMusicAdapter(Context context, List<NewMusicBean.PagebeanBean.SonglistBean> data) {
        super(context, data);
    }

    @Override
    public int getCount() {
        if (getData() == null)
            return 0;
        else if (getData().size() >= 2)
            return 2;//always return 2 count
        else
            return getData().size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = getInflater().inflate(R.layout.item_newmusic, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewMusicBean.PagebeanBean.SonglistBean itemData = getData().get(position);
        viewHolder.itemNewmusicName.setText(itemData.getSongname());
        viewHolder.itemNewmusicSinger.setText(itemData.getSingername());
        NBGlideManager.loadImage(itemData.getAlbumpic_small(), viewHolder.itemNewmusicAlbumpic);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_newmusic_albumpic)
        ImageView itemNewmusicAlbumpic;
        @BindView(R.id.item_newmusic_name)
        NBTextView itemNewmusicName;
        @BindView(R.id.item_newmusic_singer)
        NBTextView itemNewmusicSinger;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
