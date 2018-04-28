package com.example.android.kosebarvmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by nurcahyadiperdana on 4/20/18.
 */

public class ListitemAdapter extends RecyclerView.Adapter<ListitemAdapter.BarangViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ListitemModel> ListBarang;

    //getting the context and product list with constructor
    public ListitemAdapter(Context mCtx, List<ListitemModel> ListBarang) {
        this.mCtx = mCtx;
        this.ListBarang = ListBarang;
    }

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listitem_layout, null);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListitemAdapter.BarangViewHolder holder, int position) {
        //getting the product of the specified position
        ListitemModel listbarang = ListBarang.get(position);

        //binding the data with the viewholder views

        holder.textViewTitle.setText(listbarang.getName());
        holder.textViewShortDesc.setText(listbarang.getDescription());
        holder.CC.setText(listbarang.getStock());
        holder.DD.setText(listbarang.getPrice());

        Glide.with(mCtx).load(listbarang.getImage()).into(holder.imageView);

        final String namabrng = holder.textViewTitle.getText().toString();
        final String hargabrng = holder.DD.getText().toString();
        final String deskbrng = holder.textViewShortDesc.getText().toString();
        holder.dftrbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pindahkedetailbarang = new Intent(v.getContext(), DetailbarangActivity.class);
                pindahkedetailbarang.putExtra("name",namabrng);
                pindahkedetailbarang.putExtra("price",hargabrng);
                pindahkedetailbarang.putExtra("description",deskbrng);
                mCtx.startActivity(pindahkedetailbarang);
            }
        });
    }





    @Override
    public int getItemCount() {
        return ListBarang.size();
    }


    class BarangViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, CC, DD;
        ImageView imageView;
        CardView dftrbarang;

        public BarangViewHolder(View itemView) {
            super(itemView);


            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            CC = itemView.findViewById(R.id.cc);
            DD = itemView.findViewById(R.id.dd);
            dftrbarang = itemView.findViewById(R.id.daftarbarang);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}