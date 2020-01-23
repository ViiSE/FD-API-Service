/*
 *  Copyright 2020 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.repository.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductsGidsRowMapper implements RowMapper<List<String>> {

    @Override
    public List<String> mapRow(ResultSet rs, int i) throws SQLException {
        List<String> gids = new ArrayList<>();
        do {
            String gid = rs.getString("GID").trim();
            gids.add(gid);
        } while (rs.next());

        return gids;
    }
}
