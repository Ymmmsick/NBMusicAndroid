package com.msr.nbmusic.api.error;


import com.msr.nbmusic.bean.base.BaseResponseBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;

/**
 * 此方法用于RxJava zip操作符，两个请求同时发出，返回的数据进行转换成List
 * 适用场景：两个HTTP请求同时发出
 * 优点：能够准确捕捉两个请求结束的时间，从而进行回调
 * Created by Ymmmsick on 5/15/17.
 */
public class LLBiFunction implements BiFunction<BaseResponseBean, BaseResponseBean, List> {

    @Override
    public List apply(@NonNull BaseResponseBean baseResponseBean, @NonNull BaseResponseBean baseResponseBean2) throws Exception {
        boolean res1 = baseResponseBean.isSuccess();
        boolean res2 = baseResponseBean2.isSuccess();
        if (!res1 || !res2) {
            //服务端返回需要重新登录的status
            if (UserStatus.isNeedReLogin(baseResponseBean) || UserStatus.isNeedReLogin(baseResponseBean2)) {
                //弹出重新登录对话框
//                DialogHelper.openReloginDialog(LLAppManager.getInstance().currentActivity());
            }
            String message;
            if (!res1)
                message = baseResponseBean.getErrorData().getMessage() != null ? baseResponseBean.getErrorData().getMessage() : "ErrorData unknow";
            else
                message = baseResponseBean2.getErrorData().getMessage() != null ? baseResponseBean2.getErrorData().getMessage() : "ErrorData unknow";
            ServerException serverException = new ServerException();
            serverException.message = message;
            throw serverException;
        }
        List list = new ArrayList();
        list.add(baseResponseBean.getData());
        list.add(baseResponseBean2.getData());
        return list;
    }
}
