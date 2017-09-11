package com.msr.nbmusic.bean.response;

import java.util.List;

/**
 * Created by Ymmmsick on 9/11/17.
 */

public class NewMusicBean {
    /**
     * ret_code : 0
     * songlist : [{"albumid":1181826,"downUrl":"http://tsmusic24.tc.qq.com/104779440.mp3","seconds":291,"singerid":4286,"singername":"林俊杰","songid":104779440,"songname":"只要有你的地方 (《消失的爱人》电影主题曲)","url":"http://ws.stream.qqmusic.qq.com/104779440.m4a?fromtag=46"}]
     * totalpage : 1
     */

    private PagebeanBean pagebean;
    /**
     * pagebean : {"ret_code":0,"songlist":[{"albumid":1181826,"downUrl":"http://tsmusic24.tc.qq.com/104779440.mp3","seconds":291,"singerid":4286,"singername":"林俊杰","songid":104779440,"songname":"只要有你的地方 (《消失的爱人》电影主题曲)","url":"http://ws.stream.qqmusic.qq.com/104779440.m4a?fromtag=46"}],"totalpage":1}
     * ret_code : 0
     */

    private int ret_code;

    public PagebeanBean getPagebean() {
        return pagebean;
    }

    public void setPagebean(PagebeanBean pagebean) {
        this.pagebean = pagebean;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public static class PagebeanBean {
        private int ret_code;
        private int totalpage;
        /**
         * albumid : 1181826
         * downUrl : http://tsmusic24.tc.qq.com/104779440.mp3
         * seconds : 291
         * singerid : 4286
         * singername : 林俊杰
         * songid : 104779440
         * songname : 只要有你的地方 (《消失的爱人》电影主题曲)
         * url : http://ws.stream.qqmusic.qq.com/104779440.m4a?fromtag=46
         */

        private List<SonglistBean> songlist;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public List<SonglistBean> getSonglist() {
            return songlist;
        }

        public void setSonglist(List<SonglistBean> songlist) {
            this.songlist = songlist;
        }

        public static class SonglistBean {
            private int albumid;
            private String downUrl;
            private String albumpic_big;
            private String albumpic_small;
            private int seconds;
            private int singerid;
            private String singername;
            private int songid;
            private String songname;
            private String url;

            public int getAlbumid() {
                return albumid;
            }

            public void setAlbumid(int albumid) {
                this.albumid = albumid;
            }

            public String getDownUrl() {
                return downUrl;
            }

            public void setDownUrl(String downUrl) {
                this.downUrl = downUrl;
            }

            public String getAlbumpic_big() {
                return albumpic_big;
            }

            public void setAlbumpic_big(String albumpic_big) {
                this.albumpic_big = albumpic_big;
            }

            public String getAlbumpic_small() {
                return albumpic_small;
            }

            public void setAlbumpic_small(String albumpic_small) {
                this.albumpic_small = albumpic_small;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getSingerid() {
                return singerid;
            }

            public void setSingerid(int singerid) {
                this.singerid = singerid;
            }

            public String getSingername() {
                return singername;
            }

            public void setSingername(String singername) {
                this.singername = singername;
            }

            public int getSongid() {
                return songid;
            }

            public void setSongid(int songid) {
                this.songid = songid;
            }

            public String getSongname() {
                return songname;
            }

            public void setSongname(String songname) {
                this.songname = songname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
