package com.appsnipp.trendingapps.Downloaders;

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

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import kotlin.text.UStringsKt;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Mxtakatak extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;
    public String VideoUrl="";
    Context MainContext;
    ProgressDialog progressDialog;

    public Mxtakatak(Context c, AsyncResponse asyncResponse)
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
                .url("http://mxtakatakvideodownloader.shivjagar.co.in/MXTakatak-service.php")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request ).execute();
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
            String DownloadableURL=new JSONObject(b).getString("videourl");;
            StringBuilder sb = new StringBuilder();
            sb.append(MainContext.getResources().getString(R.string.MXTakatak_Suffix));
            sb.append(System.currentTimeMillis());
            sb.append(".mp4");
            String str3 = Utils.RootDirectoryMXTakatak;
            long DownloadId= Utils.startDownload(DownloadableURL, str3, MainContext, sb.toString());
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
