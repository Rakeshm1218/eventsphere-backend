package com.project.eventsphereBackend.repository;

import com.project.eventsphereBackend.models.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel,Long> {

}
