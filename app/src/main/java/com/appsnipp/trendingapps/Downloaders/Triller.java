package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import es.dmoral.toasty.Toasty;

public class Triller extends Base {
    public Triller(Context c, AsyncResponse asyncResponse)
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
            this.videoUrl = document.select("video").last().attr("src");
            if(this.videoUrl.startsWith("//"))
            {
                this.videoUrl="https:"+this.videoUrl;
            }
            if(! this.videoUrl.equals("") && this.videoUrl !=null )
            {
                String ThisUrl = this.videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.Triller_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryTriller;
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
        catch(Exception ex)
        {
            DownloadFailed();
        }
    }
}
