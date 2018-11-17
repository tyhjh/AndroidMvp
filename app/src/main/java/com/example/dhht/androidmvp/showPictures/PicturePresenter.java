package com.example.dhht.androidmvp.showPictures;

import com.example.dhht.androidmvp.data.Picture;
import com.example.dhht.androidmvp.data.source.PictureDataSource;
import com.example.dhht.androidmvp.data.source.local.AppDataBase;
import com.example.dhht.androidmvp.data.source.local.PictureLocalDataSource;
import com.example.dhht.androidmvp.util.AppExecutors;
import com.yorhp.picturepick.OnPickListener;
import com.yorhp.picturepick.PicturePickUtil;

import java.io.File;
import java.util.List;

public class PicturePresenter implements PicturesContract.Presenter {

    PictureLocalDataSource mLocalDataSource;
    PicturesActivity mPicturesActivity;

    public PicturePresenter(PicturesContract.View view) {

        mPicturesActivity = (PicturesActivity) view;
        mLocalDataSource = PictureLocalDataSource.getInstance(new AppExecutors(),
                AppDataBase.getInstance(mPicturesActivity).pictureDao());
    }

    @Override
    public void getLocalPic() {
        mLocalDataSource.getPics(new PictureDataSource.GetPicsCallback() {
            @Override
            public void onPicGet(List<Picture> pictureList) {
                mPicturesActivity.showPic(pictureList);
            }
        });
    }

    @Override
    public void deletePic(Picture picture) {
        mLocalDataSource.deletePic(picture.getId());
    }

    @Override
    public void addPic() {
        PicturePickUtil.setCreatNewFile(false);
        PicturePickUtil.pick(mPicturesActivity, new OnPickListener() {
            @Override
            public void pickPicture(File file) {
                Picture picture = new Picture(file);
                mLocalDataSource.savePic(picture);
                mPicturesActivity.addPic(picture);
            }
        });
    }

}
