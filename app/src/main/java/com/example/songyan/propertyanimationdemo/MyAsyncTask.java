package com.example.songyan.propertyanimationdemo;

import android.os.AsyncTask;
import android.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    private List<String> items;//该异步类的任务是为了加载获取数据
    private DialogFragment dialogFragment;//加载过程中需要提示框

    private boolean isCompleted;//判断加载是否完成的标志
    private FinalActivity finalActivity;//保存的相关的Activity实例

    public MyAsyncTask(FinalActivity finalActivity){
        this.finalActivity=finalActivity;
    }

    //加载数据前,显示提示框
    @Override
    protected void onPreExecute() {
        dialogFragment=new LoadingDialog();
        dialogFragment.show(finalActivity.getFragmentManager(),"loading");
    }

    //加载数据
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){

        }
        items=new ArrayList<String>(Arrays.asList("通过Fragment保存大量数据",
                "onSaveInstanceState保存数据",
                "getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
                "Spark"));
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //数据加载结束
        isCompleted=true;//设置标志位
        if(dialogFragment!=null){
            dialogFragment.dismiss();//取消提示框
        }
        if(finalActivity!=null){
            finalActivity.onTaskCompleted();//加载结束后回调Activity的方法
        }
    }

    //提供给Activity获取加载的数据
    public List<String> getItems(){
        return items;
    }

    //根据Activity的状态更新Dialog
    public void updataDialog(FinalActivity activity){
        this.finalActivity=activity;
        if(finalActivity==null && dialogFragment!=null){
            dialogFragment.dismiss();
        }

        if(finalActivity!=null && !isCompleted){//重新创建了Activity并且加载没有结束则重新创建提示框
            dialogFragment=new LoadingDialog();
            dialogFragment.show(finalActivity.getFragmentManager(),"loading");
        }

        if(isCompleted && finalActivity!=null){
            finalActivity.onTaskCompleted();
        }
    }
}
