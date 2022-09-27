package com.ema.app.controller;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AddressEndpoint {
    @GET("/address/{cep}")
    @Headers("accept: application/json")
    CompletableFuture<Address> getAddress(@Path("cep") String cep);

    @POST("/address/info")
    CompletableFuture<Info> getInfo();
}
