package com.example.dhht.androidmvp.data.source;

import com.example.dhht.androidmvp.data.Picture;

public class PictureRepository implements PictureDataSource {


    private static PictureDataSource instance;

    private PictureRepository() {

    }




    @Override
    public void getPics(GetPicsCallback getPicsCallback) {

    }

    @Override
    public void getPic(Long picId, GetPicCallback getPicCallback) {

    }

    @Override
    public void deletePic(Long picId) {

    }

    @Override
    public void deleteAllPics() {

    }

    @Override
    public void savePic(Picture picture) {

    }

    @Override
    public void updatePic(Picture picture) {

    }


}
