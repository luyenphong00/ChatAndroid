package com.example.chatandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatandroid.R;
import com.example.chatandroid.model.UserModel;

import java.util.List;

/**
 * Created by luyenphong on 30/03/2021
 * 0358844343
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private Context context;
    private List<UserModel> userModels;
    private itemListener listener;

    public FriendAdapter(Context context, List<UserModel> userModels, itemListener listener) {
        this.context = context;
        this.userModels = userModels;
        this.listener = listener;
    }

    public void setUserModels(List<UserModel> userModels) {
        this.userModels = userModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = userModels.get(position);
        holder.binData(userModel);
        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onCLickItem(userModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModels != null ? userModels.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvNameFriend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameFriend = itemView.findViewById(R.id.name_friend);
        }

        public void binData(UserModel userModel) {
            tvNameFriend.setText(userModel.getUsername());
        }
    }

    public interface itemListener {
        void onCLickItem(UserModel userModel);
    }
}
