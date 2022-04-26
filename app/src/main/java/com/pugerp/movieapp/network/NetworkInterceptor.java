package com.pugerp.movieapp.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder reqBuilder = original.newBuilder().method(original.method(), original.body());

//        reqBuilder = reqBuilder.header("AUTH", "AUTH");
        return chain.proceed(reqBuilder.build());
    }
}
