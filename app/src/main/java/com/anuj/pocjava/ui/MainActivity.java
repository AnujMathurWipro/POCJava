package com.anuj.pocjava.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import com.anuj.pocjava.R;
import com.anuj.pocjava.databinding.ActivityMainBinding;
import com.anuj.pocjava.ui.fragment.MainScreenFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        addMainFragment();
    }

    private void addMainFragment() {
        MainScreenFragment frag = MainScreenFragment.newInstance(null);
        addFragment(frag, binding.flFragmentContainer.getId());
    }

    private void addFragment(MainScreenFragment frag, int id) {
        addFragment(frag, id, false);
    }

    private void addFragment(MainScreenFragment frag, int id, boolean addToBackStack) {
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(id, frag, frag.getClass().getSimpleName());
        if (addToBackStack)
            fragTransaction.addToBackStack(null);
        fragTransaction.commit();
    }

    public void setScreenTitle(String title) {
        binding.toolbar.setTitle(title);
    }

}
