package com.project.eventsphereBackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key from User table
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false) // Foreign key from Event table
    private EventModel event;

    @Column(nullable = false, unique = true)
    private String paymentId; // Unique payment transaction ID

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private String paymentType;


}
