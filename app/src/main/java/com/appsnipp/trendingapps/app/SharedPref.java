package com.appsnipp.trendingapps.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{
    //
    private static SharedPreferences mSharedPref;

    public static final String KEY_ADS = "ads";
    public static final String ADS_DEFAULT = Ads.FACEBOOK;

    public static String ADMOB_BANNER_AD_DEFAULT_1 = "ca-app-pub-3940256099942544/6300978111";
    public static String ADMOB_BANNER_AD_DEFAULT_2 = "ca-app-pub-3940256099942544/6300978111";
    public static String ADMOB_BANNER_AD_DEFAULT_3 = "ca-app-pub-3940256099942544/6300978111";
    public static String ADMOB_BANNER_AD_DEFAULT_4 = "ca-app-pub-3940256099942544/6300978111";
    public static String ADMOB_INTER_AD_DEFAULT = "ca-app-pub-3940256099942544/1033173712";
    public static String ADMOB_INTER_AD_DEFAULT_2 = "ca-app-pub-3940256099942544/1033173712";
    public static String FB_ADMOB_BANNERADS_AD_DEFAULT_1 = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String FB_ADMOB_BANNERADS_AD_DEFAULT_2 = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String FB_ADMOB_BANNERADS_AD_DEFAULT_3 = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String FB_ADMOB_BANNERADS_AD_DEFAULT_4 = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID";
    public static String FB_ADMOB_INTERADS_AD_DEFAULT =   "391833248570597_391848878569034";
    public static String FB_ADMOB_INTERADS_AD_DEFAULT_2 = "391833248570597_391848878569034";



    // strings in firebase
    public static final String KEY_ADMOB_BANNER_AD_1 = "key_admob_banner_ad_1"; // 14
    public static final String KEY_ADMOB_BANNER_AD_2 = "key_admob_banner_ad_2"; // 14
    public static final String KEY_ADMOB_BANNER_AD_3 = "key_admob_banner_ad_3"; // 14
    public static final String KEY_ADMOB_BANNER_AD_4 = "key_admob_banner_ad_4"; // 14
    public static final String KEY_ADMOB_INTER = "key_admob_inter_ad"; // 15
    public static final String KEY_ADMOB_INTER_2 = "key_admob_inter_ad_2"; // 15
     // fb ads
     public static final String KEY_FB_ADMOB_BANNER_AD_1 = "key_fb_admob_banner_ad_1";
     public static final String KEY_FB_ADMOB_BANNER_AD_2 = "key_fb_admob_banner_ad_2";
     public static final String KEY_FB_ADMOB_BANNER_AD_3 = "key_fb_admob_banner_ad_3";
     public static final String KEY_FB_ADMOB_BANNER_AD_4 = "key_fb_admob_banner_ad_4";
     public static final String KEY_FB_ADMOB_INTER_AD_1 = "key_fb_admob_inter_ad";
     public static final String KEY_FB_ADMOB_INTER_AD_2 = "key_fb_admob_inter_ad_2";



    private SharedPref()
    {
    }
    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }










}
