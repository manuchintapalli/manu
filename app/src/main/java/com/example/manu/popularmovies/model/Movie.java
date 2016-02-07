package com.example.manu.popularmovies.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.manu.popularmovies.PopularMoviesFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Manu on 12/12/15.
 **/

public class Movie implements Parcelable {

    private int id;
    private String title;
    private String image;
    private String image2;
    private String overview;
    private int rating;
    private String date;

    public static final String KEY_ID = "id";
    public static final String KEY_ORIGINAL_TITLE = "original_title";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_PATH = "poster_path";
    public static final String KEY_VOTE_AVERAGE = "vote_average";
    public static final String KEY_RELEASE_DATE = "release_date";
    public static final String KEY_IMAGE_2 = "backdrop_path";

    public Movie() {

    }

    public Movie(JSONObject movie) throws JSONException {
        this.id = movie.getInt(KEY_ID);
        this.title = movie.getString(KEY_ORIGINAL_TITLE);
        this.image = movie.getString(KEY_PATH);
        this.image2 = movie.getString(KEY_IMAGE_2);
        this.overview = movie.getString(KEY_OVERVIEW);
        this.rating = movie.getInt(KEY_VOTE_AVERAGE);
        this.date = movie.getString(KEY_RELEASE_DATE);
    }

    public Movie(Cursor cursor) {
        this.id = cursor.getInt(PopularMoviesFragment.COL_MOVIE_ID);
        this.title = cursor.getString(PopularMoviesFragment.COL_TITLE);
        this.image = cursor.getString(PopularMoviesFragment.COL_IMAGE);
        this.image2 = cursor.getString(PopularMoviesFragment.COL_IMAGE2);
        this.overview = cursor.getString(PopularMoviesFragment.COL_OVERVIEW);
        this.rating = cursor.getInt(PopularMoviesFragment.COL_RATING);
        this.date = cursor.getString(PopularMoviesFragment.COL_DATE);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getImage2() {
        return image2;
    }

    public String getOverview() {
        return overview;
    }

    public int getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(image2);
        dest.writeString(overview);
        dest.writeInt(rating);
        dest.writeString(date);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        image2 = in.readString();
        overview = in.readString();
        rating = in.readInt();
        date = in.readString();
    }
}
