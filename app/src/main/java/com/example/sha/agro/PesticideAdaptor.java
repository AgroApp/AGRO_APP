package com.example.sha.agro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PesticideAdaptor extends RecyclerView.Adapter<PesticideAdaptor.ViewHolder> {

    public List<PesticideView> pesticideViewList;

    public PesticideAdaptor(List<PesticideView> pesticideViewList){
        this.pesticideViewList = pesticideViewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aName.setText(pesticideViewList.get(position).getName());
        holder.aMobile.setText(pesticideViewList.get(position).getMobile());
        holder.aLocation.setText(pesticideViewList.get(position).getLocation());
        holder.aAddress.setText(pesticideViewList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return pesticideViewList.size();
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
