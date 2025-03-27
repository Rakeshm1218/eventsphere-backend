package com.project.eventsphereBackend.services;

import com.project.eventsphereBackend.models.ReservationModel;
import com.project.eventsphereBackend.models.UserModel;
import com.project.eventsphereBackend.models.EventModel;
import com.project.eventsphereBackend.repository.ReservationRepository;
import com.project.eventsphereBackend.repository.UserRepository;
import com.project.eventsphereBackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public List<ReservationModel> getAllReservations() {
        return reservationRepository.findAll();
    }

    public ReservationModel getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
    }

    public List<ReservationModel> getReservationsByUser(Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return reservationRepository.findByUser(user);
    }

    public List<ReservationModel> getReservationsByEvent(Long eventId) {
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        return reservationRepository.findByEvent(event);
    }
}
