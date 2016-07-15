package com.example.rajat.sunshine1;

/**
 * Created by rajat on 10/7/16.
 */
public class revieobject {
    private String reviewtitle;
    private String reviewcontent;

    public revieobject(){}

    public revieobject(String reviewtitle, String reviewcontent) {
        this.reviewtitle = reviewtitle;
        this.reviewcontent = reviewcontent;
    }

    public String getReviewtitle() {

        return reviewtitle;
    }

    public void setReviewtitle(String reviewtitle) {
        this.reviewtitle = reviewtitle;
    }

    public String getReviewcontent() {
        return reviewcontent;
    }

    public void setReviewcontent(String reviewcontent) {
        this.reviewcontent = reviewcontent;
    }
}
