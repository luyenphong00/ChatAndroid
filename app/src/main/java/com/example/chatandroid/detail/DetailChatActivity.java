package com.example.chatandroid.detail;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.chatandroid.R;
import com.example.chatandroid.adapter.MessageAdapter;
import com.example.chatandroid.base.BaseActivity;
import com.example.chatandroid.databinding.ActivityDetailChatBinding;
import com.example.chatandroid.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

/**
 * Created by luyenphong on 31/03/2021
 * 0358844343
 */
public class DetailChatActivity extends BaseActivity<ActivityDetailChatBinding> {

    public static final String USER_MODEL = "extra_detail_user";
    private UserModel userModel;
    private MessageAdapter messageAdapter;
    private DetailViewModel detailViewModel;
    private FirebaseUser firebaseUser;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail_chat;
    }

    @Override
    protected void initActivity() {
        userModel = getIntent().getParcelableExtra(USER_MODEL);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        binding.setItem(userModel);
        binding.imgBack.setOnClickListener(view -> finish());
        binding.imgSend.setOnClickListener(view -> {
            detailViewModel.sendMessage(firebaseUser.getUid(), userModel.getId(),
                    Objects.requireNonNull(binding.editQuery.getText()).toString().trim());
            binding.editQuery.setText("");
        });

    }

    @Override
    protected void initLiveData() {
        detailViewModel = getViewModel(DetailViewModel.class);
        detailViewModel.startViewModel();
        detailViewModel.readMessage(firebaseUser.getUid(), userModel.getId(), "");
        detailViewModel.getChatMutableLiveData().observe(this, chats -> {
            messageAdapter = new MessageAdapter(this);
            messageAdapter.setChatList(chats);
            binding.rclMessage.setAdapter(messageAdapter);
            binding.rclMessage.scrollToPosition(chats.size() - 1);
        });
    }
}
