package com.project.eventsphereBackend.models;

import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "userId", nullable = false) // Foreign key to User table
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false) // Foreign key to Event table
    private EventModel event;

    @Column(nullable = false, unique = true)
    private String paymentId; // Unique payment transaction ID

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private String paymentType;
}
