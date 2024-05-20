package com.example.try_out1.spain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TimService {
    @GET("search_all_teams.php?s=Soccer&c=Spain")
    abstract Call<TimResponse> getTeams();
}
