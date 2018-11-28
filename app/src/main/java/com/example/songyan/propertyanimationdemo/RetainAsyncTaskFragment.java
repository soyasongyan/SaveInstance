package com.example.songyan.propertyanimationdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class RetainAsyncTaskFragment extends Fragment {
    private MyAsyncTask myAsyncTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setData(MyAsyncTask myAsyncTask) {
        this.myAsyncTask = myAsyncTask;
    }

    public MyAsyncTask getData(){
        return myAsyncTask;
    }
}
