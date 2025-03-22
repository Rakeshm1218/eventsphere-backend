package com.project.eventsphereBackend.controllers;

import com.project.eventsphereBackend.models.EventModel;
import com.project.eventsphereBackend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events") // Base path for event APIs
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Get all events
    @GetMapping("/getAll")
    public List<EventModel> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Get event by ID
    @GetMapping("/{eventId}")
    public EventModel getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }

    // Create a new event
    @PostMapping("/create")
    public EventModel createEvent(@RequestBody EventModel eventModel) {
        return eventService.createEvent(eventModel);
    }

    // Update an existing event
    @PutMapping("/update/{eventId}")
    public EventModel updateEvent(@PathVariable Long eventId, @RequestBody EventModel eventModel) {
        return eventService.updateEvent(eventId, eventModel);
    }

    // Delete an event
    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }
}
