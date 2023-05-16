package com.ana.api.service;

import com.ana.api.entity.Clock;
import com.ana.api.repository.ClockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return clockRepository.findAll();
    }
}
