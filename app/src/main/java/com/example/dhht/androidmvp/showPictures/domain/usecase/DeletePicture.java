package com.example.dhht.androidmvp.showPictures.domain.usecase;

import com.example.dhht.androidmvp.app.UseCase;
import com.example.dhht.androidmvp.data.source.local.PictureLocalDataSource;

public class DeletePicture extends UseCase<DeletePicture.RequestValues, DeletePicture.ResponseValue> {

    PictureLocalDataSource mLocalDataSource;

    public DeletePicture(PictureLocalDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mLocalDataSource.deletePic(requestValues.getPictureId());
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static final class RequestValues implements UseCase.RequestValues {
        Long pictureId;

        public RequestValues(Long pictureId) {
            this.pictureId = pictureId;
        }

        public Long getPictureId() {
            return pictureId;
        }
    }


    public static final class ResponseValue implements UseCase.ResponseValue {

    }

}
