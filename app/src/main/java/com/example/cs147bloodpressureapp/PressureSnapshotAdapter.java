package com.example.cs147bloodpressureapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PressureSnapshotAdapter extends ArrayAdapter<PressureSnapshot> {
    private ArrayList<PressureSnapshot> pressureSnapshots;

    public PressureSnapshotAdapter(@NonNull Context context, int resource, ArrayList<PressureSnapshot> list) {
        super(context, resource);
        this.pressureSnapshots = list;
    }

    public PressureSnapshotAdapter(Context context, ArrayList<PressureSnapshot> list) {
        super(context, R.layout.calendar_pressure_list_item, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_pressure_list_item, parent, false);
        }

        TextView systolicTextView = convertView.findViewById(R.id.systolicTextView);
        TextView diastolicTextView = convertView.findViewById(R.id.diastolicTextView);
        TextView timeTextView = convertView.findViewById(R.id.timeTextView);

        systolicTextView.setText("Systolic: " + pressureSnapshots.get(position).getSystolic());
        diastolicTextView.setText("Diastolic: " + pressureSnapshots.get(position).getDiastolic());
        timeTextView.setText("Time: " + pressureSnapshots.get(position).getTime());
        return super.getView(position, convertView, parent);
/*
        PressureSnapshot snap = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_pressure_list_item, parent, false);
        }

        TextView systolicTextView = convertView.findViewById(R.id.systolicTextView);
        TextView diastolicTextView = convertView.findViewById(R.id.diastolicTextView);
        TextView timeTextView = convertView.findViewById(R.id.timeTextView);

        systolicTextView.setText(snap.getSystolic());
        diastolicTextView.setText(snap.getDiastolic());
        timeTextView.setText(snap.getTime());
        return super.getView(position, convertView, parent);*/
    }
}
