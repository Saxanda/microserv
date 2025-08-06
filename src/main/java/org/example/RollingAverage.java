package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

class SensorReading {
    String sensorId;
    LocalDateTime timestamp;
    double value;

    SensorReading(String id, LocalDateTime t, double v) {
        this.sensorId = id;
        this.timestamp = t;
        this.value = v;
    }
}

public class RollingAverage {
    public static void main(String[] args) {
        List<SensorReading> data = Arrays.asList(
                new SensorReading("A", LocalDateTime.parse("2025-08-06T10:00:00"), 20),
                new SensorReading("A", LocalDateTime.parse("2025-08-06T10:02:00"), 22),
                new SensorReading("A", LocalDateTime.parse("2025-08-06T10:04:00"), 24),
                new SensorReading("A", LocalDateTime.parse("2025-08-06T10:08:00"), 26)
        );

        int windowMinutes = 5;
        for (SensorReading r : data) {
            double avg = computeWindowAvg(data, r.timestamp, windowMinutes);
            System.out.printf("Time: %s, Rolling avg: %.2f\n", r.timestamp, avg);
        }
    }

    static double computeWindowAvg(List<SensorReading> readings, LocalDateTime now, int windowMin) {
        return readings.stream()
                .filter(r -> ChronoUnit.MINUTES.between(r.timestamp, now) >= 0 &&
                        ChronoUnit.MINUTES.between(r.timestamp, now) < windowMin)
                .mapToDouble(r -> r.value)
                .average()
                .orElse(Double.NaN);
    }
}

