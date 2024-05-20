package com.example.try_out1.Event;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {
    @GET("searchevents.php?e=Arsenal_vs_Chelsea&s=2016-2017")
    Call<EventResponse> getEvent();
}
