package ru.fd.api.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fd.api.service.database.SQLQueryCreator;
import ru.fd.api.service.entity.Departments;
import ru.fd.api.service.exception.CreatorException;
import ru.fd.api.service.exception.RepositoryException;
import ru.fd.api.service.producer.entity.DepartmentProducer;
import ru.fd.api.service.producer.entity.DepartmentsProducer;
import ru.fd.api.service.repository.mapper.DepartmentsDefaultRowMapper;

@Repository("departmentsRepositoryDefault")
public class DepartmentsRepositoryDefaultImpl implements DepartmentsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final DepartmentProducer departmentProducer;
    private final DepartmentsProducer departmentsProducer;
    private final SQLQueryCreator<String, String> sqlQueryCreator;

    public DepartmentsRepositoryDefaultImpl(
            DepartmentProducer departmentProducer,
            DepartmentsProducer departmentsProducer,
            SQLQueryCreator<String, String> sqlQueryCreator) {
        this.departmentProducer = departmentProducer;
        this.departmentsProducer = departmentsProducer;
        this.sqlQueryCreator = sqlQueryCreator;
    }

    @Override
    public Departments readDepartments() throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(
                    sqlQueryCreator.create("departments.sql")
                            .content(),
                    new DepartmentsDefaultRowMapper(departmentProducer, departmentsProducer));
//            return jdbcTemplate.queryForObject(
//                    "SELECT e.NAME, UUID_TO_CHAR(e.GID) as GID FROM ELU e\n" +
//                        "    LEFT JOIN SpDI_Values(\n" +
//                        "        (SELECT df.KOD FROM DI_FIELD df WHERE df.IDENT = 'Elu_CloseTK'),\n" +
//                        "        (SELECT tr.KOD FROM TYPERES tr WHERE tr.IDENT = 'Elu'),\n" +
//                        "        e.KOD,\n" +
//                        "        cast('NOW' as date))\n" +
//                        "        cl on 1=1\n" +
//                        "    WHERE (e.IDENT STARTING 'ShopIndex_' OR E.IDENT STARTING 'Skl_')\n" +
//                        "    AND coalesce(cl.FI, 0) = 0\n" +
//                        "    AND e.IDENT <> 'Skl_9'\n" +
//                        "ORDER BY 1 collate pxw_cyrl\n",
//                    new DepartmentsDefaultRowMapper(departmentProducer, departmentsProducer));
        } catch (DataAccessException | CreatorException ex) {
            throw new RepositoryException(ex.getMessage(), ex.getCause());
        }
    }
}
