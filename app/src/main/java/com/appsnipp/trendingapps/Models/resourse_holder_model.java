package com.appsnipp.trendingapps.Models;

import android.webkit.MimeTypeMap;

import java.io.File;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class resourse_holder_model {

    private ArrayList<downloadable_resource_model> video_files;
    private ArrayList<downloadable_resource_model> document_files;

    private ArrayList<String> video_types=new ArrayList<>();
    private ArrayList<String> image_types=new ArrayList<>();

    private String page_title;

    public resourse_holder_model() {
        this.document_files=new ArrayList<>();
        this.video_files=new ArrayList<>();
        this.page_title="";
        init_arraylists();
    }

    private void init_arraylists(){
        video_types.add("mp4");
        video_types.add("wmv");
        video_types.add("avi");

        image_types.add("png");
        image_types.add("jpg");
        image_types.add("gif");
        image_types.add("webp");
    }


    public void add_Video (String size, String type, String link, String name, String page)
    {
        add_video_files(new downloadable_resource_model(name,link,file_type.VIDEO,size));
    }

    public void add_video_files(downloadable_resource_model _downloadable_resource_model){
        boolean found=false;
        if ( (_downloadable_resource_model.getURL() !=null) &&  ( !_downloadable_resource_model.getURL().startsWith("blob"))) {
            try
            {
                for (downloadable_resource_model item : this.video_files) {
                    if (item.getURL().equals(_downloadable_resource_model.getURL())) {
                        found = true;
                    }
                }
                if (found == false) {
                    this.video_files.add(_downloadable_resource_model);
                }
            }
            catch (Exception ex)
            {}
        }
    }



    public ArrayList<downloadable_resource_model> getVideo_files() {
        for (downloadable_resource_model model:video_files) {
            model.setTitle(this.page_title);
        }
        return video_files;
    }

    public void setVideo_files(ArrayList<downloadable_resource_model> video_files) {
        this.video_files = video_files;
    }

    public String getPage_title() {return page_title;}

    public void setPage_title(String page_title) {this.page_title = page_title;}
}
