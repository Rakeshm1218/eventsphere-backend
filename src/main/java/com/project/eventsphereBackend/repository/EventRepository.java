package com.project.eventsphereBackend.repository;

import com.project.eventsphereBackend.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {

}
