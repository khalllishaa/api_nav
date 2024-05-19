package com.example.try_out1.Event;

import com.example.try_out1.Team.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {
    @GET("search_all_leagues.php?c=England")
    Call<EventResponse> getEvent();
}
