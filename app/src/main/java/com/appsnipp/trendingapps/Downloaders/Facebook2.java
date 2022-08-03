package com.appsnipp.trendingapps.Downloaders;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class Facebook2 extends Base
{

    Activity act;
    WebView webView;
    String URL="";
    public Facebook2(Context c, AsyncResponse asyncResponse, Activity _act)
    {
        super(c,asyncResponse);
        act=_act;
        webView=(WebView) act.findViewById(R.id.webview);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            this.roposoDoc = Jsoup.connect("https://fdown.net/download.php")
                    .data("URLz", strings[0])
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Safari/537.36")
                    .referrer("http://www.google.com")
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.roposoDoc;
    }

    @Override
    public void onPostExecute(Document document)
    {
        this.videoUrl= document.select("a#hdlink").last().attr("href");
        if(! this.videoUrl.equals("") && this.videoUrl !=null )
        {
            String ThisUrl = this.videoUrl;
            StringBuilder sb = new StringBuilder();
            sb.append(mainContext.getResources().getString(R.string.Facebook_Suffix));
            sb.append(System.currentTimeMillis());
            sb.append(".mp4");
            String str3 = Utils.RootDirectoryFacebook;
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
    protected void DownloadFailedLocal()
    {
        progressDialog.hide();
        String errorMsg=mainContext.getResources().getString(R.string.EnabletoDownload);
        if(this.URL.contains("/posts/"))
        {
            errorMsg="Error : You are providing Post url, its not video url";
        }
        Toasty.error(mainContext, errorMsg, Toast.LENGTH_LONG, true).show();
    }

}
