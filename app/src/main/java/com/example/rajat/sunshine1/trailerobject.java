package com.example.rajat.sunshine1;

/**
 * Created by rajat on 10/7/16.
 */
public class trailerobject {
    private String trailerlink;
    private String trailerimagelink;

    public trailerobject(String trailerlink, String trailerimagelink) {
        this.trailerlink = trailerlink;
        this.trailerimagelink = trailerimagelink;
    }

    public trailerobject() {
    }

    public String getTrailerimagelink() {

        return trailerimagelink;
    }

    public void setTrailerimagelink(String trailerimagelink) {
        this.trailerimagelink = trailerimagelink;
    }

    public String getTrailerlink() {
        return trailerlink;
    }

    public void setTrailerlink(String trailerlink) {
        this.trailerlink = trailerlink;
    }
}
