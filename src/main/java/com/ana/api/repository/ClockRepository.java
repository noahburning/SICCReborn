package com.ana.api.repository;

import com.ana.api.entity.Clock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClockRepository extends JpaRepository<Clock, Long> {
    @Query(value = "SELECT * FROM Clock c WHERE c.userId = :userId ORDER BY c.clock_in_time DESC LIMIT 1", nativeQuery = true)
    Optional<Clock> findLatestByUserId(Long userId);
}
