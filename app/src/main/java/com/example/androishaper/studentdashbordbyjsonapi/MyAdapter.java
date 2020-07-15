package com.example.androishaper.studentdashbordbyjsonapi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<MyItem> Mylist;
    public Context context;
    public MyAdapter(List<MyItem> mylist, Context context) {
        this.Mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyItem myItemPosition=Mylist.get(position);
        holder.name.setText(myItemPosition.getName());
        holder.role.setText(myItemPosition.getRole());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=myItemPosition.getName();
                final  String role=myItemPosition.getRole();

                new AlertDialog.Builder(view.getRootView().getContext())
                        .setTitle("Delete"+" "+name)
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Toast.makeText(context,"Delet"+" "+myItemPosition.getName(),Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(context,MainActivityDetails.class);
                                intent.putExtra("name",name);
                                intent.putExtra("role",role);
                                context.startActivity(intent);

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Mylist.size();
    }
}
