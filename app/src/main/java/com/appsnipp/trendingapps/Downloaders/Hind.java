package com.appsnipp.trendingapps.Downloaders;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.dmoral.toasty.Toasty;

public class Hind extends Base {


    public Hind(Context c, AsyncResponse asyncResponse)
    {
        super(c,asyncResponse);
        this.delegate=asyncResponse;
        Utils.createFileFolder();
        mainContext=c;
        progressDialog=new ProgressDialog(mainContext);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    protected Document doInBackground(String... strings) {
        try {
            this.roposoDoc = Jsoup.connect(strings[0]).get();
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
        try {
            Elements SS=document.getElementsByTag("script");
            for (Element element : SS)
            {
                if (element.data().contains("window.__STATE__"))
                {
                    String dsdd=element.html();
                    dsdd=dsdd.replace("window.__STATE__","").replace(";","").replace("=","");
                    JSONArray jsA=new JSONArray("["+dsdd+"]");
                    JSONObject SSd=new JSONObject( jsA.get(0).toString());
                    this.videoUrl=String.valueOf(new JSONObject(SSd.getJSONObject("feed").getJSONArray("feed").get(0).toString()).get("download_media") );
                    if(! this.videoUrl.equals("") && this.videoUrl !=null )
                    {
                        String ThisUrl = this.videoUrl;
                        StringBuilder sb = new StringBuilder();
                        sb.append(mainContext.getResources().getString(R.string.Hind_Suffix));
                        sb.append(System.currentTimeMillis());
                        sb.append(".mp4");
                        String str3 = Utils.RootDirectoryHind;
                        long DownloadId= Utils.startDownload(ThisUrl, str3,mainContext , sb.toString());
                        progressDialog.hide();
                        Toasty.success(mainContext, mainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                        delegate.processFinish(DownloadId);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            DownloadFailed();
        }
    }
}
