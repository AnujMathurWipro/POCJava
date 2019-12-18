package com.anuj.pocjava;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.anuj.pocjava.ui.fragment.MainScreenFragment;

public class MainScreenFragmentFactory extends FragmentFactory {
    private MainScreenFragment.IdlingCallback callback;
    public MainScreenFragmentFactory(MainScreenFragment.IdlingCallback cb) {
        callback = cb;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        MainScreenFragment f = new MainScreenFragment(callback);
        return f;
    }
}
