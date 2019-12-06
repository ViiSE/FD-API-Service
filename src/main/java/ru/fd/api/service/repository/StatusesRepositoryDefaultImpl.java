package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.StatusesProducer;
import ru.fd.api.service.repository.mapper.StatusesDefaultRowMapper;

@Repository("statusesRepositoryDefault")
public class StatusesRepositoryDefaultImpl implements StatusesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StatusesProducer statusesProducer;

    @Override
    public Statuses readStatuses() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject("SQL HERE", new StatusesDefaultRowMapper(statusesProducer));
        } catch (DataAccessException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
