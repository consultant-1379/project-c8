package com.ericsson.testreporttool.database;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Projects> {

    /**
     * Maps Object to Row
     * @param resultSet
     * @param rowNumber
     * @return
     * @throws SQLException
     */
    @Override
    public Projects mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new Projects(
                resultSet.getString("CSA"),
                resultSet.getString("CRA"),
                resultSet.getString("CNA"),
                resultSet.getString("CXP"),
                resultSet.getString("number"),
                resultSet.getString("rpm"),
                resultSet.getString("taf")
                );
    }
}
