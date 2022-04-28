package com.example.chatandroid.fragment;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.widget.SearchView;

import com.example.chatandroid.R;
import com.example.chatandroid.adapter.FriendAdapter;
import com.example.chatandroid.base.BaseFragment;
import com.example.chatandroid.databinding.FragmentFriendBinding;
import com.example.chatandroid.detail.DetailChatActivity;
import com.example.chatandroid.model.UserModel;
import com.example.chatandroid.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyenphong on 30/03/2021
 * 0358844343
 */
public class FragmentFriends extends BaseFragment<FragmentFriendBinding> implements FriendAdapter.itemListener {

    private List<UserModel> userModels = new ArrayList<>();
    private FriendAdapter friendAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void initFragment() {
        assert getArguments() != null;
        ViewUtils.setupUI(binding.friend,getActivity());
        userModels = getArguments().getParcelableArrayList("abc");
        Log.d("sssssssssssssssss", userModels.size() + "");
        if (userModels != null && !userModels.isEmpty()){
            friendAdapter = new FriendAdapter(getContext(),userModels,this);
            binding.rclFriend.setAdapter(friendAdapter);
        }
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                friendAdapter.setUserModels(getListSearchArtist(newText,userModels));
                return false;
            }
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initLiveData() {

    }

    public List<UserModel> getListSearchArtist(String textQuery, List<UserModel> userModels) {
        List<UserModel> listAlbumSearch = new ArrayList<>();
        for (UserModel movies : userModels) {
            if (movies.getUsername().toLowerCase().contains(textQuery.toLowerCase())) {
                listAlbumSearch.add(movies);
            }
        }
        return listAlbumSearch;
    }

    @Override
    public void onCLickItem(UserModel userModel) {
        Intent intent = new Intent(getContext(), DetailChatActivity.class);
        intent.putExtra(DetailChatActivity.USER_MODEL,userModel);
        startActivity(intent);
    }
}
