package com.ana.api.dao.impl;

import com.ana.api.dao.IBaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BaseDAO<T extends Serializable> extends JdbcDaoSupport implements IBaseDAO<T> {

    private final Class<T> entityClass;

    @Autowired
    public BaseDAO(Class<T> entityClass, JdbcTemplate jdbcTemplate) {
        this.entityClass = entityClass;
        setJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<T> getAll() {
        String tableName = entityClass.getSimpleName().toLowerCase();
        String sql = String.format("SELECT * FROM %s", tableName);
        List<T> entities;

        try {
            entities = Objects.requireNonNull(getJdbcTemplate()).query(sql, new BeanPropertyRowMapper<>(entityClass));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving entities: " + e.getMessage());
        }

        return entities;
    }

    @Override
    public T getById(int id) {
        String tableName = entityClass.getSimpleName().toLowerCase();
        String sql = String.format("SELECT * FROM %s WHERE id = ?", tableName);
        List<T> entities;

        try {
            entities = Objects.requireNonNull(getJdbcTemplate()).query(sql, new BeanPropertyRowMapper<>(entityClass));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving entity: " + e.getMessage());
        }

        if(entities.size() > 1) {
            throw new RuntimeException("More than one entity with the same ID was found. (Primary key violation)");
        } else if(entities.size() == 0) {
            return null;
        } else {
            return entities.get(0);
        }
    }

    @Override
    public void save(T entity) {
        String tableName = entityClass.getSimpleName().toLowerCase();
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
        Field[] fields = entityClass.getDeclaredFields();
        Object[] values = new Object[fields.length];

        for(int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
               values[i] = fields[i].get(entity);
               columns.append(fields[i].getName()).append(", ");
               placeholders.append("?,");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error getting field value: " + e.getMessage());
            }
        }

        columns.deleteCharAt(columns.length() - 1);
        placeholders.deleteCharAt(placeholders.length() - 1);

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, placeholders);

        try {
            Objects.requireNonNull(getJdbcTemplate()).update(sql, values);
        } catch(DataAccessException e) {
            throw new RuntimeException("Error saving entity: " + e.getMessage());
        }
    }

    @Override
    public void update(T entity) {
        String tableName = entityClass.getSimpleName().toLowerCase();
        StringBuilder setClause = new StringBuilder();
        Field[] fields = entityClass.getDeclaredFields();
        Object[] values = new Object[fields.length];
        Object idValue = null;

        for(int i = 0; i < fields.length; i++) {
          fields[i].setAccessible(true);

          try {
              Object fieldValue = fields[i].get(entity);

              if(fields[i].getName().equals("id")) {
                  idValue = fieldValue;
              } else {
                  values[i - 1] = fieldValue;
                  setClause.append(fields[i].getName()).append("=?,");
              }
          } catch(IllegalAccessException e) {
              throw new RuntimeException("Error getting field value: " + e.getMessage());
          }
        }

        setClause.deleteCharAt(setClause.length() - 1);

        String sql = String.format("UPDATE %s SET %s WHERE id=?", tableName, setClause);
        List<Object> paramList = new ArrayList<>(Arrays.asList(values));
        paramList.add(idValue);

        try {
            Objects.requireNonNull(getJdbcTemplate()).update(sql, paramList.toArray());
        } catch(DataAccessException e) {
            throw new RuntimeException("Error updating entity: " + e.getMessage());
        }
    }

    @Override
    public void delete(T entity) {
        String tableName = entityClass.getSimpleName().toLowerCase();
        String sql = String.format("DELETE FROM %s WHERE id=?", tableName);
        Field idField;

        try {
            idField = entityClass.getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("No 'id' field found in entity class: " + e.getMessage());
        }

        idField.setAccessible(true);

        try {
            Object idValue = idField.get(entity);
            Objects.requireNonNull(getJdbcTemplate()).update(sql, idValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error getting field value: " + e.getMessage());
        } catch(DataAccessException e) {
            throw new RuntimeException("Error deleting entity: " + e.getMessage());
        }
    }
}
