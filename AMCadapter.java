package com.spp.shitalsurgicalco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AMCadapter extends RecyclerView.Adapter<AMCadapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;
    String Name  , Email , Date  , Discription , Address;

    public AMCadapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.amcitem,parent,false);

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context , Spare_Part.class);
                i.putExtra("Name_S" , Name);

                i.putExtra("Email_S" , Email);
                i.putExtra("Date_S" , Date);
                i.putExtra("Discription_S" , Discription);
                context.startActivity(i);
                Toast.makeText(context, " "+Name, Toast.LENGTH_SHORT).show();
            }
        });*/

        return  new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.date.setText(user.getReg_Date());
        holder.nomachine.setText(user.getNoMachine());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name , email , date , nomachine;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvamcname);

            email = itemView.findViewById(R.id.tvamcemail);
            date = itemView.findViewById(R.id.tvamcdate);
            nomachine = itemView.findViewById(R.id.tvnomachine);




        }
    }
}