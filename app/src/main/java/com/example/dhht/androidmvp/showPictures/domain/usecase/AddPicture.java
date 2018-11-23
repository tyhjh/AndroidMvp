package com.example.dhht.androidmvp.showPictures.domain.usecase;

import com.example.dhht.androidmvp.app.UseCase;
import com.example.dhht.androidmvp.data.source.local.PictureLocalDataSource;
import com.example.dhht.androidmvp.showPictures.domain.model.Picture;

import java.io.File;

public class AddPicture extends UseCase<AddPicture.RequestValues, AddPicture.ResponseValue> {

    PictureLocalDataSource mLocalDataSource;

    public AddPicture(PictureLocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Picture picture = new Picture(requestValues.getPictureFile());
        mLocalDataSource.savePic(picture);
        getUseCaseCallback().onSuccess(new ResponseValue(picture));
    }

    public static final class RequestValues implements UseCase.RequestValues {
        File pictureFile;

        public RequestValues(File pictureFile) {
            this.pictureFile = pictureFile;
        }

        public File getPictureFile() {
            return pictureFile;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        Picture mPicture;

        public ResponseValue(Picture picture) {
            mPicture = picture;
        }

        public Picture getPicture() {
            return mPicture;
        }
    }
}
