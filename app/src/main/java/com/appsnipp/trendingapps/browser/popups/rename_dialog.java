package com.appsnipp.trendingapps.browser.popups;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.appsnipp.trendingapps.Models.downloadable_resource_model;
import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Commons;


public class rename_dialog  extends AppCompatDialogFragment {

    private downloadable_resource_model result;
    private Context mContext;
    private Activity activity;
    EditText txtFileName;
    Button btnDownload;
    private com.facebook.ads.InterstitialAd FBinterstitialAd;
    private InterstitialAd mInterstitialAd;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.rename_dialog,null);
        mContext=this.getContext();
        builder.setView(view)
                .setPositiveButton(mContext.getString(R.string.Close), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        btnDownload=view.findViewById(R.id.btnDownloadNow);

        activity=getActivity();

        AdRequest adRequestinter = new AdRequest.Builder().build();
        InterstitialAd.load(mContext,getResources().getString(R.string.AdmobInterstitial_2), adRequestinter, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });

        AudienceNetworkAds.initialize(mContext);
        FBinterstitialAd = new com.facebook.ads.InterstitialAd(mContext, getResources().getString(R.string.FB_Interstitial_Ad_PlacemaneId_2));
        FBinterstitialAd.loadAd(FBinterstitialAd.buildLoadAdConfig().build());


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                sb.append( Commons.SanitizeTitle(txtFileName.getText().toString()) + "_");
                sb.append(System.currentTimeMillis());


                sb.append(".mp4");
                ShowAd();
                Commons.startDownload( result.getURL() ,"",mContext,sb.toString(),mContext,result.getFile_type());
                dismiss();
            }
        });

        txtFileName=view.findViewById(R.id.txtFileNameNew);
        txtFileName.setText(result.getTitle());
        return  builder.create();
    }
    public rename_dialog(downloadable_resource_model _result)
    {
        result=_result;
    }


    private void ShowAd()
    {
        if( getResources().getString(R.string.Ads).equals("ADMOB") ){
            if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
            }
        }
        else if (getResources().getString(R.string.Ads).equals("FACEBOOK")){
            if(FBinterstitialAd.isAdLoaded()){
                FBinterstitialAd.show();
            }
        }
    }



}
