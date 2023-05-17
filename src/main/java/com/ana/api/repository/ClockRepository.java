package com.ana.api.repository;

import com.ana.api.entity.Clock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClockRepository extends JpaRepository<Clock, Long> {

    Optional<Clock> findFirstByUserIdOrderByClockInTimeDesc(Long userId);

}
