package com.anuj.pocjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anuj.pocjava.models.BaseResult;
import com.anuj.pocjava.models.Response;
import com.anuj.pocjava.repository.Repository;
import com.anuj.pocjava.util.Constants;

public class MainScreenViewModel extends ViewModel {

    public MainScreenViewModel(MutableLiveData<BaseResult<Response>> data, Repository r) {
        this.result = data;
        repository = r;
    }

    private final MutableLiveData<BaseResult<Response>> result;
    private final Repository repository;

    public LiveData<BaseResult<Response>> getResult() {
        return result;
    }

    public boolean getResponse(boolean force) {
        if (force || result.getValue() == null) {
            repository.getMainScreenList(result);
            return true;
        }
        return false;
    }

    public String getErrorMessage() {
        return Constants.INTERNET_CONNECTIVITY_ERROR;
    }

    public boolean isSuccessful(Response res) {
        return res != null && res.getRows() != null && res.getRows().size() > 0;
    }
}
