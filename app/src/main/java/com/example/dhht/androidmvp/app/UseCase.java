package com.example.dhht.androidmvp.app;

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    //请求参数
    private Q mRequestValues;

    //返回监听
    private UseCaseCallback<P> mUseCaseCallback;

    //执行业务
    void run() {
        executeUseCase(mRequestValues);
    }

    //执行的具体方法
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
    //返回监听
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
