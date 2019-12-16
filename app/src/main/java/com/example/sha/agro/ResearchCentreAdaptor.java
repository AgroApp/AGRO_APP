package com.example.sha.agro;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.List;

import id.zelory.compressor.Compressor;

public class ResearchCentreAdaptor extends RecyclerView.Adapter<ResearchCentreAdaptor.ViewHolder> {


    private final View.OnClickListener call;
    public Context context;

    public List<ResearchCentreView> researchCentreViewList;
    public ResearchCentreAdaptor(List<ResearchCentreView> researchCentreViewList, View.OnClickListener call){
        this.researchCentreViewList = researchCentreViewList;
        this.call = call;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(view, call);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aName.setText(researchCentreViewList.get(position).getName());
        holder.aMobile.setText(researchCentreViewList.get(position).getMobile());
        holder.aLocation.setText(researchCentreViewList.get(position).getLocation());
        holder.aAddress.setText(researchCentreViewList.get(position).getAddress());
        String image_url = researchCentreViewList.get(position).getImage();

        holder.setBlogImage(image_url);


    }

    @Override
    public int getItemCount() {
        return researchCentreViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View mView;

        public TextView aName, aMobile, aLocation, aAddress;
        public ImageView aImage;
        public ViewHolder(View itemView, View.OnClickListener call) {
            super(itemView);
            mView = itemView;
            aImage = mView.findViewById(R.id.image);
            aName = mView.findViewById(R.id.name1);
            aMobile = mView.findViewById(R.id.name2);
            aLocation = mView.findViewById(R.id.name3);
            aAddress = mView.findViewById(R.id.name4);

            aMobile.setOnClickListener(ResearchCentreAdaptor.this.call);
        }


        public void setBlogImage(String downloadUri){

            aImage = mView.findViewById(R.id.image);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.image_placeholder);

            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(downloadUri).into(aImage);

        }
    }

}