package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class Pinterest extends Base {
    public Pinterest(Context c, AsyncResponse asyncResponse) {
        super(c, asyncResponse);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            String URL= strings[0].replace("\n","").replace(" ","");
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
            String html = document.select("script[id=\"initial-state\"]").last().html();
            if (!html.equals(""))
            {
                JSONObject resourceResponse=new JSONObject( new JSONObject(html).getJSONArray("resourceResponses").get(0).toString());
                this.videoUrl=String.valueOf( resourceResponse.getJSONObject("response").getJSONObject("data").getJSONObject("videos").getJSONObject("video_list").getJSONObject("V_720P").get("url"));
                if(! this.videoUrl.equals("") && this.videoUrl !=null )
                {
                    String ThisUrl = this.videoUrl;
                    StringBuilder sb = new StringBuilder();
                    sb.append(mainContext.getResources().getString(R.string.Pinterest_Suffix));
                    sb.append(System.currentTimeMillis());
                    sb.append(".mp4");
                    String str3 = Utils.RootDirectoryPinterest;
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
