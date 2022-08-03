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

public class Vimeo extends Base {
    public Vimeo(Context c, AsyncResponse asyncResponse)
    {
        super(c,asyncResponse);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            this.roposoDoc = Jsoup.connect("https://www.expertsphp.com/instagram-reels-downloader.php")
                    .data("url", strings[0])
                    .userAgent("Mozilla")
                    .post();
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
            this.videoUrl= document.select("a[download]").first().attr("href");
            if(! this.videoUrl.equals("") && this.videoUrl !=null )
            {
                String ThisUrl = this.videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.Vimeo_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryvimeo;
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
        {DownloadFailed();}
    }
}
