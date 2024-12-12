package boundary;

import entity.*;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Lee Seng Wai
 */
public class TutorialMaintenanceUI {

    Scanner scan = new Scanner(System.in);

    // Prompt Tutorial Main Menu
    public int getMenuChoice() {
        System.out.println("\n===================");
        System.out.println("     MAIN MENU");
        System.out.println("===================");
        System.out.println("1. Add a student to a tutorial group");
        System.out.println("2. Remove a student from a tutorial group");
        System.out.println("3. Change tutorial group for a student");
        System.out.println("4. Find a student from all tutorial group");
        System.out.println("5. List all students in a tutorial group");
        System.out.println("6. Filter tutorial groups based on criteria");
        System.out.println("7. Generate report");
        System.out.println("0. Quit");
        System.out.print("Enter Choice: ");
        int choice = Integer.parseInt(scan.nextLine());
        return choice;
    }

    // Prompt Tutorial List
    public void listAllTutorial(String tutorialList) {
        System.out.println("\n===================");
        System.out.println("  Tutorial List: ");
        System.out.println("===================");
        System.out.printf("%2s %-14s %-10s %-10s\n", "", "Tutorial Group", "Programme", "Faculty");
        System.out.println(tutorialList);
    }
    
    // Tutorial header UI
    public void tutorialHeaderUI() {
        System.out.printf("%2s %-14s %-10s %-10s\n", "", "Tutorial Group", "Programme", "Faculty");
    }

    // Prompt Student List
    public void listAllStudents(String studentList) {
        System.out.println("\n===================");
        System.out.println("  Student List: ");
        System.out.println("===================");
        System.out.printf("%2s %-10s %-10s %-10s %-20s %-5s\n", "", "ID", "Name", "Gender", "Email", "Entry Year");
        System.out.println(studentList);
    }

    // Student Header UI
    public void studentHeaderUI() {
        System.out.printf("\n%-10s %-10s %-10s %-20s %-5s\n", "ID", "Name", "Gender", "Email", "Entry Year");
    }

    // Select tutorial group to add student
    public int selectTutorialGroup() {
        System.out.print("Select Tutorial Group: ");
        int selectTutorialGroup = Integer.parseInt(scan.nextLine());
        return selectTutorialGroup;
    }

    // Add student details
    public Student addStudentDetails() {
        System.out.println("\nAdd Student Details: ");
        return new Student(addStudentID(), addStudentName(), addStudentGender(), addStudentEmail(), addStudentEntryYear());
    }

    //Add student ID
    public String addStudentID() {
        System.out.print("Enter Student ID: ");
        String studentID = scan.nextLine();
        return studentID;
    }

    //  Add student Name
    public String addStudentName() {
        System.out.print("Enter Student Name: ");
        String studentName = scan.nextLine();
        return studentName;
    }

    //Add student Gender
    public String addStudentGender() {
        System.out.print("Enter Student Gender: ");
        String studentGender = scan.nextLine();
        return studentGender;
    }

    //  Add student Email
    public String addStudentEmail() {
        System.out.print("Enter Student Email: ");
        String studentEmail = scan.nextLine();
        return studentEmail;
    }

    //  Add student Entry Year
    public int addStudentEntryYear() {
        System.out.print("Enter Student Entry Year: ");
        String studentEntryYear = scan.nextLine();
        return Integer.parseInt(studentEntryYear);
    }

    // Remove student UI
    public int removeStudentUI() {
        System.out.println("Remove Student: ");
        System.out.println("1. By Enter Student ID");
        System.out.println("2. By Enter Student Name");
        System.out.println("0. Back");
        System.out.print("Select Choice: ");
        String removeStudent = scan.nextLine();
        return Integer.parseInt(removeStudent);
    }

    // Remove student by student ID
    public String removeStudentID() {
        System.out.print("Enter Student ID: ");
        String studentID = scan.next();
        scan.nextLine();
        return studentID;
    }

    // Remove student by student name
    public String removeStudentName() {
        System.out.print("Enter Student Name: ");
        String studentName = scan.next();
        scan.nextLine();
        return studentName;
    }

    // Find student by student ID
    public String inputStudentID() {
        System.out.print("Enter Student ID: ");
        String studentIDFind = scan.nextLine();
        return studentIDFind;
    }
    
    // Change a student by entering student ID
    public String changeStudentUI() {
        System.out.print("Please Enter Student ID: ");
        String changeStudentByID = scan.nextLine();
        return changeStudentByID;
    }

    // Student find
    public void studentFindUI(Student studentFind) {
        System.out.println("\nStudent Find: ");
        System.out.println(studentFind);
    }

    // Filter Tutorial group UI
    public int filterTutorialGroupUI() {
        System.out.println("\nFilter Tutorial Group");
        System.out.println("1. By Programme");
        System.out.println("2. By Faculty");
        System.out.println("0. Back");
        System.out.print("Select Number To Filter: ");
        return Integer.parseInt(scan.nextLine());
    }

    // Filter Tutorial Group By Programme
    public String filterTutorialByProgramme() {
        System.out.print("\nEnter Programme: ");
        String programme = scan.nextLine();
        return programme.toUpperCase();
    }
    
    // Display Filtered Programme
    public void displayFilteredProgramme(String filterProgramme, String display) {
        System.out.println("\nList Filtered Programme: (" + display + ")\n");
        if (filterProgramme == null || filterProgramme.equals("")) {
            MessageUI.displayNotFoundMessage();
        } else {
            tutorialHeaderUI();
            System.out.println(filterProgramme);
        }
    }
    
    // Filter Tutorial Group By Faculty
    public String filterTutorialByFaculty() {
        System.out.print("\nEnter Faculty: ");
        String faculty = scan.nextLine();
        return faculty.toUpperCase();
    }
    
    // Display Filtered Faculty
    public void displayFilteredFaculty(String filterFaculty, String display) {
        System.out.println("\nList Filtered Programme: (" + display + ")\n");
        if (filterFaculty == null || filterFaculty.equals("")) {
            MessageUI.displayNotFoundMessage();
        } else {
            tutorialHeaderUI();
            System.out.println(filterFaculty);
        }
    }

    public int generateReportUI() {
        System.out.println("\nGenerate Report");
        System.out.println("1. Programme Report");
        System.out.println("2. Faculty Report");
        System.out.println("0. Back");
        System.out.print("Select Option To Generate Report: ");
        return Integer.parseInt(scan.nextLine());
    }

    
}
