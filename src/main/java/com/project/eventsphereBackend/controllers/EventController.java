package com.project.eventsphereBackend.controllers;

import com.project.eventsphereBackend.models.EventModel;
import com.project.eventsphereBackend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")// Base path for event APIs
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Get all events
    @GetMapping("/events/getAll")
    public List<EventModel> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Get event by ID
    @GetMapping("/events/{eventId}")
    public EventModel getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }

    // Create a new event
    @PostMapping("admin/events/create")
    public EventModel createEvent(@RequestBody EventModel eventModel) {
        return eventService.createEvent(eventModel);
    }

    // Update an existing event
    @PutMapping("admin/events/update/{eventId}")
    public EventModel updateEvent(@PathVariable Long eventId, @RequestBody EventModel eventModel) {
        return eventService.updateEvent(eventId, eventModel);
    }

    // Delete an event
    @DeleteMapping("admin/events/delete/{eventId}")
    public String deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return "Event deleted";
    }
}
