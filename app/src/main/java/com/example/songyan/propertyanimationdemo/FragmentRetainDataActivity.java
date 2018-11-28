package com.example.songyan.propertyanimationdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.DialogFragment;
import android.widget.ImageView;

public class FragmentRetainDataActivity extends Activity {

    private static final String TAG=FragmentRetainDataActivity.class.getSimpleName();
    private ImageView imageView;
    private Bitmap bitmap;
    private DialogFragment mLoadingDialog;
    private RetainFragment retainFragment;
    private LoadImageAsyncTask loadImageAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        imageView=findViewById(R.id.id_image);
        FragmentManager fm=getFragmentManager();
        retainFragment=(RetainFragment)fm.findFragmentByTag("Datas");
        if(retainFragment==null){
            retainFragment=new RetainFragment();
            fm.beginTransaction().add(retainFragment,"Datas").commit();
        }
        bitmap=retainFragment.getData();
        if(bitmap==null){
            mLoadingDialog=new LoadingDialog();
            mLoadingDialog.show(fm,"loading");
            loadImageAsyncTask=new LoadImageAsyncTask();
            loadImageAsyncTask.execute();
        }else {
            imageView.setImageBitmap(bitmap);
        }

    }

    private class LoadImageAsyncTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){

            }

            bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.image5);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mLoadingDialog.dismiss();
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        retainFragment.setData(bitmap);
    }
}
