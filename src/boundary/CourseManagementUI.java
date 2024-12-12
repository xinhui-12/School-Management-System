package boundary;

import adt.*;
import entity.*;
import java.util.Scanner;

/**
 *
 * @author KIMBERLEY LIEW YAN TUNG
 */
public class CourseManagementUI {

    Scanner scanner = new Scanner(System.in);
    ListInterface<Course> courseList = new ArrayList<>();
    ListInterface<Programme> programmeList = new ArrayList<>();

    //Get user input choice
    public int getCourseMenu() {
        System.out.println("====================================");
        System.out.println("            COURSE MENU");
        System.out.println("====================================");
        System.out.println("1. LIST ALL COURSE");
        System.out.println("2. ADD NEW COURSE");
        System.out.println("3. REMOVE COURSE");
        System.out.println("4. SEARCH COURSE");
        System.out.println("5. EDIT COURSE DETAILS");
        System.out.println("6. ADD PROGRAMME TO COURSE");
        System.out.println("7. REMOVE PROGRAMME FROM COURSE");
        System.out.println("8. GENERATE REPORT");
        System.out.println("0. BACK");
        System.out.print("Enter Your Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    //List out all the course
    public void listAllCourse(String allCourse) {
        System.out.println("\nList all course: \n");
        System.out.printf("%s %-9s %-38s %-8s %-14s %-20s \n", "No", "Course ID", "Course Name", "Faculty", "Credit Hours", "Programme Offered");
        System.out.println(allCourse);
    }

    /*Add new course : courseID, courseName, faculty, credit hours*/
    //course id
    public String newCourseID() {
        System.out.print("New course ID: ");
        return scanner.nextLine();
    }

    //course name
    public String newCourseName() {
        System.out.print("New course name: ");
        return scanner.nextLine();
    }

    //faculty
    public String newCourseFaculty() {
        System.out.print("Faculty: ");
        return scanner.nextLine();
    }

    //credit hours
    public int courseCreditHours() {
        System.out.print("Credit Hours: ");
        return scanner.nextInt();
    }

    //add new course 
    public Course addNewCourse() {
        System.out.println("==============================");
        System.out.println("    New Course Details ");
        System.out.println("==============================");
        return new Course(newCourseID(), newCourseName(), newCourseFaculty(), courseCreditHours());
    }

    //Removing course: by position, by id
    public int removeCourse() {

        System.out.println("==============================");
        System.out.println("        REMOVE COURSE ");
        System.out.println("==============================");
        System.out.println("1. Remove by course position");
        System.out.println("2. Remove by course id");
        System.out.println("0. Back");
        System.out.print("Choice: ");
        return scanner.nextInt();
    }

    //remove by position
    public int removeByPosition() {
        System.out.print("Position to remove: ");
        return scanner.nextInt();
    }

    //remove by id
    public String removeByID() {
        System.out.print("Course id to remove: ");
        return scanner.next();
    }

    //Search Course: by id, by name
    public int searchCourseUI() {
        System.out.println("==============================");
        System.out.println("        SEARCH COURSE ");
        System.out.println("==============================");
        System.out.println("1. Search by course id");
        System.out.println("2. Search by course name");
        System.out.println("3. Search by faculty");
        System.out.println("0. Back");
        System.out.print("Choice: ");
        return scanner.nextInt();
    }

    //search by id
    public String searchByID() {
        System.out.print("Course ID: ");
        return scanner.next();
    }

    //search by name
    public String searchByName() {
        System.out.print("Course Name: ");
        scanner.nextLine();
        return scanner.nextLine();
    }

    //search by faculty
    public String searchByFaculty() {
        System.out.print("Faculty: ");
        return scanner.next();
    }

    //Edit course details
    public int editCourseUI() {
        System.out.print("Enter position to edit: ");
        int positionToEdit = scanner.nextInt();
        return positionToEdit;

    }

    //UI for user input choices
    public int editCourseUIChoice(Course courseToEdit) {
        System.out.println("==============================");
        System.out.println("        EDIT COURSE ");
        System.out.println("==============================");
        System.out.println("1. Edit Course ID");
        System.out.println("2. Edit Course Name");
        System.out.println("3. Edit faculty");
        System.out.println("4. Edit credit hours");
        System.out.println("0. Back");
        System.out.print("Choice: ");
        return scanner.nextInt();
    }

    //edit course id
    public String editID() {
        System.out.print("Edited ID: ");
        return scanner.next();
    }

    //edit course name
    public String editName() {
        System.out.print("Edited Name: ");
        scanner.nextLine();
        return scanner.nextLine();

    }

    //edit faculty
    public String editFaculty() {
        System.out.print("Edited Faculty: ");
        return scanner.next();
    }

    //edit credit hours
    public int editCreditHours() {
        System.out.print("Edited Credit Hours: ");
        return scanner.nextInt();
    }

    //get course position
    public int addPorgrammePosition() {
        System.out.print("Choose a course to add programme (Position): ");
        return scanner.nextInt();
    }

    //add programme
    public int addPorgramme() {
        System.out.print("Select Programme to add (Position) : ");
        return scanner.nextInt();
    }

    //remove programme position
    public int removePorgrammePosition() {
        System.out.print("Choose a course to remove programme (Position): ");
        return scanner.nextInt();
    }

//    remove programme
    public int removePorgramme(Course courseSelected) {
        programmeOffered(courseSelected);
        System.out.print("Programme to be removed (Position): ");
        return scanner.nextInt();
    }

    //show programme offered in course
    public void programmeOffered(Course courseSelected) {
        System.out.println("Course ID: " + courseSelected.getCourseID());
        System.out.println("Course Name: " + courseSelected.getCourseName());
        System.out.println("Programme Offered: ");
        System.out.println(courseSelected.getProgrammeList());
    }

    //Generate Report
    public void reportUI() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                          COURSE ANALYSIS REPORT                                      |");
        System.out.println("+--------------------------------------------------------------------------------------+");


    }
}
