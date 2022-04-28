package com.example.chatandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatandroid.R;
import com.example.chatandroid.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by luyenphong on 31/03/2021
 * 0358844343
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final int MESSAGE_YOU = 0;
    private static final int MESSAGE_THEY = 1;

    private Context context;
    private List<Chat> chatList;
    private LayoutInflater inflater;
    private String imgUrl;
    private FirebaseUser firebaseUser;

    public MessageAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // b2 : setting item view
        View view;
        if (viewType == MESSAGE_YOU){
            view = inflater.inflate(R.layout.item_message_you,parent,false);
        }else {
            view = inflater.inflate(R.layout.item_message_they,parent,false);
        }
        return new ViewHolder(view);
    }

    // b1 : Setting adapter
    @Override
    public int getItemViewType(int position) {
        // switch type item
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (chatList.get(position).getSender().equals(firebaseUser.getUid())) {
            return MESSAGE_YOU;
        }else {
            return MESSAGE_THEY;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.binData(chat);
    }

    @Override
    public int getItemCount() {
        return chatList != null ? chatList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }

        public void binData(Chat chat){
            tvMessage.setText(chat.getMessage());
        }
    }
}
