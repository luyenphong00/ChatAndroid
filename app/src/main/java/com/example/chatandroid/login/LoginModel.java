package com.example.chatandroid.login;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chatandroid.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luyenphong on 19/03/2021
 * 0358844343
 */
public class LoginModel extends ViewModel {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private MutableLiveData<Boolean> isRegisterAccount= new MutableLiveData<>();
    private MutableLiveData<Boolean> isLogin = new MutableLiveData<>();
    private MutableLiveData<UserModel> account = new MutableLiveData<>();
    private FirebaseUser firebaseUser;
    private MutableLiveData<List<UserModel>> listUserLiveData = new MutableLiveData<>();

    public void startViewModel(){
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void createAccount(final String userName, String email, String passWord){
        firebaseAuth.createUserWithEmailAndPassword(email, passWord)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        firebaseUser = firebaseAuth.getCurrentUser();
                        String userID = firebaseUser.getUid();
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("id", userID);
                        hashMap.put("username", userName);
                        hashMap.put("imageURL", "default");

                        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(userID);
                        databaseReference.setValue(hashMap).addOnCompleteListener(task1 -> isRegisterAccount.postValue(task1.isSuccessful()));

                    }else {
                        Log.d("AAAAAAAAAAAAAA", " failed");
                    }
                });
    }

    public void loginAccount(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> isLogin.postValue(task.isSuccessful()));
    }

    public void getDataUser(){
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                account.postValue(userModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getListDataUser(){
        firebaseUser = firebaseAuth.getCurrentUser();
        final List<UserModel> userModels = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    assert userModel != null;
                    if (!userModel.getId().equals(firebaseUser.getUid())){
                        userModels.add(userModel);
                    }
                }
                listUserLiveData.postValue(userModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public MutableLiveData<List<UserModel>> getListUserLiveData() {
        return listUserLiveData;
    }

    public MutableLiveData<Boolean> getIsRegisterAccount() {
        return isRegisterAccount;
    }

    public MutableLiveData<Boolean> getIsLogin() {
        return isLogin;
    }

    public MutableLiveData<UserModel> getAccount() {
        return account;
    }
}
