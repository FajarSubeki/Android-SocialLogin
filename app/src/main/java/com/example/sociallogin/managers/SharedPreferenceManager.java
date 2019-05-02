package com.example.sociallogin.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.sociallogin.R;
import com.example.sociallogin.constants.AppConstants;
import com.example.sociallogin.model.UserModel;

public class SharedPreferenceManager {

    private static final String EMPTY_STRING = "";
    private static SharedPreferenceManager instance;
    private SharedPreferences sharedPreferences;

    public static SharedPreferenceManager getSharedInstance() {
        if(instance == null)
            instance = new SharedPreferenceManager();
        return instance;
    }

    private SharedPreferenceManager() {
        super();
    }

    public void initiateSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    private boolean setStringInPreferences(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    private String getStringFromPreferances(String key) {
        String data = sharedPreferences.getString(key, EMPTY_STRING);
        return data;
    }

    private boolean setIntInPreferences(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    private int getIntFromPreferences(String key) {
        int data = sharedPreferences.getInt(key, -1);
        return data;
    }

    private boolean setDoubleInPreferences(String key, Double value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, Double.doubleToLongBits(value));
        return editor.commit();
    }

    private Double getDoubleFromPreferences(String key) {
        Double data = Double.longBitsToDouble(sharedPreferences.getLong(key, Double.doubleToLongBits(-1.0)));
        return data;
    }

    private boolean setBooleanInPreferences(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    private boolean getBooleanFromPreferences(String key) {
        boolean data = sharedPreferences.getBoolean(key, false);
        return data;
    }

    private boolean setLongInPreferences(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    private long getLongFromPreferences(String key) {
        long data = sharedPreferences.getLong(key, -1);
        return data;
    }

    private boolean setFloatInPreferences(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    private float getFloatFromPreferences(String key) {
        float data = sharedPreferences.getFloat(key, -1.0f);
        return data;
    }

    private boolean deleteDataFromPreferences(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
    }

    public void clearAllPreferences(){
        sharedPreferences.edit().clear().commit();
    }

    public void saveUserModel(UserModel userModel)
    {
        if(userModel==null)
            return;

        setStringInPreferences(AppConstants.SharedPreferenceKeys.USER_NAME.getKey(), userModel.userName);
        setStringInPreferences(AppConstants.SharedPreferenceKeys.USER_EMAIL.getKey(), userModel.userEmail);
        setStringInPreferences(AppConstants.SharedPreferenceKeys.USER_IMAGE_URL.getKey(), userModel.profilePic);
    }

    public UserModel getUserModelFromPreferences()
    {
        UserModel userModel =null;
        String name = getStringFromPreferances(AppConstants.SharedPreferenceKeys.USER_NAME.getKey());
        if(!TextUtils.isEmpty(name)) {
            userModel = new UserModel();
            userModel.userName = name;
            userModel.userEmail = getStringFromPreferances(AppConstants.SharedPreferenceKeys.USER_EMAIL.getKey());
            userModel.profilePic = getStringFromPreferances(AppConstants.SharedPreferenceKeys.USER_IMAGE_URL.getKey());

        }
        return userModel;
    }

}
