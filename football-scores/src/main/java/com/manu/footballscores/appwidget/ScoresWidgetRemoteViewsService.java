package com.manu.footballscores.appwidget;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.manu.footballscores.R;
import com.manu.footballscores.provider.ScoresContract;
import com.manu.footballscores.util.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScoresWidgetRemoteViewsService extends RemoteViewsService {
    public static final String TAG = ScoresWidgetRemoteViewsService.class.getSimpleName();

    private interface ScoresQuery {
        String[] PROJECTION = {
                ScoresContract.ScoreEntry.COLUMN_LEAGUE,
                ScoresContract.ScoreEntry.COLUMN_HOME,
                ScoresContract.ScoreEntry.COLUMN_AWAY,
                ScoresContract.ScoreEntry.COLUMN_HOME_GOALS,
                ScoresContract.ScoreEntry.COLUMN_AWAY_GOALS,
                ScoresContract.ScoreEntry.COLUMN_MATCH_DAY,
                ScoresContract.ScoreEntry.COLUMN_MATCH_ID
        };

        int LEAGUE = 0;
        int HOME = 1;
        int AWAY = 2;
        int HOME_GOALS = 3;
        int AWAY_GOALS = 4;
        int MATCH_DAY = 5;
        int MATCH_ID = 6;
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor mCursor = null;

            @Override
            public void onCreate() {
                Log.d(TAG, "onCreate");
            }

            @Override
            public void onDataSetChanged() {
                Log.d(TAG, "onDataSetChanged");
                if (mCursor != null) {
                    mCursor.close();
                }
                final long identityToken = Binder.clearCallingIdentity();
                Uri uri = ScoresContract.ScoreEntry.buildScoreWithDate();
                String formatString = getString(R.string.date_format_ymd);
                SimpleDateFormat format = new SimpleDateFormat(formatString);
                String todayDate = format.format(new Date());

                mCursor = getContentResolver().query(uri,
                        ScoresQuery.PROJECTION,
                        null,
                        new String[]{todayDate},
                        null);
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (mCursor != null) {
                    mCursor.close();
                    mCursor = null;
                }
            }

            @Override
            public int getCount() {
                return mCursor == null ? 0 : mCursor.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        mCursor == null || !mCursor.moveToPosition(position)) {
                    return null;
                }
                final RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_item_score);

                String league = Utilities.getLeague(getResources(), mCursor.getInt(ScoresQuery.LEAGUE));
                Log.d(TAG, "league=" + league);
                String matchDay = getString(R.string.match_day, mCursor.getString(ScoresQuery.MATCH_DAY));
                String homeTeamName = mCursor.getString(ScoresQuery.HOME);
                String awayTeamName = mCursor.getString(ScoresQuery.AWAY);
                String score = Utilities.getScores(getResources(),
                        mCursor.getInt(ScoresQuery.HOME_GOALS), mCursor.getInt(ScoresQuery.AWAY_GOALS));

                views.setTextViewText(R.id.widget_item_league, league);
                views.setTextViewText(R.id.widget_item_home_name, homeTeamName);
                views.setTextViewText(R.id.widget_item_away_name, awayTeamName);
                views.setTextViewText(R.id.widget_item_score, score);
                views.setTextViewText(R.id.widget_item_matchday, matchDay);

                views.setImageViewResource(R.id.widget_item_home_crest, Utilities.getTeamCrestByTeamName(homeTeamName));
                views.setImageViewResource(R.id.widget_item_away_crest, Utilities.getTeamCrestByTeamName(awayTeamName));

                views.setContentDescription(R.id.widget_item_home_crest, homeTeamName);
                views.setContentDescription(R.id.widget_item_away_crest, awayTeamName);
                views.setContentDescription(R.id.widget_item_home_name, homeTeamName);
                views.setContentDescription(R.id.widget_item_away_name, awayTeamName);
                views.setContentDescription(R.id.widget_item_league, league);
                views.setContentDescription(R.id.widget_item_matchday, matchDay);
                views.setContentDescription(R.id.widget_item_score, score);

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_item_score);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (mCursor.moveToPosition(position))
                    return mCursor.getLong(ScoresQuery.MATCH_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}