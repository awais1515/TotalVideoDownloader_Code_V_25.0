package com.appsnipp.trendingapps.browser.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.trendingapps.BuildConfig;
import com.appsnipp.trendingapps.Utils.Utils;
import com.appsnipp.trendingapps.Models.file_type;
import com.appsnipp.trendingapps.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DownloadsAdapter extends RecyclerView.Adapter<DownloadsAdapter.SavedHolder>  {

    private List<File> list;
    private Context context;
    file_type File_type;

    public DownloadsAdapter(List<File> list, Context context,file_type _file_type) {
        this.list = list;
        this.context = context;
        this.File_type=_file_type;
    }

    @NonNull
    @Override
    public SavedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.download_item , parent, false);
        return new SavedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedHolder holder,final int position) {
        holder.name.setText(list.get(position).getName());

        Uri u= Uri.fromFile(list.get(position).getAbsoluteFile());


        Glide.with(context)
                .asBitmap()
                .load(u)
                .into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri fileUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() +".provider", list.get(position));
                if(File_type==file_type.VIDEO)
                {
                    intent.setDataAndType(fileUri, "video/*");
                }
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(intent);
            }
        });

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUp(view, list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SavedHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView thumbnail;
        private ImageView menu;

        public SavedHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.downloadCompletedName);
            thumbnail = itemView.findViewById(R.id.downloadThumnail);
            menu = itemView.findViewById(R.id.download_menu);
        }
    }

    private void popUp(View view, final File f) {
        final PopupMenu popup = new PopupMenu(context.getApplicationContext(), view);
        popup.getMenuInflater().inflate(R.menu.download_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.download_delete) {
                    new AlertDialog.Builder(context)
                            .setMessage( context.getString(R.string.suretodelete) )
                            .setPositiveButton(context.getString(R.string.yes), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (f.delete()) {
                                        updateList();
                                    }
                                }
                            })
                            .setNegativeButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //nada
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                    return true;
                }
                else if (i == R.id.download_share) {
                    File file = f.getAbsoluteFile();
                    Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
                    Intent intentShareFile = new Intent(Intent.ACTION_SEND);
                    intentShareFile.setType(URLConnection.guessContentTypeFromName(file.getName()));
                    intentShareFile.putExtra(Intent.EXTRA_STREAM,uri);
                    context.startActivity(Intent.createChooser(intentShareFile, context.getString(R.string.share)));
                    return true;
                }
                else {
                    return onMenuItemClick(item);
                }
            }
        });
        popup.show();
    }

    public void updateList() {
        File videoFile = new File(Utils.RootDirectoryBrowser);
        if (videoFile.exists()) {
            List<File> nonExistentFiles = new ArrayList<>();
            nonExistentFiles.addAll(Arrays.asList(videoFile.listFiles()));
            this.list = nonExistentFiles;
            notifyDataSetChanged();
        }
    }
}
