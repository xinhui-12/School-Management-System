
import adt.*;
import control.*;
import entity.*;
import utility.MessageUI;
import java.util.Scanner;

/**
 *
 * @author Xin Hui
 */
public class index {

    public static void main(String[] args) {
        // Initialize in the control class, thus when the control class be called the list has been initialized
        TutorialMaintenance tutorial = new TutorialMaintenance();
        ProgrammeMaintainance programme = new ProgrammeMaintainance();
        CourseManagement course = new CourseManagement();
        TutorManagement tutor = new TutorManagement();

        // able to be use the updated list between the different management
        ListInterface<Course> courseList = course.courseList;
        ListInterface<Tutor> tutorList = tutor.tutorList;

        int choice;

        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nUniversity System");
            System.out.println("---------------------");
            System.out.println("1. Tutorial Group Management");
            System.out.println("2. Tutor Management");
            System.out.println("3. Course Management");
            System.out.println("4. Programme Management");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scan.nextInt();
            scan.nextLine();
            System.out.println("");
            
            switch (choice) {
                case 1: //Tutorial Group Management
                    tutorial.runTutorialMaintenance();
                    break;
                case 2: // Tutor Management 
                    // update tutor list after done the management
                    tutorList = tutor.runTutorManagement(courseList);
                    break;
                case 3: // Course Management
                    course.runCourseManagement();
                    courseList = course.courseList;
                    break;
                case 4: // Programme Management
                    programme.runProgrammeMaintainance();
                    break;
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }

        } while (choice != 0);

    }

}
