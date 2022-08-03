package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class tiki  extends Base {

    public tiki(Context c, AsyncResponse asyncResponse)
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
        try
        {
            Elements SS=document.getElementsByTag("script");
            for (Element element : SS)
            {
                if (element.data().contains("window.data = "))
                {
                    String data=element.data();
                    data=data.replace("window.data = ","");
                    String videoUrl=new JSONObject(data).getJSONObject("originVideoInfo").getString("video_url");
                    this.videoUrl=videoUrl;
                    if(! this.videoUrl.equals("") && this.videoUrl !=null )
                    {
                        this.videoUrl= this.videoUrl.replace("_4","");
                        String ThisUrl = this.videoUrl;
                        StringBuilder sb = new StringBuilder();
                        sb.append(mainContext.getResources().getString(R.string.Tiki_Suffix));
                        sb.append(System.currentTimeMillis());
                        sb.append(".mp4");
                        String str3 = Utils.RootDirectorytiki;
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
            }




        }
        catch (Exception Ex)
        {
            DownloadFailed();
        }
    }
}
