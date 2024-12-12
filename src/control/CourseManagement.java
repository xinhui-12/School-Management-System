package control;

import adt.*;
import boundary.CourseManagementUI;
import dao.*;
import entity.*;
import utility.MessageUI;
import java.time.LocalDateTime;

/**
 *
 * @author KIMBERLEY LIEW YAN TUNG
 */
public class CourseManagement {

    public ListInterface<Course> courseList = new ArrayList<>();
    private ListInterface<Programme> programmeList = new ArrayList<>();
    private ProgrammeMaintainance programmeInitializer = new ProgrammeMaintainance();
    private CourseManagementUI courseUI = new CourseManagementUI();
    private ListInterface<String> idList = new ArrayList<>();
    private ListInterface<String> nameList = new ArrayList<>();

    
    public CourseManagement() {
        courseList = CourseInitializer.initializeCourse();
        programmeList = ProgrammeInitializer.initializeProgrammes();
        updateCourseIDList();
        updateCourseNameList();
    }

    public void runCourseManagement() {
        int choice = 0;
        do {
            choice = courseUI.getCourseMenu();

            switch (choice) {
                case 1: //List all course
                    courseUI.listAllCourse(courseList.toString());
                    break;
                case 2: //Add new course
                    addNewCourse();
                    courseUI.listAllCourse(courseList.toString());
                    break;
                case 3: //Remove course
                    courseUI.listAllCourse(courseList.toString());
                    removeExistedCourse();
                    break;
                case 4: //search course
                    searchCourse();
                    break;
                case 5: //Edit course detail
                    courseUI.listAllCourse(courseList.toString());
                    editCourse();
                    break;
                case 6: //Add programme to course 
                    addProgramme();
                    break;
                case 7: //Remove programme from course
                    courseUI.listAllCourse(courseList.toString());
                    removeProgramme();
                    break;
                case 8: //Generate report
                    generateReport();
                    break;
                case 0: //exit
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        } while (choice != 0);

    }

    //Add new course
    public void addNewCourse() {
        Course newCourse = courseUI.addNewCourse();
        courseList.add(newCourse);
        updateCourseIDList();
        updateCourseNameList();
    }

    //Remove course
    public void removeExistedCourse() {
        switch (courseUI.removeCourse()) {  //show UI
            case 1: //remove by position
                int positionToRemove = courseUI.removeByPosition();
                if (positionToRemove > 0) {
                    Course removedCourse = courseList.remove(positionToRemove);
                    System.out.println("   " + removedCourse);
                    updateCourseIDList();
                    updateCourseNameList();
                } else {
                    System.out.println("Position not found");
                }
                break;
            case 2: //remove by id 
                String IdToRemove = courseUI.removeByID();
                int idPosition = idList.searchPosition(IdToRemove);//get the id position
                if (idPosition > 0) {
                    Course removedCourse = courseList.remove(idPosition);
                    System.out.println("   " + removedCourse);
                    updateCourseIDList();
                } else {
                    System.out.print("ID not found\n");
                }
                break;
            default:
                break;
        }
    }

    //Search course
    public void searchCourse() {
        switch (courseUI.searchCourseUI()) {
            case 1: //serach by id
                String idToSearch = courseUI.searchByID();
                int idPosition = idList.searchPosition(idToSearch);
                if (idPosition > 0) {
                    Course searchCourse = courseList.getEntry(idPosition);
                    System.out.println("Result:");
                    System.out.println("   " + searchCourse);
                } else {
                    System.out.println("Course not found");
                }
                break;
            case 2: //search by name
                String nameToSearch = courseUI.searchByName();
                int namePosition = nameList.searchPosition(nameToSearch);
                if (namePosition >= 0) {
                    Course searchCourse = courseList.getEntry(namePosition);
                    System.out.println("Result:");
                    System.out.println("   " + searchCourse);
                } else {
                    System.out.println("Course not found");
                }
                break;
            case 3:
                String facultyToSearch = courseUI.searchByFaculty();
                String facultyFromList = null;
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    facultyFromList = courseList.getEntry(i).getCourseFaculty();

                    if (facultyFromList.equals(facultyToSearch)) {
                        System.out.println(i + ". " + courseList.getEntry(i).toString());
                    }
                }
                break;
            default: //exit
                break;
        }
    }

    //Edit course
    public void editCourse() {
        int editCourse = courseUI.editCourseUI();  //show UI
        Course courseEditing = courseList.getEntry(editCourse);//find the position to edit

        if (editCourse > 0 && courseEditing != null) {  //validation

            int choiceSelected = courseUI.editCourseUIChoice(courseEditing);
            switch (choiceSelected) {
                case 1: //edit course ID
                    System.out.println("Original ID: " + courseList.getEntry(editCourse).getCourseID());
                    String editedID = courseUI.editID();
                    courseList.getEntry(editCourse).setCourseID(editedID);
                    System.out.println( "   " +courseList.getEntry(editCourse).toString());
                    updateCourseIDList();
                    break;
                case 2: //edit name
                    System.out.println("Original Course Name: " + courseList.getEntry(editCourse).getCourseName());
                    courseList.getEntry(editCourse).setCourseName(courseUI.editName());
                    System.out.println("   " +courseList.getEntry(editCourse).toString());
                    updateCourseNameList();
                    break;
                case 3: //edit faculty
                    System.out.println("Original Faculty: " + courseList.getEntry(editCourse).getCourseFaculty());
                    courseList.getEntry(editCourse).setCourseFaculty(courseUI.editFaculty());
                    System.out.println("   " + courseList.getEntry(editCourse).toString());
                    break;
                case 4: //edit credit hours
                    System.out.println("Original Course Credit Hours: " + courseList.getEntry(editCourse).getCreditHours());
                    courseList.getEntry(editCourse).setCreditHours(courseUI.editCreditHours());
                    System.out.println("   " + courseList.getEntry(editCourse).toString());
                    break;
                default://exit
                    break;
            }

        } else {
            MessageUI.displayInvalidChoiceMessage();
        }

    }

    //add programme
    public void addProgramme() {
        //List out course to add programme
        System.out.println("==============================");
        System.out.println("        Course List ");
        System.out.println("==============================");
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + courseList.getEntry(i).getCourseName());
        }
        int courseToAdd = courseUI.addPorgrammePosition();

        //List out programme to be added
        System.out.println("==============================");
        System.out.println("        Programme Offered ");
        System.out.println("==============================");
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + programmeList.getEntry(i).getProgrammeId() + " " + programmeList.getEntry(i).getProgrammeName());
        }
        int programmePosition = courseUI.addPorgramme();

        //add programme
        Programme prorgammeToAdd = new Programme(programmeList.getEntry(programmePosition).getProgrammeId(), programmeList.getEntry(programmePosition).getProgrammeName());
        courseList.getEntry(courseToAdd).addProgramme(prorgammeToAdd);
        System.out.println("   " + courseList.getEntry(courseToAdd).toString());

    }

    //remove programme
    public void removeProgramme() {
        int courseRemovePosition = courseUI.removePorgrammePosition();
        Course courseSelected = courseList.getEntry(courseRemovePosition);

        //List out programme to be removed
        int programmePosition = courseUI.removePorgramme(courseSelected);

        //remove Programme
        Programme programmeToRemove = courseSelected.removeProgramme(programmePosition);
        if (programmeToRemove != null) {
            boolean isSucess = courseList.replace(courseRemovePosition, courseSelected);
            if (isSucess == true) {
                MessageUI.displaySuccessfullMessage();
                System.out.println("   " + courseSelected.toString());
            } else {
                MessageUI.displayFailureMessage();
            }
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    //Generate Report 
    public void generateReport() {
        courseUI.reportUI();
        int most = 0;
        int least = 0;
        //temporary arrayList to store data in accending order
        ListInterface<Course> tempCourseList = new ArrayList<>(); //empty array
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            tempCourseList.add(courseList.getEntry(i)); //add entry into the empty array
        }

        //list the course and programme offered in an accending order
        for (int i = 1; i <= tempCourseList.getNumberOfEntries(); i++) {
            int lessProgramme = tempCourseList.getEntry(i).getProgrammeList().getNumberOfEntries(); //assume first array is the smallest

            int index = i;
            //compare with the next array
            for (int j = i + 1; j <= tempCourseList.getNumberOfEntries(); j++) {
                if (lessProgramme > tempCourseList.getEntry(j).getProgrammeList().getNumberOfEntries()) {
                    lessProgramme = tempCourseList.getEntry(j).getProgrammeList().getNumberOfEntries();
                    index = j;
                }

            }
            Course course = tempCourseList.getEntry(index);
            if (lessProgramme == 0) {
                System.out.println("  No programme is offered to: ");
                System.out.println("  " + course.getCourseName() + " [" + course.getCourseID() + "]");
                tempCourseList.replace(index, tempCourseList.getEntry(i));
                tempCourseList.replace(i, course);
            } else if (lessProgramme == 1) {
                System.out.println("\n  " + course.getProgrammeList().getNumberOfEntries() + " programme is offered to: ");
                 System.out.println("  " + course.getCourseName() + " [" + course.getCourseID() + "]");
                tempCourseList.replace(index, tempCourseList.getEntry(i));
                tempCourseList.replace(i, course);
            } else {
                System.out.println("\n  " +course.getProgrammeList().getNumberOfEntries() + " programmes are offered to ");
                 System.out.println("  " + course.getCourseName() + " [" + course.getCourseID() + "]");
                tempCourseList.replace(index, tempCourseList.getEntry(i));
                tempCourseList.replace(i, course);
              
            }
            
        }
        System.out.println("\n  Date Generated: " + java.time.LocalDate.now() + " " + java.time.LocalTime.now());
    }
    //Update Course ID into the idList
    private void updateCourseIDList() {
        idList.clear();
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            idList.add(courseList.getEntry(i).getCourseID());
        }
    }

    //Update Course Name into the nameList
    private void updateCourseNameList() {
        nameList.clear();
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            nameList.add(courseList.getEntry(i).getCourseName());
        }
    }
}
