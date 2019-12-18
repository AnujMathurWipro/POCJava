package com.anuj.pocjava.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anuj.pocjava.R;
import com.anuj.pocjava.adapter.MainScreenListAdapter;
import com.anuj.pocjava.databinding.FragmentMainBinding;
import com.anuj.pocjava.ui.MainActivity;
import com.anuj.pocjava.util.Utility;
import com.anuj.pocjava.viewmodel.MainScreenViewModel;

public class MainScreenFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainScreenViewModel viewModel;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel.class);
        observeFields();
    }

    private void observeFields() {
        viewModel.getResult().observe(this, it -> {
            if(viewModel.isSuccessful(it.getResponse())) {
                removeErrorMessage();
                ((MainScreenListAdapter)binding.rvItemList.getAdapter()).setListItems(it.getResponse().getRows());
                ((MainActivity)getActivity()).setScreenTitle(it.getResponse().getTitle());
            } else {
                setErrorMessage(viewModel.getErrorMessage());
            }
            binding.srlSwipeRefresh.setRefreshing(false);
        });
    }

    private void setErrorMessage(String message) {
        binding.tvErrorText.setText(message);
        binding.tvErrorText.setVisibility(View.VISIBLE);
    }

    private void removeErrorMessage() {
        binding.tvErrorText.setVisibility(View.GONE);
        binding.tvErrorText.setText("");
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getResponseFromServer(false);
        binding.srlSwipeRefresh.setOnRefreshListener(() -> getResponseFromServer(true));
        binding.rvItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvItemList.setAdapter(new MainScreenListAdapter(LayoutInflater.from(getActivity()), null));
    }

    private void getResponseFromServer(boolean force) {
        if(Utility.isNetworkAvailable(getActivity())) {
            binding.srlSwipeRefresh.setRefreshing(true);
            viewModel.getResponse(force);
        } else
            setErrorMessage("It seems like internet is not available. Please connect and try again.");
    }


    public static MainScreenFragment newInstance(Bundle args) {
        MainScreenFragment instance = new MainScreenFragment();
        if(args != null)
            instance.setArguments(args);
        return instance;
    }

}
