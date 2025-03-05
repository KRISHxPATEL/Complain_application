package com.spp.shitalsurgicalco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GetEmailAdapter extends RecyclerView.Adapter<GetEmailAdapter.MyViewHolder> {

    Context context;
    SharedPreferences sp;
    ArrayList<User> list;
    String Name  , Email , Date  , Discription , Address;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public GetEmailAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.getemaillist,parent,false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context , SecondActivity.class);

                i.putExtra("hosname",Name);
                i.putExtra("hosadd",Address);


                context.startActivity(i);

            }
        });

        return  new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);

        holder.name.setText(user.getName());
        holder.address.setText(user.getAddress());
        Name = user.getName();
        Address = user.getAddress();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name  , address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.hosnametv);


            address = itemView.findViewById(R.id.hosaddtv);






        }
    }
}