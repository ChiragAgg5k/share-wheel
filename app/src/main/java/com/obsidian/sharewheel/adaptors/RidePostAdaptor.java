package com.obsidian.sharewheel.adaptors;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obsidian.sharewheel.R;
import com.obsidian.sharewheel.objects.RidePost;

import java.util.ArrayList;

public class RidePostAdaptor extends RecyclerView.Adapter<RidePostAdaptor.ViewHolder> {

    private final ArrayList<RidePost> ridePostList;

    public RidePostAdaptor(ArrayList<RidePost> ridePostList) {
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
        RidePost ridePost = ridePostList.get(position);
        Log.d("RidePostAdaptor", "onBindViewHolder: " + ridePost.getPickup());
        holder.ridePostLocation.setText(ridePost.getPickup());
        holder.ridePostFare.setText(ridePost.getPrice());
        holder.ridePostWhereTo.setText(ridePost.getWhereTo());
        String seatsLeft = String.format("%d seats left", ridePost.getSeats());
        holder.ridePostSeats.setText(seatsLeft);
    }

    @Override
    public int getItemCount() {
        return ridePostList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ridePostLocation, ridePostFare, ridePostWhereTo, ridePostSeats;
        public ViewHolder(@NonNull ViewGroup itemView){
            super(itemView);
            ridePostLocation = itemView.findViewById(R.id.ridePostLocation);
            ridePostFare = itemView.findViewById(R.id.ridePostFare);
            ridePostWhereTo = itemView.findViewById(R.id.ridePostWhereTo);
            ridePostSeats = itemView.findViewById(R.id.ridePostSeatsLeft);
        }
    }
}
