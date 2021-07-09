package com.example.e_l;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfitLossAdapters extends RecyclerView.Adapter<ProfitLossAdapters.profitLossViewHolder> {
    ArrayList<String> dates;
    ArrayList<String> openingStock;
    ArrayList<String> totalPurchase;
    ArrayList<String> expenses;
    ArrayList<String> totalSales;
    ArrayList<String> stockOnhand;
    ArrayList<String> profitLoss;
    public ProfitLossAdapters(  ArrayList<String> datess,  ArrayList<String> openingStocks,  ArrayList<String> totalPurchases,
                                ArrayList<String> expensess,    ArrayList<String> totalSaless,
                                ArrayList<String> stockOnhands, ArrayList<String> profitLosss){
        this.dates= datess;
        this.openingStock = openingStocks;
        this.totalPurchase = totalPurchases;
        this.expenses = expensess;
        this.totalSales = totalSaless;
        this.stockOnhand = stockOnhands;
        this.profitLoss = profitLosss;
    }

    @NonNull
    @Override
    public profitLossViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.profit_loss_list,parent,false);
        return new ProfitLossAdapters.profitLossViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull profitLossViewHolder holder, int position) {
        String date = dates.get(position);
        String openingS =openingStock.get(position);
        String totalPur = totalPurchase.get(position);
        String expense = expenses.get(position);
        String totalSa = totalSales.get(position);
        String stockOnhands = stockOnhand.get(position);
        String profitlosss = profitLoss.get(position);

        holder.dates.setText(date);
        holder.openingSt.setText(openingS);
        holder.totalP.setText(totalPur);
        holder.expenss.setText(expense);
        holder.totalS.setText(totalSa);
        holder.stock.setText(stockOnhands);
        holder.profits.setText(profitlosss);


    }

    @Override
    public int getItemCount() {
       return dates.size();
    }

    public class profitLossViewHolder extends RecyclerView.ViewHolder{
        TextView dates, openingSt, totalP, expenss, totalS,stock,profits;
        public profitLossViewHolder(@NonNull View itemView) {
            super(itemView);
            dates = itemView.findViewById(R.id.t1);
            openingSt = itemView.findViewById(R.id.t2);
            totalP = itemView.findViewById(R.id.t3);
            expenss = itemView.findViewById(R.id.t4);
            totalS = itemView.findViewById(R.id.t5);
            stock = itemView.findViewById(R.id.t6);
            profits = itemView.findViewById(R.id.t7);
        }
    }

}
