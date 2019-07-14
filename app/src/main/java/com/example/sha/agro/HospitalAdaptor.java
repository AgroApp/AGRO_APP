package com.example.sha.agro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HospitalAdaptor extends RecyclerView.Adapter<HospitalAdaptor.ViewHolder> {

    public List<HospitalView> hospitalViewList;

    public HospitalAdaptor(List<HospitalView> hospitalViewList){
        this.hospitalViewList = hospitalViewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aName.setText("Shop Name : "+hospitalViewList.get(position).getName());
        holder.aMobile.setText("Mobile no : "+hospitalViewList.get(position).getMobile());
        holder.aLocation.setText("Location : "+hospitalViewList.get(position).getLocation());
        holder.aAddress.setText("Address : "+hospitalViewList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return hospitalViewList.size();
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