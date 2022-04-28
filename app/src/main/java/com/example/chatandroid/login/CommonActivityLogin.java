package com.example.chatandroid.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.chatandroid.MainActivity;
import com.example.chatandroid.R;
import com.example.chatandroid.base.BaseActivity;
import com.example.chatandroid.databinding.ActivityLoginBinding;
import com.example.chatandroid.utils.ViewUtils;


public class CommonActivityLogin extends BaseActivity<ActivityLoginBinding> {

    private LoginModel model;
    private boolean isLoginEmail = true;

    @Override
    protected void initActivity() {
        model = getViewModel(LoginModel.class);
        model.startViewModel();
        ViewUtils.setupUI(binding.activityMain, this);
        checkLoginAndRegister();
        binding.submit.setOnClickListener(view -> {
            binding.progressBarCyclic.setVisibility(View.VISIBLE);
            model.createAccount(
                    binding.userName.getText().toString(),

                    binding.email.getText().toString(),
                    binding.password.getText().toString());
//            if (!isLoginEmail) {
//                model.createAccount(
//                        binding.userName.getText().toString(),
//
//                        binding.email.getText().toString(),
//                        binding.password.getText().toString());
//            } else {
//                model.loginAccount(
//                        binding.email.getText().toString(),
//                        binding.password.getText().toString());
//            }
        });
    }

    private void checkLoginAndRegister() {
        binding.submit.setText(isLoginEmail ? "Đăng Nhập" : "Tạo tài khoản");
//        binding.userName.setVisibility(isLoginEmail ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void initLiveData() {
        model.getIsRegisterAccount().observe(this, aBoolean -> {
            binding.progressBarCyclic.setVisibility(View.GONE);
            if (aBoolean) {
                Toast.makeText(CommonActivityLogin.this, "Đăng kí tài khoản thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CommonActivityLogin.this, "Đăng kí tài khoản thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        model.getIsLogin().observe(this, aBoolean -> {
            binding.progressBarCyclic.setVisibility(View.GONE);
            if (aBoolean) {
                Intent intent = new Intent(CommonActivityLogin.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(CommonActivityLogin.this, "login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

}