package com.example.e_l;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VendorsListAdapter extends RecyclerView.Adapter<VendorsListAdapter.VendorsViewHolders> {

    ArrayList<String> vName;
    ArrayList<String> vCompany;
    ArrayList<String> vAddress;
    ArrayList<String> vEmail;
    ArrayList<String> vPhone;
    public VendorsListAdapter(ArrayList<String> vendorName,
                              ArrayList<String> vendorCompany,
                              ArrayList<String> address,
                              ArrayList<String> email,
                              ArrayList<String> phNo)
    {
        this.vName=vendorName;
        this.vCompany=vendorCompany;
        this.vAddress=address;
        this.vEmail=email;
        this.vPhone=phNo;

    }

    @NonNull
    @Override
    public VendorsViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_vendors_list,parent,false);
        return new VendorsViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorsViewHolders holder, int position) {
    String name = vName.get(position);
    String company = vCompany.get(position);
    String vendorAddress = vAddress.get(position);
    String vendEmail = vEmail.get(position);
    String vendorPhone = vPhone.get(position);

    holder.name.setText(name);
    holder.vComp.setText(company);
    holder.vAdd.setText(vendorAddress);
    holder.vEmaill.setText(vendEmail);
    holder.vPhoneNo.setText(vendorPhone);
    }

    @Override
    public int getItemCount() {
        return vName.size();
    }

    public class VendorsViewHolders extends RecyclerView.ViewHolder {
        TextView name, vComp, vAdd, vEmaill, vPhoneNo;
        public VendorsViewHolders(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.vName);
            vComp = itemView.findViewById(R.id.vCompany);
            vAdd = itemView.findViewById(R.id.vAddress);
            vEmaill = itemView.findViewById(R.id.vEmail);
            vPhoneNo = itemView.findViewById(R.id.vPhone);

        }
    }


}
