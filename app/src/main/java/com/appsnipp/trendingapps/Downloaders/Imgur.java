package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.jsoup.nodes.Document;

import es.dmoral.toasty.Toasty;

public class Imgur extends Base {

    public Imgur(Context c, AsyncResponse asyncResponse) {
        super(c, asyncResponse);
    }

    @Override
    protected Document doInBackground(String... strings) {

        String URL= strings[0].replace("\n","").replace(" ","");
        String VideoId=URL.split("/")[ URL.split("/").length  -1 ];
        this.videoUrl="https://i.imgur.com/"+VideoId+".mp4";
        return null;
    }

    @Override
    public void onPostExecute(Document document)
    {
        try
        {
            if(! this.videoUrl.equals("") && this.videoUrl !=null )
            {
                String ThisUrl = this.videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.Imgur_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryIMGUR;
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
