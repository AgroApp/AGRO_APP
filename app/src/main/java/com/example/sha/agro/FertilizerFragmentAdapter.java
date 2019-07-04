package com.example.sha.agro;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FertilizerFragmentAdapter extends RecyclerView.Adapter<FertilizerFragmentAdapter.ViewHolder>{

public List<FertilizersView> fertilizerViewList;

public  FertilizerFragmentAdapter(List<FertilizersView> fertilizerViewList){
     this.fertilizerViewList = fertilizerViewList;
}

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

       holder.aName.setText(fertilizerViewList.get(position).getAddress());
        holder.aMobile.setText(fertilizerViewList.get(position).getAddress());
        holder.aLocation.setText(fertilizerViewList.get(position).getAddress());
        holder.aAddress.setText(fertilizerViewList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return fertilizerViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
View mView;
public TextView aName,aMobile,aLocation,aAddress;
        public ViewHolder( View itemView) {
            super(itemView);

            aName =(TextView) mView.findViewById(R.id.name1);
            aMobile =(TextView) mView.findViewById(R.id.name2);
            aLocation =(TextView) mView.findViewById(R.id.name3);
            aAddress =(TextView) mView.findViewById(R.id.name4);
        }
    }
}