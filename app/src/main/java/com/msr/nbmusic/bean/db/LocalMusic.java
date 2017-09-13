package com.msr.nbmusic.bean.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

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
    private Long fileSize;
    @Property(nameInDb = "singer")
    private String singer;
    @Property(nameInDb = "duration")
    private int duration;
    @Property(nameInDb = "filePath")
    private String filePath;
    @Generated(hash = 478028767)
    public LocalMusic(Long id, String fileName, String fileType, String summary,
            Long fileSize, String singer, int duration, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.summary = summary;
        this.fileSize = fileSize;
        this.singer = singer;
        this.duration = duration;
        this.filePath = filePath;
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
    public Long getFileSize() {
        return this.fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public String getSinger() {
        return this.singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getFilePath() {
        return this.filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
