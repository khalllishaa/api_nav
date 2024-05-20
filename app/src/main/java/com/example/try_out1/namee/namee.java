package com.example.try_out1.namee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.try_out1.R;
import com.example.try_out1.spain.Tim;
import com.example.try_out1.spain.TimAdapter;
import com.example.try_out1.spain.TimResponse;
import com.example.try_out1.spain.TimService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class namee extends Fragment {
    private RecyclerView recyclerView;
    private NameAdapter adapter;
    private List<Name> teamList;
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_name, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        teamList = new ArrayList<>();
        adapter = new NameAdapter(getContext(), teamList);
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

        NameService service = retrofit.create(NameService.class);
        Call<NameResponse> call = service.getName();
        call.enqueue(new Callback<NameResponse>() {
            @Override
            public void onResponse(Call<NameResponse> call, Response<NameResponse> response) {
                if (response.isSuccessful()) {
                    NameResponse teamResponse = response.body();
                    if (teamResponse != null && teamResponse.getName() != null) {
                        teamList.addAll(teamResponse.getName());
                        adapter.notifyDataSetChanged();
                        System.out.println("API call successful");
                    }
                } else {
                    // Handle unsuccessful response
                    System.out.println("API call failed with response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<NameResponse> call, Throwable t) {
                // Handle network failures
                System.out.println("API call failed with error: " + t.getMessage());
            }
        });
    }
}
