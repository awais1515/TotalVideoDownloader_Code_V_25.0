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

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import es.dmoral.toasty.Toasty;

public class Facebook extends Base
{

    Activity act;
    WebView webView;
    String URL="";
    public Facebook(Context c, AsyncResponse asyncResponse,Activity _act)
    {
        super(c,asyncResponse);
        act=_act;
        webView=(WebView) act.findViewById(R.id.webview);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            if(strings[0].contains("posts"))
            {
                this.roposoDoc = Jsoup.connect(strings[0])
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Safari/537.36")
                        .referrer("http://www.google.com")
                        .get();
            }
            else
            {
                this.roposoDoc = Jsoup.connect(strings[0])
                        //.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        //.referrer("http://www.google.com")
                        .get();
            }
            this.URL=strings[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.roposoDoc;
    }

    @Override
    public void onPostExecute(Document document)
    {
        try
        {
            try
            {
                String data=document.toString();
                try
                {
                    String split_1= data.split("hd_src:\"")[1];
                    this.videoUrl=split_1.split("\",")[0];
                }
                catch (Exception ee)
                {
                    String split_1= data.split("hd_src_no_ratelimit:\"")[1];
                    this.videoUrl=split_1.split("\",")[0];
                }
            }
            catch (Exception exx)
            {
                this.videoUrl= document.select("meta[property=\"og:video\"]").last().attr("content");
            }

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

            }
        }
        catch (Exception Ex)
        {
            try
            {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebChromeClient(new WebChromeClient() {
                    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                        if (!consoleMessage.message().startsWith("MAGIC")) {
                            return false;
                        }
                       try
                       {
                           String fbHTML=consoleMessage.message().substring(5);
                           String attr = ((Element) Jsoup.parse(consoleMessage.message().substring(5)).select("meta[property=\"og:video:url\"]").last()).attr("content");

                           if(! attr.equals("") && attr !=null )
                           {
                               String ThisUrl = attr;
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
                               DownloadFailedLocal();
                           }
                       }
                       catch(Exception ex){
                           try
                           {
                               String attr = consoleMessage.message().substring(5);

                               String[] parts = attr.split("https:\\\\/\\\\/video.");
                               String FirstLink= "https://video."+ parts[1].replaceAll("&amp;","&");

                               FirstLink=FirstLink.replaceAll("\\/","/");

                               String[] paths2=FirstLink.split("&quot;");
                               String VideoUrl=paths2[0].replaceAll("\\\\","");

                               String ThisUrl = VideoUrl;
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
                           catch (Exception ex3){



                               DownloadFailedLocal();
                           }


                       }
                        return true;
                    }
                });
                webView.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(final WebView webView, String str) {
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                webView.loadUrl("javascript:console.log('MAGIC'+document.getElementsByTagName('html')[0].innerHTML);");
                            }
                        }, 1000);
                    }
                });
                webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6");
                webView.loadUrl(this.URL);
            }
            catch(Exception eex)
            {
                DownloadFailedLocal();
            }

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
