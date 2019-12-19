package com.anuj.pocjava.repository;

import androidx.lifecycle.MutableLiveData;

import com.anuj.pocjava.log.Logger;
import com.anuj.pocjava.mainscreen.FetchListService;
import com.anuj.pocjava.models.BaseResult;
import com.anuj.pocjava.models.Response;
import com.anuj.pocjava.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    public void getMainScreenList(MutableLiveData<BaseResult<Response>> liveData) {
        Retrofit retrofit = createRetrofit(Constants.API_BASE_URL);
        FetchListService service = retrofit.create(FetchListService.class);
        Call<Response> call = service.getList();
        getResponseFromServer(call, liveData);
    }

    private <T> void getResponseFromServer(Call<T> call,
                                           MutableLiveData<BaseResult<T>> liveData) {
        String url = call.request().url().toString();
        Logger.debugLog("Url = " + url);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NotNull Call<T> call, @NotNull retrofit2.Response<T> response) {
                Logger.debugLog("API Response----\n" + response.toString());
                handleResponse(response, liveData);
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
                retrofit2.Response<T> response = retrofit2.Response.error(0, ResponseBody.create(MediaType.get("application/json"), (String)null));
                handleResponse(response, liveData);
            }
        });
    }

    private <T> void handleResponse(retrofit2.Response<T> result,
                                    MutableLiveData<BaseResult<T>> liveData) {

        BaseResult value = new BaseResult<T>();
        if (result.isSuccessful()) {
            T res = result.body();
            value.setResponse(res);
        }
        liveData.postValue(value);
    }

    private Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
