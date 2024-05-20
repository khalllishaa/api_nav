package com.example.try_out1.spain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.try_out1.R;
import com.example.try_out1.Team.TeamService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class spains extends Fragment {

    private RecyclerView recyclerView;
    private TimAdapter adapter;
    private List<Tim> teamList;
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spain, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        teamList = new ArrayList<>();
        adapter = new TimAdapter(getContext(), teamList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        fetchDataFromAPI();

        return view;
    }

    private void fetchDataFromAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TimService service = retrofit.create(TimService.class);
        Call<TimResponse> call = service.getTeams();
        call.enqueue(new Callback<TimResponse>() {
            @Override
            public void onResponse(Call<TimResponse> call, Response<TimResponse> response) {
                if (response.isSuccessful()) {
                    TimResponse teamResponse = response.body();
                    if (teamResponse != null && teamResponse.getTeams() != null) {
                        teamList.addAll(teamResponse.getTeams());
                        adapter.notifyDataSetChanged();
                        System.out.println("API call successful");
                    }
                } else {
                    // Handle unsuccessful response
                    System.out.println("API call failed with response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TimResponse> call, Throwable t) {
                // Handle network failures
                System.out.println("API call failed with error: " + t.getMessage());
            }
        });
    }
}
