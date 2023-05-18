package com.ana.client.model;

import com.ana.api.entity.Clock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClockModel {

    /**
     * Retrieves the latest clock entry for the specified user ID.
     *
     * @param userId the ID of the user
     * @return an optional containing the latest clock entry, or empty if not found
     */
    Long findLastClockIdByUserId(Long userId);

    /**
     * Creates a new clock entry.
     *
     * @param clock the clock entry to create
     * @return the created clock entry
     */
    Clock createClock(Clock clock);

    /**
     * Updates an existing clock entry.
     *
     * @param clock the clock entry to update
     * @return the updated clock entry
     */

    Long getUserIdByUsername(String username);
    void updateClockOutTimeByClockIdAndTime(Long clockId, LocalDateTime currentTime);
}
