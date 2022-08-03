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

public class Ojoo extends Base {

    WebView webView;
    Activity act;
    String URL="";
    boolean Downloaded=false;
    public Ojoo(Context c, AsyncResponse asyncResponse, Activity _act) {
        super(c, asyncResponse);
        act=_act;
        webView=(WebView) act.findViewById(R.id.webview);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            Downloaded=false;
            this.roposoDoc = Jsoup.connect(strings[0]).get();
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
            this.videoUrl= document.select("video[class=\"vjs-tech\"]").last().attr("src");
            if(! this.videoUrl.equals("") && this.videoUrl !=null )
            {
                String ThisUrl = this.videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.Ojoo_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryOJOO;
                long DownloadId= Utils.startDownload(ThisUrl, str3,mainContext , sb.toString());
                progressDialog.hide();
                Toasty.success(mainContext, mainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                delegate.processFinish(DownloadId);
            }
            else
            {
                //DownloadFailed();

            }
        }
        catch(Exception ex)
        {
            webView.getSettings().setJavaScriptEnabled(true);

            webView.setWebChromeClient(new WebChromeClient() {
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    if (!consoleMessage.message().startsWith("MAGIC")) {
                        return false;
                    }
                    String attr = ((Element) Jsoup.parse(consoleMessage.message().substring(5)).select("video[class=\"vjs-tech\"]").last()).attr("src");

                    if(! attr.equals("") && attr !=null )
                    {
                            String ThisUrl = attr;
                            StringBuilder sb = new StringBuilder();
                            sb.append(mainContext.getResources().getString(R.string.Ojoo_Suffix));
                            sb.append(System.currentTimeMillis());
                            sb.append(".mp4");
                            String str3 = Utils.RootDirectoryOJOO;
                            long DownloadId= Utils.startDownload(ThisUrl, str3,mainContext , sb.toString());
                            progressDialog.hide();
                            Toasty.success(mainContext, mainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                            delegate.processFinish(DownloadId);
                    }
                    else
                    {
                        DownloadFailed();
                    }
                    String attdr="";
                    return true;
                }
            });

            webView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(final WebView webView, String str) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if(Downloaded==false)
                            {
                                Downloaded=true;
                                webView.loadUrl("javascript:console.log('MAGIC'+document.getElementsByTagName('html')[0].innerHTML);");
                            }
                        }
                    }, 1000);
                }
            });
            webView.loadUrl(this.URL);
        }
    }
}
