package com.example.dhht.androidmvp.showPictures.domain.usecase;

import com.example.dhht.androidmvp.app.UseCase;
import com.example.dhht.androidmvp.data.source.PictureDataSource;
import com.example.dhht.androidmvp.data.source.local.PictureLocalDataSource;
import com.example.dhht.androidmvp.showPictures.domain.model.Picture;

import java.util.List;

public class GetPictures extends UseCase<GetPictures.RequestValues, GetPictures.ResponseValue> {

    PictureLocalDataSource mLocalDataSource;

    public GetPictures(PictureLocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mLocalDataSource.getPics(new PictureDataSource.GetPicsCallback() {
            @Override
            public void onPicGet(List<Picture> pictureList) {
                ResponseValue responseValue = new ResponseValue(pictureList);
                getUseCaseCallback().onSuccess(responseValue);
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<Picture> mPictures;

        public ResponseValue(List<Picture> pictures) {
            mPictures = pictures;
        }

        public List<Picture> getPictures() {
            return mPictures;
        }
    }

}
