package com.ana.api.repository;

import com.ana.api.entity.Clock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClockRepository extends CrudRepository<Clock, Long> {

    Optional<Clock> findFirstByUserIdOrderByClockInTimeDesc(Long userId);

}
