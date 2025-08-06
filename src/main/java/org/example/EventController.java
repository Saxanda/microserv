package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event saved = eventService.save(event);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Event> getEvents(
            @RequestParam String tag,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return eventService.findEvents(tag, from, to);
    }

    @GetMapping("/rolling-average")
    public double getRollingAverage(
            @RequestParam String tag,
            @RequestParam int window
    ) {
        return eventService.rollingAverage(tag, window);
    }

    @GetMapping("/breaches")
    public List<Event> getThresholdBreaches(
            @RequestParam String tag,
            @RequestParam double threshold
    ) {
        return eventService.thresholdBreaches(tag, threshold);
    }
}
