package com.example.sha.agro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PesticideAdaptor extends RecyclerView.Adapter<PesticideAdaptor.ViewHolder> {

    public List<PesticideView> pesticideViewList;
    private final View.OnClickListener call;
    public Context context;
    public PesticideAdaptor(List<PesticideView> pesticideViewList, View.OnClickListener call){
        this.pesticideViewList = pesticideViewList;
        this.call = call;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aName.setText(pesticideViewList.get(position).getName());
        holder.aMobile.setText(pesticideViewList.get(position).getMobile());
        holder.aLocation.setText(pesticideViewList.get(position).getLocation());
        holder.aAddress.setText(pesticideViewList.get(position).getAddress());
        String image_url = pesticideViewList.get(position).getImage();
        holder.setImage(image_url);
    }

    @Override
    public int getItemCount() {
        return pesticideViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        public TextView aName, aMobile, aLocation, aAddress;
        public ImageView aImage;
        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            aName = mView.findViewById(R.id.name1);
            aMobile = mView.findViewById(R.id.name2);
            aLocation = mView.findViewById(R.id.name3);
            aAddress = mView.findViewById(R.id.name4);

            aMobile.setOnClickListener(PesticideAdaptor.this.call);
        }


        public void setImage(String downloadUri){

            aImage = mView.findViewById(R.id.image);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.image_placeholder);

            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(downloadUri).into(aImage);

        }
    }
}
