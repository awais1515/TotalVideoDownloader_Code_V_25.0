package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import es.dmoral.toasty.Toasty;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Tiktok extends Base {

    String Token="";
    static String VideoUrl="";
    public Tiktok(Context c, AsyncResponse asyncResponse)
    {
        super(c,asyncResponse);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            Tiktok.VideoUrl= Utils.SanitiseURL(strings[0]) ;
            this.roposoDoc = Jsoup.connect( "https://tokconv.herokuapp.com/?url=" + Tiktok.VideoUrl)
                    //.data("tiktok-url", Tiktok.VideoUrl)
                    .userAgent("Mozilla")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            DownloadFailed("Unable to download");
        }
        return this.roposoDoc;
    }

    @Override
    public void onPostExecute(Document document)
    {
        try
        {
            try {
                // String htmlString = document.select("button.btn.btn-info.mt-3").last().attr("onclick");
                // htmlString=htmlString.replace("window.location.href='","");
                // htmlString=htmlString.replace("'","");

                String VideoUrl=new JSONObject(document.html().toString()).getString("no_watermark");

                String ThisUrl =VideoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.tiktok_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryTikTok;
                long DownloadId= Utils.startDownload(ThisUrl, str3,mainContext , sb.toString());
                progressDialog.hide();
                Toasty.success(mainContext, mainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                delegate.processFinish(DownloadId);

            } catch (NullPointerException e2) {
                e2.printStackTrace();
                DownloadFailed();
            }

        }
        catch(Exception ex)
        {
            DownloadFailed();
        }
    }
}
