package com.example.dhht.androidmvp.data.source.local;

import com.example.dhht.androidmvp.data.source.PictureDataSource;
import com.example.dhht.androidmvp.showPictures.domain.model.Picture;
import com.example.dhht.androidmvp.util.AppExecutors;

import java.util.List;

public class PictureLocalDataSource implements PictureDataSource {

    private static volatile PictureLocalDataSource INSTANCE;

    private PictureDao mPictureDao;

    private AppExecutors mAppExecutors;

    private PictureLocalDataSource(AppExecutors appExecutors, PictureDao pictureDao) {
        mPictureDao = pictureDao;
        mAppExecutors = appExecutors;
    }

    public static PictureLocalDataSource getInstance(AppExecutors appExecutors, PictureDao pictureDao) {
        if (INSTANCE == null) {
            synchronized (PictureLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PictureLocalDataSource(appExecutors, pictureDao);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getPics(final GetPicsCallback getPicsCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Picture> pictureList = mPictureDao.getPics();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        getPicsCallback.onPicGet(pictureList);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getPic(final Long picId, final GetPicCallback getPicCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Picture picture = mPictureDao.getPicById(picId);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        getPicCallback.onPicGet(picture);
                    }
                });
            }
        };
    }

    @Override
    public void deletePic(final Long picId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mPictureDao.deletePicById(picId);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteAllPics() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mPictureDao.deleteAllPic();
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void savePic(final Picture picture) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mPictureDao.insertPic(picture);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updatePic(final Picture picture) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mPictureDao.updataPic(picture);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

}
