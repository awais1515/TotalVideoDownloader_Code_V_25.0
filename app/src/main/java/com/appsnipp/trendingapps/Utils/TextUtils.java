package com.appsnipp.trendingapps.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.URLUtil;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public  class TextUtils<Public>
{

    static ArrayList<String> Ristricteds;
    private static  ArrayList<String>  GetRistricteds(){
        Ristricteds=new ArrayList<>();
        Ristricteds.add("youtube");
        Ristricteds.add("youtu.be");
        return Ristricteds;
    }
    public static boolean IsContainsRistriced(String Query)
    {
        boolean _IsContainsRistriced=false;
        ArrayList<String> list=GetRistricteds();
        for (int i=0;i< list.size();i++){
            if( Query.contains(list.get(i)) ){
                _IsContainsRistriced=true;
            }
        }
        return _IsContainsRistriced;
    }

    public static Services GetServiceByURL(String Urltxt, Context c)
    {
            if(Urltxt.contains("roposo"))
            {
                return Services.ROPOSO;
            }
            else if(Urltxt.contains("sharechat"))
            {
                return Services.SHARECHAT;
            }
            else if(Urltxt.contains("facebook") || Urltxt.contains("fb.com") || Urltxt.contains("fb.watch"))
            {
                return Services.FACEBOOK;
            }
            else if(Urltxt.contains("instagram") )
            {
                return Services.INSTAGRAM;
            }
            else if(Urltxt.contains("twitter") )
            {
                return Services.TWITTER;
            }
            else if(Urltxt.contains("likee") )
            {
                return Services.LIKEE;
            }
            else if(Urltxt.contains("tiktok") )
            {
                return Services.TIKTOK;
            }
            else if(Urltxt.toLowerCase().contains("vimeo") )
            {
                return Services.VIMEO;
            }
            else if(Urltxt.toLowerCase().contains("drive.google") )
            {
                return Services.GDRIVE;
            }
            else if(Urltxt.toLowerCase().contains("chingari") )
            {
                return Services.CHINGARI;
            }
            else if(Urltxt.toLowerCase().contains("dailymotion") )
            {
                return Services.DAILYMOTION;
            }
            else if(Urltxt.toLowerCase().contains("rizzle") )
            {
                return Services.RIZZELE;
            }
            else if(Urltxt.toLowerCase().contains("myjosh") )
            {
                return Services.JOSH;
            }
            else if(Urltxt.toLowerCase().contains("mitron") )
            {
                return Services.MITRON;
            }
            else if(Urltxt.toLowerCase().contains("trell") )
            {
                return Services.TRELL;
            }
            else if(Urltxt.toLowerCase().contains("dubsmash") )
            {
                return Services.Dubsmash;
            }
            else if(Urltxt.toLowerCase().contains("triller") )
            {
                return Services.Triller;
            }
            else if(Urltxt.toLowerCase().contains("boloindya") )
            {
                return Services.BoloIndya;
            }
            else if(Urltxt.toLowerCase().contains("hind.app") )
            {
                return Services.HIND;
            }
            else if(Urltxt.toLowerCase().contains("ok.ru") )
            {
                return Services.OKRU;
            }
            else if(Urltxt.toLowerCase().contains("mediafire") )
            {
                return Services.MEDIAFIRE;
            }
            else if(Urltxt.toLowerCase().contains("solidfiles") )
            {
                return Services.SOLIDFILES;
            }
            else if(Urltxt.toLowerCase().contains("vk.com") )
            {
                return Services.VK;
            }
            else if(Urltxt.toLowerCase().contains("sendvid") )
            {
                return Services.SENDVID;
            }
            else if(Urltxt.toLowerCase().contains("vidoza") )
            {
                return Services.VIDEOZA;
            }
            else if(Urltxt.toLowerCase().contains("sck.io") )
            {
                return Services.SNACKVIDEO;
            }
            else if(Urltxt.toLowerCase().contains("api.zilivideo") )
            {
                return Services.ZILI;
            }
            else if(Urltxt.toLowerCase().contains("funimate") )
            {
                return Services.FUNIMATE;
            }
            else if(Urltxt.toLowerCase().contains("byte.co") )
            {
                return Services.BYTE;
            }
            else if(Urltxt.toLowerCase().contains("byte.co") )
            {
                return Services.BYTE;
            }
            else if(Urltxt.toLowerCase().contains("v.mxtakatak") )
            {
                return Services.MXTAKATAK;
            }
            else if(Urltxt.toLowerCase().contains("share.mxtakatak") )
            {
                return Services.MXTAKATAK;
            }
            else if(Urltxt.toLowerCase().contains("mxtakatak.com") )
            {
                return Services.MXTAKATAK;
            }
            else if(Urltxt.toLowerCase().contains("fairtok") )
            {
                return Services.FAIRTOK;
            }
            else if(Urltxt.toLowerCase().contains("raask.in") )
            {
                return Services.RAASK;
            }
            else if(Urltxt.toLowerCase().contains("bemate.co") )
            {
                return Services.OJOO;
            }
            else if(Urltxt.toLowerCase().contains("imgur.com") )
            {
                return Services.IMGUR;
            }
            else if(Urltxt.toLowerCase().contains("tumblr.com") )
            {
                return Services.TUMBLR;
            }
            else if(Urltxt.toLowerCase().contains("imdb.com") )
            {
                return Services.IMDB;
            }
            else if(Urltxt.toLowerCase().contains("pinterest.com") )
            {
                return Services.Pinterest;
            }
            else if(Urltxt.toLowerCase().contains("flic.kr") )
            {
                return Services.Flickr;
            }
            else if(Urltxt.toLowerCase().contains("mojapp.in") )
            {
                return Services.MOJ;
            }
            else if(Urltxt.toLowerCase().contains("reddit.com") )
            {
                return Services.REDDIT;
            }
            else if(Urltxt.toLowerCase().contains("soundcloud.com") )
            {
                return Services.Soundcloud;
            }
            else if(Urltxt.toLowerCase().contains("fansubs.tv") )
            {
                return Services.fansubs;
            }
            else if(Urltxt.toLowerCase().contains("https://espn") || (Urltxt.toLowerCase().contains("http://espn")) || (Urltxt.toLowerCase().contains("http://www.espn")) || (Urltxt.toLowerCase().contains("https://www.espn")) )
            {
                return Services.espn;
            }
            else if(Urltxt.toLowerCase().contains("9gag.com") )
            {
                return Services.nonegag;
            }
            else if(Urltxt.toLowerCase().contains("bilibili.com") )
            {
                return Services.bilibili;
            }
            else if(Urltxt.toLowerCase().contains("blogspot.com") )
            {
                return Services.blogger;
            }
            else if(Urltxt.toLowerCase().contains("izlesene.com") )
            {
                return Services.izlesene;
            }
            else if(Urltxt.toLowerCase().contains("liveleak.com") )
            {
                return Services.liveleak;
            }
            else if(Urltxt.toLowerCase().contains("bitchute.com") )
            {
                return Services.bitchute;
            }
            else if(Urltxt.toLowerCase().contains("linkedin.com") )
            {
                return Services.linkedin;
            }
            else if(Urltxt.toLowerCase().contains("popcornflix.com") )
            {
                return Services.popcornflix;
            }
            else if(Urltxt.toLowerCase().contains("me.me") )
            {
                return Services.meme;
            }
            else if(Urltxt.toLowerCase().contains("kickstarter") )
            {
                return Services.kickstarter;
            }
            else if(Urltxt.toLowerCase().contains("vlive.tv") )
            {
                return Services.vlive;
            }
            else if(Urltxt.toLowerCase().contains("vlipsy.com") )
            {
                return Services.vlipsy;
            }
            else if(Urltxt.toLowerCase().contains("vidlit.com") )
            {
                return Services.vidlit;
            }
            else if(Urltxt.toLowerCase().contains("gloria.tv") )
            {
                return Services.gloriatv;
            }
            else if(Urltxt.toLowerCase().contains("wwe.com") )
            {
                return Services.wwe;
            }
            else if(Urltxt.toLowerCase().contains("aparat.com") )
            {
                return Services.aparat;
            }
            else if(Urltxt.toLowerCase().contains("1tv.ru") )
            {
                return Services.onetvru;
            }
            else if(Urltxt.toLowerCase().contains("allocine.fr") )
            {
                return Services.allocine;
            }
            else if(Urltxt.toLowerCase().contains("tezpage.com") )
            {
                return Services.veer;
            }
            else if(Urltxt.toLowerCase().contains("kooapp.com") )
            {
                return Services.kooapp;
            }
            else if(Urltxt.toLowerCase().contains("ifunny.co") )
            {
                return Services.IFUNNY;
            }
            else if(Urltxt.toLowerCase().contains("streamable.com") )
            {
                return Services.streamable;
            }
            else if(Urltxt.toLowerCase().contains("gfycat.com") )
            {
                return Services.gfycat;
            }
            else if(Urltxt.toLowerCase().contains("fthis.gr") )
            {
                return Services.fthis;
            }
            else if(Urltxt.toLowerCase().contains("fireworktv.com") )
            {
                return Services.fireworktv;
            }
            else if(Urltxt.toLowerCase().contains("coub.com") )
            {
                return Services.coub;
            }
            else if(Urltxt.toLowerCase().contains("rumble.com") )
            {
                return Services.rumble;
            }
            else if(Urltxt.toLowerCase().contains("4shared.com") )
            {
                return Services.fourshared;
            }
            else if(Urltxt.toLowerCase().contains("ted.com") )
            {
                return Services.ted;
            }
            else if(Urltxt.toLowerCase().contains("4anime.to") )
            {
                return Services.ted;
            }
            else if(Urltxt.toLowerCase().contains("pdisk.net") )
            {
                return Services.pdisk;
            }
            else if(Urltxt.toLowerCase().contains("l.tiki.video") )
            {
                return Services.tiki;
            }
            else if(Urltxt.toLowerCase().contains("lomotif.com") )
            {
                return Services.Lomotif;
            }
            else
            {
                return  null;
            }
    }

    public static List<String> extractUrls(String input) {
        List<String> result = new ArrayList<String>();

        input=input.trim();
        if(URLUtil.isValidUrl(input))
        {
            result.add(input);
        }
        else
        {
            Pattern pattern = Pattern.compile(
                    "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
                            "(\\w+:)?(([-\\w]+\\.)+(com|org|net|gov" +
                            "|mil|biz|info|mobi|name|aero|jobs|museum" +
                            "|travel|[a-z]{2}))(:[\\d]{1,5})?" +
                            "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                            "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                            "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                            "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                            "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                            "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                result.add(matcher.group());
            }
        }

        return result;
    }

    public static String formatFileSize(long size) {
        String hrSize = null;

        double b = size;
        double k = size/1024.0;
        double m = ((size/1024.0)/1024.0);
        double g = (((size/1024.0)/1024.0)/1024.0);
        double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if ( t>1 ) {
            hrSize = dec.format(t).concat(" TB");
        } else if ( g>1 ) {
            hrSize = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            hrSize = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }

    public static void disableSSLCertificateChecking() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                // Not implemented
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                // Not implemented
            }
        }};

        try {
            SSLContext sc = SSLContext.getInstance("TLS");

            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                @SuppressLint("BadHostnameVerifier")

                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
