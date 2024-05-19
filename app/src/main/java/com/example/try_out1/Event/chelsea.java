package com.example.try_out1.Event;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.try_out1.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class chelsea extends Fragment {

    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<Event> eventList;
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chelsea, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        eventList = new ArrayList<>();
        adapter = new EventAdapter(getContext(), eventList);
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

        EventService service = retrofit.create(EventService.class);
        Call<EventResponse> call = service.getEvent();
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {
                    EventResponse eventResponse = response.body();
                    if (eventResponse != null && eventResponse.getEvents() != null) {
                        eventList.addAll(eventResponse.getEvents());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                // Handle network failures
            }
        });
    }
}