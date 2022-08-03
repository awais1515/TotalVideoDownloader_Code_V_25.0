package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class Reddit extends Base {

    public Reddit(Context c, AsyncResponse asyncResponse)
    {
        super(c,asyncResponse);
    }




    @Override
    protected Document doInBackground(String... strings) {
        try {
            this.roposoDoc = Jsoup.connect("https://redditsave.com/info?url="+strings[0]).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.roposoDoc;
    }
    @Override
    public void onPostExecute(Document document)
    {
        String html = document.select(".downloadbutton").first().attr("href");
        if (!html.equals("")) {
            this.videoUrl =html;

            if(! this.videoUrl.equals("") && this.videoUrl !=null )
            {
                String ThisUrl = this.videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.Reddit_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryREDDIT;
                long DownloadId= Utils.startDownload(ThisUrl, str3,mainContext , sb.toString());
                progressDialog.hide();
                Toasty.success(mainContext, mainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                delegate.processFinish(DownloadId);
            }
        }
        else
        {
            DownloadFailed();
        }
    }
}
