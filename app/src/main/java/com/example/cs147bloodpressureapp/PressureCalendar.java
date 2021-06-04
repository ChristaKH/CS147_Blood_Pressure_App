package com.example.cs147bloodpressureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class PressureCalendar extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    CalendarView pressureCalendarView;
    HashMap<String, Vector<PressureSnapshot>> pressures = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_calendar);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("pressure");
        pressureCalendarView = findViewById(R.id.pressureCalendarView);

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

        // perform setOnDateChangeListener event on CalendarView
        pressureCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Check to see if this date exists in the database.
                // For all instances where the date exists in the database, display them in a list.
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                TextView calendarTextView = findViewById(R.id.calendarTextView);

                if(pressures.containsKey(date)) {
                    // Display the first pressure for that day
                    ArrayList<PressureSnapshot> tempList = new ArrayList<>(pressures.get(date));
                    PressureSnapshot tempSnap = tempList.get(tempList.size() - 1);
                    String text = "Systolic: " + tempSnap.getSystolic() + "\nDiastolic: " + tempSnap.getDiastolic() + "\nTime: " + tempSnap.getTime();
                    calendarTextView.setText(text);
                    PressureSnapshotAdapter adapter = new PressureSnapshotAdapter(getApplicationContext(),
                                                        R.layout.calendar_pressure_list_item, tempList);
                    ListView calendarPressureListView = findViewById(R.id.calendarPressureListView);
                    calendarPressureListView.setAdapter(adapter);
                } else {
                    // Say that there are none for that day
                    calendarTextView.setText("No pressure taken");
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}