package com.peng.launcherrecyclerviewhorizontal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ResolveInfo> MyAppList;
    private PackageManager myPackageManager;
    private Context context;
    private OnItemClickListener mListener = null ;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v,int pos);

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvPrice;


        MyViewHolder(View itemView){
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }

    MyAdapter(List<ResolveInfo> MyAppList,Context context){
       this.MyAppList = MyAppList;
       this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewTypem) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        ResolveInfo resolveInfo = MyAppList.get(position);

        myPackageManager = context.getPackageManager();

        myViewHolder.ivPicture.setImageDrawable(resolveInfo.loadIcon(myPackageManager));
        myViewHolder.tvPrice.setText(resolveInfo.loadLabel(myPackageManager));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.onItemClick(view,position);


            }
        });
    }

    @Override
    public int getItemCount() {
        return MyAppList.size();
    }

}
