package com.example.sha.agro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CanarabankAdaptor extends RecyclerView.Adapter<CanarabankAdaptor.ViewHolder> {
    public List<CanarabankView> canarabankViewList;

    public CanarabankAdaptor(List<CanarabankView> canarabankViewList){
        this.canarabankViewList = canarabankViewList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_list, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aScheme.setText(canarabankViewList.get(position).getScheme());
        holder.aPurpose.setText(canarabankViewList.get(position).getPurpose());
        holder.aEligibility.setText(canarabankViewList.get(position).getEligibility());
        holder.aMargin.setText(canarabankViewList.get(position).getMargin());
        holder.aSecurity.setText(canarabankViewList.get(position).getSecurity());
    }

    @Override
    public int getItemCount() {
        return canarabankViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View mView;

        public TextView aScheme,aPurpose,aEligibility, aMargin, aSecurity;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

           aScheme = mView.findViewById(R.id.scheme);
            aPurpose = mView.findViewById(R.id.purpose);
            aEligibility = mView.findViewById(R.id.eligibility);
            aMargin = mView.findViewById(R.id.margin);
            aSecurity = mView.findViewById(R.id.security);
        }
    }
}
