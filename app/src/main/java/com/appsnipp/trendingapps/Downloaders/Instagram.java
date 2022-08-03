package com.appsnipp.trendingapps.Downloaders;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import es.dmoral.toasty.Toasty;


public class Instagram extends AsyncTask<String, Void, String> {
    public AsyncResponse delegate = null;
    public String VideoUrl="";
    Context MainContext;
    ProgressDialog progressDialog;

    public Instagram(Context c, AsyncResponse asyncResponse)
    {
        this.delegate=asyncResponse;
        Utils.createFileFolder();
        MainContext=c;
        progressDialog=new ProgressDialog(MainContext);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        StringBuilder result = new StringBuilder();
        try {
            String URLStrin="";
            if(strings[0].contains("?"))
            {
                URLStrin=strings[0] + "&__a=1";
            }
            else
            {
                URLStrin=strings[0] +"?__a=1";
            }

            URL url = new URL(URLStrin );
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return result.toString();
    }

    @Override
    public void onPostExecute(String result)
    {
        try {
            JSONObject jObject = new JSONObject(result);
            JSONObject graphql = jObject.getJSONObject("graphql");
            JSONObject shortcode_media = graphql.getJSONObject("shortcode_media");
            this.VideoUrl=shortcode_media.getString("video_url");
            if(! this.VideoUrl.equals("") && this.VideoUrl !=null )
            {
                String ThisUrl = this.VideoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(MainContext.getResources().getString(R.string.Instagram_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryInsta;
                long DownloadId= Utils.startDownload(ThisUrl, str3,MainContext , sb.toString());
                progressDialog.hide();
                Toasty.success(MainContext, MainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                delegate.processFinish(DownloadId);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            DownloadFailed();
        }


    }
    private void DownloadFailed()
    {
        progressDialog.hide();
        Toasty.error(MainContext, MainContext.getResources().getString(R.string.EnabletoDownload), Toast.LENGTH_SHORT, true).show();
    }

}
