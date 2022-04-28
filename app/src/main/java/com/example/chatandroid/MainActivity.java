package com.example.chatandroid;

import android.os.Bundle;
import android.os.Parcelable;

import com.example.chatandroid.adapter.ViewPagerAdapter;
import com.example.chatandroid.base.BaseActivity;
import com.example.chatandroid.databinding.ActivityMainBinding;
import com.example.chatandroid.fragment.FragmentChats;
import com.example.chatandroid.fragment.FragmentFriends;
import com.example.chatandroid.login.LoginModel;

import java.util.ArrayList;

/**
 * Created by luyenphong on 21/03/2021
 * 0358844343
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private LoginModel loginModel;
    FragmentFriends fragmentFriends = new FragmentFriends();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initActivity() {
        loginModel = getViewModel(LoginModel.class);
        loginModel.startViewModel();
        loginModel.getDataUser();
        loginModel.getListDataUser();
    }

    @Override
    protected void initLiveData() {
        loginModel.getAccount().observe(this, userModel -> {
            if (userModel != null) {
                binding.userAccount.setText(userModel.getUsername());
            }
        });

        loginModel.getListUserLiveData().observe(this, userModels -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("abc", (ArrayList<? extends Parcelable>) userModels);
            fragmentFriends.setArguments(bundle);
            setupViewPager();
            binding.tabs.setupWithViewPager(binding.viewpager);
        });
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragmentFriends, "Friends");
        adapter.addFragment(new FragmentChats(), "Chats");
        binding.viewpager.setAdapter(adapter);
    }
}
