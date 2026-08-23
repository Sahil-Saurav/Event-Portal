package com.example.sahil.eventportal.repository;

import com.example.sahil.eventportal.Enumerated.RegistrationStatus;
import com.example.sahil.eventportal.models.entity.Registration;
import com.example.sahil.eventportal.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findFirstByStatusOrderByRegistrationTimeAsc(RegistrationStatus status);
    List<Registration> findAllByStudentId(Long id);

    @Query("SELECT r.student FROM Registration r WHERE r.event.id = :eventId")
    List<User> findAllStudentByEventId(@Param("eventId") Long eventId);

}
