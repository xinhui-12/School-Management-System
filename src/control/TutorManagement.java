package control;

import adt.*;
import boundary.TutorManagementUI;
import dao.*;
import entity.*;
import utility.*;

/**
 *
 * @author Xin Hui
 */
public class TutorManagement {

    public ListInterface<Tutor> tutorList = new ArrayList<>();
    private ListInterface<String> idList = new ArrayList<>();
    private final TutorManagementUI tutorUI = new TutorManagementUI();

    public TutorManagement() {
        tutorList = TutorInitializer.initializeTutor();
        updateIDList();
    }

    public ListInterface<Tutor> runTutorManagement(ListInterface<Course> courseList) {
        int choice;
        do {
            choice = tutorUI.getMenuChoice();
            switch (choice) {
                case 1: // List all tutors
                    tutorUI.listAllTutors(tutorList.toString());
                    break;
                case 2: // Add new tutor
                    addTutor();
                    break;
                case 3: // Remove a tutor
                    removeTutor();
                    break;
                case 4: // Edit a tutor
                    editTutor();
                    break;
                case 5: // Find a tutor
                    findTutor();
                    break;
                case 6: // Filter tutors based on criteria
                    filterTutor();
                    break;
                case 7: // Add course to a tutor
                    addCourseToTutor(courseList);
                    break;
                case 8: // Remove course from a tutor
                    removeCourseFromTutor();
                    break;
                case 9: // Generate report
                    generateReport();
                    break;
                case 0: // Exit tutor menu
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
        return tutorList;
    }

    public void addTutor() {
        Tutor newTutor = tutorUI.addTutorUI();
        int position = tutorUI.inputPosition();
        boolean success;
        boolean duplicate = idList.contains(newTutor.getTutorID());

        if (duplicate) {
            System.out.println("\n(Tutor ID cannot be duplicated with the exist record.)");
            MessageUI.displayFailureMessage();
        } else {
            // if position less than one, add by default method
            // else add in the specified position
            if (position < 1) {
                success = tutorList.add(newTutor);
            } else {
                success = tutorList.add(position, newTutor);
            }

            if (success) {
                updateIDList();
                MessageUI.displaySuccessfullMessage();
            } else {
                MessageUI.displayFailureMessage();
            }
        }
    }

    public void removeTutor() {
        switch (tutorUI.removeTutorUI()) {
            case 1: // by entry position
                removeByPosition(tutorUI.inputPosition());
                break;
            case 2: // by tutor id
                String idRemove = tutorUI.inputTutorID();
                int positionFound = idList.searchPosition(idRemove);
                if (positionFound == -1) {
                    MessageUI.displayNotFoundMessage();
                } else {
                    removeByPosition(positionFound);
                }
                break;
            case 0: // back
                break;
            default:
                MessageUI.displayInvalidChoiceMessage();
        }
    }

    private void removeByPosition(int position) {
        Tutor removedTutor = tutorList.remove(position);
        if (removedTutor == null) {
            MessageUI.displayNotFoundMessage();
        } else {
            System.out.println(removedTutor);
            updateIDList();
            MessageUI.displaySuccessfullMessage();
        }
    }

    public void editTutor() {
        int editPosition = tutorUI.editTutorUI();
        Tutor tutorEdited = tutorList.getEntry(editPosition);
        // the position does not have any entry, unable to edit
        if (tutorEdited == null) {
            MessageUI.displayNotFoundMessage();
        } else {
            int choice = 0;
            do {
                choice = tutorUI.choiceEditTutorUI(tutorEdited);
                switch (choice) {
                    case 1:
                        tutorEdited.setTutorName(tutorUI.inputTutorName());
                        break;
                    case 2:
                        tutorEdited.setTutorGender(tutorUI.inputTutorGender());
                        break;
                    case 3:
                        tutorEdited.setTutorEmail(tutorUI.inputTutorEmail());
                        break;
                    case 4:
                        tutorEdited.setTutorFaculty(tutorUI.inputTutorFaculty());
                        break;
                    case 5:
                        tutorEdited.setTutorSalary(tutorUI.inputTutorSalary());
                        break;
                    case 0: // done edit, and replace to the list
                        if (tutorList.replace(editPosition, tutorEdited)) {
                            MessageUI.displaySuccessfullMessage();
                        } else {
                            MessageUI.displayFailureMessage();
                        }
                        break;
                    default:
                        MessageUI.displayInvalidChoiceMessage();
                }
            } while (choice != 0);
        }
    }

    public void findTutor() {
        String idFind = tutorUI.inputTutorID();
        int positionFound = idList.searchPosition(idFind);
        if (positionFound == -1) {
            MessageUI.displayNotFoundMessage();
        } else {
            tutorUI.showTutor(tutorList.getEntry(positionFound));

        }
    }

    public void filterTutor() {
        switch (tutorUI.filterTutorUI()) {
            case 1: // by faculty
                filterTutorByFaculty(tutorUI.inputTutorFaculty());
                break;
            case 2: // by range of salary
                filterTutorBySalary(tutorUI.inputTutorMinSalary(), tutorUI.inputTutorMaxSalary());
                break;
            case 3: // by gender
                filterTutorByGender(tutorUI.inputTutorGender());
                break;
            case 0: // back
                break;
            default:
                MessageUI.displayInvalidChoiceMessage();
        }
    }

    private void filterTutorByFaculty(String faculty) {
        ListInterface<Tutor> filteredTutor = new ArrayList<>();
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            if (tutorList.getEntry(i).getTutorFaculty().equalsIgnoreCase(faculty)) {
                filteredTutor.add(tutorList.getEntry(i));
            }
        }
        String condition = "Faculty: " + faculty;
        tutorUI.showFilteredTutorUI(filteredTutor.toString(), condition);
    }

    private void filterTutorBySalary(float minSalary, float maxSalary) {
        if (minSalary < maxSalary) {
            ListInterface<Tutor> filteredTutor = new ArrayList<>();
            for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                if (tutorList.getEntry(i).getTutorSalary() >= minSalary && tutorList.getEntry(i).getTutorSalary() <= maxSalary) {
                    filteredTutor.add(tutorList.getEntry(i));
                }
            }
            String condition = "The range of the salary is RM" + minSalary + " to RM" + maxSalary;
            tutorUI.showFilteredTutorUI(filteredTutor.toString(), condition);
        } else {
            MessageUI.displayFailureMessage();
        }
    }

    private void filterTutorByGender(String gender) {
        ListInterface<Tutor> filteredTutor = new ArrayList<>();
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            if (tutorList.getEntry(i).getTutorGender().equalsIgnoreCase(gender)) {
                filteredTutor.add(tutorList.getEntry(i));
            }
        }
        String condition = "Gender: " + gender;
        tutorUI.showFilteredTutorUI(filteredTutor.toString(), condition);
    }

    public void addCourseToTutor(ListInterface<Course> courseList) {
        String tutorIDAdd = tutorUI.inputTutorID();
        int positionFound = idList.searchPosition(tutorIDAdd);
        if (positionFound == -1) {
            MessageUI.displayNotFoundMessage();
        } else {
            Tutor tutor = tutorList.getEntry(positionFound);
            if (tutor == null) {
                MessageUI.displayNotFoundMessage();
            } else {
                tutorUI.showTutor(tutor);

                // make a new course list that remove the course that already added in the tutor
                // to avoid redundancy of adding the same course into the tutor
                ListInterface<Course> newCourseList = new ArrayList<>();
                // copy all entries in the courseList to newCourseList, to avoid any affect in the courseList as the courseList is pass by reference
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    newCourseList.add(courseList.getEntry(i));
                }
                for (int i = 1; i <= tutor.getCourseList().getNumberOfEntries(); i++) {
                    for (int j = 1; j <= newCourseList.getNumberOfEntries(); j++) {
                        if (newCourseList.getEntry(j).getCourseID().equals(tutor.getCourseList().getEntry(i).getCourseID())) {
                            newCourseList.remove(j);
                            break;
                        }
                    }
                }

                //show only course details
                int courseNo = tutorUI.addCourseToTutor(courseString(newCourseList));
                if (courseNo > 0 && courseNo <= newCourseList.getNumberOfEntries()) {
                    if (tutor.addCourse(newCourseList.getEntry(courseNo)) && tutorList.replace(positionFound, tutor)) {
                        MessageUI.displaySuccessfullMessage();
                    } else {
                        MessageUI.displayFailureMessage();
                    }
                } else {
                    MessageUI.displayInvalidChoiceMessage();
                }
            }
        }
    }

    public String courseString(ListInterface<Course> courseList) {
        String courseString = "";
        if (courseList.getNumberOfEntries() == 0) {
            System.out.println("-");
        } else {
            for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                courseString += i + ". ";
                courseString += String.format("%-9s %-38s %-8s %-13d\n", courseList.getEntry(i).getCourseID(), courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getCourseFaculty(), courseList.getEntry(i).getCreditHours());
            }
        }
        return courseString;
    }

    public void removeCourseFromTutor() {
        String tutorID = tutorUI.inputTutorID();
        int positionFound = idList.searchPosition(tutorID);
        if (positionFound == -1) {
            MessageUI.displayNotFoundMessage();
        } else {
            Tutor tutor = tutorList.getEntry(positionFound);
            tutorUI.showTutor(tutor);
            if (tutor.getCourseList().getNumberOfEntries() == 0) {
                MessageUI.displayUnableFunctionMessage();
            } else {
                int numRemove = tutorUI.removeCourseFromTutor();
                if (numRemove > 0) {
                    Course courseRemoved = tutor.removeCourse(numRemove);
                    if (courseRemoved != null) {
                        if (tutorList.replace(positionFound, tutor)) {
                            MessageUI.displaySuccessfullMessage();
                        } else {
                            MessageUI.displayFailureMessage();
                        }
                    } else {
                        MessageUI.displayNotFoundMessage();
                    }
                } else {
                    MessageUI.displayInvalidChoiceMessage();
                }
            }
        }
    }

    public void generateReport() {
        String tutorString = "";
        float average = 0;

        // Redundant the tutorList to sort, do not want to affect the tutorList when generate report
        ListInterface<Tutor> tempList = new ArrayList<>();
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            tempList.add(tutorList.getEntry(i));
        }
        for (int i = 1; i <= tempList.getNumberOfEntries(); i++) {
            // to list out by the tutor which teach the most to the least
            int higher = tempList.getEntry(i).getCourseList().getNumberOfEntries();
            int index = i;
            for (int j = i + 1; j <= tempList.getNumberOfEntries(); j++) {
                if (higher < tempList.getEntry(j).getCourseList().getNumberOfEntries()) {
                    higher = tempList.getEntry(j).getCourseList().getNumberOfEntries();
                    index = j;
                }
            }
            Tutor tutor = tempList.getEntry(index);
            average = average + tutor.getCourseList().getNumberOfEntries();
            tutorString += tutor.getTutorID() + " " + tutor.getTutorName() + " teaches " + tutor.getCourseList().getNumberOfEntries() + " course(s)\n";
            tempList.replace(index, tempList.getEntry(i));
            tempList.replace(i, tutor);
        }
        average = average / tutorList.getNumberOfEntries();
        tutorUI.generateReportUI(tutorString, average, tempList.getNumberOfEntries());
    }

    // Validation of input
    public static boolean validGender(String gender) {
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    public static boolean validFaculty(String faculty) {
        return faculty.matches("[a-zA-Z]+") && faculty.length() == 4;
    }

    public static boolean validSalary(String salary) {
        return salary.matches("[1-9]\\d*\\.?\\d*");
    }

    // to make constant between tutorList and idList
    private void updateIDList() {
        idList.clear();
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            idList.add(tutorList.getEntry(i).getTutorID());
        }
    }
}
