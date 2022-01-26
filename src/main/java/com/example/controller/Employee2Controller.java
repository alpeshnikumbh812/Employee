package com.example.controller;

import com.example.bean.Employee;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class Employee2Controller {

    static final Log LOG = LogFactory.getLog(EmployeeController.class);

    @Autowired
    EmployeeController employeeController;

    @PostMapping(path = "/insert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Map<String, Object>> insert(Employee employee) throws ParseException {
        LOG.debug("inside insert");
        LOG.info("Alpesh");
        return employeeController.insert(employee);
    }

    @PostMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> update(Employee employee) throws ParseException {

       return employeeController.update(employee);

    }
}
