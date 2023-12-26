/**
 * Input: Take marks obtained (out of 100) in each subject.
 * Calculate Total Marks: Sum up the marks obtained in all subjects.
 * Calculate Average Percentage: Divide the total marks by the total number of
 * subjects to get the
 * average percentage.
 * Grade Calculation: Assign grades based on the average percentage achieved.
 * Display Results: Show the total marks, average percentage, and the
 * corresponding grade to the user
 */
package Java.task2_Studgrade;

import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Take marks obtained in each subject
        System.out.println("Enter marks obtained in each subject (out of 100):");
        int numberOfSubjects = 0;
        int totalMarks = 0;

        while (true) {
            System.out.print("Enter marks for Subject " + (numberOfSubjects + 1) + " (or -1 to finish): ");
            int marks = scanner.nextInt();

            if (marks == -1) {
                break;
            }

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid input. Marks should be between 0 and 100.");
                continue;
            }

            totalMarks += marks;
            numberOfSubjects++;
        }

        // Calculate Total Marks
        System.out.println("Total Marks: " + totalMarks);

        // Calculate Average Percentage
        if (numberOfSubjects > 0) {
            double averagePercentage = (double) totalMarks / numberOfSubjects;
            System.out.println("Average Percentage: " + averagePercentage + "%");

            // Grade Calculation
            char grade = calculateGrade(averagePercentage);
            System.out.println("Grade: " + grade);
        } else {
            System.out.println("No subjects entered. Cannot calculate average and grade.");
        }
    }

    // Grade Calculation Method
    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
