package com.peng.launcherrecyclerviewhorizontal;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<ResolveInfo> MyAppList;
    private PackageManager myPackageManager;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvPrice;

        MyViewHolder(View view){
            super(view);
            ivPicture = view.findViewById(R.id.iv_picture);
            tvPrice = view.findViewById(R.id.tv_price);
        }
    }

    MyAdapter(List<ResolveInfo> l ,PackageManager m){
        MyAppList = l;
        myPackageManager = m;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        ResolveInfo resolveInfo = MyAppList.get(position);

        myViewHolder.ivPicture.setImageDrawable(resolveInfo.loadIcon(myPackageManager));
        myViewHolder.tvPrice.setText(resolveInfo.loadLabel(myPackageManager));
    }

    @Override
    public int getItemCount() {
        return MyAppList.size();
    }
}
