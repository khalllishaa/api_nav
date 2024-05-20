package com.example.try_out1.League;


import retrofit2.Call;
import retrofit2.http.GET;

public interface LeaguesService {
    @GET("all_leagues.php")
    abstract Call<LeaguesResponse> getLeagues();
}
