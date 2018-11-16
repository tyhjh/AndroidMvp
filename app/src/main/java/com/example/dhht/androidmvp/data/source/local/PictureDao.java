package com.example.dhht.androidmvp.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dhht.androidmvp.data.Picture;

import java.util.List;

@Dao
public interface PictureDao {

    /**
     * 获取所有图片
     *
     * @return
     */
    @Query("SELECT * FROM Pictures ORDER BY pictureid DESC")
    List<Picture> getPics();

    /**
     * 根据id获取图片
     *
     * @param picId
     * @return
     */
    @Query("SELECT * FROM Pictures WHERE pictureid= :picId")
    Picture getPicById(Long picId);

    /**
     * 更新图片
     *
     * @param picture
     * @return
     */
    @Update
    int updataPic(Picture picture);

    /**
     * 插入图片
     *
     * @param picture
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPic(Picture picture);

    /**
     * 删除所有图片
     */
    @Query("DELETE FROM Pictures")
    void deleteAllPic();

    /**
     * 删除图片byID
     */
    @Query("DELETE FROM Pictures WHERE pictureid=:picId")
    void deletePicById(Long picId);


}
