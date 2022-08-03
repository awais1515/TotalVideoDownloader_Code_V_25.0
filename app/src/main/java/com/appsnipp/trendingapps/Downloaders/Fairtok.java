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

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Fairtok extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;
    public String VideoUrl="";
    Context MainContext;
    ProgressDialog progressDialog;


    public Fairtok(Context c, AsyncResponse asyncResponse) {
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
        StringId=StringId.replace("\n","");

        OkHttpClient client = new OkHttpClient();

         MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        okhttp3.RequestBody body = RequestBody.create ("{\"device_fingerprint_id\":\"838529202513017602\",\"identity_id\":\"838529202537882206\",\"hardware_id\":\"ebb088d29e7287b1\",\"is_hardware_id_real\":true,\"brand\":\"samsung\",\"model\":\"SM-J200G\",\"screen_dpi\":240,\"screen_height\":960,\"screen_width\":540,\"wifi\":true,\"ui_mode\":\"UI_MODE_TYPE_NORMAL\",\"os\":\"Android\",\"os_version\":22,\"cpu_type\":\"armv7l\",\"build\":\"LMY47X.J200GDCU2ARL1\",\"locale\":\"en_GB\",\"connection_type\":\"wifi\",\"os_version_android\":\"5.1.1\",\"country\":\"GB\",\"language\":\"en\",\"local_ip\":\"192.168.43.18\",\"app_version\":\"1.19\",\"facebook_app_link_checked\":false,\"is_referrable\":0,\"debug\":false,\"update\":1,\"latest_install_time\":1601158937336,\"latest_update_time\":1601158937336,\"first_install_time\":1601158937336,\"previous_update_time\":1601158937336,\"environment\":\"FULL_APP\",\"android_app_link_url\":\"https:\\/\\/fairtok.app.link\\/"+ StringId +"\",\"external_intent_uri\":\"https:\\/\\/fairtok.app.link\\/Y7ov2al149\",\"cd\":{\"mv\":\"-1\",\"pn\":\"com.fairtok\"},\"metadata\":{},\"advertising_ids\":{\"aaid\":\"094dfa1f-77cf-4f84-b373-2c15bf74f9d1\"},\"lat_val\":0,\"google_advertising_id\":\"094dfa1f-77cf-4f84-b373-2c15bf74f9d1\",\"instrumentation\":{\"v1\\/open-qwt\":\"0\"},\"sdk\":\"android5.0.1\",\"branch_key\":\"key_live_hjLYp0Wi3i6R1qQ1Lr51TlpcBvkxEp53\",\"retryNumber\":0}", JSON);

        Request request = new Request.Builder()
                .url("https://api2.branch.io/v1/open")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request ).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String b )
    {
        try {
            String dataObject=new JSONObject(b).getString("data");
            String subDataObject=new JSONObject(dataObject).getString("data");
            String UrlID=String.valueOf(new JSONObject(subDataObject).get("post_video"));
            String videoUrl="https://bucket-fairtok.s3.ap-south-1.amazonaws.com/"+ UrlID;

            if(! videoUrl.equals("") && videoUrl !=null )
            {
                String ThisUrl = videoUrl;
                StringBuilder sb = new StringBuilder();
                sb.append(MainContext.getResources().getString(R.string.Fairtok_Suffix));
                sb.append(System.currentTimeMillis());
                sb.append(".mp4");
                String str3 = Utils.RootDirectoryFAIRTOK;
                long DownloadId= Utils.startDownload(ThisUrl, str3,MainContext , sb.toString());
                progressDialog.hide();
                Toasty.success(MainContext, MainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
                delegate.processFinish(DownloadId);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String SS="";
    }
}
