package com.ema.app.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class I {

    public static OkHttpClient client_auth(ObjectNode auth, String method) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                            Request request = null;
                            if (method.equals("GET")) {
                                Request originalRequest = chain.request();
                                HttpUrl newUrl = originalRequest.url().newBuilder()
                                        .addQueryParameter("api_id", auth.get("api_id").asText())
                                        .addQueryParameter("api_secret", auth.get("api_secret").asText())
                                        .build();

                                request = originalRequest.newBuilder().url(newUrl).build();
                            } else if (method.equals("POST")) {
                                Request originalRequest = chain.request();
                                HttpUrl newUrl = originalRequest.url().newBuilder().build();
                                RequestBody newBody = RequestBody.create(auth.toString(),MediaType.parse("application/json"));

                                request = originalRequest.newBuilder().url(newUrl).post(newBody).build();
                            }
                            return chain.proceed(request);
                        }).build();
    }
}