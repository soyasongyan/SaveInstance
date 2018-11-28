package com.example.songyan.propertyanimationdemo;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class RetainFragment extends Fragment {

    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);//保留实例
    }

    public void setData(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getData() {
        return bitmap;
    }
}
