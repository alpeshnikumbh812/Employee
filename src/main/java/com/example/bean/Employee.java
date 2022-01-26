package com.example.bean;

public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String joiningDate;
    private String dateOfBirth;
    private int salary;

    public Employee(){}

    public Employee(String firstName, String lastName, String address, String joiningDate, String dateOfBirth, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.joiningDate = joiningDate;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", salary=" + salary +
                '}';
    }

}


