package com.msr.nbmusic.api.error;


import com.msr.nbmusic.bean.base.BaseResBean;

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
public class LLBiFunction implements BiFunction<BaseResBean, BaseResBean, List> {

    @Override
    public List apply(@NonNull BaseResBean baseResBean, @NonNull BaseResBean baseResBean2) throws Exception {
        boolean res1 = baseResBean.isSuccess();
        boolean res2 = baseResBean2.isSuccess();
        if (!res1 || !res2) {
            //服务端返回需要重新登录的status
//            if (UserStatus.isNeedReLogin(baseResBean) || UserStatus.isNeedReLogin(baseResBean2)) {
            //弹出重新登录对话框
//                DialogHelper.openReloginDialog(LLAppManager.getInstance().currentActivity());
//            }
            String message;
            if (!res1)
                message = baseResBean.getRet_message() != null ? baseResBean.getRet_message() : "ErrorData unknow";
            else
                message = baseResBean2.getRet_message() != null ? baseResBean2.getRet_message() : "ErrorData unknow";
            ServerException serverException = new ServerException();
            serverException.message = message;
            throw serverException;
        }
        List list = new ArrayList();
        list.add(baseResBean.getData());
        list.add(baseResBean2.getData());
        return list;
    }
}
