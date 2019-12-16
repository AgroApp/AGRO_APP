package com.example.sha.agro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class EquipmentsAdapter extends RecyclerView.Adapter<EquipmentsAdapter.ViewHolder> {

    public List<EquipmentsView> equipmentsViewList;
    private final View.OnClickListener call;
    public Context context;
    public EquipmentsAdapter(List<EquipmentsView> equipmentsViewList, View.OnClickListener call) {
        this.equipmentsViewList = equipmentsViewList;
        this.call = call;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        context = parent.getContext();
        return new EquipmentsAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.aName.setText(equipmentsViewList.get(position).getName());
        holder.aMobile.setText(equipmentsViewList.get(position).getMobile());
        holder.aLocation.setText(equipmentsViewList.get(position).getLocation());
        holder.aAddress.setText(equipmentsViewList.get(position).getAddress());
        String image_url = equipmentsViewList.get(position).getImage();
        holder.setImage(image_url);
    }



        // String nameData = fertilizerViewList.get(position).getName();
        //holder.nameText(nameData);
        //  String mobileData = fertilizerViewList.get(position).getMobile();
        //  holder.MobileText(mobileData);
        //  String locationData = fertilizerViewList.get(position).getLocation();
        //  holder.locationText(locationData);
        //  String addressData = fertilizerViewList.get(position).getAddress();
        //   holder.addressText(addressData);



    @Override
    public int getItemCount() {
        return equipmentsViewList.size();
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

            aMobile.setOnClickListener(EquipmentsAdapter.this.call);
        }


        public void setImage(String downloadUri){

            aImage = mView.findViewById(R.id.image);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.image_placeholder);

            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(downloadUri).into(aImage);

        }
    }
}