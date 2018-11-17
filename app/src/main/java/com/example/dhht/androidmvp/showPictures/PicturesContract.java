package com.example.dhht.androidmvp.showPictures;

import com.example.dhht.androidmvp.app.BasePresenter;
import com.example.dhht.androidmvp.app.BaseView;
import com.example.dhht.androidmvp.data.Picture;

import java.util.List;

public interface PicturesContract {

    interface View extends BaseView {
        //显示图片、刷新列表
        void showPic(List<Picture> pictureList);
        //删除本地图片成功
        void deletePic(int position);
        //添加本地图片
        void addPic(Picture picture);
    }
    interface Presenter extends BasePresenter {
        //获取本地图片
        void getLocalPic();
        //删除本地图片
        void deletePic(Picture picture);
        //添加本地图片
        void addPic();
    }
}
