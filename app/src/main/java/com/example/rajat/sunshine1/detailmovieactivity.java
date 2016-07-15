package com.example.rajat.sunshine1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class detailmovieactivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmovieactivity);


        Intent recievedintent = getIntent();
      movieobject movie=recievedintent.getParcelableExtra("movieobject");
        FragmentManager fm = getSupportFragmentManager();
        Bundle args=new Bundle();
        args.putParcelable("movieobject",movie);
        Log.i("hello",movie.getTitle());
        if (savedInstanceState == null) {
            Fragment fragment = fm.findFragmentById(R.id.detailfragmentcontainer);
            if (fragment == null) {
                fragment = new detailmovieactivityFragment().newInstance(movie);
                fragment.setArguments(args);
            }
            fm.beginTransaction().add(R.id.detailfragmentcontainer, fragment).commit();

        }

    }
}