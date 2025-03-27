package com.project.eventsphereBackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events") // Ensures no conflict with the "users" table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(nullable = false)
    private String eventTitle;

    @Column(nullable = false)
    private String eventDescription;

    @Column(nullable = false)
    private String eventLocation;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date eventDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date registerCloseDate;

    @Column(nullable = false)
    private Double price; // Price of the event

    @Column(nullable = false)
    private Integer totalTickets; // Total tickets available

    @Column(nullable = false)
    private Integer remainingTickets;

    @Column(nullable = false, length = 500)
    private String eventImage;

}
