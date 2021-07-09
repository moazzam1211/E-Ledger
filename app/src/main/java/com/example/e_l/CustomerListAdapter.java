package com.example.e_l;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> {
    ArrayList<String> cusName;
    ArrayList<String> cusCompany;
    ArrayList<String> cusAddress;
    ArrayList<String> cusEmail;
    ArrayList<String> cusPhone;

    public CustomerListAdapter(ArrayList<String> customerName,
                               ArrayList<String> customerCompany,
                               ArrayList<String> customerAddress,
                               ArrayList<String> customerEmail,
                               ArrayList<String> phNo)
    {
        this.cusName=customerName;
        this.cusCompany=customerCompany;
        this.cusAddress=customerAddress;
        this.cusEmail=customerEmail;
        this.cusPhone=phNo;

    }
    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_customers_list,parent,false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        String name = cusName.get(position);
        String company = cusCompany.get(position);
        String cAddress = cusAddress.get(position);
        String cEmail = cusEmail.get(position);
        String cPh = cusPhone.get(position);
        holder.name.setText(name);
        holder.cComp.setText(company);
        holder.cAdd.setText(cAddress);
        holder.cusEmaill.setText(cEmail);
        holder.cPhNo.setText(cPh);

    }



    @Override
    public int getItemCount() {
        return cusName.size();
    }
    public class CustomerViewHolder extends RecyclerView.ViewHolder{
        TextView name, cComp, cAdd, cusEmaill, cPhNo;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cName);
            cComp = itemView.findViewById(R.id.cCompany);
            cAdd = itemView.findViewById(R.id.cAddress);
            cusEmaill = itemView.findViewById(R.id.cEmail);
            cPhNo = itemView.findViewById(R.id.cPhone);
        }
    }
}
