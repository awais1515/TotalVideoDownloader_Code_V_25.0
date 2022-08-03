package com.appsnipp.trendingapps.Downloaders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;
import com.google.android.gms.common.internal.ImagesContract;
import com.yxcorp.gifshow.util.CPU;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

import es.dmoral.toasty.Toasty;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SnackVideo extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;
    public String VideoUrl="";
    Context MainContext;
    ProgressDialog progressDialog;
    Activity act;

    public SnackVideo(Context c, AsyncResponse asyncResponse, Activity _Act)
    {
        this.delegate=asyncResponse;
        act=_Act;
        Utils.createFileFolder();
        MainContext=c;
        progressDialog=new ProgressDialog(MainContext);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);



    }

    @Override
    protected String doInBackground(String... strings) {


        try
        {
            if(Utils.IsDemoMode==true)
            {
                return null;
            }

            URI uri;
            try {
                uri = new URI(strings[0]);
            } catch (Exception e) {
                e.printStackTrace();
                uri = null;
            }
            String[] split = uri.getPath().split("/");
            String str2 = split[split.length - 1];
            String str3 = "android";
            String str4 = "8c46a905";
            StringBuilder sb = new StringBuilder("ANDROID_");
            sb.append(Settings.Secure.getString(act.getContentResolver(), "android_id"));
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList.add("mod=OnePlus(ONEPLUS A5000)");
            arrayList.add("lon=0");
            arrayList.add("country_code=in");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("did=");
            sb2.append(sb);
            arrayList.add(sb2.toString());
            arrayList.add("app=1");
            arrayList.add("oc=UNKNOWN");
            arrayList.add("egid=");
            arrayList.add("ud=0");
            arrayList.add("c=GOOGLE_PLAY");
            arrayList.add("sys=KWAI_BULLDOG_ANDROID_9");
            arrayList.add("appver=2.7.1.153");
            arrayList.add("mcc=0");
            arrayList.add("language=en-in");
            arrayList.add("lat=0");
            arrayList.add("ver=2.7");
            arrayList2.addAll(arrayList);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("shortKey=");
            sb3.append(str2);
            arrayList2.add(sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append("os=");
            sb4.append(str3);
            arrayList2.add(sb4.toString());
            StringBuilder sb5 = new StringBuilder();
            sb5.append("client_key=");
            sb5.append(str4);
            arrayList2.add(sb5.toString());
            try {
                Collections.sort(arrayList2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String clockData = CPU.getClockData(act, TextUtils.join("", arrayList2).getBytes(Charset.forName("UTF-8")), 0);
            Log.d("clockData",clockData);
            StringBuilder sb6 = new StringBuilder();
            sb6.append("https://g-api.snackvideo.com/rest/bulldog/share/get?");
            sb6.append(TextUtils.join("&", arrayList));
            OkHttpClient client = new OkHttpClient();

            RequestBody formbody=new FormBody.Builder()
                    .addEncoded("shortKey", str2)
                    .addEncoded("os", "android")
                    .addEncoded("sig", clockData)
                    .addEncoded("client_key", "8c46a905")
                    .build();

            Request request = new Request.Builder()
                    .url(sb6.toString())
                    .post(formbody)
                    .build();
            try {
                Response response = client.newCall(request ).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            return "Error";
        }

        return null;
    }

    @Override
    protected void onPostExecute(String b )
    {
        try
        {
            JSONObject jsonObject=new JSONObject(b);
            String videoUrl =new JSONObject(jsonObject.getJSONObject("photo").getJSONArray("main_mv_urls").get(0).toString()).get("url").toString();
            if(! videoUrl.equals("") && videoUrl !=null )
            {
                String ThisUrl = videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(MainContext.getResources().getString(R.string.SnackVideo_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectorySnackVideo;
                long DownloadId= Utils.startDownload(ThisUrl, str3,MainContext , sb.toString());
                progressDialog.hide();
                Toasty.success(MainContext, MainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                delegate.processFinish(DownloadId);
            }
            else
            {
                DownloadFailed();
            }

        }
        catch (Exception ex)
        {
            DownloadFailed();
        }
    }

    protected void DownloadFailed()
    {
        if(Utils.IsDemoMode==false)
        {
            progressDialog.hide();
            Toasty.error(MainContext,"Server is busy, Please try again after some time", Toast.LENGTH_SHORT, true).show();
        }
        else
        {
            progressDialog.hide();
            Toasty.error(MainContext,"For Security reason, Snack Video Downloader is not available in Demo Mode !", Toast.LENGTH_SHORT, true).show();
        }
    }
}
