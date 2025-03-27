package com.project.eventsphereBackend.controllers;

import com.project.eventsphereBackend.models.ReservationModel;
import com.project.eventsphereBackend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:5173/")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationModel> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationModel getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping("/users/{userId}")
    public List<ReservationModel> getReservationsByUser(@PathVariable Long userId) {
        return reservationService.getReservationsByUser(userId);
    }

    @GetMapping("/events/{eventId}")
    public List<ReservationModel> getReservationsByEvent(@PathVariable Long eventId) {
        return reservationService.getReservationsByEvent(eventId);
    }

}
