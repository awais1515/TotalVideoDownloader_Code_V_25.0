package com.appsnipp.trendingapps.Downloaders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import com.appsnipp.trendingapps.Models.ServicesModel;
import com.appsnipp.trendingapps.R;

import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;
import com.htetznaing.lowcostvideo.LowCostVideo;
import com.htetznaing.lowcostvideo.Model.XModel;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class GDrive extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;
    Document RoposoDoc;
    String VideoUrl="";
    Context MainContext;
    ProgressDialog progressDialog;
    LowCostVideo xGetter;
    ServicesModel CurrentService;

    public GDrive(Context c, AsyncResponse asyncResponse, ServicesModel servicesModel)
    {
        this.delegate=asyncResponse;
        Utils.createFileFolder();
        MainContext=c;
        CurrentService=servicesModel;
        progressDialog=new ProgressDialog(MainContext);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        xGetter= new LowCostVideo(MainContext);
        xGetter.onFinish(new LowCostVideo.OnTaskCompleted(){
            @Override
            public void onTaskCompleted(ArrayList<XModel> vidURL, boolean multiple_quality) {
                if (multiple_quality){
                    if (vidURL!=null){
                        multipleQualityDialog(vidURL);
                    }
                    else
                    {
                        DownloadFailed();
                    }
                }
                else
                {
                    done(vidURL.get(0));
                }
            }

            @Override
            public void onError() {
                DownloadFailed();
            }
        });

    }

    private void multipleQualityDialog(final ArrayList<XModel> model) {
        CharSequence[] name = new CharSequence[model.size()];

        for (int i = 0; i < model.size(); i++) {
            name[i] = model.get(i).getQuality();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(MainContext)
                .setTitle("Quality!")
                .setItems(name, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        done(model.get(which));
                    }
                })
                .setPositiveButton("OK", null);
        builder.show();
    }

    private void done(XModel xModel)
    {
        this.VideoUrl= xModel.getUrl();
        if(! this.VideoUrl.equals("") && this.VideoUrl !=null )
        {

            StringBuilder sb = new StringBuilder();
            sb.append(CurrentService.GetSuffex()+"_");
            sb.append(System.currentTimeMillis());
            sb.append(".mp4");
            String str3 = CurrentService.getRootDirectoryStr();
            long DownloadId= Utils.Xdownload( str3,MainContext , sb.toString(),xModel);
            progressDialog.hide();
            Toasty.success(MainContext, MainContext.getResources().getString(R.string.downloadstarted), Toast.LENGTH_SHORT, true).show();
            delegate.processFinish(DownloadId);
        }
        else
        {
            DownloadFailed();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return strings[0];
    }

    @Override
    public void onPostExecute(String document)
    {
        xGetter.find(document);
    }

    private void DownloadFailed()
    {
        progressDialog.hide();
        Toasty.error(MainContext, MainContext.getResources().getString(R.string.EnabletoDownloadTry), Toast.LENGTH_SHORT, true).show();
    }
}
