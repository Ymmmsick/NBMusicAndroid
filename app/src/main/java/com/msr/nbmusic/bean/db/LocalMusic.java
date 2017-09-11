package com.msr.nbmusic.bean.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by SIMEON on 2017/9/11.
 */

@Entity
public class LocalMusic {

    @Id(autoincrement=true)
    private Long id;
    @Property(nameInDb = "fileName")
    private String fileName;
    @Property(nameInDb = "fileType")
    private String fileType;
    @Property(nameInDb = "summary")
    private String summary;
    @Property(nameInDb = "fileSzie")
    private String fileSize;
    @Property(nameInDb = "singer")
    private String singer;
    @Property(nameInDb = "time")
    private String time;
    @Generated(hash = 649732074)
    public LocalMusic(Long id, String fileName, String fileType, String summary,
            String fileSize, String singer, String time) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.summary = summary;
        this.fileSize = fileSize;
        this.singer = singer;
        this.time = time;
    }
    @Generated(hash = 1197306124)
    public LocalMusic() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileType() {
        return this.fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getFileSize() {
        return this.fileSize;
    }
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    public String getSinger() {
        return this.singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
