package com.example.rajat.sunshine1;

/**
 * Created by rajat on 10/7/16.
 */

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
 */
public class reviewadapter extends RecyclerView.Adapter<reviewadapter.reviewholder> {
    private List<revieobject> reviewobjectarray;

    public reviewadapter(List<revieobject> array){
        reviewobjectarray=array;
    }

    public class reviewholder extends RecyclerView.ViewHolder{
        public TextView mauthorname;
        public TextView mcontent;

        public reviewholder(View itemview){
            super(itemview);
            mauthorname=(TextView) itemview.findViewById(R.id.review_authorname);
            mcontent=(TextView)itemview.findViewById(R.id.review_content);
        }
    }

    @Override
    public int getItemCount(){
        return reviewobjectarray.size();
    }

    @Override
    public void onBindViewHolder(reviewholder holder,int position){
        revieobject object=reviewobjectarray.get(position);
        holder.mauthorname.setText(object.getReviewtitle());
        holder.mcontent.setText(object.getReviewcontent());
    }

    @Override
    public reviewholder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.reviewlistitem,parent,false);
        return new reviewholder(v);
    }





}

