package com.ana.api.service;

import com.ana.api.entity.Clock;
import com.ana.api.repository.ClockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClockService {

    @Autowired
    private ClockRepository clockRepository;

    public Optional<Clock> getById(Long id) {
        return clockRepository.findById(id);
    }

    public Clock createClock(Clock clock) {
        return clockRepository.save(clock);
    }

    public Clock updateClock(Clock clock) {
        return clockRepository.save(clock);
    }

    public boolean deleteClock(Long id) {
        Optional<Clock> clock = clockRepository.findById(id);

        if (clock.isPresent()) {
            clockRepository.delete(clock.get());
            return true;
        } else {
            return false;
        }
    }
}
