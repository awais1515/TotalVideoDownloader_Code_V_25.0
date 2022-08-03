package com.appsnipp.trendingapps.browser.Statics;

import com.appsnipp.trendingapps.Models.downloadable_resource_model;
import com.appsnipp.trendingapps.Models.file_type;
import com.appsnipp.trendingapps.Models.resourse_holder_model;

import java.util.ArrayList;
import java.util.List;

public class static_variables {
    public static resourse_holder_model resourse_holder;


    public static downloadable_resource_model get_by_type_position(file_type _type,int position){
        List<downloadable_resource_model> list;
        if( _type==file_type.VIDEO ){
            list= resourse_holder.getVideo_files();
        }
        else
        {
            return null;
        }
        if(list !=null){
            return  list.get(position);
        }
        else
        {
            return null;
        }
    }

    public static ArrayList<downloadable_resource_model> get_downloadable_resource_model_By_Type(file_type _type){
        if( _type==file_type.VIDEO ){
            return resourse_holder.getVideo_files();
        }
        else
        {
            return null;
        }
    }

}
