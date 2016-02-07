package com.example.manu.popularmovies;

import android.content.Context;
import android.database.Cursor;

import com.example.manu.popularmovies.data.MovieContract;

/**
 * Created by manu on 12/24/2015.
 */
public class FavoritedUtility {

    public static int isFavorited(Context context, int id) {
        Cursor cursor = context.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null,
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ?",
                new String[] { Integer.toString(id) },
                null
        );
        int numRows = 0;
        if (cursor != null) {
            numRows = cursor.getCount();
            cursor.close();
        }
        return numRows;
    }

    public static String buildImageUrl(int width, String fileName) {
        return "http://image.tmdb.org/t/p/w" + Integer.toString(width) + fileName;
    }
}
