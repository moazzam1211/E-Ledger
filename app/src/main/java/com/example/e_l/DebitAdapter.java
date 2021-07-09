package com.example.e_l;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DebitAdapter extends RecyclerView.Adapter<DebitAdapter.DebitViewHolder> {
    ArrayList<String> dateOfPurchases;
    ArrayList<String> prodPrices;
    ArrayList<String> totalAmount;
    ArrayList<String> vendNames;

    public DebitAdapter(
                        ArrayList<String> datePurchase,
                        ArrayList<String> prices,
                        ArrayList<String> totals ,
                        ArrayList<String> vNames
                        ){

        this.dateOfPurchases =datePurchase;
        this.prodPrices = prices;
        this.totalAmount = totals;
        this.vendNames = vNames;
    }
    @NonNull
    @Override
    public DebitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.debit_payments,parent,false);
        return new DebitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DebitViewHolder holder, int position) {
        String dates = dateOfPurchases.get(position);
        String price = prodPrices.get(position);
        String totalA = totalAmount.get(position);
        String vnamee =vendNames.get(position);


        holder.datePurchase.setText(dates);
        holder.proPrice.setText(price);
        holder.totalPrice.setText(totalA);
        holder.vendname.setText(vnamee);
      ;
    }

    @Override
    public int getItemCount() {
        return vendNames.size();
    }

    public class DebitViewHolder extends RecyclerView.ViewHolder{
        TextView datePurchase, proPrice, totalPrice,vendname;
        String ptype;
        public DebitViewHolder(@NonNull View itemView) {
            super(itemView);
            datePurchase = itemView.findViewById(R.id.t2);
            proPrice = itemView.findViewById(R.id.t3);
            totalPrice   = itemView.findViewById(R.id.t4);
            vendname   = itemView.findViewById(R.id.t1);
        }
    }
}
