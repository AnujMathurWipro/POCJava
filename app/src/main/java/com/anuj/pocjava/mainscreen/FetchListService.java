package com.anuj.pocjava.mainscreen;

import com.anuj.pocjava.models.Response;
import com.anuj.pocjava.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchListService {
    @GET(Constants.API_URL)
    Call<Response> getList();
}
