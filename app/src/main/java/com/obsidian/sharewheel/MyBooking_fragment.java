package com.obsidian.sharewheel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.obsidian.sharewheel.adaptors.RidePostAdaptor;
import com.obsidian.sharewheel.objects.RidePost;

import java.util.ArrayList;

public class MyBooking_fragment extends Fragment {

    DatabaseReference databaseReference;
    ArrayList<RidePost> usersRidePosts;
    RecyclerView myBookingRecyclerView;

    public MyBooking_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_booking_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference("RidePosts");
        usersRidePosts = new ArrayList<>();
        myBookingRecyclerView = view.findViewById(R.id.myBookingRecyclerView);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersRidePosts.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    RidePost ridePost = dataSnapshot.getValue(RidePost.class);
                    if (ridePost.getId().equals(userId)) {
                        usersRidePosts.add(ridePost);
                    }
                }

                myBookingRecyclerView.setAdapter(new RidePostAdaptor(usersRidePosts));
                myBookingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}