package com.example.controller;

import com.example.bean.Employee;
import com.example.dbConnector.DbConnector;
import com.example.validation.Validation;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Configuration
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    DbConnector dbConnector;

    static final Log LOG = LogFactory.getLog(EmployeeController.class);

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> insert(@RequestBody Employee employee) throws ParseException {

        LOG.debug("inside insert");
        Validation validation = new Validation();
        Map<String, Object> validationList = validation.validationList(employee);
        System.out.println(validationList);


        Map<String, Object> out = new HashMap<>();

        if (validationList.isEmpty()) {
            dbConnector.insert(employee.getFirstName(), employee.getLastName(), employee.getAddress()
                    , employee.getJoiningDate(), employee.getDateOfBirth(), employee.getSalary());
            out.put("status", "Success");
            out.put("message", "Entry saved successfully");
        } else {
            out.put("status", "Fail");
            out.put("messages", validationList);
            return ResponseEntity.badRequest().body(out);
        }
        return ResponseEntity.accepted().body(out);

    }

    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>>  update(@RequestBody Employee employee) throws ParseException {

        Validation validation = new Validation();
        Map<String, Object> validationList = validation.validationList(employee);

        Map<String, Object> out = new HashMap<>();

        if (validationList.isEmpty()) {
            int updateCount = dbConnector.update(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getAddress()
                    , employee.getJoiningDate(), employee.getDateOfBirth(), employee.getSalary());

            if(updateCount==0){
                out.put("status","Fail");
                out.put("message","No record found");

            }
            else{
                out.put("status","Success");
                out.put("message", "Entry updated successfully");
            }
        } else {
            out.put("status", "Fail");
            out.put("messages", validationList);
            return ResponseEntity.badRequest().body(out);
        }
        return ResponseEntity.badRequest().body(out);
    }

    @GetMapping(path = "/get-list")
    public Map<String, Object> getList() {
        Map<String, Object> employee = new HashMap<>();

        employee.put("status", "Success");
        employee.put("employeeList",dbConnector.getList());
        return employee;
    }

    @GetMapping("/delete/{employeeId}")
    public Map<String, Object> delete(@PathVariable int employeeId) {

        Map<String, Object> message = new HashMap<>();
        int deleteCount = dbConnector.delete(employeeId);


        if(deleteCount==0){
            message.put("status","Fail");
            message.put("message","No record found");
        }
        else{
            message.put("status","Success");
            message.put("message", "Entry deleted successfully");
        }

        return message;
    }

    @GetMapping("/get/{employeeId}")
    public Map<String, Object> get(@PathVariable int employeeId) {

        Map<String, Object> employeeMap = new HashMap<>();

        Employee employee = dbConnector.get(employeeId);
        if (Objects.isNull(employee)) {
            employeeMap.put("status", "Fail");
            employeeMap.put("message", "Record is not exist");

        } else {
            employeeMap.put("status", "Success");
            employeeMap.put("employeeMap", dbConnector.get(employeeId));
        }
        return employeeMap;
    }
}
