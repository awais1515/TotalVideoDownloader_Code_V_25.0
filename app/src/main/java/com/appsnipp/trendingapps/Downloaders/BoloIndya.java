package com.appsnipp.trendingapps.Downloaders;
import android.content.Context;
import android.widget.Toast;
import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import es.dmoral.toasty.Toasty;

public class BoloIndya extends Base {
    public BoloIndya(Context c, AsyncResponse asyncResponse)
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
            Elements SS=document.getElementsByTag("script");
            for (Element element : SS)
            {
                if (element.data().contains("videoFileCDN"))
                {
                    String dsdd=element.data();
                    String[] Lines=dsdd.split("\n");
                    for (String line :Lines) {
                        if(line.contains("var videoFileCDN=\"https"))
                        {
                            this.videoUrl=line.split("=")[1].replace("\"","").replace("\"","").replace(";","");
                        }
                    }
                }
            }

            if(this.videoUrl.startsWith("//"))
            {
                this.videoUrl="https:"+this.videoUrl;
            }
            if(! this.videoUrl.equals("") && this.videoUrl !=null )
            {
                String ThisUrl = this.videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(mainContext.getResources().getString(R.string.BoloIndya_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryBoloIndya;
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
