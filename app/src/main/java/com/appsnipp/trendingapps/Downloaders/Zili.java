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

import es.dmoral.toasty.Toasty;

public class Zili extends Base {

    Activity act;
    WebView webView;
    public Zili(Context c, AsyncResponse asyncResponse, Activity _act)
    {
        super(c,asyncResponse);
        act=_act;
        webView=(WebView) act.findViewById(R.id.webview);
    }


    public void LoadVideo(String url)
    {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (!consoleMessage.message().startsWith("MAGIC")) {
                    return false;
                }
                String attr = ((Element) Jsoup.parse(consoleMessage.message().substring(5)).getElementsByTag("video").get(0)).attr("src");

                if(! attr.equals("") && attr !=null )
                {
                    String ThisUrl = attr;
                    StringBuilder sb = new StringBuilder();
                    sb.append(mainContext.getResources().getString(R.string.Zili_Suffix));
                    sb.append(System.currentTimeMillis());
                    sb.append(".mp4");
                    String str3 = Utils.RootDirectoryZili;
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
                        webView.loadUrl("javascript:console.log('MAGIC'+document.getElementsByTagName('html')[0].innerHTML);");
                    }
                }, 1000);
            }
        });
        webView.loadUrl(url);
    }
    @Override
    protected Document doInBackground(String... strings) {
        try {

            this.videoUrl=strings[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.roposoDoc;
    }


    @Override
    public void onPostExecute(Document document)
    {

    }
}
