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

import es.dmoral.toasty.Toasty;

public class Trell extends Base {

    public Trell(Context c, AsyncResponse asyncResponse)
    {
        super(c,asyncResponse);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            String NewUrl=strings[0].replace("-","");
            this.roposoDoc = Jsoup.connect(NewUrl).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return this.roposoDoc;
    }

    @Override
    public void onPostExecute(Document document)
    {
        try
        {
            String html = document.select("script[id=\"__NEXT_DATA__\"]").last().html();
            if (!html.equals(""))
            {
                JSONArray ss=new JSONObject(html).getJSONObject("props").getJSONObject("pageProps").getJSONObject("result").getJSONObject("result").getJSONObject("trail").getJSONArray("posts");
                JSONObject dd= new JSONObject(ss.get(0).toString());

                this.videoUrl = String.valueOf(dd.get("video"));
                if(! this.videoUrl.equals("") && this.videoUrl !=null )
                {
                    String ThisUrl = this.videoUrl;
                    StringBuilder sb = new StringBuilder();
                    sb.append(mainContext.getResources().getString(R.string.Trell_Suffix));
                    sb.append(System.currentTimeMillis());
                    sb.append(".mp4");
                    String str3 = Utils.RootDirectoryTrell;
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
