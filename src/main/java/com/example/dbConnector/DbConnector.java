package com.example.dbConnector;

import com.example.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class DbConnector {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insert(String firstName,String lastName,String address,String joiningDate,String dateOfBirth,int salary){

        String sql = "insert into employee(first_name,last_name,address,joining_date,date_of_birth,salary,is_deleted)\n" +
                "values(:firstName,:lastName,:address,:joiningDate,:dateOfBirth,:salary,false)";

        MapSqlParameterSource  mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("firstName",firstName);
        mapSqlParameterSource.addValue("lastName",lastName);
        mapSqlParameterSource.addValue("address",address);
        mapSqlParameterSource.addValue("joiningDate",joiningDate);
        mapSqlParameterSource.addValue("dateOfBirth",dateOfBirth);
        mapSqlParameterSource.addValue("salary",salary);

        namedParameterJdbcTemplate.update(sql,mapSqlParameterSource);
    }

    public int update(int employeeId,String firstName,String lastName,String address,String joiningDate,String dateOfBirth,int salary){

        String sql = "update employee \n" +
                "set first_name = :firstName,last_name= :lastName,address= :address,joining_date= :joiningDate,\n" +
                "date_of_birth= :dateOfBirth,salary= :salary\n" +
                "where employee_id = :employeeId and is_deleted = 0;";


        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("employeeId",employeeId);
        mapSqlParameterSource.addValue("firstName",firstName);
        mapSqlParameterSource.addValue("lastName",lastName);
        mapSqlParameterSource.addValue("address",address);
        mapSqlParameterSource.addValue("joiningDate",joiningDate);
        mapSqlParameterSource.addValue("dateOfBirth",dateOfBirth);
        mapSqlParameterSource.addValue("salary",salary);

        return namedParameterJdbcTemplate.update(sql,mapSqlParameterSource);
    }

    public List<Employee> getList(){

        String sql = "select * from employee where is_deleted = 0";


        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee get(int employeeId){

        String sql = "select * from employee where employee_id = :employeeId";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("employeeId",employeeId);

        Employee employee=null;

        try{
            employee = namedParameterJdbcTemplate.queryForObject(sql,mapSqlParameterSource,BeanPropertyRowMapper.newInstance(Employee.class) );
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
        }


        return employee;
    }

    public int delete(int employeeId){

        String sql = "update employee set is_deleted = 1 where employee_id = :employeeId and is_deleted = 0";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("employeeId",employeeId);

        return namedParameterJdbcTemplate.update(sql,mapSqlParameterSource);
    }

    public void uploadFile(MultipartFile file) throws IOException {


        file.transferTo(new File("D:\\Projects\\FileUpload\\" + file.getOriginalFilename()));
    }
}
