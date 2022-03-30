package ru.digitalliague.questionsserver.entity;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionRowMapper implements ResultSetExtractor<Map<String, List<String>>> {
    @Override
    public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, List<String>> resMap = new LinkedHashMap<>();
        while (rs.next()){
            System.out.println(rs);
        }
        return null;
    }
}
