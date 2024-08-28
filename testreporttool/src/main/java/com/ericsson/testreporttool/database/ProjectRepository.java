package com.ericsson.testreporttool.database;

import com.ericsson.testreporttool.database.Processor.ProcessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Lazy
public class ProjectRepository {

    private static final String DEFAULT_URL = "https://gerrit.ericsson.se/#/admin/projects/OSS/";

    @Autowired
    JdbcTemplate template;

    public List<Product> getAllCna() throws DataAccessException{
        String sql = "SELECT * FROM CNA";
        return template.query(sql, new ProductRowMapper());
    }

    public List<Product> getAllCsa() throws DataAccessException{
        String sql = "SELECT * FROM CSA";
        return template.query(sql, new ProductRowMapper());
    }

    public List<Product> getAllCra() throws DataAccessException{
        String sql = "SELECT * FROM CRA";
        return template.query(sql, new ProductRowMapper());
    }

    public List<Projects> getAllCxp() throws DataAccessException{
        String sql = "SELECT * FROM CXP";
        return template.query(sql, new ProjectRowMapper());
    }

    /**
     * Retrieve URL based off a product number.
     * @param productNumber : (e.g) CNA 000 0000
     * @return Combined URL
     */
    public String getUrl(String productNumber) {
        String sql = "SELECT RPM FROM CXP WHERE Number=?";
        String rpm = template.queryForObject(sql, String.class, productNumber);
        return DEFAULT_URL + rpm;
    }

    /**
     * Processes a Jenkins job URL into simple form to query the database.
     * @param url : Jenkins Job URL
     * @return Product Number
     */
    public String parseJenkinsUrl(String url) {
       return getProductNumberFromJenkinsJob(ProcessInfo.ProcessJobURL(url));
    }

    /**
     * Retrieve a product number based off Jenkins Job URL.
     * @param name : Processed Jenkins Job URL (e.g : transport-api)
     * @return Product Number
     */
    public String getProductNumberFromJenkinsJob(String name) {
        String num = "";
        try {
            String sql = "SELECT Number FROM CXP WHERE RPM LIKE ?";
            num = template.queryForObject(sql, String.class, "%" + name + "%");
        } catch (EmptyResultDataAccessException exception) {
            num = "0";
        } catch (IncorrectResultSizeDataAccessException exception) {
            num = "0";
        }
        return num;
    }

    public List<JenkinsData> getAllTestsForENM() throws DataAccessException{
        String sql1 = "SELECT * FROM Jenkins";
        return template.query(sql1, new JenkinsDataRowMapper());
    }

    public List<JenkinsData> getNumberOfTestsForProduct(String productNumber) throws DataAccessException{
        String sql = "SELECT * FROM Jenkins where Number=?";
        return template.query(sql, new JenkinsDataRowMapper(), productNumber);
    }

    public List<Projects> getCxpsFromCna(String cnaName) throws DataAccessException {
        String sql = "SELECT * FROM CXP WHERE CNA=?";
        return template.query(sql, new ProjectRowMapper(), cnaName);
    }

    public List<JenkinsData> getAllJenkinsData() throws DataAccessException{
        String sql = "SELECT * FROM Jenkins";
        return template.query(sql, new JenkinsDataRowMapper());
    }

    public List<JenkinsData> getHigestTestFailureENM() throws DataAccessException{
        String sql = "SELECT * FROM Jenkins ORDER BY Fail DESC";
        return template.query(sql, new JenkinsDataRowMapper());
    }

    public List<JenkinsData> getHigestTestFailureForAProduct(String productNumber) throws DataAccessException{
        String sql = "SELECT * FROM Jenkins Where Number=? ORDER BY Fail DESC";
        return template.query(sql, new JenkinsDataRowMapper(), productNumber);
    }

    public List<JenkinsData> getJenkinsDataFromPeriod(Date start, Date end) throws DataAccessException{
        String sql = "SELECT * FROM Jenkins WHERE Date BETWEEN ? AND ?";
        return template.query(sql, new JenkinsDataRowMapper(), start, end);
    }

    public List<JenkinsData> getJenkinsDataFromPeriodForAProduct(Date start, Date end, String productNumber) throws DataAccessException{
        String sql = "SELECT * FROM Jenkins WHERE Number=? AND Date BETWEEN ? AND ?";
        return template.query(sql, new JenkinsDataRowMapper(), productNumber, start, end);
    }

}
