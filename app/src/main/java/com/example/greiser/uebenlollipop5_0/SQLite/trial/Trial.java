package com.example.greiser.uebenlollipop5_0.SQLite.trial;

public class Trial {

    public static  final String TABLE_TRIAL = "Trial";
    public static  final String COLUMN_ID_TRIAL = "_id";
    public static  final String COLUMN_TIMESTAMP_TRIAL = "timestamp";
    public static  final String COLUMN_POSITIVE_TRIAL = "positive";
    
    private final long id;
    private final String timestamp;
    private final boolean positive;

    public Trial(long id, String timestamp, boolean positive) {
        this.id = id;
        this.timestamp = timestamp;
        this.positive = positive;
    }

    public long getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isPositive() {
        return positive;
    }
}
