package com.example.songyan.propertyanimationdemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

public class LoadingDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog=new ProgressDialog(getActivity());
        dialog.setTitle("This is a progressdialog!");
        dialog.setMessage("Loading......");
        dialog.setCancelable(true);
        return dialog;
    }
}
