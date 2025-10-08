package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class PayrollCalculator {

    public static Scanner input = new Scanner(System.in);

    public static Employee[] roster = new Employee[10];

    public static void main(String[] args) {

        String fileName = getValidFileName();

        analyzeEmployeeInfo(fileName);

        System.out.println("Employee info has been analyzed.");

        boolean isRunning = true;

        while (isRunning) {

            System.out.println("""
                    What would you like to do?
                    (1) Print out employee gross pay information
                    (2) Create file for gross pay information
                    (3) Exit program
                    """);

            int choice = getValidNumber();

            switch (choice) {
                case 1:
                    printGrossPayInfo();
                    break;
                case 2:
                    createGrossPayFile();
                    break;
                case 3:
                    System.out.println("EXITING....");
                    isRunning = false;
                    break;
                default:
                    System.out.println("That is not an available menu option. Please try again.");
                    break;
            }

        }
    }

    public static String getValidFileName() {

        // initializes inputNumber and boolean badInput
        String inputName = "";
        boolean badInput = false;

        // uses do/while loop so validating happens at least once
        do {

            // sets badInput to false first
            badInput = false;

            // ask the user for fileName
            System.out.println("Please enter the name of the file you'd like to analyze: ");
            inputName = input.nextLine();

            try {
                // try to open the file by creating FileReader
                FileReader fileReader = new FileReader(inputName);

                // if that works, the file is real and can be analyzed
                System.out.println("File found: " + inputName);
                // close the file after checking
                fileReader.close();

            } catch (FileNotFoundException e) {
                // if the file does not exist, catch the FileNotFoundException
                System.out.println("Error: file not found. Please try again.");
                e.getStackTrace();
                badInput = true;

            } catch (IOException e) {
                // catch other IOExceptions
                System.out.println("Error: file cannot be read. Please try again.");
                e.getStackTrace();
                badInput = true;
            }

            // conditional checks badInput boolean
        } while (badInput);

        // returns the correct inputNumber as an int
        return inputName;

    }

    public static int getValidNumber() {

        // initializes inputNumber and boolean badInput
        int inputNumber = 0;
        boolean badInput = false;

        // uses do/while loop
        do {

            // sets badInput to false first
            badInput = false;

            //tries to get an input
            try {
                inputNumber = input.nextInt();
                // if it can't read as int, throws exception with error message and sets badInput to true to try again
            } catch (Exception e) {
                System.out.println("Sorry I don't know what you mean, let's try again.");
                e.getStackTrace();
                badInput = true;
            }
            // eats buffer
            input.nextLine();
            // conditional checks badInput boolean
        } while (badInput);

        // returns the correct inputNumber as an int
        return inputNumber;

    }

    // using try/catch with resources for the BufferedReader
    public static void analyzeEmployeeInfo(String fileName) {

        // initializing BufferedReader
        try (BufferedReader bufReader = new BufferedReader(new FileReader(fileName))) {

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

                // adds newEmp to roster and adds to employeeCount
                roster[employeeCount++] = newEmp;
            }

            // catches exceptions with simple print of error message
        } catch (Exception e) {

            System.out.println("Error: File cannot be read. Please try again.");
            e.getStackTrace();
        }

    }

    public static void printGrossPayInfo() {

        for (Employee employee : roster) {

            if (employee != null) {
                // prints out ID, name, and gross pay of each Employee only if they are not null
                System.out.printf("""
                        Employee %s, Name: %s, Gross Pay: $%.2f
                        """, employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

            }
        }
    }

    public static void createGrossPayFile() {

            System.out.println("Enter the name of the .csv or .json file you would like to create: ");
            String newFileName = input.nextLine();



            if (newFileName.endsWith(".csv") && newFileName.length() > 4) {

                try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter(newFileName))) {

                    bufWriter.write("id|name|gross pay\n");

                    for (Employee employee : roster) {

                        if (employee != null) {
                            bufWriter.write(String.join("|", employee.getEmployeeId(), employee.getName(),
                                    String.format("%.2f", employee.getGrossPay())) + "\n");
                        }
                    }
                    System.out.println("File created successfully.");

                } catch (IOException e) {
                    System.out.println("Error: file cannot be written. Please try again.");
                    e.getStackTrace();
                }
            } else if (newFileName.endsWith(".json") && newFileName.length() > 5) {

                try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter(newFileName))) {

                    bufWriter.write("[\n");

                    for (Employee employee : roster) {

                        if (employee != null) {
                            bufWriter.write(
                                    "{ " +
                                            String.join(", ",
                                                    "\"id\" : " + employee.getEmployeeId(),
                                                    "\"name\" : " + employee.getName(),
                                                    "\"grossPay\" : " + String.format("%.2f", employee.getGrossPay()))
                                            + " }\n");
                        }
                    }
                    bufWriter.write("]");

                    System.out.println("File created successfully.");

                } catch (IOException e) {
                    System.out.println("Error: file cannot be written. Please try again.");
                    e.getStackTrace();
                }
            } else {
                System.out.println("This file name is invalid/too short. Please try again with different file name.");
            }
    }

}
