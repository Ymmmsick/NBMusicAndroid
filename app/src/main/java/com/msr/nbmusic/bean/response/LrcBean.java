package com.msr.nbmusic.bean.response;

/**
 * Created by Ymmmsick on 9/13/17.
 */

public class LrcBean {

    /**
     * lyric : ti:海阔天空 (Edited Version)
     * lyric_txt :             背弃了理想   谁人都可以            哪会怕有一天只你共我            原谅我这一生不羁放纵爱自由      也会怕有一天会跌倒            背弃了理想谁人都可以            哪会怕有一天只你共我
     * ret_code : 0
     */

    private String lyric;
    private String lyric_txt;
    private int ret_code;

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getLyric_txt() {
        return lyric_txt;
    }

    public void setLyric_txt(String lyric_txt) {
        this.lyric_txt = lyric_txt;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }
}
