package control;

import adt.*;
import boundary.TutorialMaintenanceUI;
import dao.*;
import entity.*;
import utility.*;
import java.util.Scanner;

/**
 *
 * @author Seng Wai
 */
public class TutorialMaintenance {

    Scanner scan = new Scanner(System.in);

    private ListInterface<Tutorial> tutorialList = new ArrayList<>();
    private TutorialMaintenanceUI tutorialUI = new TutorialMaintenanceUI();

    public TutorialMaintenance() {
        tutorialList = TutorialInitializer.initializeTutorial();
    }

    public void runTutorialMaintenance() {
        int choice = 0;

        do {
            choice = tutorialUI.getMenuChoice();
            switch (choice) {
                case 1: // Add a student to a tutorial goup
                    addStudent();
                    break;
                case 2: // Remove a student from a tutorial group
                    removeStudent();
                    break;
                case 3: // Change the tutorial group for a student
                    changeStudent();
                    break;
                case 4: // Find a student from a tutorial group
                    findStudent();
                    break;
                case 5: // List all students in a tutorial group
                    tutorialUI.listAllTutorial(tutorialList.toString()); // List all tutorial groups

                    System.out.print("Select tutorial group: "); // Select tutorial group (1-4)
                    int option = scan.nextInt();

                    tutorialUI.listAllStudents(tutorialList.getEntry(option).getStudentList().toString());  // List all students according to tutorial group input
                    break;
                case 6: // Filter tutorial groups based on criteria
                    filterTutorialGroup();
                    break;
                case 7: // Generate report
                    generateReport();
                    break;
                case 0: // Exit to tutorial menu
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addStudent() {
        // List all tutorial groups
        tutorialUI.listAllTutorial(tutorialList.toString());
        int option = tutorialUI.selectTutorialGroup();      //  Select a tutorial group to add student

        boolean duplicate = false;

        tutorialUI.listAllStudents(tutorialList.getEntry(option).getStudentList().toString());  // List all students
        Student addStudent = tutorialUI.addStudentDetails();    // Add student details
        for (int i = 1; i <= tutorialList.getNumberOfEntries() && !duplicate; i++) {
            for (int j = 1; j <= tutorialList.getEntry(i).getStudentList().getNumberOfEntries(); j++) {
                if (addStudent.getStudentID().equals(tutorialList.getEntry(i).getStudentList().getEntry(j).getStudentID())) {
                    duplicate = true;
                    break;
                }
            }
        }
        if (duplicate) {
            MessageUI.displayFailToAddStudentMessage();
        } else {
            tutorialList.getEntry(option).getStudentList().add(addStudent);
            MessageUI.displayAddStudentSuccessfulMessage();
        }
    }

    public void removeStudent() {
        // List all tutorial groups
        tutorialUI.listAllTutorial(tutorialList.toString());
        int selectTutorialGroup = tutorialUI.selectTutorialGroup();      //  Choose a tutorial group to remove student
        tutorialUI.listAllStudents(tutorialList.getEntry(selectTutorialGroup).getStudentList().toString());  // List all students
        int choice = 0;
        do {
            choice = tutorialUI.removeStudentUI();
            switch (choice) {
                case 1: // Remove student by enter student ID
                    removeByStudentID(tutorialUI.removeStudentID(), selectTutorialGroup);
                    break;
                case 2: //  Remove student by enter student name
                    removeByStudentName(tutorialUI.removeStudentName(), selectTutorialGroup);
                    //String removeStudentName = removeByStudentID(nameRemove);
                    break;
                case 0: // Exit to tutorial menu
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
                    System.out.println();
            }
        } while (choice != 0);
    }

    private void removeByStudentID(String studentID, int selectTutorialGroup) {
        boolean remove = false;

        for (int i = 1; i <= tutorialList.getEntry(selectTutorialGroup).getStudentList().getNumberOfEntries(); i++) {
            if (tutorialList.getEntry(selectTutorialGroup).getStudentList().getEntry(i).getStudentID().equalsIgnoreCase(studentID)) {
                tutorialList.getEntry(selectTutorialGroup).getStudentList().remove(i);
                remove = true;
                break;
            }
        }
        if (remove) {
            MessageUI.displayRemoveStudentSuccessfulMessage();
        } else {
            MessageUI.displayFailToRemoveStudentMessage();
        }
    }

    private void removeByStudentName(String studentName, int selectTutorialGroup) {
        boolean remove = false;

        for (int i = 1; i <= tutorialList.getEntry(selectTutorialGroup).getStudentList().getNumberOfEntries(); i++) {
            if (tutorialList.getEntry(selectTutorialGroup).getStudentList().getEntry(i).getStudentName().equalsIgnoreCase(studentName)) {
                tutorialList.getEntry(selectTutorialGroup).getStudentList().remove(i);
                remove = true;
                break;
            }
        }
        if (remove) {
            MessageUI.displayRemoveStudentSuccessfulMessage();
        } else {
            MessageUI.displayFailToRemoveStudentMessage();
        }
    }

    public void changeStudent() {
        //  Change the tutorial group for a student
        int option = tutorialUI.selectTutorialGroup();      //  Select a tutorial group to change student
        tutorialUI.listAllStudents(tutorialList.getEntry(option).getStudentList().toString());
        String changeStudent = tutorialUI.changeStudentUI();   // Add Student ID

        Student studentDetails = null;
        boolean student = false;

        for (int i = 1; i <= tutorialList.getEntry(option).getStudentList().getNumberOfEntries(); i++) {
            if (changeStudent.equals(tutorialList.getEntry(option).getStudentList().getEntry(i).getStudentID())) {
                student = true;
                studentDetails = tutorialList.getEntry(option).getStudentList().remove(i);
                break;
            }
        }
        if (!student) {
            MessageUI.displayNotFoundMessage();
        } else {
            tutorialUI.studentHeaderUI();
            System.out.print(studentDetails.toString());
            System.out.print("\n\nSelect tutorial group to change student: ");
            int changeTutorial = scan.nextInt();
            scan.nextLine();
            tutorialList.getEntry(changeTutorial).getStudentList().add(studentDetails);
            MessageUI.displayChangeTutorialGroupSuccessfulMessage();
        }
    }

    // Find a student in a tutorial group through studentID or studentName  
    public void findStudent() {

        // List all tutorial groups
        tutorialUI.listAllTutorial(tutorialList.toString());
        int selectTutorialGroup = tutorialUI.selectTutorialGroup();  //  Select a tutorial group to find student
        tutorialUI.listAllStudents(tutorialList.getEntry(selectTutorialGroup).getStudentList().toString());  // List all students

        // Find a student by entering student ID
        String studentIDFind = tutorialUI.inputStudentID();
        findStudentByID(studentIDFind);
        boolean matchID = false;

        for (int i = 1; i <= tutorialList.getEntry(selectTutorialGroup).getStudentList().getNumberOfEntries(); i++) {
            if (studentIDFind.equals(tutorialList.getEntry(selectTutorialGroup).getStudentList().getEntry(i).getStudentID())) {
                matchID = true;
                tutorialUI.studentFindUI(tutorialList.getEntry(selectTutorialGroup).getStudentList().getEntry(i));
                break;
            }
        }
        if (!matchID) {
            MessageUI.displayStudentNotFoundMessage();
        }
    }

    private int findStudentByID(String studentID) {
        Student studentFind = new Student(studentID);
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
            if (tutorialList.getEntry(i).getStudentList().equals(studentFind)) {
                return i;
            }
        }
        return 0;
    }

    public void filterTutorialGroup() {
        int choice = 0;
        do {
            choice = tutorialUI.filterTutorialGroupUI();
            switch (choice) {
                case 1:
                    // Programme
                    filterTutorialByProgramme(tutorialUI.filterTutorialByProgramme());
                    break;
                case 2:
                    // Faculty
                    filterTutorialByFaculty(tutorialUI.filterTutorialByFaculty());
                    break;
                case 0:
                    // Back to system
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
                    break;

            }

        } while (choice != 0);
    }

    public void filterTutorialByProgramme(String programme) {
        ListInterface<Tutorial> filterProgramme = new ArrayList<>();
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
            if (tutorialList.getEntry(i).getProgrammeCode().equalsIgnoreCase(programme)) {
                filterProgramme.add(tutorialList.getEntry(i));
            }
        }
        String display = "Programme " + programme;
        tutorialUI.displayFilteredProgramme(filterProgramme.toString(), display);
    }

    public void filterTutorialByFaculty(String faculty) {
        ListInterface<Tutorial> filterFaculty = new ArrayList<>();
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
            if (tutorialList.getEntry(i).getFaculty().equalsIgnoreCase(faculty)) {
                filterFaculty.add(tutorialList.getEntry(i));
            }
        }
        String display = "Faculty " + faculty;
        tutorialUI.displayFilteredProgramme(filterFaculty.toString(), display);
    }

    public void generateReport() {
        int choice = 0;
        do {
            choice = (tutorialUI.generateReportUI());
            switch (choice) {
                case 1:
                    // Programme Report
                    programmeReport();
                    break;
                case 2:
                    // Faculty Report
                    facultyReport();
                    break;
                case 0:
                    // Back to system
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    private void programmeReport() {
        System.out.println("\n");
        System.out.println("====================");
        System.out.println("  Programme Report");
        System.out.println("====================");
        ListInterface<String> programmeList = new ArrayList<>();
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
            String programme = tutorialList.getEntry(i).getProgrammeCode();
            if (!programmeList.contains(programme)) {
                programmeList.add(programme);
            }
        }
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            ListInterface<Tutorial> filteredProgramme = new ArrayList<>();
            for (int j = 1; j <= tutorialList.getNumberOfEntries(); j++) {
                if (tutorialList.getEntry(j).getProgrammeCode().equalsIgnoreCase(programmeList.getEntry(i))) {
                    filteredProgramme.add(tutorialList.getEntry(j));
                }
            }

            System.out.println("\nProgramme " + programmeList.getEntry(i));
            tutorialUI.tutorialHeaderUI();
            System.out.println(filteredProgramme);
            System.out.println("Number of records displayed : " + filteredProgramme.getNumberOfEntries());
            System.out.println("\n====================================");
        }
        System.out.println("\n");
    }

    private void facultyReport() {
        System.out.println("\n");
        System.out.println("====================");
        System.out.println("   Faculty Report");
        System.out.println("====================");
        ListInterface<String> facultyList = new ArrayList<>();
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
            String faculty = tutorialList.getEntry(i).getFaculty();
            if (!facultyList.contains(faculty)) {
                facultyList.add(faculty);
            }
        }
        for (int i = 1; i <= facultyList.getNumberOfEntries(); i++) {
            ListInterface<Tutorial> filteredFaculty = new ArrayList<>();
            for (int j = 1; j <= tutorialList.getNumberOfEntries(); j++) {
                if (tutorialList.getEntry(j).getFaculty().equalsIgnoreCase(facultyList.getEntry(i))) {
                    filteredFaculty.add(tutorialList.getEntry(j));
                }
            }

            System.out.println("\nFaculty " + facultyList.getEntry(i));
            tutorialUI.tutorialHeaderUI();
            System.out.println(filteredFaculty);
            System.out.println("Number of records displayed : " + filteredFaculty.getNumberOfEntries());
            System.out.println("\n====================================");
        }
        System.out.println("\n");
    }
}
