package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class Rizzle extends Base {


    public Rizzle(Context c, AsyncResponse asyncResponse)
    {
        super(c,asyncResponse);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            this.roposoDoc = Jsoup.connect(strings[0]).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.roposoDoc;
    }
    @Override
    public void onPostExecute(Document document)
    {
        try {
            String html = document.select("script[id=\"__NEXT_DATA__\"]").last().html();
            if (!html.equals("")) {
                this.videoUrl = String.valueOf(new JSONObject(html).getJSONObject("props").getJSONObject("pageProps").getJSONObject("post").getJSONObject("video").get("originalUrl"));
                if(! this.videoUrl.equals("") && this.videoUrl !=null )
                {
                    String ThisUrl = this.videoUrl;
                    StringBuilder sb = new StringBuilder();
                    sb.append(mainContext.getResources().getString(R.string.Rizzle_Suffix));
                    sb.append(System.currentTimeMillis());
                    sb.append(".mp4");
                    String str3 = Utils.RootDirectoryRizzle;
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
        catch (Exception ex)
        {
            DownloadFailed();
        }
    }
}
