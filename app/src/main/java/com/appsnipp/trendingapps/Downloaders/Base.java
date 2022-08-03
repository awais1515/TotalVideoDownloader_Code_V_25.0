package com.appsnipp.trendingapps.Downloaders;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.interfaces.AsyncResponse;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public abstract class Base extends AsyncTask<String, Void, Document> {

    public AsyncResponse delegate = null;
    Document roposoDoc;
    String videoUrl="";
    Context mainContext;
    ProgressDialog progressDialog;

    public Base(Context c, AsyncResponse asyncResponse)
    {
        this.delegate=asyncResponse;
        Utils.createFileFolder();
        mainContext=c;
        progressDialog=new ProgressDialog(mainContext);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
    protected void DownloadFailed()
    {
        progressDialog.hide();
        Toasty.error(mainContext, mainContext.getResources().getString(R.string.EnabletoDownload), Toast.LENGTH_SHORT, true).show();
    }

    protected void DownloadFailed(String Message)
    {
        progressDialog.hide();
        Toasty.error(mainContext, Message, Toast.LENGTH_SHORT, true).show();
    }
}
