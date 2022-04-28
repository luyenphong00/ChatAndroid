package com.example.chatandroid.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by luyenphong on 19/03/2021
 * 0358844343
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    protected T binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false);
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragment();
        initLiveData();
        initAdapter();
    }

    protected abstract int getLayoutResource();

    protected abstract void initFragment();

    protected abstract void initAdapter();

    protected abstract void initLiveData();

    protected <OUT extends ViewModel>  OUT getViewModel(Class<? extends ViewModel> viewModelType) {
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        OUT vm = (OUT) new ViewModelProvider(this, factory).get(viewModelType);
        return vm;
    }
}
