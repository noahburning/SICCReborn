package com.ana.api.controller;

import com.ana.api.entity.Clock;
import com.ana.api.service.ClockService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.Optional;

@RestController
@RequestMapping("/clocks")
public class ClockController {

    LocalDateTime LocalDateTime;

    @Autowired
    private ClockService clockService;

    @GetMapping(path = "/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clock> getLatestClockByUserId(@RequestParam("userId") Long userId) {
        Long latestClockId = clockService.findLastClockIdByUserId(userId);

        if (latestClockId != null) {
            Optional<Clock> latestClock = clockService.getById(latestClockId);
            return latestClock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        return ResponseEntity.notFound().build();
    }


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
    public ResponseEntity<Clock> updateClockOutTimeByClockIdAndTime(@PathVariable("clockId") Long clockId, @RequestBody Clock clock) {
        Optional<Clock> existingClock = clockService.getById(clockId);

        if (existingClock.isPresent()) {

            Clock updatedClock = clockService.updateClockOutTimeByClockIdAndTime(clockId, LocalDateTime);
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
