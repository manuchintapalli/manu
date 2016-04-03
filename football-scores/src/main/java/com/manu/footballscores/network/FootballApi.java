package com.manu.footballscores.network;

import com.manu.footballscores.network.model.FixturesResponse;
import com.manu.footballscores.network.model.Season;
import com.manu.footballscores.network.model.TeamResponse;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface FootballApi {

    @GET("/soccerseasons") List<Season> seasons();

    @GET("/soccerseasons/{id}/teams") TeamResponse teams(
            @Path("id") String seasonId);

    @GET("/fixtures") FixturesResponse fixtures(
            @Query("timeFrame") String timeFrame);

}
