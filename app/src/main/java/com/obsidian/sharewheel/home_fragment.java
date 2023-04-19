package com.obsidian.sharewheel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.obsidian.sharewheel.adaptors.RidePostAdaptor;

import java.util.ArrayList;

public class home_fragment extends Fragment {

    RecyclerView ridePostRecycler;

    public home_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ridePostRecycler = view.findViewById(R.id.ridePostRecycler);

        ArrayList<String> ridePostList = new ArrayList<>();
        ridePostList.add("1");
        ridePostList.add("2");
        ridePostList.add("3");

        RidePostAdaptor adaptor = new RidePostAdaptor(ridePostList);
        ridePostRecycler.setAdapter(adaptor);
        ridePostRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}