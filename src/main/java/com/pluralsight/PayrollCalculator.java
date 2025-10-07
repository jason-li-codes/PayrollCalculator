package com.pluralsight;

import java.io.*;

public class PayrollCalculator {

    public static void main(String[] args) throws IOException {

        // using try/catch with resources for the BufferedReader
        try (BufferedReader bufReader = new BufferedReader(new FileReader("employees.csv"))) {

            // creates Employee array to place Employee objects
            Employee[] roster = new Employee[10];
            // creates int employeeCount
            int employeeCount = 0;

            // eats the first line because it is a label of file columns
            String input = bufReader.readLine();

            // while loop to read each Employee object by initializing input as the bufReader.readLine()
            while ((input = bufReader.readLine()) != null) {

                // splits String along | to get employee info individually
                String[] info = input.split("\\|");

                // creates Employee object based on the readLine info
                Employee newEmp = new Employee(info[0], info[1], Double.parseDouble(info[2]), Double.parseDouble(info[3]));

                // prints out ID, name, and gross pay of each Employee
                System.out.printf("""
                        Employee %s, Name: %s, Gross Pay: $%.2f
                        """, newEmp.getEmployeeId(), newEmp.getName(), newEmp.getGrossPay());

                // adds newEmp to roster and adds to employeeCount
                roster[employeeCount++] = newEmp;

            }

            // catches exceptions with simple print of error message
        } catch (Exception e) {

            System.out.println("Problem reading file.");

        }
    }

}
