package org.example;

import java.time.LocalDateTime;

public class Event {
    private String tag;
    private double value;
    private LocalDateTime timestamp;

    // Constructors
    public Event() {}

    public Event(String tag, double value, LocalDateTime timestamp) {
        this.tag = tag;
        this.value = value;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
