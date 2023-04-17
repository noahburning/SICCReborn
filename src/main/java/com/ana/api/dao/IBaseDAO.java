package com.ana.api.dao;

import java.util.List;

/**
 * The base interface for all Data Access Objects.
 * Separating the DAOs from the entities allows for the DAOs to be reused for different entities.
 * Video: <a href="https://www.youtube.com/watch?v=ui01Li4vqDc">The DAO Pattern in Java | Data Access Object Design Pattern</a>
 *
 * @param <T> The type of the entity.
 */
public interface IBaseDAO<T> {
    /**
     * Gets all entities.
     *
     * @return A list of all entities.
     */
    List<T> getAll();

    /**
     * Gets an entity by its ID.
     *
     * @param id The ID of the entity.
     * @return The entity with the given ID.
     */
    T getById(int id);

    /**
     * Adds an entity.
     *
     * @param entity The entity to add.
     */
    void save(T entity);

    /**
     * Updates an entity.
     *
     * @param entity The entity to update.
     */
    void update(T entity);

    /**
     * Deletes an entity.
     *
     * @param entity The entity to delete.
     */
    void delete(T entity);
}
