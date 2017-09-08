package com.msr.nbmusic.api.error;

import com.msr.nbmusic.bean.base.BaseResponseBean;
import com.orhanobut.logger.Logger;

/**
 * Created by Ymmmsick on 5/15/17.
 */

public class UserStatus {

    public static boolean isNeedReLogin(BaseResponseBean bean) {
        String code = bean.getErrorData().getCode();
        //服务端返回需要重新登录的status
        if ("USER-LOGIN-NOTEXIST".equals(code) || "LOGIN-REQUIRED".equals(code) || "LOGIN-EXPIRED".equals(code)) {
            Logger.e("Server request user relogin!");
            return true;
        }
        return false;
    }

}
