package com.example.sha.agro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CorporationbankAdaptor extends RecyclerView.Adapter<CorporationbankAdaptor.ViewHolder> {

public List<CorporationbankView> corporationbankViewList;

public CorporationbankAdaptor(List<CorporationbankView> corporationbankViewList)
{
    this.corporationbankViewList = corporationbankViewList;
}

    @NonNull
    @Override
    public CorporationbankAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CorporationbankAdaptor.ViewHolder holder, int position) {
        holder.aScheme.setText(corporationbankViewList.get(position).getScheme());
        holder.aPurpose.setText(corporationbankViewList.get(position).getPurpose());
        holder.aEligibility.setText(corporationbankViewList.get(position).getEligibility());
        holder.aMargin.setText(corporationbankViewList.get(position).getMargin());
        holder.aSecurity.setText(corporationbankViewList.get(position).getSecurity());
    }

    @Override
    public int getItemCount() {
        return corporationbankViewList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        public View mView;

        public TextView aScheme, aPurpose, aEligibility, aMargin, aSecurity;

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
