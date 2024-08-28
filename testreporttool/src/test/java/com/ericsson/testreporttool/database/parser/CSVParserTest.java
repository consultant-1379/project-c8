package com.ericsson.testreporttool.database.parser;

import com.ericsson.testreporttool.database.Product;
import com.ericsson.testreporttool.database.Projects;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVParserTest {

    @Test
    void parseCXP() {
        //size 2527
        CSVParser csvParser = new CSVParser();
        List<Projects> cxpList = csvParser.parseCXP();

        assertEquals(2526, cxpList.size());
    }

    @Test
    void parse() {
        CSVParser csvParser = new CSVParser();
        List<Product> res = csvParser.parse("CNA.csv");

        assertEquals(321, res.size());
    }

    @Test
    void parseNull() {
        CSVParser csvParser = new CSVParser();
        List<Product> res = csvParser.parse("asd.asd");

        assertNull(res);
    }
}