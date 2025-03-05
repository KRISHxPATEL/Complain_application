package com.spp.shitalsurgicalco;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    DatabaseReference database  ;

    ArrayList<User> list;
    String Name  , Email , Date  , Discription , Address;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
        this.database = FirebaseDatabase.getInstance().getReference("data");
       }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        v.setOnClickListener(new View.OnClickListener() {
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
        });

        return  new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        User user = list.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.date.setText(user.getReg_Date());
        holder.discription.setText(user.getFulldisp());
        holder.operated.setText(user.getTakenphone()+"("+user.getTaken()+")");

        Name = user.getName().toString();
        Address = user.getAddress().toString();
        Email = user.getEmail().toString();
        Date = user.getReg_Date().toString();
        Discription = user.getFulldisp().toString();

        holder.iconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUserFromFirebase(user.getName(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageButton iconButton;
        TextView name , email , date , discription , operated;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvfirstName);
            operated = itemView.findViewById(R.id.tvtaken);
            email = itemView.findViewById(R.id.tvage);
            date = itemView.findViewById(R.id.tvdate);
            discription = itemView.findViewById(R.id.tvdetail);
            iconButton = itemView.findViewById(R.id.iconButton);

        }
    }
    private void deleteUserFromFirebase(String userId, int position) {
        // Reference to the specific user's data in Firebase
        database.child(userId).removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Data deleted successfully, remove from local list and notify adapter
                        list.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Complain Finished", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Error deleting Complain", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}