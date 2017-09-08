package com.msr.nbmusic.api.error;

import com.msr.nbmusic.bean.base.BaseResBean;

import io.reactivex.functions.Function;


/**
 * 对请求的返回结果进行分析(转换map)
 *
 * @param <T>
 * @author Ymmmsick
 * @date 2017-05-12 16:58:10
 */
public class HandleFuc<T> implements Function<BaseResBean<T>, T> {
    @Override
    public T apply(BaseResBean<T> response) throws Exception {
        //response中code码不为0 出现错误
        if (!response.isSuccess()) {
            //服务端返回需要重新登录的status
//            if (UserStatus.isNeedReLogin(response) && !(NBAppManager.getInstance().currentActivity() instanceof LoginActivity)) {
//                Logger.e("Server request user relogin!");
//                //如果当前不是登录界面,弹出重新登录对话框
//                DialogHelper.openReloginDialog(LLAppManager.getInstance().currentActivity());
//            }
            String message = response.getShowapi_res_error() != null ? response.getShowapi_res_error() : "ErrorData unknow";
            ServerException serverException = new ServerException();
            serverException.message = message;
            throw serverException;
        }
        return response.getShowapi_res_body();
    }
}