package com.example.rajat.sunshine1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by rajat on 6/6/16.
 */
public class movieobject implements Parcelable {
    private String posterpath;
    private String Overview;
    private long id;
    private String original_title;
    private String release_date;
    private String rating;
    private String backdroppath;



    public movieobject(){};

   public String getPosterPath(){
       return posterpath;
    }
    public String getBackdroppath(){
        return backdroppath;
    }
    public void setBackdroppath(String s){
        backdroppath=s;
    }

    public String getOverview(){
        return Overview;
    }

    public long getMovieId(){
        return  id;
    }
    public String getTitle(){
        return original_title;
    }
    public String getReleaseDate(){
        return release_date;
    }
    public String getMovieRating() {
        return rating;
    }
    public void setPosterPath(String s1){
        posterpath=s1;
    }

    public void setOverview(String s2){
        Overview=s2;
    }
    public void setMovieId(long id){
        id=id;
    }

    public void setTitle(String s3){
        original_title=s3;
    }

    public void setReleaseDate(String d1){
        release_date=d1;
    }

    public void setMovieRating(String f1){
        rating=f1;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,int flags){
        dest.writeLong(id);
        dest.writeString(posterpath);
        dest.writeString(Overview);
        dest.writeString(release_date);
        dest.writeString(original_title);
        dest.writeString(rating);
        dest.writeString(backdroppath);
    }
    private movieobject(Parcel in){
        this.id=in.readLong();
        this.posterpath=in.readString();
        this.Overview=in.readString();
        this.release_date=in.readString();
        this.original_title=in.readString();
        this.rating=in.readString();
        this.backdroppath=in.readString();

    }

    public static Parcelable.Creator<movieobject> CREATOR=new Parcelable.Creator<movieobject>(){
        @Override
        public movieobject createFromParcel(Parcel par){
            return new movieobject(par);
        }

        @Override
        public movieobject[] newArray(int size){
            return new movieobject[size];
        }

    };



}
