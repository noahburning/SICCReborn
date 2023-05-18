package com.ana.api.service;

import com.ana.api.entity.Clock;
import com.ana.api.repository.ClockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ClockService {

    @Autowired
    private ClockRepository clockRepository;

    @Transactional
    public Optional<Clock> getById(Long clockId) {
        return clockRepository.findById(clockId);
    }

    @Transactional
    public Clock createClock(Clock clock) {
        return clockRepository.save(clock);
    }

    @Transactional
    public Clock updateClock(Clock clock) {
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
        return (List<Clock>) clockRepository.findAll();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateClockInTimeByUserIdAndTime(Long userId, LocalDateTime currentTime) {
        String jpql = "UPDATE Clock c SET c.clockInTime = :currentTime WHERE c.userId = :userId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("currentTime", currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Transactional
    public void updateClockOutTimeByUserIdAndTime(Long userId, LocalDateTime currentTime) {
        String jpql = "UPDATE Clock c SET c.clockOutTime = :currentTime WHERE c.userId = :userId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("currentTime", currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Transactional
    public Optional<Clock> getLatestClockByUserId(Long userId) {
        return clockRepository.findFirstByUserIdOrderByClockInTimeDesc(userId);
    }
}
