package com.example.try_out1.countries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.try_out1.R;

import java.util.List;

public class CoAdapter extends RecyclerView.Adapter<CoAdapter.ViewHolder> {
    private List<Countriess> teamList;
    private LayoutInflater inflater;

    // Constructor accepting context and teamList
    public CoAdapter(Context context, List<Countriess> teamList) {
        this.teamList = teamList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Countriess team = teamList.get(position);
        holder.bind(team);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        ImageView teamImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.textView);
            teamImage = itemView.findViewById(R.id.image);
        }

        public void bind(Countriess team) {
            // Bind data to views
            teamName.setText(team.getStrLeague());
            // Load the image using Glide
            Glide.with(itemView.getContext())
                    .load(team.getStrBadge())
                    .into(teamImage);
        }
    }
}
