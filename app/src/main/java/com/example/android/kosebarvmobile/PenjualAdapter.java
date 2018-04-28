package com.example.android.kosebarvmobile;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

/**
 * Created by nurcahyadiperdana on 4/20/18.
 */

public class PenjualAdapter extends RecyclerView.Adapter<PenjualAdapter.PenjualViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Penjual> penjualList;

    //getting the context and product list with constructor
    public PenjualAdapter(Context mCtx, List<Penjual> penjualList) {
        this.mCtx = mCtx;
        this.penjualList = penjualList;
    }

    @Override
    public PenjualViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new PenjualViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PenjualViewHolder holder, final int position) {
        //getting the product of the specified position
        final Penjual penjual = penjualList.get(position);
        final Penjual abc = penjualList.get(position);
        //binding the data with the viewholder views
        holder.textViewTitle.setText(penjual.getStore_name());
        holder.textViewShortDesc.setText(penjual.getAddress());



       Glide.with(mCtx).load(penjual.getImage()).into(holder.imageView);

       holder.AA.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent pindahkelistbarang = new Intent(v.getContext(), ListItemActivity.class);
               mCtx.startActivity(pindahkelistbarang);
           }
       });
    }


    @Override
    public int getItemCount() {
        return penjualList.size();
    }


    class PenjualViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc;
        CardView AA;
        ImageView imageView;

        public PenjualViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            AA = itemView.findViewById(R.id.daftarpenjual);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}