package com.pluralsight;

import java.io.*;

public class PayrollCalculator {

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("employees.csv");
        BufferedReader bufReader = new BufferedReader(fileReader);

        Employee[] roster = new Employee[10];
        int employeeCount = 0;

        String input = bufReader.readLine();

        while((input = bufReader.readLine()) != null) {

            String[] info = input.split("\\|");

            Employee newEmp = new Employee(info[0], info[1], Double.parseDouble(info[2]), Double.parseDouble(info[3]));

            System.out.printf("""
                    Employee %s, Name: %s, Gross Pay: $%.2f
                    """, newEmp.getEmployeeId(), newEmp.getName(), newEmp.getGrossPay());

            roster[employeeCount++] = newEmp;

        }

    }

}
