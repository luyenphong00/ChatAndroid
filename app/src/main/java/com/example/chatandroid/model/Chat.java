package com.example.chatandroid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by luyenphong on 31/03/2021
 * 0358844343
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Chat {
    private String sender; // user send
    private String receiver; // user read
    private String message;
}
