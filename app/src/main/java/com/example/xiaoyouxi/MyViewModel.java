package com.example.xiaoyouxi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MyViewModel  extends AndroidViewModel {
    private SavedStateHandle handle;
    private static  String KEY_LEFT = "key1";
    private static  String KEY_RIGHT = "key2";
    private static  String KEY_HIGH = "key3";
    private static  String KEY_DATA = "key4";
    private static  String KEY_OPERATOR = "key5";
    private static  String KEY_SCORE = "key6";
    private static  String KEY_ANSWER = "key7";
    Boolean win_flag=false;

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        if (!handle.contains(KEY_HIGH)) {
            SharedPreferences shp = getApplication().getSharedPreferences(KEY_DATA, Context.MODE_PRIVATE);
            handle.set(KEY_HIGH, shp.getInt(KEY_HIGH, 0));
            handle.set(KEY_LEFT, 0);
            handle.set(KEY_RIGHT, 0);
            handle.set(KEY_OPERATOR, "+");
            handle.set(KEY_SCORE, 0);
            handle.set(KEY_ANSWER, 0);
        }
        this.handle = handle;
    }
    public MutableLiveData<Integer> getLeftNumber(){
        return handle.getLiveData(KEY_LEFT);
    }
    public MutableLiveData<Integer> getRightNumber(){
        return handle.getLiveData(KEY_RIGHT);
    }
    public MutableLiveData<String> getOperatorNumber(){
        return handle.getLiveData(KEY_OPERATOR);
    }
    public MutableLiveData<Integer> getScoreNumber(){
        return handle.getLiveData(KEY_SCORE);
    }
    public MutableLiveData<Integer> getHighNumber(){
        return handle.getLiveData(KEY_HIGH);
    }
    public MutableLiveData<Integer> getAnswer(){
        return  handle.getLiveData(KEY_ANSWER);
    }
    public void generator(){
        int LEVEL=100;
        Random random =new Random();
        int x,y;
        x = random.nextInt(LEVEL+1);
        y = random.nextInt(LEVEL+1);
        if (x%2==0){
            getOperatorNumber().setValue("+");
            if (x>y){
                getAnswer().setValue(x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x-y);
            }else {
                getAnswer().setValue(y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y-x);
            }
        }else{
            getOperatorNumber().setValue("-");
            if (x>y){
                getAnswer().setValue(x-y);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);
            } else{
                getAnswer().setValue(y-x);
                getLeftNumber().setValue(y);
                getRightNumber().setValue(x);
            }

        }

    }
    void save(){
        SharedPreferences shp = getApplication().getSharedPreferences(KEY_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = shp.edit();
        edit.putInt(KEY_HIGH,getHighNumber().getValue());
        edit.apply();
    }
    void answerCorrect(){
        getScoreNumber().setValue(getScoreNumber().getValue()+1);
        if (getScoreNumber().getValue()>getHighNumber().getValue()){
            getHighNumber().setValue(getScoreNumber().getValue());
            win_flag=true;
        }
        generator();
    }
}
