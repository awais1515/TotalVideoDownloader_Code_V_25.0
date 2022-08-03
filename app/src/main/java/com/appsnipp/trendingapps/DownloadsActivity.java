package com.appsnipp.trendingapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.appsnipp.trendingapps.Utils.Utils;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.appsnipp.trendingapps.browser.Adapters.DownloadsAdapter;
import com.appsnipp.trendingapps.Models.file_type;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DownloadsActivity extends AppCompatActivity {

    AdView adViewDetailAct;
    com.facebook.ads.AdView fb_banner_container_detail;

    private RecyclerView downloadsList;
    private DownloadsAdapter adapter;
    file_type File_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);

        Toolbar toolbar =  findViewById(R.id.toolbar_download);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        downloadsList = findViewById(R.id.downloadsCompletedList);
        File videoFile = Utils.RootDirectoryBrowserShow;
        if(videoFile.exists())
        {
            List<File> nonExistentFiles = new ArrayList<>(Arrays.asList(videoFile.listFiles()));
            if(!nonExistentFiles.isEmpty())
            {
                adapter=new DownloadsAdapter(nonExistentFiles,this,File_type);
                downloadsList.setAdapter(adapter);
                downloadsList.setLayoutManager(new GridLayoutManager(this, 2));
                downloadsList.setHasFixedSize(true);
            }
        }



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adViewDetailAct=findViewById(R.id.adViewDetailAct);
        AudienceNetworkAds.initialize(this);
        if( getResources().getString(R.string.Ads).equals("ADMOB") ){
            AdRequest adRequest = new AdRequest.Builder().build();
            adViewDetailAct.loadAd(adRequest);
        }
        else if (getResources().getString(R.string.Ads).equals("FACEBOOK")){
            adViewDetailAct.setVisibility(View.GONE);
            fb_banner_container_detail=new com.facebook.ads.AdView(this, getResources().getString(R.string.FB_Banner_Ad_PlacemaneId_4), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            LinearLayout adContainer = (LinearLayout) findViewById(R.id.fb_banner_container_detail);
            adContainer.addView(fb_banner_container_detail);
            fb_banner_container_detail.loadAd();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            DownloadsActivity.this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}