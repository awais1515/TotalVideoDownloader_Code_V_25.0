package com.appsnipp.trendingapps.browser.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.trendingapps.Models.downloadable_resource_model;
import com.appsnipp.trendingapps.Models.file_type;
import com.appsnipp.trendingapps.R;
import com.appsnipp.trendingapps.browser.Statics.static_variables;
import com.appsnipp.trendingapps.browser.popups.rename_dialog;
import com.appsnipp.trendingapps.browser.popups.video_player;



public class ResultHolderAdapter extends  RecyclerView.Adapter <ResultHolderAdapter.MyViewHolder>  {
    private Context mContext;
    private Activity activity;
    file_type _type;

    public ResultHolderAdapter(
                Context mContext,
                file_type _type,
                Activity mActivity){
        this.mContext=mContext;
        this.activity=mActivity;
        this._type=_type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.download_result_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final downloadable_resource_model result= static_variables.get_by_type_position(this._type,position);

        if( (result.getFile_size() !=null) && (!result.getFile_size().equals("")))
        {
            holder.txtVidSize.setText(result.getFile_size());
        }

        if(result !=null){
            holder.tv_film_name.setText(result.getTitle() +"");

            holder.btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentActivity activity = (FragmentActivity) (mContext);
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();

                    rename_dialog _rename_dialog=new rename_dialog(result);
                    _rename_dialog.show(fragmentManager,"TAG");

                }
            });

            holder.btnPreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(result.getFile_type()==file_type.VIDEO  )
                    {
                        FragmentActivity activity = (FragmentActivity) (mContext);
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();
                        video_player player=new video_player(result);
                        player.show(fragmentManager,"TAG");
                    }


                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return static_variables.get_downloadable_resource_model_By_Type(_type).size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_film_name;
        public Button btnDownload,btnPreview;
        public TextView txtVidSize;


        public MyViewHolder(@NonNull View view) {
            super(view);
            tv_film_name=view.findViewById(R.id.tv_film_name);
            btnDownload=view.findViewById(R.id.btnDownload);
            txtVidSize=view.findViewById(R.id.txtVidSize);
            btnPreview=view.findViewById(R.id.btnPreview);


        }
    }





}
