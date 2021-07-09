package com.example.e_l;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    ArrayList<String> srNoRank;
    ArrayList<String> eT;
    ArrayList<String> eDp;
    ArrayList<String> eDt;
    ArrayList<String> eA;

    public ExpenseAdapter(  ArrayList<String> srRank, ArrayList<String> exT,  ArrayList<String> exDp,
                            ArrayList<String> exDt,   ArrayList<String> exA){
        this.srNoRank = srRank;
        this.eT = exT;
        this.eDp = exDp;
        this.eDt = exDt;
        this.eA = exA;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.expense_list,parent,false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        String srRank = position+1+"";
        String ext = eT.get(position);
        String exdp = eDp.get(position);
        String exdt = eDt.get(position);
        String exa = eA.get(position);

        holder.srRankNo.setText(srRank);
        holder.expenseT.setText(ext);
        holder.expenseDp.setText(exdp);
        holder.expenseDt.setText(exdt);
        holder.expenseAt.setText(exa);
    }

    @Override
    public int getItemCount() {
        return eT.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView srRankNo, expenseT, expenseDp, expenseDt, expenseAt;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            srRankNo = itemView.findViewById(R.id.t1);
            expenseT = itemView.findViewById(R.id.t3);
            expenseDp = itemView.findViewById(R.id.t4);
            expenseDt = itemView.findViewById(R.id.t2);
            expenseAt = itemView.findViewById(R.id.t5);
        }
    }
}
