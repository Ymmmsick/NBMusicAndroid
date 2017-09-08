package com.msr.nbmusic.api.error;


import com.msr.nbmusic.bean.base.BaseResBean;

import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 此方法用于RxJava flatmap操作符，两个请求顺序发出，map进行转换
 * Created by Ymmmsick on 5/18/17.
 */

public abstract class LLFunction<L, R> implements Function<BaseResBean<L>, ObservableSource<R>> {

    @Override
    public ObservableSource<R> apply(@NonNull BaseResBean<L> lBaseResBean) throws Exception {
        if (!lBaseResBean.isSuccess()) {
            //服务端返回需要重新登录的status
//            if (UserStatus.isNeedReLogin(baseResBean) || UserStatus.isNeedReLogin(baseResBean2)) {
            //弹出重新登录对话框
//                DialogHelper.openReloginDialog(LLAppManager.getInstance().currentActivity());
//            }
            String message = lBaseResBean.getRet_message() != null ? lBaseResBean.getRet_message() : "ErrorData unknow";
            ServerException serverException = new ServerException();
            serverException.message = message;
            throw serverException;
        }
        return applyL(lBaseResBean);
    }

    public abstract ObservableSource<R> applyL(@NonNull BaseResBean<L> baseResBean) throws Exception;
}
