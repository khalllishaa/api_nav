package com.example.try_out1.countries;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoService {
    @GET("search_all_leagues.php?c =England&s=Soccer")
    Call<CoResponse> getCountries();
}
