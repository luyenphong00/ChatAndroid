package com.example.chatandroid.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by luyenphong on 19/03/2021
 * 0358844343
 */
public class Utils {
    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
