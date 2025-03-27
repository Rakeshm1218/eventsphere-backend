package com.project.eventsphereBackend.repository;

import com.project.eventsphereBackend.models.ReservationModel;
import com.project.eventsphereBackend.models.UserModel;
import com.project.eventsphereBackend.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
    List<ReservationModel> findByUser(UserModel user);
    List<ReservationModel> findByEvent(EventModel event);
}
