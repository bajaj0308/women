package com.example.meghabajaj.womanhackathon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MEGHA BAJAJ on 11-03-2018.
 */

public class searchAdapter extends RecyclerView.Adapter<searchAdapter.viewHolder>{
    Context context;
    ArrayList<String> Type;
    ArrayList<String> Name;
    ArrayList<String> Phone;
    ArrayList<String> Address;
    class viewHolder extends RecyclerView.ViewHolder{
        TextView name,phone,type,address;
        public viewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.txtName);
            phone=(TextView)itemView.findViewById(R.id.txtPhone);
            type=(TextView)itemView.findViewById(R.id.txtType);
            address=(TextView)itemView.findViewById(R.id.txtAddress);
        }
    }
    public searchAdapter(Context context,ArrayList<String> Type,ArrayList<String> Name,ArrayList<String> Phone,ArrayList<String>Address){

        this.context=context;
        this.Name=Name;
        this.Type=Type;
        this.Phone=Phone;
        this.Address=Address;
    }

    @Override
    public searchAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_search_result_activity,parent,false);
        return new searchAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.name.setText(Name.get(position));
        holder.phone.setText(Phone.get(position));
        holder.address.setText(Address.get(position));
        holder.type.setText(Type.get(position));
    }


    @Override
    public int getItemCount() {
        return Name.size();
    }
}
