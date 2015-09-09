package com.springapp.mvc.DAO;

import com.springapp.mvc.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PeopleDAOImpl implements PeopleDAO{

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }

    @Override
    public People findByName(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);

        String sql = "SELECT * FROM peoples WHERE name=:name";

        People result = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new PeopleMapper());

        //new BeanPropertyRowMapper(Customer.class));

        return result;
    }
    @Override
    public List<People> findAll() {

        Map<String, Object> params = new HashMap<String, Object>();

        String sql = "SELECT * FROM peoples";

        List<People> result = namedParameterJdbcTemplate.query(sql, params, new PeopleMapper());

        return result;

    }

    private static final class PeopleMapper implements RowMapper<People> {

        public People mapRow(ResultSet rs, int rowNum) throws SQLException {
            People people = new People();
            people.setId(rs.getInt("id"));
            people.setName(rs.getString("name"));
            people.setAge(rs.getInt("age"));
            return people;
        }
    }
}
