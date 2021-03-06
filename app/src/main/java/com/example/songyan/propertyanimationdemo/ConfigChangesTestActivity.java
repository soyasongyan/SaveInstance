package com.example.songyan.propertyanimationdemo;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ConfigChangesTestActivity extends ListActivity {

    private static final String TAG=ConfigChangesTestActivity.class.getSimpleName();
    private ListAdapter mListAdapter;
    private ArrayList<String> mDatas;
    private DialogFragment mLoadingDialog;
    private LoadDataAsyncTask loadDataAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
        if(savedInstanceState!=null)
            mDatas=savedInstanceState.getStringArrayList("mDatas");

        if(mDatas==null){
            //开启后台线程加载数据
            mLoadingDialog=new LoadingDialog();
            mLoadingDialog.show(getFragmentManager(),"Loading");
            loadDataAsyncTask=new LoadDataAsyncTask();
            loadDataAsyncTask.execute();
        }else{
            initAdapter();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this,"landscape",Toast.LENGTH_SHORT).show();
        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this,"portrait",Toast.LENGTH_SHORT).show();
        }
    }

    private void initAdapter(){
        mListAdapter=new ArrayAdapter<String>(ConfigChangesTestActivity.this,android.R.layout.simple_list_item_1,mDatas);
        setListAdapter(mListAdapter);//给ListActivity内部维护的ListView实例设置适配器
    }

    //加载数据的异步类
    class LoadDataAsyncTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            //进行耗时操作
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){

            }

            mDatas=new ArrayList<String>(Arrays.asList("通过Fragment保存大量数据",
                    "onSaveInstanceState保存数据",
                    "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
                    "Spark"));
            return null;
        }

        //数据加载完成以后

        @Override
        protected void onPostExecute(Void aVoid) {
            initAdapter();//设置适配器,显示数据内容
            mLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
