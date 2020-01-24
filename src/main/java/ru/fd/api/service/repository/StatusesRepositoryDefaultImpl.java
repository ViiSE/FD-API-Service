package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Statuses;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.StatusProducer;
import ru.fd.api.service.producer.entity.StatusesProducer;
import ru.fd.api.service.repository.mapper.StatusesDefaultRowMapper;

// TODO: 23.01.2020 CREATE SQL
@Repository("statusesRepositoryDefault")
public class StatusesRepositoryDefaultImpl implements StatusesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final StatusProducer statusProducer;
    private final StatusesProducer statusesProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public StatusesRepositoryDefaultImpl(
            StatusProducer statusProducer,
            StatusesProducer statusesProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.statusProducer = statusProducer;
        this.statusesProducer = statusesProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Statuses read() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("statuses.sql").content(),
                    new StatusesDefaultRowMapper(/*statusProducer,*/ statusesProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
