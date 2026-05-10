/* Programmer:  Sara Sunny
Language:       Java
Purpose:        Application to calculate employees pay and print paycheck
URL:
Assignment:     Homework 7
Date:           11/18/20
Course:         Info-210
*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author slbru
 */
import java.security.SecureRandom;
import java.util.Scanner;
enum employeeStatus {F, P};

public class HW7 {
    public static void main (String [] args) {
    Scanner input = new Scanner (System.in); //scanner for user input
    SecureRandom randomNum = new SecureRandom();
    
    
    String choice; String first; String last;
    double hRate = 0; double payCheck = 0; double straightP = 0;
    double overTime = 0; double bonus = 0; double netP = 0;
    int hoursWorked = 0; double percent = 0; int iD = 0;
    employeeStatus status = employeeStatus.P;
    
    do {
        System.out.println("Enter the employee's first name: ");
        first = input.next();
        System.out.println("Enter employee's last name: ");
        last = input.next();
        System.out.println("Enter employee ID: ");
        while (input.hasNext()) {
            if (input.hasNextInt()){
                iD = input.nextInt();
                if (iD <= 0) { 
                    System.out.println("Error. Enter employee's ID: ");
                }
                else {
                    break;
                }
            }    
            else {
                System.out.println("Error. Enter employee's ID: ");
                input.next();
            }    
        }
       
//Prompt for employee hourly rate with check for invalid inputs
        System.out.println("Enter employee's hourly rate: ");
        while (input.hasNext()) {
            if (input.hasNextDouble()){
                hRate = input.nextDouble();
                if (hRate <= 0) { 
                    System.out.println("Error. Enter employee's hourly rate: ");
                }
                else {
                    break;
                }
            }    
            else {
                System.out.println("Error. Enter employee's hourly rate: ");
                input.next();
            }    
        }
//Prompt for hours work for week with check for invalid inputs
        System.out.println("Enter the number of hours employee worked for the week: ");
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                hoursWorked = input.nextInt();
                if (hoursWorked <= 0) {
                    System.out.println("Error. Enter the number of hours worked: ");
                }
                else if (hoursWorked >= 168) {
                    System.out.println("Error. Enter the number of hours worked: ");
                }
                else {
                    break;
                }
            }
            else {
                System.out.println("Error. Enter the number of hours worked: ");
                input.next();
            }
        }
//status of employee with check for invalid inputs
        System.out.println("Is employee Full Time (F) or Part Time (P)");
        while (input.hasNext()) {
            String answer = input.next();
            if (answer.equals("F")) {
                status = employeeStatus.F;
                break;
            }
            else if (answer.equals("P")) {
                status = employeeStatus.P;
                break;
            }
            else {
                System.out.println("Error. Enter employee status as Full Time (F) or Part Time (P)");
                input.hasNext();
            }
        }
//section to calculate paycheck
        
            straightP = Pay(hRate, hoursWorked, status);
            overTime = Pay(hRate, hoursWorked);
            
            netP = straightP + overTime;
        
//random bonus generator calculator
        System.out.println("Is the employee to receive a bonus? Y/N ");
        String response;
        response = input.next();
        if ("Y".equals(response)) {
            percent = (1 + randomNum.nextInt(5)) / 100.0;
            bonus = straightP * percent;
            netP = straightP + bonus;
        }
        
        
//add section for displaying results
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%s\t%s\t\t%s\t%s%n", "Employee Id: ", iD, "First Name: ", first);
        System.out.printf("%s\t%s\t\t%s\t%s%n", "Status: ", status, "Last Name: ", last);
        System.out.printf("%s\t%.2f\t\t%s\t%.2f%n", "Hourly rate: ", hRate, "Straight Pay: ", straightP);
        System.out.printf("%s\t%d\t\t%s\t%.2f%n", "Hours Worked: ", hoursWorked, "Overtime Pay: ", overTime);
        if (status.equals(employeeStatus.F)) {
            System.out.printf("%s%s%n", "Fees Deducted: ", "10");
        }
        System.out.printf("%s\t%.0f\t\t%s\t\t%.2f%n", "Bonus (%): ", percent * 100, "Bonus: ", bonus);
        System.out.printf("%s\t%.2f%n", "Net Pay: ", netP);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println();
        
//loop on choice
        System.out.println("Do you wish to continue? Y/N ");
        choice = input.next();
    } while ("Y".equals(choice));
}
   
    public static double Pay (double hRate, int hoursWorked, employeeStatus status) {
        double straightP = 0;
        
        if (hoursWorked > 40) {
            hoursWorked = 40;
        }
            straightP = hRate * hoursWorked;
        if (employeeStatus.F == status) {
            straightP = straightP - 10;
        }
        return straightP;
}
    
     public static double Pay (double hRate, int hoursWorked) {
        double overTime = 0;
        hoursWorked = hoursWorked - 40;
        if (hoursWorked < 0) {
            hoursWorked = 0;
        }
            overTime = hRate * hoursWorked;
        
        return overTime;
}
}
