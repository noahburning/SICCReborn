package com.ana.client.model;

import com.ana.api.entity.Clock;

import java.util.List;
import java.util.Optional;

public interface ClockModel {

    /**
     * Retrieves the latest clock entry for the specified user ID.
     *
     * @param userId the ID of the user
     * @return an optional containing the latest clock entry, or empty if not found
     */
    Optional<Clock> getLatestClockByUserId(Long userId);

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
    Clock updateClock(Clock clock);

    /**
     * Retrieves all clock entries for the specified user ID.
     *
     * @param userId the ID of the user
     * @return a list of clock entries
     */
    List<Clock> getAllClocksByUserId(Long userId);

    /**
     * Retrieves the user ID associated with the given username.
     *
     * @param username the username of the user
     * @return the user ID if found, null otherwise
     */
    Long getUserIdByUsername(String username);
}
