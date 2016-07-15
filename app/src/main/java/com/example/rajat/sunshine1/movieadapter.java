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
 */
public class movieadapter extends RecyclerView.Adapter<movieadapter.movieholder> {
    private List<movieobject> movieobjectarray;
    private onmovieselectedlistener mmovielistener;

    public movieadapter(List<movieobject> array,onmovieselectedlistener listener){
        movieobjectarray=array;
        mmovielistener=listener;
    }

    public interface onmovieselectedlistener{
         void onmovieselected(movieobject movie);
    }

    public class movieholder extends RecyclerView.ViewHolder {
        public ImageView mimageview;
        public TextView mtextview;

        public movieholder(View itemview){
            super(itemview);
            mimageview=(ImageView)itemview.findViewById(R.id.movie_imagemainscreen);
            mtextview=(TextView)itemview.findViewById(R.id.movie_titlemainscreen);
        }

        public void bind(final movieobject movie,final onmovieselectedlistener listener ){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onmovieselected(movie);
                }
            });
        }
    }

    @Override
    public int getItemCount(){
        return movieobjectarray.size();
    }

    @Override
    public void onBindViewHolder(movieholder holder,int position){
        movieobject object=movieobjectarray.get(position);
        holder.mtextview.setText(object.getTitle());
        holder.bind(object,mmovielistener);
        Picasso.with(holder.itemView.getContext()).load(getimageurl(object)).into(holder.mimageview);
    }
    @Override
    public movieholder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.movielistitem,parent,false);
        return new movieholder(v);
    }

    public String getimageurl(movieobject object){

        String link=object.getPosterPath();
        String fin="http://image.tmdb.org/t/p/w185/"+link;
        return fin;
    }




}
