package com.appsnipp.trendingapps.app;


import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

public class App extends Application {
// hassan
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        SharedPref.init(this);
    }

    public static void showLog(String message){
        Log.d("abc",message);
    }

    public static void showToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void showMessageDialog(Context context,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }



}
