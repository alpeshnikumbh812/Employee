package com.example.validation;

import com.example.bean.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class Validation {

    public static final String FIRST_NAME_VALIDATION = "First Name length should be less than 20 charecter";
    public static final String LAST_NAME_VALIDATION = "Last Name length should be less than 20 charecter";
    public static final String ADDRESS_VALIDATION = "Address length should be less than 50 charecter";
    public static final String JOINING_DATE_VALIDATION = "Joining date should be in proper format yyyy/mm/dd";
    public static final String DATE_OF_BIRTH_VALIDATION = "Date of birth shoub in proper formate yyyy/mm/dd";

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String,Object> validationList(Employee employee){

        Map<String, Object> validationMessages = new HashMap<>();

        if (employee.getFirstName().length() > 20) {
            validationMessages.put("firstName", FIRST_NAME_VALIDATION);
        }

        if (employee.getLastName().length() > 20) {
            validationMessages.put("lastName", LAST_NAME_VALIDATION);
        }

        if (employee.getAddress().length() > 50) {
            validationMessages.put("address", ADDRESS_VALIDATION);
        }

        if (!isDateValid(employee.getJoiningDate())) {
            validationMessages.put("joiningDate", JOINING_DATE_VALIDATION);
        }

        if (!isDateValid(employee.getDateOfBirth())) {
            validationMessages.put("dateOfBirth", DATE_OF_BIRTH_VALIDATION);
        }

        return validationMessages;
    }

    public boolean isDateValid(String strDate){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        dateFormat.setLenient(false);

        try {
            dateFormat.parse(strDate);
            String[] dates = strDate.split("-");
            try {
                LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1])
                        , Integer.parseInt(dates[2]));
            } catch (Exception e) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
