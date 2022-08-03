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

public class Flickr extends Base {

    Activity act;
    WebView webView;
    String URL="";
    boolean  IsDownloading=false;

    public Flickr(Context c, AsyncResponse asyncResponse,Activity _act) {
        super(c, asyncResponse);
        act=_act;
        webView=(WebView) act.findViewById(R.id.webview);
    }

    @Override
    protected Document doInBackground(String... strings) {
        this.URL= strings[0].replace("\n","").replace(" ","");
        return null;
    }

    @Override
    public void onPostExecute(Document document)
    {
        try
        {
            IsDownloading=false;

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient() {
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    if (!consoleMessage.message().startsWith("MAGIC")) {
                        return false;
                    }

                    if(IsDownloading==false)
                    {
                        String attr = ((Element) Jsoup.parse(consoleMessage.message().substring(5)).select("video#video_1_html5_api").last()).attr("src");
                        if(! attr.equals("") && attr !=null )
                        {
                            IsDownloading=true;
                            String ThisUrl = attr;
                            StringBuilder sb = new StringBuilder();
                            sb.append(mainContext.getResources().getString(R.string.Flickr_Suffix));
                            sb.append(System.currentTimeMillis());
                            sb.append(".mp4");
                            String str3 = Utils.RootDirectoryFlickr;
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
                    }, 10000);
                }
            });
            webView.loadUrl(this.URL);
        }
        catch (Exception ex)
        {
            DownloadFailed();
        }
    }
}
