package com.anuj.pocjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anuj.pocjava.models.BaseResult;
import com.anuj.pocjava.models.Response;
import com.anuj.pocjava.repository.Repository;
import com.anuj.pocjava.util.Constants;

public class MainScreenViewModel extends ViewModel {

    private MutableLiveData<BaseResult<Response>> result = new MutableLiveData<>();
    private Repository repository = new Repository();

    public LiveData<BaseResult<Response>> getResult() {
        return result;
    }

    public void getResponse(boolean force) {
        if (force || result.getValue() == null)
            repository.getMainScreenList(result);
    }

    public String getErrorMessage() {
        return Constants.INTERNET_CONNECTIVITY_ERROR;
    }

    public boolean isSuccessful(Response res) {
        return res != null && res.getRows() != null && res.getRows().size() > 0;
    }
}
