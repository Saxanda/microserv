package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SensorReadingTest {
    @Test
    void testRollingAverage () {
        List<SensorReading> data = Arrays.asList(
                new SensorReading("A", LocalDateTime.parse("2025-08-06T10:00:00"), 20),
                new SensorReading("A", LocalDateTime.parse("2025-08-06T10:02:00"), 22),
                new SensorReading("A", LocalDateTime.parse("2025-08-06T10:04:00"), 24),
                new SensorReading("A", LocalDateTime.parse("2025-08-06T10:08:00"), 26)
        );
    double avg = RollingAverage.computeWindowAvg(
            data, LocalDateTime.parse("2025-08-06T10:04:00"), 5);
            assertEquals((20 + 22 + 24) / 3.0, avg, 0.001);

           avg = RollingAverage.computeWindowAvg(
                data, LocalDateTime.parse("2025-08-06T10:08:00"), 5);
            assertEquals(25, avg, 0.000);
        // No data in window
            avg = RollingAverage.computeWindowAvg(
                data, LocalDateTime.parse("2025-08-06T09:59:00"), 5);
        assertTrue(Double.isNaN(avg));
    }
}