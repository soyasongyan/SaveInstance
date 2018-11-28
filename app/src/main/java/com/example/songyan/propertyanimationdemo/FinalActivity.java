package com.example.songyan.propertyanimationdemo;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

public class FinalActivity extends ListActivity {

    //显示数据
    private ListAdapter adapter;
    private List<String> mDatas;
    //加载数据
    private MyAsyncTask asyncTask;
    //保存数据
    private RetainAsyncTaskFragment retainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm=getFragmentManager();
        retainFragment=(RetainAsyncTaskFragment)fm.findFragmentByTag("data");
        if(retainFragment==null){
            retainFragment=new RetainAsyncTaskFragment();
            fm.beginTransaction().add(retainFragment,"data").commit();
        }
        asyncTask=retainFragment.getData();
        if(asyncTask==null){
            asyncTask=new MyAsyncTask(this);
            retainFragment.setData(asyncTask);
            asyncTask.execute();
        }else{
            asyncTask.updataDialog(this);//重启Activity更新提示框
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        asyncTask.updataDialog(null);//取消dialog防止内存泄露
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //asyncTask.updataDialog(null);//取消dialog防止内存泄露
        //这一句不能放在onDestroy中,因为此时Activity已经销毁,DialogFragment也已经不存在了
    }

    public void onTaskCompleted(){
        mDatas=asyncTask.getItems();
        adapter=new ArrayAdapter<String>(FinalActivity.this,android.R.layout.simple_list_item_1,mDatas);
        setListAdapter(adapter);
    }
}
