package com.example.sha.agro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class PharmacyAdaptor extends RecyclerView.Adapter<PharmacyAdaptor.ViewHolder> {
    public List<PharmacyView> pharmacyViewList;

    public PharmacyAdaptor(List<PharmacyView> pharmacyViewList){
        this.pharmacyViewList = pharmacyViewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aName.setText(pharmacyViewList.get(position).getName());
        holder.aMobile.setText(pharmacyViewList.get(position).getMobile());
        holder.aLocation.setText(pharmacyViewList.get(position).getLocation());
        holder.aAddress.setText(pharmacyViewList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return pharmacyViewList.size();
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
