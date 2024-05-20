package com.example.try_out1.namee;


import retrofit2.Call;
import retrofit2.http.GET;

public interface NameService {
    @GET("all_countries.php")
    abstract Call<NameResponse> getName();
}
