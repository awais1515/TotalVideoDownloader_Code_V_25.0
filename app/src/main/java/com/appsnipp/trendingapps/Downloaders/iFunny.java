package com.appsnipp.trendingapps.Downloaders;

import android.content.Context;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class iFunny extends Base {

    public iFunny(Context c, AsyncResponse asyncResponse)
    {
        super(c,asyncResponse);
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
        try
        {
            String html = document.select("script[class=\"js-media-template\"]").first().html();
            String imgRegex = "<video[^>]+poster\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
            Pattern p = Pattern.compile(imgRegex);
            Matcher m = p.matcher(html);
            while(m.find())
            {
                String imgSrc = m.group(1);
                this.videoUrl=imgSrc.replace("jpg","mp4").replace("images","videos").replace("_3","_1");
            }
            if(! this.videoUrl.equals("") && this.videoUrl !=null )
            {
                String ThisUrl = this.videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.Ifunny_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryIFUNNY;
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
