package com.example.dhht.androidmvp.data.source;

import com.example.dhht.androidmvp.data.Picture;

import java.util.List;

/**
 * 对图片进行操作
 */
public interface PictureDataSource {

    interface GetPicCallback {
        void onPicGet(Picture picture);
    }

    interface GetPicsCallback {
        void onPicGet(List<Picture> pictureList);
    }

    //获取所有图片
    void getPics(GetPicsCallback getPicsCallback);

    //获取一张图片
    void getPic(Long picId, GetPicCallback getPicCallback);

    //删除一张图片
    void deletePic(Long picId);

    //删除所有图片
    void deleteAllPics();

    //保存图片
    void savePic(Picture picture);

    //更新图片
    void updatePic(Picture picture);
}
