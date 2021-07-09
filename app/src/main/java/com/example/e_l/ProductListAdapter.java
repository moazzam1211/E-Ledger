package com.example.e_l;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.produtListViewHolder> {

    ArrayList<String> prodNames;
    ArrayList<String> prodCategory;
    ArrayList<String> prodQtys;
    ArrayList<String> description;
    ArrayList<String> proColor;
    ArrayList<String> barcode;
    public ProductListAdapter( ArrayList<String> pName,  ArrayList<String>category, ArrayList<String> pQty,
                               ArrayList<String> pDescription,  ArrayList<String>colorr,
                               ArrayList<String> code){
        this.prodNames= pName;
        this.prodCategory = category;
        this.prodQtys = pQty;
        this.description = pDescription;
        this.proColor = colorr;
        this.barcode = code;

    }
    @NonNull
    @Override
    public produtListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pro_list,parent,false);
        return new ProductListAdapter.produtListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull produtListViewHolder holder, int position) {

        String name = prodNames.get(position);
        String pCategory =prodCategory.get(position);
        String qty = prodQtys.get(position);
        String descr = description.get(position);
        String proCl = proColor.get(position);
        String barCode = barcode.get(position);

        holder.proname.setText(name);
        holder.procat.setText(pCategory);
        holder.proqt.setText(qty);
        holder.prode.setText(descr);
        holder.procl.setText(proCl);
        holder.probarcode.setText(barCode);

    }


    @Override
    public int getItemCount() {
        return prodNames.size();
    }
    public class produtListViewHolder extends RecyclerView.ViewHolder{
        TextView proname, procat, prode, procl, proqt,probarcode;
        public produtListViewHolder(@NonNull View itemView) {
            super(itemView);
            proname = itemView.findViewById(R.id.pN);
            procat = itemView.findViewById(R.id.pC);
            proqt = itemView.findViewById(R.id.pQ);
            prode   = itemView.findViewById(R.id.pD);
            procl   = itemView.findViewById(R.id.pCl);
            probarcode   = itemView.findViewById(R.id.pBc);
        }
    }
}
