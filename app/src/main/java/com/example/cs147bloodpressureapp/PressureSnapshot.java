package com.example.cs147bloodpressureapp;

public class PressureSnapshot {
    public String systolic;
    public String diastolic;
    public String timestamp;

    PressureSnapshot() {
    }

    PressureSnapshot(String systolic, String diastolic, String timestamp) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.timestamp = timestamp;
    }

    String getSystolic() {
        return this.systolic;
    }

    String getDiastolic() {
        return this.diastolic;
    }

    String getTimestamp() {
        return this.timestamp;
    }

    String getDate() {
        String year = timestamp.substring(0, 4);
        String month = timestamp.substring(5, 7);
        String day = timestamp.substring(8, 10);
        if(day.charAt(0) == '0') {
            day = day.substring(1, 2);
        }

        if(month.charAt(0) == '0') {
            month = month.substring(1, 2);
        }

        String date = month + "/" + day + "/" + year;

        return date;
    }

    String getTime() {
        String time = timestamp.substring(14,19);
        return time;
    }

    void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
