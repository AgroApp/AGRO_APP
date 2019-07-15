package com.example.sha.agro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IndianbankAdaptor extends RecyclerView.Adapter<IndianbankAdaptor.ViewHolder> {

    public List<IndianbankView> indianbankViewList;

    public IndianbankAdaptor(List<IndianbankView> indianbankViewList){
        this.indianbankViewList = indianbankViewList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.indian_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.aScheme.setText(indianbankViewList.get(position).getScheme());
        holder.aPurpose.setText(indianbankViewList.get(position).getPurpose());
        holder.aEligibility.setText(indianbankViewList.get(position).getEligibility());
        holder.aAmount_of_Loan.setText(indianbankViewList.get(position).getAmount_of_Loan());
        holder.aMargin.setText(indianbankViewList.get(position).getMargin());
        holder.aInterest_Rate.setText(indianbankViewList.get(position).getInterest_Rate());
        holder.aRepayment.setText(indianbankViewList.get(position).getRepayment());
        holder.aSecurity.setText(indianbankViewList.get(position).getSecurity());
    }

    @Override
    public int getItemCount() {
        return indianbankViewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


            public View mView;

            public TextView aScheme,aPurpose,aEligibility, aMargin, aSecurity, aAmount_of_Loan, aRepayment, aInterest_Rate;

            public ViewHolder(View itemView) {
                super(itemView);
                mView = itemView;

                aScheme = mView.findViewById(R.id.in_scheme);
                aPurpose = mView.findViewById(R.id.in_purpose);
                aEligibility = mView.findViewById(R.id.in_eligibility);
                aAmount_of_Loan = mView.findViewById(R.id.in_amount);
                aMargin = mView.findViewById(R.id.in_margin);
                aInterest_Rate = mView.findViewById(R.id.in_interest);
                aRepayment = mView.findViewById(R.id.in_repay);
                aSecurity = mView.findViewById(R.id.in_security);

        }
    }
}
