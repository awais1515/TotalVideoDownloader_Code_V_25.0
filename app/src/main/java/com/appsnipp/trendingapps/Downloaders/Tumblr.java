package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class Tumblr extends Base {
    public Tumblr(Context c, AsyncResponse asyncResponse) {
        super(c, asyncResponse);
    }

    @Override
    protected Document doInBackground(String... strings) {
        String URL= strings[0].replace("\n","").replace(" ","");
        try {
            this.roposoDoc = Jsoup.connect(URL).get();
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
            this.videoUrl= document.select("meta[property=\"og:video:secure_url\"]").last().attr("content");
            if(! this.videoUrl.equals("") && this.videoUrl !=null )
            {
                String ThisUrl = this.videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.Tumblr_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryTUMBLR;
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
        catch (Exception ex)
        {
            DownloadFailed();
        }
    }
}
