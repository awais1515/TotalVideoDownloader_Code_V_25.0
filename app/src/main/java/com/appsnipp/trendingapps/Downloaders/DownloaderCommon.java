package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.appsnipp.trendingapps.Models.ServicesModel;
import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloaderCommon extends Base {

    public static String APIURL="http://videodownloader.shivjagar.co.in";

    ServicesModel currentService;
    public DownloaderCommon(Context c, AsyncResponse asyncResponse,ServicesModel service)
    {
        super(c,asyncResponse);
        currentService=service;
    }
    @Override
    protected Document doInBackground(String... strings) {
        try {
            String Url= Utils.SanitiseURL(strings[0]) ;
            this.roposoDoc = Jsoup.connect( DownloaderCommon.APIURL +"/services/downloader_api.php")
                    .data("url",Url)
                    .userAgent("Mozilla")
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.roposoDoc;
    }

    @Override
    public void onPostExecute(Document document){

        try{
            JSONObject jObbject=new JSONObject(document.text());
            JSONArray VideoResult=jObbject.getJSONArray("VideoResult");
            boolean InternalDownload=false;
            try{
                InternalDownload=jObbject.getBoolean("InternalDownload");
            }
            catch (Exception ex){

            }

            if(InternalDownload==true && VideoResult.length()>0){
                JSONObject firstObject=VideoResult.getJSONObject(0);
                this.videoUrl=firstObject.getString("VideoUrl");
                new LoadExtirnalLink().execute(videoUrl,currentService.getName());
            }

            else if(VideoResult.length()>0 && InternalDownload !=true  ){
                JSONObject firstObject=VideoResult.getJSONObject(0);
                this.videoUrl=firstObject.getString("VideoUrl");
                if(this.videoUrl.startsWith("//")){
                    this.videoUrl="https:"+this.videoUrl;
                }
                if(! this.videoUrl.equals("") && this.videoUrl !=null )
                {
                    String ThisUrl = this.videoUrl;
                    StringBuilder sb = new StringBuilder();
                    sb.append(currentService.getName() +"_");
                    sb.append(System.currentTimeMillis());
                    sb.append(".mp4");
                    String str3= currentService. getRootDirectoryStr();
                    long DownloadId= Utils.startDownload(ThisUrl, str3,mainContext , sb.toString());
                    progressDialog.hide();
                    Toasty.success(mainContext, mainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                    delegate.processFinish(DownloadId);
                }
                else
                {
                    DownloadFailed();
                }
            }
            else{
                DownloadFailed();
            }

        }
        catch (Exception ex){
            DownloadFailed();
        }
    }

    public class LoadExtirnalLink extends AsyncTask<String, Void, String>{

        public String _Title="";
        @Override
        protected String doInBackground(String... strings){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .build();;


            try {
                String URL= URLEncoder.encode(strings[0],"UTF-8");
                _Title=strings[1];
                Request request = new Request.Builder()
                        .url(DownloaderCommon.APIURL+ "/download.php?type=v&url="+URL+"&title="+strings[1])
                        .get()
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request ).execute();
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                //dialog.dismisDialoig();
                //Toasty.error(mContext,  mContext.getResources().getString(R.string.ErrorwhileDownloading), Toast.LENGTH_SHORT, true).show();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String b ){

            try
            {
                Document doc = Jsoup.parse(b);
                String DownloadURL=  doc.select("a").last().attr("href");
                DownloadURL= DownloaderCommon.APIURL+ ""+DownloadURL;


                String ThisUrl = DownloadURL;
                StringBuilder sb = new StringBuilder();
                sb.append(currentService.getName() +"_");
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3= currentService. getRootDirectoryStr();
                long DownloadId= Utils.startDownload(ThisUrl, str3,mainContext , sb.toString());
                progressDialog.hide();
                Toasty.success(mainContext, mainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                delegate.processFinish(DownloadId);

//                String DownloadableURL=DownloadURL;
//                StringBuilder sb = new StringBuilder();
//                sb.append(Utils.SanitizeName(_Title));
//                sb.append(System.currentTimeMillis());
//                sb.append(".mp4");
//                String str3 = currentService.getRootDirectoryStr();
//                Utils.startDownload(DownloadableURL, str3,mContext , sb.toString());
//                Toasty.success(mContext,  mContext.getResources().getString(R.string.DownloadingStarted), Toast.LENGTH_SHORT, true).show();
//                dialog.dismisDialoig();
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                }
            }
            catch (Exception ex){
               // dialog.dismisDialoig();
                //Toasty.error(mContext,  mContext.getResources().getString(R.string.ErrorwhileDownloading), Toast.LENGTH_SHORT, true).show();
                DownloadFailed();
            }
        }
    }


}
