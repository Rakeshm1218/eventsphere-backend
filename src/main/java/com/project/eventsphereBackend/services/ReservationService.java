package com.project.eventsphereBackend.services;

import com.project.eventsphereBackend.models.ReservationModel;
import com.project.eventsphereBackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationModel> getAllReservation() {
        return reservationRepository.findAll();
    }
}
