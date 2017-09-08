package com.msr.nbmusic.api.error;


import com.msr.nbmusic.bean.base.BaseResponseBean;

import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 此方法用于RxJava flatmap操作符，两个请求顺序发出，map进行转换
 * Created by Ymmmsick on 5/18/17.
 */

public abstract class LLFunction<L, R> implements Function<BaseResponseBean<L>, ObservableSource<R>> {

    @Override
    public ObservableSource<R> apply(@NonNull BaseResponseBean<L> lBaseResponseBean) throws Exception {
        if (!lBaseResponseBean.isSuccess()) {
            //服务端返回需要重新登录的status
            if (UserStatus.isNeedReLogin(lBaseResponseBean)) {
                //弹出重新登录对话框
//                DialogHelper.openReloginDialog(LLAppManager.getInstance().currentActivity());
            }
            String message = lBaseResponseBean.getErrorData().getMessage() != null ? lBaseResponseBean.getErrorData().getMessage() : "ErrorData unknow";
            ServerException serverException = new ServerException();
            serverException.message = message;
            throw serverException;
        }
        return applyL(lBaseResponseBean);
    }

    public abstract ObservableSource<R> applyL(@NonNull BaseResponseBean<L> baseResponseBean) throws Exception;
}
