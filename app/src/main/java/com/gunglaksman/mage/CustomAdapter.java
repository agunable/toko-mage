package com.gunglaksman.mage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Laksman on 2/7/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;

    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.deskripsi.setText(my_data.get(position).getDeskripsi());

        holder.harga.setText(my_data.get(position).getHarga());
        holder.nama.setText(my_data.get(position).getNama());
        holder.stok.setText(my_data.get(position).getStok());

        Glide.with(context).load(my_data.get(position).getGambar()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView deskripsi, nama, harga, stok;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            deskripsi = (TextView) itemView.findViewById(R.id.deskripsiBarang);
            nama = (TextView) itemView.findViewById(R.id.namaBarang);
            harga = (TextView) itemView.findViewById(R.id.harga);
            stok = (TextView) itemView.findViewById(R.id.stokBarang);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
