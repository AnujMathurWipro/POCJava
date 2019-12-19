package com.anuj.pocjava.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.anuj.pocjava.repository.Repository;
import com.anuj.pocjava.viewmodel.MainScreenViewModel;

public class MainScreenViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new MainScreenViewModel(new MutableLiveData<>(), new Repository());
    }
}
