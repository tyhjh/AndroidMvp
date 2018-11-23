package com.example.dhht.androidmvp.showPictures;

import com.example.dhht.androidmvp.app.UseCase;
import com.example.dhht.androidmvp.app.UseCaseHandler;
import com.example.dhht.androidmvp.app.UseCaseThreadPoolScheduler;
import com.example.dhht.androidmvp.data.source.local.AppDataBase;
import com.example.dhht.androidmvp.data.source.local.PictureLocalDataSource;
import com.example.dhht.androidmvp.showPictures.domain.model.Picture;
import com.example.dhht.androidmvp.showPictures.domain.usecase.AddPicture;
import com.example.dhht.androidmvp.showPictures.domain.usecase.DeletePicture;
import com.example.dhht.androidmvp.showPictures.domain.usecase.GetPictures;
import com.example.dhht.androidmvp.util.AppExecutors;
import com.yorhp.picturepick.OnPickListener;
import com.yorhp.picturepick.PicturePickUtil;

import java.io.File;

public class PicturePresenter implements PicturesContract.Presenter {

    PicturesActivity mPicturesActivity;
    GetPictures mGetPictures;
    DeletePicture mDeletePicture;
    AddPicture mAddPicture;
    private final UseCaseHandler mUseCaseHandler;


    public PicturePresenter(PicturesContract.View view) {
        mPicturesActivity = (PicturesActivity) view;
        PictureLocalDataSource mLocalDataSource = PictureLocalDataSource.getInstance(new AppExecutors(),
                AppDataBase.getInstance(mPicturesActivity).pictureDao());
        mGetPictures = new GetPictures(mLocalDataSource);
        mDeletePicture = new DeletePicture(mLocalDataSource);
        mAddPicture=new AddPicture(mLocalDataSource);
        mUseCaseHandler = new UseCaseHandler(new UseCaseThreadPoolScheduler());
    }

    @Override
    public void getLocalPic() {

        mUseCaseHandler.execute(mGetPictures, new GetPictures.RequestValues(), new UseCase.UseCaseCallback<GetPictures.ResponseValue>() {
            @Override
            public void onSuccess(GetPictures.ResponseValue response) {
                mPicturesActivity.showPic(response.getPictures());
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void deletePic(Picture picture) {
        DeletePicture.RequestValues requestValues = new DeletePicture.RequestValues(picture.getId());
        mUseCaseHandler.execute(mDeletePicture, requestValues, new UseCase.UseCaseCallback<DeletePicture.ResponseValue>() {
            @Override
            public void onSuccess(DeletePicture.ResponseValue response) {

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void addPic() {
        PicturePickUtil.setCreatNewFile(false);
        PicturePickUtil.pick(mPicturesActivity, new OnPickListener() {
            @Override
            public void pickPicture(File file) {
                AddPicture.RequestValues requestValues=new AddPicture.RequestValues(file);
                mUseCaseHandler.execute(mAddPicture, requestValues, new UseCase.UseCaseCallback<AddPicture.ResponseValue>() {
                    @Override
                    public void onSuccess(AddPicture.ResponseValue response) {
                        mPicturesActivity.addPic(response.getPicture());
                    }

                    @Override
                    public void onError() {

                    }
                });

            }
        });
    }

}
