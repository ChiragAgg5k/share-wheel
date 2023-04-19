package com.obsidian.sharewheel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.obsidian.sharewheel.adaptors.RidePostAdaptor;
import com.obsidian.sharewheel.objects.RidePost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class home_fragment extends Fragment {

    private AutoCompleteTextView pickupText;
    private AutoCompleteTextView dropText;
    private TextView priceText;
    RadioGroup seat_radio;
    RecyclerView ridePostRecycler;
    Button ridePostButton;
    DatabaseReference databaseReference, userDatabaseReference;

    public home_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        pickupText = view.findViewById(R.id.ridePostPickupInput);
        dropText = view.findViewById(R.id.ridePostWhereToInput);
        priceText = view.findViewById(R.id.ridePostPriceInput);
        ridePostButton = view.findViewById(R.id.ridePostButton);

        seat_radio = view.findViewById(R.id.seat_radio);

        databaseReference = FirebaseDatabase.getInstance().getReference("RidePosts");

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

        ridePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pickup = pickupText.getText().toString().trim();
                String drop = dropText.getText().toString().trim();
                String price = priceText.getText().toString().trim();

                int selectedId = seat_radio.getCheckedRadioButtonId();

                if (pickup.isEmpty()) {
                    pickupText.setError("Pickup location is required");
                    pickupText.requestFocus();
                    return;
                }

                if (drop.isEmpty()) {
                    dropText.setError("Drop location is required");
                    dropText.requestFocus();
                    return;
                }

                if (price.isEmpty()) {
                    priceText.setError("Price is required");
                    priceText.requestFocus();
                    return;
                }

                int selectedSeats;

                if (selectedId == -1) {
                    Toast.makeText(getContext(), "Please select number of seats", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    switch (selectedId) {
                        case R.id.seat_1:
                            selectedSeats = 1;
                            break;
                        case R.id.seat_2:
                            selectedSeats = 2;
                            break;
                        case R.id.seat_3:
                            selectedSeats = 3;
                            break;
                        case R.id.seat_4:
                            selectedSeats = 4;
                            break;
                        default:
                            selectedSeats = 0;
                    }

                }

                RidePost ridePost = new RidePost(pickup, drop, selectedSeats,price);
                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(ridePost);

                pickupText.setText("");
                dropText.setText("");
                priceText.setText("");
                seat_radio.clearCheck();
                Toast.makeText(getContext(), "Ride Posted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ridePostRecycler = view.findViewById(R.id.ridePostRecycler);

        ArrayList<RidePost> ridePostList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("RidePosts");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ridePostList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    RidePost ridePost = dataSnapshot.getValue(RidePost.class);
                    ridePostList.add(ridePost);
                }

                RidePostAdaptor adaptor = new RidePostAdaptor(ridePostList);
                ridePostRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                ridePostRecycler.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}