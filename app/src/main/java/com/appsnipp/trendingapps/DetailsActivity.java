package com.appsnipp.trendingapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.trendingapps.Downloaders.ByteVodeo;
import com.appsnipp.trendingapps.Downloaders.DownloaderCommon;
import com.appsnipp.trendingapps.Downloaders.Facebook2;
import com.appsnipp.trendingapps.Downloaders.Fairtok;
import com.appsnipp.trendingapps.Downloaders.Flickr;
import com.appsnipp.trendingapps.Downloaders.Funimate;
import com.appsnipp.trendingapps.Downloaders.Imdb;
import com.appsnipp.trendingapps.Downloaders.Imgur;
import com.appsnipp.trendingapps.Downloaders.Lomotif;
import com.appsnipp.trendingapps.Downloaders.Moj;
import com.appsnipp.trendingapps.Downloaders.MojSecond;
import com.appsnipp.trendingapps.Downloaders.Mxtakatak;
import com.appsnipp.trendingapps.Downloaders.Ojoo;
import com.appsnipp.trendingapps.Downloaders.Pinterest;
import com.appsnipp.trendingapps.Downloaders.Raask;
import com.appsnipp.trendingapps.Downloaders.Reddit;
import com.appsnipp.trendingapps.Downloaders.Roposo;
import com.appsnipp.trendingapps.Downloaders.BoloIndya;
import com.appsnipp.trendingapps.Downloaders.Chingari;
import com.appsnipp.trendingapps.Downloaders.Dubsmash;
import com.appsnipp.trendingapps.Downloaders.Facebook;
import com.appsnipp.trendingapps.Downloaders.GDrive;
import com.appsnipp.trendingapps.Downloaders.Hind;
import com.appsnipp.trendingapps.Downloaders.SnackVideo;
import com.appsnipp.trendingapps.Downloaders.Soundcloud;
import com.appsnipp.trendingapps.Downloaders.Tumblr;
import com.appsnipp.trendingapps.Downloaders.fouranim;
import com.appsnipp.trendingapps.Downloaders.iFunny;
import com.appsnipp.trendingapps.Downloaders.Instagram;
import com.appsnipp.trendingapps.Downloaders.Josh;
import com.appsnipp.trendingapps.Downloaders.Likee;
import com.appsnipp.trendingapps.Downloaders.Mitron;
import com.appsnipp.trendingapps.Downloaders.Rizzle;
import com.appsnipp.trendingapps.Downloaders.Sharechat;
import com.appsnipp.trendingapps.Downloaders.Tiktok;
import com.appsnipp.trendingapps.Downloaders.Trell;
import com.appsnipp.trendingapps.Downloaders.Triller;
import com.appsnipp.trendingapps.Downloaders.Twitter;
import com.appsnipp.trendingapps.Downloaders.Vimeo;
import com.appsnipp.trendingapps.Downloaders.Zili;
import com.appsnipp.trendingapps.Downloaders.pdisk;
import com.appsnipp.trendingapps.Downloaders.tiki;
import com.appsnipp.trendingapps.Models.ServicesModel;
import com.appsnipp.trendingapps.Utils.Services;
import com.appsnipp.trendingapps.Utils.TextUtils;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.app.SharedPref;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;
import com.facebook.ads.AudienceNetworkAds;
import com.github.nikartm.button.FitButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.appsnipp.trendingapps.Utils.Services.Lomotif;

public class DetailsActivity extends AppCompatActivity  {

    private static final int PERMISSION_REQUEST_CODE = 100;
    FloatingActionButton floatingActionButton_Back;
    Context c;
    EditText txtVideoURL;
    FitButton fbBtn_Download;
    long DownloadId;
    ArrayList<String> FilesList;
    ListView listview;
    Services CurrentService;
    ImageView img_Service_Logo;
    ArrayAdapter arrayAdapter;
    TextView txt_Service_Name;
    ServicesModel servicesModel;
    String Url="";
    AdView adViewDetail;
    private InterstitialAd mInterstitialAd;
    com.facebook.ads.AdView fb_AdView;
    private com.facebook.ads.InterstitialAd FBinterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        c=this;
        initViews();
        registerDownloaders();
        CurrentService=(Services) getIntent().getSerializableExtra("SERVICEENUM");
        try {
            onSharedIntent();
        }
        catch (Exception ex)
        {}
        servicesModel=Utils.GetServicesModel(CurrentService,this);


        SetServiceHeaders();
        LoadFiles();
        try {
            Url=getIntent().getSerializableExtra("URL").toString();
            txtVideoURL.setText(Url);
            DownloadProcess();
        }
        catch (Exception ex)
        {}

        AdRequest adRequestinter = new AdRequest.Builder().build();
        InterstitialAd.load(this,SharedPref.read(SharedPref.KEY_ADMOB_INTER,SharedPref.KEY_ADMOB_INTER), adRequestinter, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });

        //mInterstitialAd=InterstitialAd();
        // mInterstitialAd.setAdUnitId(getResources().getString(R.string.AdmobInterstitial) );
        //mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //adViewDetail=findViewById(R.id.adViewDetail);
//        mInterstitialAd.setAdListener(new AdListener(){
//            @Override
//            public void onAdLoaded() {
//
//            }
//            @Override
//            public void onAdOpened() {
//                mInterstitialAd.loadAd(new AdRequest.Builder().build());
//            }
//            @Override
//            public void onAdClosed() {
//                Intent intent = getIntent();
//                finish();
//                startActivity(intent);
//            }
//        });



        AudienceNetworkAds.initialize(this);
        FBinterstitialAd = new com.facebook.ads.InterstitialAd(this, getResources().getString(R.string.FB_Interstitial_Ad_PlacemaneId));
        FBinterstitialAd.loadAd(FBinterstitialAd.buildLoadAdConfig().build());

        adViewDetail = new AdView(this);
        adViewDetail.setAdSize(AdSize.SMART_BANNER);
        adViewDetail.setAdUnitId(SharedPref.read(SharedPref.KEY_ADMOB_BANNER_AD_2,SharedPref.ADMOB_BANNER_AD_DEFAULT_2));
        if( getResources().getString(R.string.Ads).equals("ADMOB") ){
            LinearLayout layout = (LinearLayout) findViewById(R.id.ADMOBBANNER2);
            layout.addView(adViewDetail);
            AdRequest adRequest = new AdRequest.Builder().build();
            adViewDetail.loadAd(adRequest);
        }else if (getResources().getString(R.string.Ads).equals("FACEBOOK")){
            adViewDetail.setVisibility(View.GONE);
            fb_AdView=new com.facebook.ads.AdView(this, getResources().getString(R.string.FB_Banner_Ad_PlacemaneId_2), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            LinearLayout adContainer = (LinearLayout) findViewById(R.id.fb_banner_container_detail);
            adContainer.addView(fb_AdView);
            fb_AdView.loadAd();
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
        List<String> result= TextUtils.extractUrls(text);
        if(result.size()==0)
        {
            Toasty.error(DetailsActivity.this, "No Url Found in you input", Toast.LENGTH_SHORT, true).show();
        }
        else
        {
            Services s=TextUtils.GetServiceByURL( result.get(0), DetailsActivity.this );
            if(s !=null)
            {
                CurrentService=s;
                txtVideoURL.setText(result.get(0));
                DownloadProcess();
            }
            else
            {
                Toasty.error(DetailsActivity.this, "Given URL not match", Toast.LENGTH_SHORT, true).show();
            }
        }
    }

    private void SetServiceHeaders()
    {
        txt_Service_Name.setText(servicesModel.getName());
        txt_Service_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(servicesModel.getServiceLink()));
                // startActivity(browserIntent);
            }
        });
        img_Service_Logo.setImageDrawable(servicesModel.getLogoImg());
    }

    private void initViews()
    {
        FilesList=new ArrayList<>();
        floatingActionButton_Back=findViewById(R.id.floatingActionButton_Back);
        floatingActionButton_Back.setOnClickListener(BackPress());
        txtVideoURL=findViewById(R.id.txtVideoURL);
        listview=findViewById(R.id.fileslist);
        img_Service_Logo=findViewById(R.id.img_Service_Logo);
        fbBtn_Download=findViewById(R.id.fbBtn_Download);
        fbBtn_Download.setOnClickListener(DownloadPress());
        txt_Service_Name=findViewById(R.id.txt_Service_Name);
//        adViewDetail=findViewById(R.id.adViewDetail);
    }

    private void LoadFiles()
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if(checkPermission())
            {
                LogFiles();
            }
            else
            {
                requestPermission();
            }
        }
        else
        {
            LogFiles();
        }
    }


    private void LogFiles()
    {
        FilesList=new ArrayList<>();
       try {
           File dir = new File( servicesModel.getRootDirectory().getAbsolutePath() + "/");
           if (dir.exists()) {
               File list[] = dir.listFiles();
               if (list != null && list.length >= 1) {
                   Arrays.sort( list, new Comparator()
                   {
                       public int compare(Object o1, Object o2) {

                           if (((File)o1).lastModified() > ((File)o2).lastModified()) {
                               return -1;
                           } else if (((File)o1).lastModified() < ((File)o2).lastModified()) {
                               return +1;
                           } else {
                               return 0;
                           }
                       }

                   });
               }
               for (int i = 0; i < list.length; i++) {
                   FilesList.add(list[i].getName());
               }
               arrayAdapter = new ArrayAdapter(DetailsActivity.this, android.R.layout.simple_list_item_1, FilesList);
               listview.setAdapter(arrayAdapter);
               registerForContextMenu(listview);
               listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       PlayVideo(position);
                   }
               });
               listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                   @Override
                   public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                       return false;
                   }
               });

           }
       }
       catch (Exception ex)
       {

       }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.fileslist) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            String title = ((String) arrayAdapter.getItem(info.position));
            menu.add(Menu.NONE,info.position,1,getResources().getString(R.string.Play));
            menu.add(Menu.NONE,info.position,2,getResources().getString(R.string.Delete));
            menu.add(Menu.NONE,info.position,3,getResources().getString(R.string.Share));
        }
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == getResources().getString(R.string.Play)) {
             PlayVideo(item.getItemId());
        }
        if (item.getTitle() == getResources().getString(R.string.Delete)) {
            DeleteConfirm(item.getItemId());
        }
        if (item.getTitle() == getResources().getString(R.string.Share)) {
            ShareFile(item.getItemId());
        }
        return true;
    }

    private void ShareFile(int itemId)
    {
        File file = new File(getFullPath(itemId)).getAbsoluteFile();
        Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        intentShareFile.setType(URLConnection.guessContentTypeFromName(file.getName()));
        intentShareFile.putExtra(Intent.EXTRA_STREAM,uri);
        startActivity(Intent.createChooser(intentShareFile, getResources().getString(R.string.ShareFile)));
    }

    private void DeleteConfirm(int index) {
        final int DeletableId=index;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(R.string.app_name);
        builder.setMessage(getResources().getString(R.string.ConfirmDelete) + arrayAdapter.getItem(index).toString());
        builder.setIcon(R.mipmap.flirt_icon);
        builder.setPositiveButton(getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                File fdelete = new File(getFullPath(DeletableId)).getAbsoluteFile();
                if (fdelete.exists()) {
                    if (fdelete.delete()) {
                        LoadFiles();
                        Toasty.success(c, getResources().getString(R.string.FileDeleted), Toast.LENGTH_SHORT, true).show();
                    } else {
                        Toasty.error(c, getResources().getString(R.string.UnabletoDelete), Toast.LENGTH_SHORT, true).show();
                    }
                }
                else
                {
                    LoadFiles();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void PlayVideo(int index)
    {
        File file = new File(   getFullPath(index) ).getAbsoluteFile();
        if(file.exists())
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(String.valueOf(file)), "video/mp4");
            startActivity(intent);
        }
    }

    private String getFullPath(int index)
    {
        return servicesModel.getRootDirectory().getAbsoluteFile()+"/"+ arrayAdapter.getItem(index).toString();
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(DetailsActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
                )) {
            Toasty.error(c, getResources().getString(R.string.NoExternalStoragepermission), Toast.LENGTH_SHORT, true).show();
        } else {
            ActivityCompat.requestPermissions(DetailsActivity.this, new String[]
                    {android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LoadFiles();

            } else {
                    Toasty.error(c, getResources().getString(R.string.PermissionDenied), Toast.LENGTH_SHORT, true).show();
            }
            break;
        }
    }

    private boolean checkPermission()
    {
        int result = ContextCompat.checkSelfPermission(DetailsActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void registerDownloaders()
    {
        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private View.OnClickListener DownloadPress()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DownloadProcess();
            }
        };
    }

    private void DownloadButtonClick(long output)
    {
        DownloadId=output;
        txtVideoURL.setText("");
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //FBinterstitialAd
                if( getResources().getString(R.string.Ads).equals("ADMOB") ){
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(DetailsActivity.this);
                    }
                }
                else if (getResources().getString(R.string.Ads).equals("FACEBOOK")){
                    if(FBinterstitialAd.isAdLoaded()){
                        FBinterstitialAd.show();
                    }
                }


            }
        }, 1000);
        getIntent().removeExtra("URL");
    }
    private void DownloadProcess()
    {
        if (txtVideoURL.getText().toString().equals("") )
        {
            Toasty.warning(c, getResources().getString(R.string.enterUrl), Toast.LENGTH_SHORT, true).show();
        }
        else if (! URLUtil.isValidUrl( txtVideoURL.getText().toString() ))
        {
            Toasty.warning(c, getResources().getString(R.string.enterValidUrl), Toast.LENGTH_SHORT, true).show();
        }
        else {
            if(servicesModel.getEnmService()==Services.ROPOSO)
            {
                new Roposo(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.SHARECHAT)
            {
                new Sharechat(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.FACEBOOK)
            {
                new Facebook2(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },DetailsActivity.this).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.INSTAGRAM)
            {
                new Instagram(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
//            else if (servicesModel.getEnmService()==Services.TWITTER)
//            {
//                new Twitter(c, new AsyncResponse() {
//                    @Override
//                    public void processFinish(long output) {
//                        DownloadButtonClick(output);
//                    }
//                }).execute(new String[]{txtVideoURL.getText().toString()});
//            }
            else if (servicesModel.getEnmService()==Services.LIKEE)
            {
                new Likee(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
//            else if (servicesModel.getEnmService()==Services.TIKTOK)
//            {
//                new Tiktok(c, new AsyncResponse() {
//                    @Override
//                    public void processFinish(long output) {
//                        DownloadButtonClick(output);
//                    }
//                }).execute(new String[]{txtVideoURL.getText().toString()});
//            }

            else if (servicesModel.getEnmService()==Services.VIMEO)
            {
                new Vimeo(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.CHINGARI)
            {
                new Chingari(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.RIZZELE)
            {
                new Rizzle(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }

            else if (servicesModel.getEnmService()==Services.JOSH)
            {
                new Josh(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.ZILI)
            {
                Zili zili= new Zili(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },DetailsActivity.this);
                zili.LoadVideo(txtVideoURL.getText().toString());

            }
            else if (servicesModel.getEnmService()==Services.MITRON)
            {
                new Mitron(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.TRELL)
            {
                new Trell(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.Dubsmash)
            {
                new Dubsmash(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }

            else if (servicesModel.getEnmService()==Services.Triller)
            {
                new Triller(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.BoloIndya)
            {
                new BoloIndya(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }

            else if (servicesModel.getEnmService()==Services.HIND)
            {
                new Hind(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.FUNIMATE)
            {
                new Funimate(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.BYTE)
            {
                new ByteVodeo(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
//            else if (servicesModel.getEnmService()==Services.IFUNNY)
//            {
//                new iFunny(c, new AsyncResponse() {
//                    @Override
//                    public void processFinish(long output) {
//                        DownloadButtonClick(output);
//                    }
//                }).execute(new String[]{txtVideoURL.getText().toString()});
//            }

            else if (servicesModel.getEnmService()==Services.MXTAKATAK)
            {
                new Mxtakatak(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.REDDIT)
            {
                new Reddit(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.Soundcloud)
            {
                new Soundcloud(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }

            else if (servicesModel.getEnmService()==Services.FAIRTOK)
            {
                new Fairtok(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.RAASK)
            {
                new Raask(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }

            else if (servicesModel.getEnmService()==Services.OJOO)
            {
                new Ojoo(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },DetailsActivity.this).execute(new String[]{txtVideoURL.getText().toString()});
            }

            else if (servicesModel.getEnmService()==Services.SNACKVIDEO)
            {
                new SnackVideo(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },DetailsActivity.this).execute(new String[]{txtVideoURL.getText().toString()});
            }

            else if (servicesModel.getEnmService()==Services.IMGUR)
            {
                new Imgur(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.TUMBLR)
            {
                new Tumblr(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.IMDB)
            {
                new Imdb(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },DetailsActivity.this).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.Pinterest)
            {
                new Pinterest(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.Flickr)
            {
                new Flickr(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },DetailsActivity.this).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.MOJ)
            {
                new MojSecond(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }


            else if (servicesModel.getEnmService()==Services.GDRIVE)
            {
                new GDrive(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },servicesModel).execute(new String[]{txtVideoURL.getText().toString()});
            }

            else if (servicesModel.getEnmService()==Services.fouranim)
            {
                new fouranim(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.pdisk)
            {
                new pdisk(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()==Services.tiki)
            {
                new tiki(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }
            else if (servicesModel.getEnmService()== Services.Lomotif)
            {
                new Lomotif(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                }).execute(new String[]{txtVideoURL.getText().toString()});
            }


            /*else if (servicesModel.getEnmService()==Services.DAILYMOTION)
            {
                new GDrive(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },servicesModel).execute(new String[]{txtVideoURL.getText().toString()});
            }*/
            else if ((servicesModel.getEnmService()==Services.fansubs)
                    || (servicesModel.getEnmService()==Services.espn)
                    || (servicesModel.getEnmService()==Services.nonegag)
                    || (servicesModel.getEnmService()==Services.bilibili)
                    || (servicesModel.getEnmService()==Services.blogger)
                    || (servicesModel.getEnmService()==Services.izlesene)
                    || (servicesModel.getEnmService()==Services.liveleak)
                    || (servicesModel.getEnmService()==Services.bitchute)
                    || (servicesModel.getEnmService()==Services.linkedin)
                    || (servicesModel.getEnmService()==Services.popcornflix)
                    || (servicesModel.getEnmService()==Services.meme)
                    || (servicesModel.getEnmService()==Services.kickstarter)
                    || (servicesModel.getEnmService()==Services.vlive)
                    || (servicesModel.getEnmService()==Services.vlipsy)
                    || (servicesModel.getEnmService()==Services.vidlit)
                    || (servicesModel.getEnmService()==Services.gloriatv)
                    || (servicesModel.getEnmService()==Services.wwe)
                    || (servicesModel.getEnmService()==Services.aparat)
                    || (servicesModel.getEnmService()==Services.onetvru)
                    || (servicesModel.getEnmService()==Services.allocine)
                    || (servicesModel.getEnmService()==Services.veer)
                    || (servicesModel.getEnmService()==Services.kooapp)
                    || (servicesModel.getEnmService()==Services.IFUNNY)
                    || (servicesModel.getEnmService()==Services.TIKTOK)
                    || (servicesModel.getEnmService()==Services.streamable)
                    || (servicesModel.getEnmService()==Services.gfycat)
                    || (servicesModel.getEnmService()==Services.fthis)
                    || (servicesModel.getEnmService()==Services.fireworktv)
                    || (servicesModel.getEnmService()==Services.coub)
                    || (servicesModel.getEnmService()==Services.rumble)
                    || (servicesModel.getEnmService()==Services.fourshared)
                    || (servicesModel.getEnmService()==Services.ted)
                    || (servicesModel.getEnmService()==Services.DAILYMOTION)
                    || (servicesModel.getEnmService()==Services.TWITTER)
                    )
            {
                new DownloaderCommon(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },servicesModel).execute(new String[]{txtVideoURL.getText().toString()});
            }


            else if (servicesModel.getEnmService()==Services.BITTUBE)
            {
                String Url=txtVideoURL.getText().toString();
                if(Url.contains(".tv"))
                {
                    String VideoId=Url.split("/")[Url.split("/").length -1];
                    Url="https://bittube.video/videos/watch/"+VideoId;

                } new GDrive(c, new AsyncResponse() {
                @Override
                public void processFinish(long output) {
                    DownloadButtonClick(output);
                }
            },servicesModel).execute(new String[]{Url});
            }
            else if (
                    (servicesModel.getEnmService()==Services.MEDIAFIRE )
                            || (servicesModel.getEnmService()==Services.OKRU)
                            || (servicesModel.getEnmService()==Services.VK)
                            || (servicesModel.getEnmService()==Services.SOLIDFILES)
                            || (servicesModel.getEnmService()==Services.VIDEOZA)
                            || (servicesModel.getEnmService()==Services.SENDVID)


            )
            {
                new GDrive(c, new AsyncResponse() {
                    @Override
                    public void processFinish(long output) {
                        DownloadButtonClick(output);
                    }
                },servicesModel).execute(new String[]{txtVideoURL.getText().toString()});
            }


        }
    }

    private View.OnClickListener BackPress()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }



    private BroadcastReceiver onDownloadComplete=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id=intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
            if(DownloadId==id)
            {
                Toasty.success(c, getResources().getString(R.string.DownloadComplete), Toast.LENGTH_SHORT, true).show();
                LoadFiles();
            }
        }
    };
}