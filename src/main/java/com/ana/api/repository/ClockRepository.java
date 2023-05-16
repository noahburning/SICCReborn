package com.ana.api.repository;

import com.ana.api.entity.Clock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClockRepository extends JpaRepository<Clock, Long> {
    // You can add custom query methods or use the default methods provided by JpaRepository
}
