package com.example.rajat.sunshine1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment{


    public interface movieselectioncallbaclistener{
        void onmovieclicked(movieobject movie);
    }
private RecyclerView mmovierecyclerview;
    private RecyclerView.LayoutManager mgridlayoutmanager;
    private List<movieobject> mmovielist;
    private movieselectioncallbaclistener mlistener;
    public MainActivityFragment() {
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof movieselectioncallbaclistener){
            mlistener=(movieselectioncallbaclistener)context;
        }else{
            throw new ClassCastException(context.toString()+"Must implement movieselectioncallbacklistener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_main, container, false);
        mmovielist=new ArrayList<>();
        mmovierecyclerview=(RecyclerView)rootview.findViewById(R.id.main_recyclerview);
        mmovierecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        mmovierecyclerview.setAdapter(new movieadapter(mmovielist, new movieadapter.onmovieselectedlistener() {
            @Override
            public void onmovieselected(movieobject movie) {
                mlistener.onmovieclicked(movie);
            }
        }));

        try{
            List<movieobject> movielisttemp=new movietask().execute("top_rated").get();
            mmovielist.clear();;
            mmovielist.addAll(movielisttemp);
            mmovierecyclerview.getAdapter().notifyDataSetChanged();
        }catch(ExecutionException| InterruptedException exp){
            exp.printStackTrace();
        }

return rootview;


    }
}
