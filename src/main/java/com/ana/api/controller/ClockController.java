package com.ana.api.controller;

import com.ana.api.entity.Clock;
import com.ana.api.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clocks")
public class ClockController {

    @Autowired
    private ClockService clockService;

    @GetMapping(path = "/{clockId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clock> getById(@PathVariable("clockId") Long clockId) {
        Optional<Clock> clock = clockService.getById(clockId);

        return clock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clock> createClock(@RequestBody Clock clock) {
        Clock createdClock = clockService.createClock(clock);
        return ResponseEntity.ok(createdClock);
    }

    @PutMapping(path = "/{clockId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clock> updateClock(@PathVariable("clockId") Long clockId, @RequestBody Clock clock) {
        Optional<Clock> existingClock = clockService.getById(clockId);

        if (existingClock.isPresent()) {
            clock.setClockId(clockId);
            Clock updatedClock = clockService.updateClock(clock);
            return ResponseEntity.ok(updatedClock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{clockId}")
    public ResponseEntity<Void> deleteClock(@PathVariable("clockId") Long clockId) {
        boolean deleted = clockService.deleteClock(clockId);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
