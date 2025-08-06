package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {
    @Test
    void testSaveAndFindEvents() {
        EventService service = new EventService();
        LocalDateTime now = LocalDateTime.now();
        Event e1 = new Event("temp", 25.0, now.minusMinutes(2));
        Event e2 = new Event("temp", 27.0, now.minusMinutes(1));
        service.save(e1);
        service.save(e2);

        assertEquals(2, service.findEvents("temp", now.minusMinutes(5), now).size());
    }

    @Test
    void testRollingAverage() {
        EventService service = new EventService();
        LocalDateTime now = LocalDateTime.now();
        service.save(new Event("temp", 10.0, now.minusSeconds(3)));
        service.save(new Event("temp", 20.0, now.minusSeconds(2)));
        service.save(new Event("temp", 30.0, now.minusSeconds(1)));
        double avg = service.rollingAverage("temp", 2); // last two: 20, 30
        assertEquals(25.0, avg, 0.01);
    }

    @Test
    void testThresholdBreaches() {
        EventService service = new EventService();
        LocalDateTime now = LocalDateTime.now();
        service.save(new Event("pressure", 99.0, now));
        service.save(new Event("pressure", 101.0, now));
        assertEquals(1, service.thresholdBreaches("pressure", 100.0).size());
    }
}