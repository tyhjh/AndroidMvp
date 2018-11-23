package com.example.dhht.androidmvp.app;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    /**
     * 请求参数
     */
    public interface RequestValues {

    }

    /**
     * 返回参数
     */
    public interface ResponseValue {

    }

    public interface UseCaseCallback<R> {
        void onSuccess(R response);

        void onError();
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }
}
