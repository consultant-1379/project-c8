package com.ericsson.testreporttool.database;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JenkinsDataRowMapper implements RowMapper<JenkinsData> {

    @Override
    public JenkinsData mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new JenkinsData(
                rs.getString("Number"),
                rs.getString("TestURLs"),
                rs.getInt("Pass"),
                rs.getInt("Fail"),
                rs.getString("Date"));
    }
}
