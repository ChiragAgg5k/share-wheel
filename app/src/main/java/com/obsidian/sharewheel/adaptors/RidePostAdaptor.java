package com.obsidian.sharewheel.adaptors;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obsidian.sharewheel.R;

import java.util.ArrayList;

public class RidePostAdaptor extends RecyclerView.Adapter<RidePostAdaptor.ViewHolder> {

    ArrayList<String> ridePostList;

    public RidePostAdaptor(ArrayList<String> ridePostList) {
        this.ridePostList = ridePostList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.ride_post_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ridePostList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ViewGroup itemView){
            super(itemView);
        }
    }
}
