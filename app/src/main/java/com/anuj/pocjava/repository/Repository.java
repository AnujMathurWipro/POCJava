package com.anuj.pocjava.repository;

import androidx.lifecycle.MutableLiveData;

import com.anuj.pocjava.log.Logger;
import com.anuj.pocjava.mainscreen.FetchListService;
import com.anuj.pocjava.models.BaseResult;
import com.anuj.pocjava.models.Response;
import com.anuj.pocjava.util.StringConstants;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    public void getMainScreenList(MutableLiveData<BaseResult<Response>> liveData) {
        Retrofit retrofit = createRetrofit(StringConstants.API_BASE_URL);
        FetchListService service = retrofit.create(FetchListService.class);
        Call<Response> call = service.getList();
        getResponseFromServer(call, liveData);
    }

    private <T> void getResponseFromServer(Call<T> call,
                                           MutableLiveData<BaseResult<T>> liveData) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String url = call.request().url().toString();
                    Logger.debugLog("Url = " + url);
                    handleResponse(call.execute(), liveData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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
