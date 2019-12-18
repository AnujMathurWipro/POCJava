package com.anuj.pocjava.mainscreen;

import com.anuj.pocjava.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchListService {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<Response> getList();
}
