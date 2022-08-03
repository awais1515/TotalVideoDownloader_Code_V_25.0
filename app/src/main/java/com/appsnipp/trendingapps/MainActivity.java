package com.appsnipp.trendingapps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import com.appsnipp.trendingapps.Utils.Services;
import com.appsnipp.trendingapps.Utils.TextUtils;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.cardview.widget.CardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private CardView cardRoposo,cardShareChat,cardFacebook,cardInstagram,cardTwitter,cardYoutube,cardLikee,cardTiktok,
            cardDailyMotion,cardGoogleDrive,cardVimeo,cardChingari,cardrizzle,cardjosh,cardZili,cardMitron,cardtrell,
            cardDubsmash,cardtriller,cardBoloIndya,cardHind,cardifunny,cardMediafire,cardokru,cardVK,cardSolidFiles,
            cardVidoza,cardSendVid,cardBittube,cardFunimate,cardByte,cardMxtakatak,cardFAIRTOK,cardRaask,cardOjoo,
            cardSnackVideo,cardImgur,cardTumblr,cardImdb,cardPinterest,cardFlickr,cardMoj,cardReddir,cardSoundcloud,
            cardFansubs,cardespn,cardnonegag,cardbilibili,cardblogger,cardizlesene,cardliveleak,cardbitchute,cardlinkedin,
            cardpopcornflix,cardmeme,cardkickstarter,cardvlive,cardvlipsy,cardvidlit,cardGloriatv,cardwwe,cardaparat,
            cardonetvru,carallocine,cardveer,carakooapp,cardstreamable,cardgfycat,cardfthis,cardfireworktv,cardcoub,
            cardrumble,cardfourshared,cardted,cardfouranim,cardpdisk,cardtiki,cardLomotif,cardBrowser,cardInstaPrivate;

    FloatingActionButton floatingActionButtonNew;
    EditText et_text;
    ImageView imClear;
    ImageView imDownload;
    AdView adViewMainAct;

    com.facebook.ads.AdView fb_AdView;

    Context c;
    AppUpdateManager mAppUpdateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        c=this;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

        initButtons();

        // Check for app update on PlayStore
        checkAppUpdate();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        et_text=findViewById(R.id.et_text);
        imClear=findViewById(R.id.imClear);
        imDownload=findViewById(R.id.imDownload);


        imClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_text.setText("");
            }
        });

        imDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckUrls(et_text.getText().toString());
            }
        });

        floatingActionButtonNew=findViewById(R.id.floatingActionButtonNew);
        floatingActionButtonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });
        onSharedIntent();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adViewMainAct=findViewById(R.id.adViewMainAct);

        AudienceNetworkAds.initialize(this);
        if( getResources().getString(R.string.Ads).equals("ADMOB") ){
            AdRequest adRequest = new AdRequest.Builder().build();
            adViewMainAct.loadAd(adRequest);
        }
        else if (getResources().getString(R.string.Ads).equals("FACEBOOK")){
            adViewMainAct.setVisibility(View.GONE);
            fb_AdView=new com.facebook.ads.AdView(this, getResources().getString(R.string.FB_Banner_Ad_PlacemaneId_1), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            LinearLayout adContainer = (LinearLayout) findViewById(R.id.fb_banner_container);
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

    private void CheckUrls(String text)
    {
        List<String> result= TextUtils.extractUrls(text);
        if(result.size()==0)
        {
            Toasty.error(MainActivity.this, "No Url Found in you input", Toast.LENGTH_SHORT, true).show();
        }
        else
        {
            Services s=TextUtils.GetServiceByURL( result.get(0),MainActivity.this );
            if(s !=null)
            {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("SERVICEENUM", s );
                intent.putExtra("URL", result.get(0));
                startActivity(intent);
            }
            else
            {
                Toasty.error(MainActivity.this, "Given URL not match", Toast.LENGTH_SHORT, true).show();
            }
        }
    }

    private void initButtons()
    {
        cardRoposo=findViewById(R.id.cardRoposo);
        cardRoposo.setOnClickListener(CardClick(Services.ROPOSO));

        cardShareChat=findViewById(R.id.cardShareChat);
        cardShareChat.setOnClickListener(CardClick(Services.SHARECHAT));

        cardFacebook=findViewById(R.id.cardFacebook);
        cardFacebook.setOnClickListener(CardClick(Services.FACEBOOK));

        cardInstagram=findViewById(R.id.cardInstagram);
        cardInstagram.setOnClickListener(CardClick(Services.INSTAGRAM));

        cardTwitter=findViewById(R.id.cardTwitter);
        cardTwitter.setOnClickListener(CardClick(Services.TWITTER));


        cardLikee=findViewById(R.id.cardLikee);
        cardLikee.setOnClickListener(CardClick(Services.LIKEE));

        cardTiktok=findViewById(R.id.cardTiktok);
        cardTiktok.setOnClickListener(CardClick(Services.TIKTOK));

        cardGoogleDrive=findViewById(R.id.cardGoogleDrive);
        cardGoogleDrive.setOnClickListener(CardClick(Services.GDRIVE));

        cardDailyMotion=findViewById(R.id.cardDailyMotion);
        cardDailyMotion.setOnClickListener(CardClick(Services.DAILYMOTION));

        cardVimeo=findViewById(R.id.cardVimeo);
        cardVimeo.setOnClickListener(CardClick(Services.VIMEO));

        cardChingari=findViewById(R.id.cardChingari);
        cardChingari.setOnClickListener(CardClick(Services.CHINGARI));

        cardrizzle=findViewById(R.id.cardrizzle);
        cardrizzle.setOnClickListener(CardClick(Services.RIZZELE));

        cardjosh=findViewById(R.id.cardjosh);
        cardjosh.setOnClickListener(CardClick(Services.JOSH));

        cardZili=findViewById(R.id.cardZili);
        cardZili.setOnClickListener(CardClick(Services.ZILI));

        cardMitron=findViewById(R.id.cardMitron);
        cardMitron.setOnClickListener(CardClick(Services.MITRON));

        cardtrell=findViewById(R.id.cardtrell);
        cardtrell.setOnClickListener(CardClick(Services.TRELL));

        cardDubsmash=findViewById(R.id.cardDubsmash);
        cardDubsmash.setOnClickListener(CardClick(Services.Dubsmash));

        cardtriller=findViewById(R.id.cardtriller);
        cardtriller.setOnClickListener(CardClick(Services.Triller));

        cardBoloIndya=findViewById(R.id.cardBoloIndya);
        cardBoloIndya.setOnClickListener(CardClick(Services.BoloIndya));

        cardHind=findViewById(R.id.cardHind);
        cardHind.setOnClickListener(CardClick(Services.HIND));

        cardifunny=findViewById(R.id.cardifunny);
        cardifunny.setOnClickListener(CardClick(Services.IFUNNY));

        cardMediafire=findViewById(R.id.cardMediafire);
        cardMediafire.setOnClickListener(CardClick(Services.MEDIAFIRE));

        cardokru=findViewById(R.id.cardokru);
        cardokru.setOnClickListener(CardClick(Services.OKRU));

        cardVK=findViewById(R.id.cardVK);
        cardVK.setOnClickListener(CardClick(Services.VK));

        cardSolidFiles=findViewById(R.id.cardSolidFiles);
        cardSolidFiles.setOnClickListener(CardClick(Services.SOLIDFILES));

        cardVidoza=findViewById(R.id.cardVidoza);
        cardVidoza.setOnClickListener(CardClick(Services.VIDEOZA));

        cardSendVid=findViewById(R.id.cardSendVid);
        cardSendVid.setOnClickListener(CardClick(Services.SENDVID));

        cardBittube=findViewById(R.id.cardBittube);
        cardBittube.setOnClickListener(CardClick(Services.BITTUBE));

        cardFunimate=findViewById(R.id.cardFunimate);
        cardFunimate.setOnClickListener(CardClick(Services.FUNIMATE));

        cardByte=findViewById(R.id.cardByte);
        cardByte.setOnClickListener(CardClick(Services.BYTE));

        cardMxtakatak=findViewById(R.id.cardMxtakatak);
        cardMxtakatak.setOnClickListener(CardClick(Services.MXTAKATAK));

        cardFAIRTOK=findViewById(R.id.cardFAIRTOK);
        cardFAIRTOK.setOnClickListener(CardClick(Services.FAIRTOK));

        cardRaask=findViewById(R.id.cardRaask);
        cardRaask.setOnClickListener(CardClick(Services.RAASK));

        cardOjoo=findViewById(R.id.cardOjoo);
        cardOjoo.setOnClickListener(CardClick(Services.OJOO));

        cardSnackVideo=findViewById(R.id.cardSnackVideo);
        cardSnackVideo.setOnClickListener(CardClick(Services.SNACKVIDEO));

        cardImgur=findViewById(R.id.cardImgur);
        cardImgur.setOnClickListener(CardClick(Services.IMGUR));

        cardTumblr=findViewById(R.id.cardTumblr);
        cardTumblr.setOnClickListener(CardClick(Services.TUMBLR));

        cardImdb=findViewById(R.id.cardImdb);
        cardImdb.setOnClickListener(CardClick(Services.IMDB));

        cardPinterest=findViewById(R.id.cardPinterest);
        cardPinterest.setOnClickListener(CardClick(Services.Pinterest));

        cardFlickr=findViewById(R.id.cardFlickr);
        cardFlickr.setOnClickListener(CardClick(Services.Flickr));

        cardMoj=findViewById(R.id.cardMoj);
        cardMoj.setOnClickListener(CardClick(Services.MOJ));

        cardReddir=findViewById(R.id.cardReddit);
        cardReddir.setOnClickListener(CardClick(Services.REDDIT));

        cardSoundcloud=findViewById(R.id.cardSoundcloud);
        cardSoundcloud.setOnClickListener(CardClick(Services.Soundcloud));

        cardFansubs=findViewById(R.id.cardFansubs);
        cardFansubs.setOnClickListener(CardClick(Services.fansubs));

        cardespn=findViewById(R.id.cardespn);
        cardespn.setOnClickListener(CardClick(Services.espn));

        cardnonegag=findViewById(R.id.cardnonegag);
        cardnonegag.setOnClickListener(CardClick(Services.nonegag));

        cardbilibili=findViewById(R.id.cardbilibili);
        cardbilibili.setOnClickListener(CardClick(Services.bilibili));

        cardblogger=findViewById(R.id.cardblogger);
        cardblogger.setOnClickListener(CardClick(Services.blogger));

        cardizlesene=findViewById(R.id.cardizlesene);
        cardizlesene.setOnClickListener(CardClick(Services.izlesene));

        cardliveleak=findViewById(R.id.cardliveleak);
        cardliveleak.setOnClickListener(CardClick(Services.liveleak));

        cardbitchute=findViewById(R.id.cardbitchute);
        cardbitchute.setOnClickListener(CardClick(Services.bitchute));

        cardlinkedin=findViewById(R.id.cardlinkedin);
        cardlinkedin.setOnClickListener(CardClick(Services.linkedin));

        cardpopcornflix=findViewById(R.id.cardpopcornflix);
        cardpopcornflix.setOnClickListener(CardClick(Services.popcornflix));

        cardmeme=findViewById(R.id.cardmeme);
        cardmeme.setOnClickListener(CardClick(Services.meme));

        cardkickstarter=findViewById(R.id.cardkickstarter);
        cardkickstarter.setOnClickListener(CardClick(Services.kickstarter));

        cardvlive=findViewById(R.id.cardvlive);
        cardvlive.setOnClickListener(CardClick(Services.vlive));

        cardvlipsy=findViewById(R.id.cardvlipsy);
        cardvlipsy.setOnClickListener(CardClick(Services.vlipsy));

        cardvidlit=findViewById(R.id.cardvidlit);
        cardvidlit.setOnClickListener(CardClick(Services.vidlit));

        cardGloriatv=findViewById(R.id.cardGloriatv);
        cardGloriatv.setOnClickListener(CardClick(Services.gloriatv));

        cardwwe=findViewById(R.id.cardwwe);
        cardwwe.setOnClickListener(CardClick(Services.wwe));

        cardaparat=findViewById(R.id.cardaparat);
        cardaparat.setOnClickListener(CardClick(Services.aparat));

        cardonetvru=findViewById(R.id.cardonetvru);
        cardonetvru.setOnClickListener(CardClick(Services.onetvru));

        carallocine=findViewById(R.id.carallocine);
        carallocine.setOnClickListener(CardClick(Services.allocine));

        cardveer=findViewById(R.id.cardveer);
        cardveer.setOnClickListener(CardClick(Services.veer));

        carakooapp=findViewById(R.id.carakooapp);
        carakooapp.setOnClickListener(CardClick(Services.kooapp));

        cardstreamable=findViewById(R.id.cardstreamable);
        cardstreamable.setOnClickListener(CardClick(Services.streamable));

        cardgfycat=findViewById(R.id.cardgfycat);
        cardgfycat.setOnClickListener(CardClick(Services.gfycat));

        cardfthis=findViewById(R.id.cardfthis);
        cardfthis.setOnClickListener(CardClick(Services.fthis));

        cardfireworktv=findViewById(R.id.cardfireworktv);
        cardfireworktv.setOnClickListener(CardClick(Services.fireworktv));

        cardcoub=findViewById(R.id.cardcoub);
        cardcoub.setOnClickListener(CardClick(Services.coub));

        cardrumble=findViewById(R.id.cardrumble);
        cardrumble.setOnClickListener(CardClick(Services.rumble));

        cardfourshared=findViewById(R.id.cardfourshared);
        cardfourshared.setOnClickListener(CardClick(Services.fourshared));

        cardted=findViewById(R.id.cardted);
        cardted.setOnClickListener(CardClick(Services.ted));

        cardfouranim=findViewById(R.id.cardfouranim);
        cardfouranim.setOnClickListener(CardClick(Services.fouranim));


        cardpdisk=findViewById(R.id.cardpdisk);
        cardpdisk.setOnClickListener(CardClick(Services.pdisk));

        cardtiki=findViewById(R.id.cardtiki);
        cardtiki.setOnClickListener(CardClick(Services.tiki));

        cardLomotif=findViewById(R.id.cardLomotif);
        cardLomotif.setOnClickListener(CardClick(Services.Lomotif));

        cardBrowser=findViewById(R.id.cardBrowser);
        cardBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, BrowserActivity.class);
                startActivity(intent);
            }
        });

        cardInstaPrivate=findViewById(R.id.cardInstaPrivate);
        cardInstaPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, BrowserActivity.class);
                intent.putExtra("key","https://www.instagram.com/");
                startActivity(intent);
            }
        });
    }

    private View.OnClickListener CardClick(final Services service)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("SERVICEENUM", service );
                startActivity(intent);
            }
        };
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.nav_privacy_policy) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Privacy Policy");
            WebView wv = new WebView(this);
            wv.loadUrl("file:///android_asset/privacy_policy.html");
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            alert.setView(wv);
            alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            alert.show();
        }
        else if (id == R.id.nav_share_app){
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,  c.getResources().getString(R.string.app_name) );
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        }
        else if (id == R.id.nav_rateus){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //// Google Play Suto Update Code


    InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        public void onStateUpdate(InstallState installState) {
            try {
                if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                    MainActivity.this.popupSnackbarForCompleteUpdate();
                } else if (installState.installStatus() != InstallStatus.INSTALLED) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("InstallStateUpdatedListener: state: ");
                    sb.append(installState.installStatus());
                    Log.i("MainActivity", sb.toString());
                } else if (MainActivity.this.mAppUpdateManager != null) {
                    MainActivity.this.mAppUpdateManager.unregisterListener(MainActivity.this.installStateUpdatedListener);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void popupSnackbarForCompleteUpdate() {
        try {
            Snackbar make = Snackbar.make(findViewById(R.id.imageView2), (CharSequence) "An update has just been downloaded.", 2);
            make.setAction((CharSequence) "RESTART", (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    if (MainActivity.this.mAppUpdateManager != null) {
                        MainActivity.this.mAppUpdateManager.completeUpdate();
                    }
                }
            });
            make.setDuration(50000);
            make.setActionTextColor(getResources().getColor(R.color.downloadbannercolor));
            make.show();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void checkAppUpdate() {
        try {
            this.mAppUpdateManager = AppUpdateManagerFactory.create(this);
            this.mAppUpdateManager.registerListener(this.installStateUpdatedListener);
            this.mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
                public void onSuccess(AppUpdateInfo appUpdateInfo) {
                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                        try {
                            MainActivity.this.mAppUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, MainActivity.this, 201);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                    } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                        MainActivity.this.popupSnackbarForCompleteUpdate();
                    } else {
                        Log.e("MainActivity", "checkForAppUpdateAvailability: something else");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
