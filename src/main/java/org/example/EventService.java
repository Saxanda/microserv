package org.example;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final List<Event> events = new ArrayList<>();

    public Event save(Event event) {
        events.add(event);
        return event;
    }

    public List<Event> findEvents(String tag, LocalDateTime from, LocalDateTime to) {
        return events.stream()
                .filter(e -> e.getTag().equals(tag))
                .filter(e -> !e.getTimestamp().isBefore(from) && !e.getTimestamp().isAfter(to))
                .collect(Collectors.toList());
    }

    // Rolling average over last N events
    public double rollingAverage(String tag, int windowSize) {
        List<Event> filtered = events.stream()
                .filter(e -> e.getTag().equals(tag))
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .limit(windowSize)
                .collect(Collectors.toList());
        return filtered.stream()
                .mapToDouble(Event::getValue)
                .average()
                .orElse(0);
    }

    // Threshold detection: events above threshold
    public List<Event> thresholdBreaches(String tag, double threshold) {
        return events.stream()
                .filter(e -> e.getTag().equals(tag) && e.getValue() > threshold)
                .collect(Collectors.toList());
    }
}
