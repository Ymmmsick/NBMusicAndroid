package com.msr.nbmusic.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.msr.nbmusic.R;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.ui.adapter.NewMusicAdapter;
import com.msr.nbmusic.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Ymmmsick on 2017-09-11.
 */

public class NewMusicActivity extends BaseActivity {

    @BindView(R.id.newmusic_listview)
    ListView newmusicListview;
    private NewMusicBean newMusicBean;
    private NewMusicAdapter newMusicAdapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_newmusic;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setActionBarBackEnable();
        newMusicBean = (NewMusicBean) getIntent().getExtras().getSerializable("newMusicData");
        newMusicAdapter = new NewMusicAdapter(this, newMusicBean.getPagebean().getSonglist());
        newmusicListview.setAdapter(newMusicAdapter);
    }

}
