package com.ana.api.service;

import com.ana.api.entity.Clock;
import com.ana.api.repository.ClockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClockService {

    private final ClockRepository clockRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public ClockService(ClockRepository clockRepository) {
        this.clockRepository = clockRepository;
    }

    @Transactional
    public Optional<Clock> getById(Long clockId) {
        return clockRepository.findById(clockId);
    }

    @Transactional
    public Clock createClock(Clock clock) {
        return clockRepository.save(clock);
    }


    @Transactional
    public boolean deleteClock(Long clockId) {
        Optional<Clock> clock = clockRepository.findById(clockId);

        if (clock.isPresent()) {
            clockRepository.delete(clock.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<Clock> getAllClocks() {
        return clockRepository.findAll();
    }



    @Transactional
    public Long findLastClockIdByUserId(Long userId) {
        String jpql = "SELECT c.clockId FROM Clock c WHERE c.userId = :userId ORDER BY c.clockInTime DESC";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("userId", userId);
        query.setMaxResults(1);

        List<Long> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }



    @Transactional
    public Clock updateClockOutTimeByClockIdAndTime(Long clockId, LocalDateTime placeholder) {
        Optional<Clock> existingClock = clockRepository.findById(clockId);

        if (existingClock.isPresent()) {
            Clock clock = existingClock.get();
            LocalDateTime currentTime = LocalDateTime.now();
            clock.setClockOutTime(currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return clockRepository.save(clock);
        } else {
            throw new NoSuchElementException("Clock not found with ID: " + clockId);
        }
    }



}
