package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class PayrollCalculator {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String fileName = getValidFileName();

        Employee[] roster = analyzeEmployeeInfo(fileName);

        boolean isRunning = true;

        while (isRunning) {

            System.out.println("""
                    Employee info has been analyzed. What would you like to do?
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
                    System.out.println("That is not a menu option. Try again.");
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
            System.out.print("Please enter the name of the file you'd like to analyze: ");
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
                badInput = true;

            } catch (IOException e) {
                // catch other IOExceptions
                System.out.println("Error: file cannot be read. Please try again.");
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

        return roster;

    }

    public static Employee[] printGrossPayInfo() {

    }

    public static void createGrossPayFile() {
        return;
    }

}
