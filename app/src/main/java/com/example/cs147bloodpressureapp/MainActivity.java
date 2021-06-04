package com.example.cs147bloodpressureapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Vector;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    MaterialButton displayButton;
    MaterialButton changeActivityButton;
    //Vector<PressureSnapshot> pressures = new Vector<>();
    HashMap<String, Vector<PressureSnapshot>> pressures = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayButton = findViewById(R.id.displayButton);
        changeActivityButton = findViewById(R.id.changeActivityButton);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("pressure");

        displayButton.setOnClickListener((View v) -> {
            buttonClick(v);
        });

        changeActivityButton.setOnClickListener((View v) -> {
            changeActivity(v);
        });

        //reference.setValue("This is where you insert something into database");
        reference.orderByChild("ts").limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                PressureSnapshot snap = snapshot.getValue(PressureSnapshot.class);
                if(pressures.containsKey(snap.getDate())) {
                    // Add to the existing vector
                    pressures.get(snap.getDate()).add(snap);
                } else {
                    // Add new vector with the initial snapshot
                    pressures.put(snap.getDate(), new Vector<PressureSnapshot>());
                    pressures.get(snap.getDate()).add(snap);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View v)
    {/*
        int last_index = pressures.size() - 1;
        TextView testTextView = findViewById(R.id.testTextView);
        testTextView.setText(pressures.get(last_index).getDiastolic());*/
    }

    public void changeActivity(View v) {
        Intent changeActivityIntent = new Intent(this, PressureCalendar.class);
        startActivity(changeActivityIntent);
    }
}