package com.appsnipp.trendingapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.util.Patterns;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.anthonycr.progress.AnimatedProgressBar;
import com.appsnipp.trendingapps.Utils.TextUtils;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.app.SharedPref;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.appsnipp.trendingapps.browser.Adapters.AutoCompleteAdapter;
import com.appsnipp.trendingapps.browser.Grabber.ContentSearch;
import com.appsnipp.trendingapps.browser.Grabber.URLAddFilter;
import com.appsnipp.trendingapps.Models.downloadable_resource_model;
import com.appsnipp.trendingapps.Models.file_type;
import com.appsnipp.trendingapps.Models.resourse_holder_model;
import com.appsnipp.trendingapps.browser.Statics.static_variables;
import com.appsnipp.trendingapps.Utils.Commons;
import com.appsnipp.trendingapps.browser.popups.available_files_dialog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;


public class BrowserActivity extends AppCompatActivity {

    AnimatedProgressBar loadingPageProgress;
    CountDownTimer countDownTimer;
    AutoCompleteTextView et_search_bar;
    WebView simpleWebView;
    Context mContext;
    Timer timer=null;
    private SSLSocketFactory defaultSSLSF;
    FloatingActionButton download_fab;
    Boolean isAllFabsVisible;
    ImageView btn_home, btn_search,btn_search_cancel,btn_settings;
    available_files_dialog _available_files_dialog;
    boolean fab_enabled=false;
    private boolean isRedirected;
    AdView adViewMainAct;

    com.facebook.ads.AdView fb_AdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_browser);
       // setSupportActionBar(toolbar);
        mContext=this;
        defaultSSLSF=HttpsURLConnection.getDefaultSSLSocketFactory();
        init_components();
        set_button_click_events();
        wv_go_to_home();
        disable_fab_button();
        try {
            onSharedIntent();
        }
        catch (Exception ex)
        {}
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adViewMainAct = new AdView(this);
        adViewMainAct.setAdSize(AdSize.SMART_BANNER);
        adViewMainAct.setAdUnitId(SharedPref.read(SharedPref.KEY_ADMOB_BANNER_AD_3,SharedPref.ADMOB_BANNER_AD_DEFAULT_3));

        AudienceNetworkAds.initialize(this);
        if( getResources().getString(R.string.Ads).equals("ADMOB") ){
            LinearLayout layout = (LinearLayout) findViewById(R.id.ADMOBBANNER3);
            layout.addView(adViewMainAct);
            AdRequest adRequest = new AdRequest.Builder().build();
            adViewMainAct.loadAd(adRequest);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            adViewMainAct.loadAd(adRequest);
        }
        else if (getResources().getString(R.string.Ads).equals("FACEBOOK")){
            adViewMainAct.setVisibility(View.GONE);
//            fb_AdView=new com.facebook.ads.AdView(this, getResources().getString(R.string.FB_Banner_Ad_PlacemaneId_3), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            fb_AdView=new com.facebook.ads.AdView(this, SharedPref.read(SharedPref.KEY_FB_ADMOB_BANNER_AD_3, SharedPref.FB_ADMOB_BANNERADS_AD_DEFAULT_3), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            LinearLayout adContainer = (LinearLayout) findViewById(R.id.fb_banner_container);
            adContainer.addView(fb_AdView);
            fb_AdView.loadAd();
        }

        havePermissionForWriteStorage();

        Intent appLinkIntent = getIntent();
        Uri appLinkData = appLinkIntent.getData();
        if (appLinkData != null)
        {
            et_search_bar.setText(appLinkData.toString());
            navigate_browser();
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            et_search_bar.setText(value);
            navigate_browser();
        }
    }

    private void onSharedIntent()
    {
        Intent receiverdIntent = getIntent();
        String receivedAction = receiverdIntent.getAction();
        String receivedType = receiverdIntent.getType();
        if (receivedAction.equals(Intent.ACTION_SEND))
        {
            if (receivedType.startsWith("text/"))
            {
                String receivedText = receiverdIntent
                        .getStringExtra(Intent.EXTRA_TEXT);
                if (receivedText != null) {
                    CheckUrls(receivedText);
                }
            }
        }
    }

    private void CheckUrls(String text){
        List<String> result= Commons.extractUrls(text);
        if(result.size()==0)
        {
            Toast.makeText(mContext,mContext.getString(R.string.enterValidUrl),Toast.LENGTH_LONG);
        }
        else
        {
            et_search_bar.setText(result.get(0));
            navigate_browser();
        }
    }



    private boolean havePermissionForWriteStorage() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Log.d("Permission Allowed", "true");
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 950);
                return false;
            } else {
                initFolers();
                return true;
            }
        } else {
            initFolers();
            return true;
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 950:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initFolers();
                } else {
                    Toast.makeText(mContext,getString(R.string.Permissiondenied), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void initFolers()
    {
        try {
            mkdirs(new File(Utils.RootDirectoryBrowser));
        }
        catch (Exception ex)
        {

        }
    }
    private void mkdirs(File _dir)
    {
        if(!_dir.exists())
            _dir.mkdir();
    }

    private  void enable_fab_button(){
        download_fab.setEnabled(true);
        download_fab.setClickable(true);
    }


    private void enable_video_fab(){
        enable_fab_button();
    }


    private  void disable_fab_button(){

        if(isAllFabsVisible){
            toggle_fab_buttons();
        }
        download_fab.setEnabled(false);
        download_fab.setClickable(false);
    }

    private void  wv_go_to_home(){
        simpleWebView.loadUrl( "about:blank" );
    }

    private void init_components(){
        loadingPageProgress=findViewById(R.id.loadingPageProgress);
        simpleWebView=findViewById(R.id.simpleWebView);
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.getSettings().setDomStorageEnabled(true);
        simpleWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        simpleWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        simpleWebView.setWebViewClient(new customWebClient());

//        adViewMainAct=findViewById(R.id.adViewMainAct);
        et_search_bar=findViewById(R.id.et_search_bar);
        int layout = android.R.layout.simple_list_item_1;
        AutoCompleteAdapter adapter = new AutoCompleteAdapter (mContext, layout);
        et_search_bar.setAdapter(adapter);

        download_fab=findViewById(R.id.download_fab);
        download_fab.bringToFront();



        btn_home=findViewById(R.id.btn_home);
        btn_search_cancel=findViewById(R.id.btn_search_cancel);
        btn_search=findViewById(R.id.btn_search);
        btn_settings=findViewById(R.id.btn_settings);

        registerForContextMenu(btn_settings);

        isAllFabsVisible=false;
    }
    @Override
    public void onCreateContextMenu(
            ContextMenu menu,
            View v,
            ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
    }

    private void toggle_fab_buttons()
    {
        if (!isAllFabsVisible){

            isAllFabsVisible = true;
        }
        else
        {
            isAllFabsVisible = false;
        }
    }

    private void set_button_click_events(){
        download_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle_fab_buttons();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv_go_to_home();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate_browser();
            }
        });

        btn_search_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_searchbar_text("");
            }
        });

        et_search_bar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus && et_search_bar.getText().toString().equals(getResources().getString(R.string.home)) ){
                    set_searchbar_text("");
                }
            }
        });

        et_search_bar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_ENTER:
                            navigate_browser();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });



        download_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _available_files_dialog=new available_files_dialog( file_type.VIDEO);
                _available_files_dialog.show(getSupportFragmentManager(),"Videos");
            }
        });



        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    btn_settings.showContextMenu(0,0);
                }
                else
                {
                    btn_settings.showContextMenu();
                }
            }
        });

        et_search_bar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigate_browser();
            }
        });
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void findLocal()
    {
        if(et_search_bar.getText().toString().contains("facebook.com"))
        {
            simpleWebView.evaluateJavascript("(function() {return document.getElementsByTagName('html')[0].outerHTML;})();",
                    new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String html) {

                           try {
                               if(html !=null)
                               {
                                   JsonReader reader = new JsonReader(new StringReader(html));
                                   reader.setLenient(true);
                                   try {
                                       if(reader.peek() == JsonToken.STRING) {
                                           String domStr = reader.nextString();
                                           if(domStr != null) {
                                               Document document=Jsoup.parse(domStr);
                                               String videoUrl= document.select("meta[property=\"og:video\"]").last().attr("content");
                                               ArrayList<downloadable_resource_model> video_files=new ArrayList<>();
                                               static_variables.resourse_holder.setVideo_files(video_files);
                                               static_variables.resourse_holder.add_Video(null,"video",videoUrl,"Video","page");
                                                   runOnUiThread(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           enable_Buttons();
                                                       }
                                                   });

                                           }
                                       }
                                   } catch (IOException e) {
                                       // handle exception
                                   } finally {
                                      // IoUtil.close(reader);
                                   }
                               }

                           }
                           catch (Exception ex)
                           {}
                        }
                    });
        }



    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_exit:
            {
                finish();
                return true;
            }
            case R.id.action_Downloads:
            {
                Intent intent = new Intent(this, DownloadsActivity.class);
                startActivity(intent);
                return true;
            }

        }
        return true;
    }

    private void navigate_browser(){
        hideKeyboard();
        if(! Patterns.WEB_URL.matcher(et_search_bar.getText()).matches())
        {
            et_search_bar.setText("https://www.google.com/search?q=" + et_search_bar.getText());
        }
        simpleWebView.loadUrl(et_search_bar.getText().toString());
    }

    private void  startTimer(){
        final int secs = 10;
        disable_fab_button();
        loadingPageProgress.setProgress(0);
        countDownTimer= new CountDownTimer((secs +1) * 100, 1000)
        {
            @Override
            public final void onTick(final long millisUntilFinished)
            {
                if(loadingPageProgress.getProgress() < 80){
                    loadingPageProgress.setProgress(loadingPageProgress.getProgress() + 8);
                }
            }
            @Override
            public final void onFinish()
            {

            }
        };
        countDownTimer.start();
        loadingPageProgress.setVisibility(View.VISIBLE);
    }
    private void stopTimer(){
        try {
            countDownTimer.cancel();
        }
        catch (Exception ex){}
        loadingPageProgress.setProgress(100);

        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingPageProgress.setProgress(0);
                loadingPageProgress.setVisibility(View.GONE);

            }
        }, 500);

    }

    private void set_searchbar_text(String text){
        if(text.equals("about:blank")){
            text=getResources().getString(R.string.home);
        }
        if(text.equals("")){
            btn_search_cancel.setVisibility(View.INVISIBLE);
            et_search_bar.requestFocus();
        }
        else
        {
            btn_search_cancel.setVisibility(View.VISIBLE);
        }
        et_search_bar.setText(text);
    }


    public class customWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            startTimer();
            isRedirected = false;
            super.onPageStarted(view, url, favicon);
            if(timer !=null)
            {
                timer.cancel();
                timer=null;
            }

            set_searchbar_text(url);
            static_variables.resourse_holder=new resourse_holder_model();
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            if(static_variables.resourse_holder==null)
            {
                static_variables.resourse_holder=new resourse_holder_model();
            }
            static_variables.resourse_holder.setPage_title(view.getTitle());
            if (!isRedirected){
                stopTimer();
                enable_Buttons();
                findLocal();
                if(download_fab.isEnabled()==true ){
                    YoYo.with(Techniques.Tada)
                            .duration(300)
                            .repeat(5)
                            .playOn(findViewById(R.id.download_fab));
                }
            }
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onLoadResource(final WebView view, final String url)
        {
            if(!URLAddFilter.DoNotCheckIf(mContext,et_search_bar.getText().toString()))
            {
                final String viewUrl = view.getUrl();
                final String title = view.getTitle();
                //Log.d("RESFILE",url);
                new ContentSearch(mContext,url, viewUrl, title){

                    @Override
                    public void onStartInspectingURL() {
                        TextUtils.disableSSLCertificateChecking();
                    }

                    @Override
                    public void onFinishedInspectingURL(boolean finishedAll) {
                        HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSF);
                    }

                    @Override
                    public void onVideoFound(String size, String type, String link, String name, String page, boolean chunked, String website, boolean audio) {
                        static_variables.resourse_holder.add_Video(size,type,link,name,page);
                        if(static_variables.resourse_holder.getVideo_files().size() > 0){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    enable_Buttons();
                                }
                            });
                        }
                    }



                }.start();
            }
        }
    }



    private void enable_Buttons() {
        if((static_variables.resourse_holder.getVideo_files().size() >0) ){
            enable_video_fab();
        }
    }




    @Override
    public void onBackPressed() {
        if (simpleWebView.copyBackForwardList().getCurrentIndex() > 0) {
            simpleWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

}