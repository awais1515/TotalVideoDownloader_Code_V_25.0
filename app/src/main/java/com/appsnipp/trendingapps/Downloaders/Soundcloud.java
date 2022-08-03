package com.appsnipp.trendingapps.Downloaders;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Soundcloud  extends AsyncTask<String, Void, String> {


    public AsyncResponse delegate = null;
    public String VideoUrl="";
    Context MainContext;
    ProgressDialog progressDialog;

    public Soundcloud(Context c, AsyncResponse asyncResponse)
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
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .build();

        String URL= strings[0].replace("\n","");

        RequestBody body = new FormBody.Builder()
                .add("url", URL)
                .build();
        Request request = new Request.Builder()
                .url("https://soundclouddownloader.org/prepare.php")
                .post(body)
                .build();

        Response response= null;
        try {
            response = client.newCall(request ).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            DownloadFailed();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String b )
    {
        try
        {
            this.VideoUrl="https://soundclouddownloader.org/downloads/"+ new JSONObject(b).getString("file_name");

            StringBuilder sb = new StringBuilder();
            sb.append(MainContext.getResources().getString(R.string.Soundcloud_Suffix));
            sb.append(System.currentTimeMillis());
            sb.append(".mp3");
            String str3 = Utils.RootDirectorySoundcloud;
            long DownloadId= Utils.startDownload(this.VideoUrl, str3, MainContext, sb.toString());
            progressDialog.hide();
            Toasty.success(MainContext, MainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
            delegate.processFinish(DownloadId);
        }
        catch (Exception ex)
        {
            DownloadFailed();
        }
    }

    protected void DownloadFailed()
    {
        progressDialog.hide();
        Toasty.error(MainContext, MainContext.getResources().getString(R.string.EnabletoDownload), Toast.LENGTH_SHORT, true).show();
    }
}
