package com.example.e_l;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PurchaseEntryAdapter extends RecyclerView.Adapter<PurchaseEntryAdapter.PurchaseEntryViewHolder> {

    ArrayList<String> srNoRank;
    ArrayList<String> dateOfPurchases;
    ArrayList<String> prodNames;
    ArrayList<String> prodQtys;
    ArrayList<String> prodPrices;
    ArrayList<String> totalAmount;
    ArrayList<String> purchaseTypes;
    ArrayList<String> vendNames;

    public PurchaseEntryAdapter( ArrayList<String> rank,
                                 ArrayList<String> datePurchase,
                                 ArrayList<String> Names,
                                 ArrayList<String> qtys,
                                 ArrayList<String> prices,
                                 ArrayList<String> totals , ArrayList<String> types,
                                 ArrayList<String> vNames)
    {
        this.srNoRank=rank;
        this.dateOfPurchases =datePurchase;
        this.prodNames= Names;
        this.prodQtys =qtys;
        this.prodPrices = prices;
        this.totalAmount = totals;
        this.purchaseTypes =types;
        this.vendNames = vNames;

    }
    @NonNull
    @Override
    public PurchaseEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.purchase_orders_list,parent,false);
        return new PurchaseEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseEntryViewHolder holder, int position) {
        String srRank = position+1+"";
        String dates = dateOfPurchases.get(position);
        String name = prodNames.get(position);
        String qty = prodQtys.get(position);
        String price = prodPrices.get(position);
        String totalA = totalAmount.get(position);
        String type = purchaseTypes.get(position);
        String vnamee =vendNames.get(position);

        holder.srRankNo.setText(srRank);
        holder.datePurchase.setText(dates);
        holder.proName.setText(name);
        holder.proQty.setText(qty);
        holder.proPrice.setText(price);
        holder.totalPrice.setText(totalA);
        holder.purchType.setText(type);
        holder.vendname.setText(vnamee);



//        String formattedDate = null;
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
//        formattedDate = simpleDateFormat.format(calendar.getTime());
//        if (dates.equals(formattedDate)){
//            holder.backk.setBackgroundColor(Color.parseColor("87ceeb"));
//        }

    }


    @Override
    public int getItemCount() {
        return prodNames.size();
    }
    public class PurchaseEntryViewHolder extends RecyclerView.ViewHolder{
        TextView srRankNo, datePurchase, proName, proQty, proPrice, totalPrice, purchType,vendname;
        LinearLayout backk ;
        public PurchaseEntryViewHolder(@NonNull View itemView) {
            super(itemView);
            srRankNo = itemView.findViewById(R.id.t1);
            datePurchase = itemView.findViewById(R.id.t2);
            proName = itemView.findViewById(R.id.t3);
            proQty = itemView.findViewById(R.id.t4);
            proPrice = itemView.findViewById(R.id.t5);
            totalPrice   = itemView.findViewById(R.id.t6);
            purchType   = itemView.findViewById(R.id.t7);
            vendname   = itemView.findViewById(R.id.t8);
            backk = itemView.findViewById(R.id.back);

        }
    }
}
