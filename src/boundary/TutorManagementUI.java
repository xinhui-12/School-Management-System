package boundary;

import entity.*;
import utility.MessageUI;
import control.TutorManagement;
import java.util.Scanner;

/**
 *
 * @author Xin Hui
 */
public class TutorManagementUI {

    Scanner scan = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("____________________________");
        System.out.println("  Tutor Menu          ");
        System.out.println("____________________________");
        System.out.println("1. List all tutors");
        System.out.println("2. Add new tutor");
        System.out.println("3. Remove a tutor");
        System.out.println("4. Edit a tutor");
        System.out.println("5. Find a tutor");
        System.out.println("6. Filter tutors based on criteria");
        System.out.println("7. Add course to a tutor");
        System.out.println("8. Remove course from a tutor");
        System.out.println("9. Generate report");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
        return Integer.parseInt(scan.nextLine());
    }

    public void tutorHeaderUI() {
        System.out.printf("%2s %-10s %-20s %-10s %-20s %-8s %-11s %-10s %s\n", "", "ID", "Name", "Gender", "Email", "Faculty", "Salary", "Course ID", "Course Name");
    }

    public void courseHeaderUI() {
        System.out.printf("%-2s %-9s %-38s %-8s %-13s\n", "", "Course ID", "Course Name", "Faculty", "Credit Hours");
    }

    public void listAllTutors(String allTutors) {
        System.out.println("\nList all tutors: \n");
        tutorHeaderUI();
        System.out.println(allTutors);
    }

    public String inputTutorID() {
        System.out.print("Enter Tutor ID: ");
        return scan.nextLine();
    }

    public String inputTutorName() {
        System.out.print("Enter Tutor Name: ");
        return scan.nextLine();
    }

    public String inputTutorGender() {
        System.out.print("Enter Tutor Gender (Male/Female): ");
        String gender = scan.nextLine();
        while (!TutorManagement.validGender(gender)) {
            System.out.println("** Invalid Gender **");
            System.out.print("Enter Tutor Gender (Male/Female): ");
            gender = scan.nextLine();
        }
        gender = gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
        return gender;
    }

    public String inputTutorEmail() {
        System.out.print("Enter Tutor Email: ");
        return scan.nextLine();
    }

    public String inputTutorFaculty() {
        System.out.print("Enter Tutor Faculty: ");
        String faculty = scan.nextLine();
        while (!TutorManagement.validFaculty(faculty)) {
            System.out.println("** Invalid Faculty (4 alphabet only) **");
            System.out.print("Enter Tutor Faculty: ");
            faculty = scan.nextLine();
        }
        return faculty.toUpperCase();
    }

    public float inputTutorSalary() {
        System.out.print("Enter Tutor Salary: RM");
        String salaryInput = scan.nextLine();
        while (!TutorManagement.validSalary(salaryInput)) {
            System.out.println("** Invalid Salary **");
            System.out.print("Enter Tutor Salary: RM");
            salaryInput = scan.nextLine();
        }
        return Float.parseFloat(salaryInput);
    }

    public int inputPosition() {
        System.out.print("Enter Entry Position (None = 0): ");
        return Integer.parseInt(scan.nextLine());
    }

    public Tutor addTutorUI() {
        System.out.println("\nNew Tutor Details: ");
        return new Tutor(inputTutorID(), inputTutorName(), inputTutorGender(), inputTutorEmail(), inputTutorFaculty(), inputTutorSalary());
    }

    public int removeTutorUI() {
        System.out.println("\nRemove Tutor");
        System.out.println("1. By Entry Position (The number sequence from the list all)");
        System.out.println("2. By Tutor ID");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
        return Integer.parseInt(scan.nextLine());
    }

    public int editTutorUI() {
        System.out.println("\nEdit Tutor");
        return inputPosition();
    }

    public int choiceEditTutorUI(Tutor tutorEdited) {
        System.out.println("\n" + tutorEdited);
        System.out.println("1. Tutor Name");
        System.out.println("2. Tutor Gender");
        System.out.println("3. Tutor Email");
        System.out.println("4. Tutor Faculty");
        System.out.println("5. Tutor Salary");
        System.out.println("0. Done");
        System.out.print("Select number to edit: ");
        return Integer.parseInt(scan.nextLine());
    }

    public int filterTutorUI() {
        System.out.println("\nFilter Tutor");
        System.out.println("1. By Faculty");
        System.out.println("2. By Range of Salaray");
        System.out.println("3. By Gender");
        System.out.println("0. Back");
        System.out.print("Select number to filter: ");
        return Integer.parseInt(scan.nextLine());
    }

    public void showFilteredTutorUI(String filteredTutor, String condition) {
        System.out.println("\nList filtered tutors: (" + condition + ")");
        tutorHeaderUI();
        if (filteredTutor == null || filteredTutor.equals("")) {
            MessageUI.displayNotFoundMessage();
        } else {
            System.out.println(filteredTutor);
        }
    }

    public float inputTutorMinSalary() {
        System.out.print("Enter Minimum Salary to filter: RM");
        String salaryInput = scan.nextLine();
        while (!TutorManagement.validSalary(salaryInput)) {
            System.out.println("** Invalid Salary **");
            System.out.print("Enter Minimum Salary to filter: RM");
            salaryInput = scan.nextLine();
        }
        return Float.parseFloat(salaryInput);
    }

    public float inputTutorMaxSalary() {
        System.out.print("Enter Maximum Salary to filter: RM");
        String salaryInput = scan.nextLine();
        while (!TutorManagement.validSalary(salaryInput)) {
            System.out.println("** Invalid Salary **");
            System.out.print("Enter Maximum Salary to filter: RM");
            salaryInput = scan.nextLine();
        }
        return Float.parseFloat(salaryInput);
    }

    public int addCourseToTutor(String allCourse) {
        System.out.println("\nAll course available");
        System.out.println("--------------------------");
        courseHeaderUI();
        System.out.println(allCourse);
        System.out.print("Select number to add: ");
        return Integer.parseInt(scan.nextLine());
    }
    
    public int removeCourseFromTutor() {
        System.out.print("Select number to remove: ");
        return Integer.parseInt(scan.nextLine());
    }

    public void showTutor(Tutor tutor) {
        System.out.println("\nTutor Details ");
        System.out.println("------------------");
        System.out.println("Tutor ID: " + tutor.getTutorID());
        System.out.println("Tutor Name: " + tutor.getTutorName());
        System.out.println("Tutor Gender: " + tutor.getTutorGender());
        System.out.println("Tutor Email: " + tutor.getTutorEmail());
        System.out.println("Tutor Faculty: " + tutor.getTutorFaculty());
        System.out.println("Tutor Salary: RM" + tutor.getTutorSalary());
        System.out.println("Course Handle:");
        if (tutor.getCourseList().getNumberOfEntries() == 0) {
            System.out.println("-");
        } else {
            courseHeaderUI();            
            System.out.println(tutor.courseString());
        }

    }

    public void generateReportUI(String tutorString, float average, int record) {
        System.out.println("\n");
        System.out.println("*************************");
        System.out.println("Analysis Report (" + java.time.LocalDate.now() + ")");
        System.out.println("*************************");
        System.out.println(tutorString);
        System.out.println("< " + record +  " record(s) displayed >");
        System.out.println("Average number of course teach by a tutor: " + average + " course(s)\n");
    }

}
