package com.example.rajat.sunshine1;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class detailmovieactivityFragment extends Fragment {
    public static final String MOVIE_OBJECT="movieobject";
    public static final String IMAGE_BASE_PATH="http://image.tmdb.org/t/p/w185/";
    public static final String BACKDROP_BASE_PATH="http://image.tmdb.org/t/p/w500/";
    private RecyclerView reviewrecyclerview,trailerrecyclerview;
    private List<trailerobject> trailersarray;
    private List<revieobject> reviewarray;
    private ContentResolver mcontentresolver;

    public detailmovieactivityFragment() {
        setHasOptionsMenu(true);
    }


    public detailmovieactivityFragment newInstance(movieobject movie){
        detailmovieactivityFragment ffagment=new detailmovieactivityFragment();
        Bundle args=new Bundle();
        args.putParcelable(MOVIE_OBJECT,movie);
        Log.i("hello 2",movie.getTitle());
        ffagment.setArguments(args);
        return ffagment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_detailmovieactivity, container, false);
        trailersarray=new ArrayList<>();
        reviewarray=new ArrayList<>();
        Bundle args=getArguments();
        mcontentresolver=getContext().getContentResolver();
        ImageView mbackdropimage=(ImageView)rootview.findViewById(R.id.movie_detail_backdrop_image);
        ImageView mmainimage=(ImageView)rootview.findViewById(R.id.movie_detail_mainimage);
        TextView mtitletext=(TextView)rootview.findViewById(R.id.movie_titledetail);
        TextView mvoteaveragetext=(TextView)rootview.findViewById(R.id.movie_detail_rating);
        FloatingActionButton maddtofavourite=(FloatingActionButton)rootview.findViewById(R.id.savetofavourites);
        TextView mreleasedate=(TextView)rootview.findViewById(R.id.movie_detail_date);
        TextView moverviewtitletext=(TextView)rootview.findViewById(R.id.movie_detail_overviewtitle);
        TextView moverviewcontenttext=(TextView)rootview.findViewById(R.id.movie_detail_overviewcontent);
        TextView mreviewtitletext=(TextView)rootview.findViewById(R.id.movie_detail_reviewstitle);
        TextView mtrailertitletext=(TextView)rootview.findViewById(R.id.movie_detail_trailertitle);
        reviewrecyclerview=(RecyclerView)rootview.findViewById(R.id.movie_detail_reviews);
        trailerrecyclerview=(RecyclerView)rootview.findViewById(R.id.movie_detail_trailers);
        reviewrecyclerview.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        reviewrecyclerview.setAdapter(new reviewadapter(reviewarray));
        trailerrecyclerview.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        trailerrecyclerview.setAdapter(new traileradapter(trailersarray));


            movieobject movie=args.getParcelable(MOVIE_OBJECT);
        Log.i("hello 3",movie.getTitle());



            final String posterpath = movie.getPosterPath();
            final String backdroppath = movie.getBackdroppath();
            final String overview = movie.getOverview();
            final String title = movie.getTitle();
            final String rating = movie.getMovieRating();
            final String releasedate = movie.getReleaseDate();
            final long id = movie.getMovieId();



            Picasso.with(getContext()).load(IMAGE_BASE_PATH + posterpath).into(mmainimage);
            Picasso.with(getContext()).load(BACKDROP_BASE_PATH + backdroppath).fit().into(mbackdropimage);
            mtitletext.setText(title);
        Log.i("hello 4",title);
            mvoteaveragetext.setText(rating + "/10");
            mreleasedate.setText(releasedate);
            moverviewtitletext.setText("SYNOPSIS");
            moverviewcontenttext.setText(overview);
            mreviewtitletext.setText("REVIEWS");
            mtrailertitletext.setText("TRAILERS");
        if(id>0){
            Log.i("status","succesful");
        }
        else{
            Log.i("status","failure");
        }
        /*try {
            List<revieobject> reviewarraytemp = new reviewtask().execute(id).get();
            reviewarray.clear();
            reviewarray.addAll(reviewarraytemp);
            reviewrecyclerview.getAdapter().notifyDataSetChanged();

        } catch (ExecutionException | InterruptedException exp) {
            exp.printStackTrace();
        }

            maddtofavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(domovieexist(id)){
                       addmovietodatabase(id,posterpath,backdroppath,overview,rating,releasedate,title);
                        Toast.makeText(getContext(),"Movie is succesfuly added to favourites",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(domovieexist(id)==false){
                            removemoviefromdatabase(id);
                            Toast.makeText(getContext(),"Movie is sucessfully removed from favourites",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

*/

return rootview;
    }


public boolean domovieexist(int movieid){
    Cursor cursor=mcontentresolver.query(moviecontract.movieobjectcolums.CONTENT_URI,null,moviecontract.movieobjectcolums.COLUMN_ID+" = ",new String[]{"2"},null);
    if(cursor==null){
        return false;
    }
    else{
        return true;
    }
}

    public void addmovietodatabase(int movieid,
                                   String posterpath,
                                   String backdroppath,
                                   String overview,
                                   String rating,
                                   String releasedate,
                                   String title){
        ContentValues values=new ContentValues();
        values.put(moviecontract.movieobjectcolums.COLUMN_ID,movieid);
        values.put(moviecontract.movieobjectcolums.COLUMN_POSTERPATH,posterpath);
        values.put(moviecontract.movieobjectcolums.COLUMN_BACKDROPPATH,backdroppath);
        values.put(moviecontract.movieobjectcolums.COLUMN_OVERVIEW,overview);
        values.put(moviecontract.movieobjectcolums.COLUMN_RATING,rating);
        values.put(moviecontract.movieobjectcolums.COLUMN_RELEASEDATE,releasedate);
        values.put(moviecontract.movieobjectcolums.COLUMN_TITLE,title);
         mcontentresolver.insert(moviecontract.movieobjectcolums.CONTENT_URI,values);
    }

    public void removemoviefromdatabase(int movieid){
        mcontentresolver.delete(moviecontract.movieobjectcolums.CONTENT_URI,moviecontract.movieobjectcolums.COLUMN_ID+" = ",new String[]{"2"});
    }






}






