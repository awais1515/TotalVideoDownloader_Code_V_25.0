package com.appsnipp.trendingapps.Models;

import android.graphics.drawable.Drawable;
import com.appsnipp.trendingapps.Utils.Services;
import java.io.File;

public class ServicesModel
{
    private File RootDirectory;
    private String Name;
    private Drawable LogoImg;
    private Services enmService;
    private String RootDirectoryStr;
    private String ServiceLink;

    public String getServiceLink() {
        return ServiceLink;
    }

    public String getRootDirectoryStr() {
        return RootDirectoryStr;
    }



    public Services getEnmService() {
        return enmService;
    }

    public String GetSuffex()
    {
        return this.getName().replace(" ","");
    }


    public Drawable getLogoImg() {
        return LogoImg;
    }


    public File getRootDirectory() {
        return RootDirectory;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ServicesModel() {
        RootDirectory = null;
        Name = null;
    }
    public ServicesModel(File rootDirectory, String name, Drawable logoImg,Services s,String serviceLink) {
        RootDirectory = rootDirectory;
        Name = name;
        LogoImg=logoImg;
        enmService=s;
        ServiceLink=serviceLink;
    }

    public ServicesModel(File rootDirectory, String name, Drawable logoImg,Services s,String RootDirString,String serviceLink) {
        RootDirectory = rootDirectory;
        Name = name;
        LogoImg=logoImg;
        enmService=s;
        RootDirectoryStr=RootDirString;
        ServiceLink=serviceLink;
    }


}
