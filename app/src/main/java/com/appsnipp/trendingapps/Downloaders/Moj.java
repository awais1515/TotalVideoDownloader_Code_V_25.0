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

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Moj extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;
    public String VideoUrl="";
    Context MainContext;
    ProgressDialog progressDialog;

    public Moj(Context c, AsyncResponse asyncResponse)
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
        String StringId=strings[0].split("/")[strings[0].split("/").length-1];
        OkHttpClient client = new OkHttpClient();

        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create("{\"appVersion\":83,\"bn\":\"broker1\",\"client\":\"android\",\"deviceId\":\"ebb088d29e7287b1\",\"message\":{\"adData\":{\"adsShown\":0,\"firstFeed\":false},\"deviceInfoKey\":\"OSyQoHJLJ4NsXPLyQePkAICh3Q0ih0bveFwm1KEV+vReMuldqo+mSyMjdhb4EeryKxk1ctAbYaDH\\nTI+PMRPZVYH5pBccAm7OT2uz69vmD/wPqGuSgWV2aVNMdM75DMb8NZn1JU2b1bo/oKs80baklsvx\\n1X7jrFPL6M5EDTdPDhs=\\n\",\"deviceInfoPayload\":\"M6g+6j6irhFT/H6MsQ/n/tEhCl7Z5QgtVfNKU8M90zTJHxqljm2263UkjRR9bRXAjmQFXXOTXJ25\\nOHRjV7L5Lw+tUCONoYfyUEzADihWfAiUgXJEcKePfZONbdXXuwGgOPeD0k4iSvI7JdzroRCScKXd\\n41CkmXFayPaRL9aqgAgs6kSoIncCWBU2gEXiX1lgPVvdmUzCZ+yi2hFA+uFOmv1MJ6dcFKKcpBM6\\nHSPIrGV+YtTyfd8nElx0kyUbE4xmjOuMrctkjnJkd2tMdxB8qOFKeYrcLzy4LZJNXyUmzs29XSE+\\nhsrMZib8fFPJhJZIyGCWqfWiURut4Bg5HxYhYhg3ejPxFjNyXxS3Ja+/pA+A0olt5Uia7ync/Gui\\n58tlDQ4SKPthCzGa1tCVN+2y/PW30+LM79t0ltJ/YrNZivQx4eEnszlM9nwmIuj5z5LPniQghA6x\\nrfQ8IqVUZfiitXj/Fr7UjKg1cs/Ajj8g4u/KooRvVkg9tMwWePtJFqrkk1+DU4cylnSEG3XHgfer\\nslrzj5NNZessMEi+4Nz0O2D+b8Y+RjqN6HqpwZPDHhZwjz0Iuj2nhZLgu1bgNJev5BwxAr8akDWv\\nvKsibrJS9auQOYVzbYZFdKMiBnh+WHq0qO2aW1akYWCha3ZsSOtsnyPnFC+1PnMbBv+FiuJmPMXg\\nSODFoRIXfxgA/qaiKBipS+kIyfaPxn6O1i6MOwejVuQiWdAPTO132Spx0cFtdyj2hX6wAMe21cSy\\n8rs3KQxiz+cq7Rfwzsx4wiaMryFunfwUwnauGwTFOW98D5j6oO8=\\n\",\"lang\":\"Hindi\",\"playEvents\":[{\"authorId\":\"18326559001\",\"networkBitrate\":1900000,\"initialBufferPercentage\":100.0,\"isRepost\":false,\"sg\":false,\"meta\":\"NotifPostId\",\"md\":\"Stream\",\"percentage\":24.68405,\"p\":\"91484006\",\"radio\":\"wifi\",\"r\":\"deeplink_VideoPlayer\",\"repeatCount\":0,\"timeSpent\":9633,\"duration\":15,\"videoStartTime\":3916,\"t\":1602255552820,\"clientType\":\"Android\",\"i\":79,\"appV\":83,\"sessionId\":\"72137847101_8863b3f5-ad2d-4d59-aa7c-cf1fb9ef32ea\"},{\"authorId\":\"73625124001\",\"networkBitrate\":1900000,\"initialBufferPercentage\":100.0,\"isRepost\":false,\"sg\":false,\"meta\":\"list2\",\"md\":\"Stream\",\"percentage\":17.766666,\"p\":\"21594412\",\"radio\":\"wifi\",\"r\":\"First Launch_VideoPlayer\",\"repeatCount\":0,\"tagId\":\"0\",\"tagName\":\"\",\"timeSpent\":31870,\"duration\":17,\"videoStartTime\":23509,\"t\":1602218215942,\"clientType\":\"Android\",\"i\":79,\"appV\":83,\"sessionId\":\"72137847101_db67c0c9-a267-4cec-a3c3-4c0fa4ea16e1\"}],\"r\":\"VideoFeed\"},\"passCode\":\"9e32d6145bfe53d14a0c\",\"resTopic\":\"response/user_72137847101_9e32d6145bfe53d14a0c\",\"userId\":\"72137847101\"}", JSON);

        Request request = new Request.Builder()
                .url("https://moj-apis.sharechat.com/videoFeed?postId="+ StringId +"&firstFetch=true")
                .post(body)
                .addHeader("X-SHARECHAT-USERID", "72137847101")
                .addHeader("X-SHARECHAT-SECRET", "9e32d6145bfe53d14a0c")
                .addHeader("APP-VERSION", "83")
                .addHeader("PACKAGE-NAME", "in.mohalla.video")
                .addHeader("DEVICE-ID", "ebb088d29e7287b1")
                .addHeader("CLIENT-TYPE", "Android")

                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Host", "moj-apis.sharechat.com")
                .addHeader("Connection", "Keep-Alive")
                //.addHeader("Accept-Encoding", "gzip")
                .addHeader("User-Agent", "okhttp/3.12.12")

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
            String DownloadableURL=new JSONObject(new JSONObject(b).getJSONObject("payload").getJSONArray("d").get(0).toString()).get("compressedVideoUrl").toString();
            StringBuilder sb = new StringBuilder();
            sb.append(MainContext.getResources().getString(R.string.Moj_Suffix));
            sb.append(System.currentTimeMillis());
            sb.append(".mp4");
            String str3 = Utils.RootDirectoryMOJ;
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
