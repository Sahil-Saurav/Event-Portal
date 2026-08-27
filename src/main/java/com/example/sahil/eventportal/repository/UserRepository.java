package com.example.sahil.eventportal.repository;

import com.example.sahil.eventportal.Enumerated;
import com.example.sahil.eventportal.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    Set<User> findAllByRoleName(@Param("roleName") String roleName);

    void deleteByEmail(String email);
}
