package com.example.e_l;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SalesEntryAdapter extends RecyclerView.Adapter<SalesEntryAdapter.SalesViewHolder> {
    ArrayList<String> srNoRank;
    ArrayList<String> dateOfSale;
    ArrayList<String> prodNames;
    ArrayList<String> prodQtys;
    ArrayList<String> salesPrices;
    ArrayList<String> totalAmount;
    ArrayList<String> salesTypes;
    ArrayList<String> custNames;
    public SalesEntryAdapter(ArrayList<String> rank,
                             ArrayList<String> dateSale,
                             ArrayList<String> Names,
                             ArrayList<String> qtys,
                             ArrayList<String> prices,
                             ArrayList<String> totals , ArrayList<String> types,
                             ArrayList<String> cNames)
    {
        this.srNoRank=rank;
        this.dateOfSale =dateSale;
        this.prodNames= Names;
        this.prodQtys =qtys;
        this.salesPrices = prices;
        this.totalAmount = totals;
        this.salesTypes =types;
        this.custNames = cNames;
    }
    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sales_order_list,parent,false);
        return new SalesEntryAdapter.SalesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder, int position) {
        String srRank = position+1+"";
        String dates = dateOfSale.get(position);
        String name = prodNames.get(position);
        String qty = prodQtys.get(position);
        String price = salesPrices.get(position);
        String totalA = totalAmount.get(position);
        String type = salesTypes.get(position);
        String vnamee =custNames.get(position);

        holder.srRankNo.setText(srRank);
        holder.datePurchase.setText(dates);
        holder.proName.setText(name);
        holder.proQty.setText(qty);
        holder.proPrice.setText(price);
        holder.totalPrice.setText(totalA);
        holder.sType.setText(type);
        holder.cname.setText(vnamee);
    }


    @Override
    public int getItemCount() {
        return prodNames.size();
    }
    public class SalesViewHolder extends RecyclerView.ViewHolder{
        TextView srRankNo, datePurchase, proName, proQty, proPrice, totalPrice, sType,cname;
        public SalesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
