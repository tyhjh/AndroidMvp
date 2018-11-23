package com.example.dhht.androidmvp.showPictures.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;


@Entity(tableName = "pictures")
public class Picture {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pictureid")
    private Long mId;

    @NonNull
    private String name;//名字

    @NonNull
    private String folderName;//文件夹名字

    @Nullable
    private String localPath;//本地地址

    @Nullable
    private String remotePath;//远程地址

    public Picture() {
    }

    public Picture(File file) {
        mId = System.currentTimeMillis();
        name = file.getName();
        localPath = file.getPath();
        folderName = file.getParent();
    }

    @NonNull
    public Long getId() {
        return mId;
    }

    public void setId(@NonNull Long id) {
        mId = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(@NonNull String folderName) {
        this.folderName = folderName;
    }

    @Nullable
    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(@Nullable String localPath) {
        this.localPath = localPath;
    }

    @Nullable
    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(@Nullable String remotePath) {
        this.remotePath = remotePath;
    }

    public String getPicture() {
        if (localPath == null) {
            return remotePath;
        }
        return localPath;
    }

}
