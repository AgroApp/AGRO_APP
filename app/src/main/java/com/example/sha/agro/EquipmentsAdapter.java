package com.example.sha.agro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquipmentsAdapter extends RecyclerView.Adapter<EquipmentsAdapter.ViewHolder> {

    public List<EquipmentsView> equipmentsViewList;

    public EquipmentsAdapter(List<EquipmentsView> equipmentsViewList) {
        this.equipmentsViewList = equipmentsViewList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new EquipmentsAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.aName.setText("Shop Name : "+equipmentsViewList.get(position).getName());
        holder.aMobile.setText("Mobile no : "+equipmentsViewList.get(position).getMobile());
        holder.aLocation.setText("Location : "+equipmentsViewList.get(position).getLocation());
        holder.aAddress.setText("Address : "+equipmentsViewList.get(position).getAddress());
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

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            aName = mView.findViewById(R.id.name1);
            aMobile = mView.findViewById(R.id.name2);
            aLocation = mView.findViewById(R.id.name3);
            aAddress = mView.findViewById(R.id.name4);
        }
    }
}