package com.obsidian.sharewheel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.obsidian.sharewheel.adaptors.RidePostAdaptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class home_fragment extends Fragment {

    private AutoCompleteTextView pickupText;
    private AutoCompleteTextView dropText;

    RecyclerView ridePostRecycler;

    public home_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        pickupText = view.findViewById(R.id.ridePostPickupInput);
        dropText = view.findViewById(R.id.ridePostWhereToInput);

        ArrayList<String> placeNames = new ArrayList<>();
        try {
            InputStream is = getResources().getAssets().open("place_names.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String names = reader.readLine();
            while (names != null) {
                placeNames.add(names);
                names = reader.readLine();
            }
        } catch (IOException e) {
            //Log.e(TAG, "Error reading txt file", e);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,placeNames);

        pickupText.setAdapter(adapter);
        dropText.setAdapter(adapter);

        pickupText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection1 = (String) parent.getItemAtPosition(position);
                pickupText.setText(selection1);
            }
        });
        dropText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection2 = (String) parent.getItemAtPosition(position);
                dropText.setText(selection2);
            }
        });

        return view;
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