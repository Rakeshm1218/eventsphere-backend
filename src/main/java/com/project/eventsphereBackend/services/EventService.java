package com.project.eventsphereBackend.services;

import com.project.eventsphereBackend.models.EventModel;
import com.project.eventsphereBackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventModel> getAllEvents() {
        return eventRepository.findAll();
    }

    public EventModel getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public EventModel createEvent(EventModel eventModel) {
        return eventRepository.save(eventModel);
    }

    public void deleteEvent(Long eventId) {
        EventModel eventModel = getEventById(eventId);
        eventRepository.delete(eventModel);
    }

    public EventModel updateEvent(Long eventId, EventModel newEvent) {
        EventModel existingEvent = getEventById(eventId);
        existingEvent.setEventTitle(newEvent.getEventTitle());
        existingEvent.setEventDescription(newEvent.getEventDescription());
        existingEvent.setEventDate(newEvent.getEventDate());
        existingEvent.setRegisterCloseDate(newEvent.getRegisterCloseDate());
        existingEvent.setEventLocation(newEvent.getEventLocation());
        existingEvent.setPrice(newEvent.getPrice());
        existingEvent.setTotalTickets(newEvent.getTotalTickets());
        existingEvent.setRemainingTickets(newEvent.getRemainingTickets());
        return eventRepository.save(existingEvent);
    }
}
