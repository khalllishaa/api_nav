package com.example.try_out1.League;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.try_out1.R;

import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.ViewHolder> {
    private List<Leaguees> teamList;
    private LayoutInflater inflater;

    // Constructor accepting context and teamList
    public LeaguesAdapter(Context context, List<Leaguees> teamList) {
        this.teamList = teamList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Leaguees team = teamList.get(position);
        if (team != null) {
            holder.bind(team);
        }
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, leagues, sport;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idLeague);
            name = itemView.findViewById(R.id.strLeague);
            sport = itemView.findViewById(R.id.strSport);
            leagues = itemView.findViewById(R.id.strLeagueAlternate);
        }

        public void bind(Leaguees team) {
            // Bind data to views
            id.setText(team.getIdLeague());
            name.setText(team.getStrLeague());
            sport.setText(team.getStrSport());
            leagues.setText(team.getStrLeagueAlternate());
        }
    }
}
