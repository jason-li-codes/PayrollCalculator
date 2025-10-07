package com.pluralsight;

public class Employee {

    // Employee attributes
    private String employeeId;
    private String name;
    private double hoursWorked;
    private double payRate;


    // Employee constructor that takes in all 4 parameters
    public Employee(String employeeId, String name, double hoursWorked, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    // getters and setters for all attributes

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }


    // getGrossPay by multiplying hoursWorked and payRate attributes
    public double getGrossPay() {
        return this.hoursWorked * this.payRate;
    }

}
