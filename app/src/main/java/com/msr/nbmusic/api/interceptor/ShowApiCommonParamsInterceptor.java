package com.msr.nbmusic.api.interceptor;

import com.msr.nbmusic.comm.NBConstants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 封装公共参数（Key和密码）
 *
 * @author Ymmmsick
 * @since 2017-09-08 15:32:47
 */
public class ShowApiCommonParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request oldRequest = chain.request();
        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter("showapi_appid", NBConstants.SHOWAPI_APPID)
                .addQueryParameter("showapi_sign", NBConstants.SHOWAPI_SECRET_KEY)
                .addQueryParameter("showapi_sign_method", "md5")
                .addQueryParameter("showapi_res_gzip", "0");
        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(newRequest);
    }
}