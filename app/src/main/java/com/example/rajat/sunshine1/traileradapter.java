package com.example.rajat.sunshine1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rajat on 10/7/16.

/**
 * Created by rajat on 10/7/16.
 */
public class traileradapter extends RecyclerView.Adapter<traileradapter.trailerholder> {
    private List<trailerobject> trailerobjectarray;

    public traileradapter(List<trailerobject> array){
        trailerobjectarray=array;
    }

    public class trailerholder extends RecyclerView.ViewHolder{
        public ImageView mimageview;


        public trailerholder(View itemview){
            super(itemview);
            mimageview=(ImageView)itemview.findViewById(R.id.trailer_image);

        }
    }

    @Override
    public int getItemCount(){
        return trailerobjectarray.size();
    }

    @Override
    public void onBindViewHolder(trailerholder holder,int position){
        trailerobject object=trailerobjectarray.get(position);

        Picasso.with(holder.itemView.getContext()).load(object.getTrailerimagelink()).resize(300,400).into(holder.mimageview);
    }

    @Override
    public trailerholder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.movielistitem,parent,false);
        return new trailerholder(v);
    }
}
