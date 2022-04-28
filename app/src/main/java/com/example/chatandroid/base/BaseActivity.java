package com.example.chatandroid.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by luyenphong on 19/03/2021
 * 0358844343
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutResource());
        initActivity();
        initLiveData();
    }

    protected abstract int getLayoutResource();

    protected abstract void initActivity();

    protected abstract void initLiveData();

    protected <OUT extends ViewModel>  OUT getViewModel(Class<? extends ViewModel> viewModelType) {
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        OUT vm = (OUT) new ViewModelProvider(this, factory).get(viewModelType);
        return vm;
    }
}
