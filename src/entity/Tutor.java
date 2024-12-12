package entity;

import adt.*;

/**
 *
 * @author Xin Hui
 */
public class Tutor {

    private String tutorID;
    private String tutorName;
    private String tutorGender;
    private String tutorEmail;
    private String tutorFaculty;
    private float tutorSalary;
    private ListInterface<Course> courseList = new ArrayList<>();

    public Tutor() {

    }

    public Tutor(String tutorID) {
        this.tutorID = tutorID;
    }

    public Tutor(String tutorID, String tutorName, String tutorGender, String tutorEmail, String tutorFaculty, float tutorSalary) {
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.tutorGender = tutorGender;
        this.tutorEmail = tutorEmail;
        this.tutorFaculty = tutorFaculty;
        this.tutorSalary = tutorSalary;
    }

    public Tutor(String tutorID, String tutorName, String tutorGender, String tutorEmail, String tutorFaculty, float tutorSalary, ListInterface<Course> courseList) {
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.tutorGender = tutorGender;
        this.tutorEmail = tutorEmail;
        this.tutorFaculty = tutorFaculty;
        this.tutorSalary = tutorSalary;
        this.courseList = courseList;
    }

    public String getTutorID() {
        return tutorID;
    }

    public String getTutorName() {
        return tutorName;
    }

    public String getTutorGender() {
        return tutorGender;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public String getTutorFaculty() {
        return tutorFaculty;
    }

    public float getTutorSalary() {
        return tutorSalary;
    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public void setTutorGender(String tutorGender) {
        this.tutorGender = tutorGender;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public void setTutorFaculty(String tutorFaculty) {
        this.tutorFaculty = tutorFaculty;
    }

    public void setTutorSalary(float tutorSalary) {
        this.tutorSalary = tutorSalary;
    }

    public void setCourseList(ListInterface<Course> courseList) {
        this.courseList = courseList;
    }

    public boolean addCourse(Course course) {
        return this.courseList.add(course);
    }

    public Course removeCourse(int position) {
        return this.courseList.remove(position);
    }

    private String courseToString() {
        if (courseList.isEmpty()) {
            return "-";
        } else if (courseList.getNumberOfEntries() <= 1) {
            return String.format("%-10s %-40s", courseList.getEntry(1).getCourseID(), courseList.getEntry(1).getCourseName());
        } else {
            String courseToString = String.format("%-10s %-40s", courseList.getEntry(1).getCourseID(), courseList.getEntry(1).getCourseName());
            for (int i = 2; i <= courseList.getNumberOfEntries(); i++) {
                courseToString += String.format("\n%-87s %-10s %-40s", "", courseList.getEntry(i).getCourseID(), courseList.getEntry(i).getCourseName());
            }
            return courseToString;
        }
    }

    public String courseString() {
        String courseString = "";
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            courseString += i + ". ";
            courseString += String.format("%-9s %-38s %-8s %-13d\n", courseList.getEntry(i).getCourseID(), courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getCourseFaculty(), courseList.getEntry(i).getCreditHours());
        }
        return courseString;
    }

    @Override
    public String toString() {
        String toString = String.format("%-10s %-20s %-10s %-20s %-8s RM%8.2f ",
                tutorID, tutorName, tutorGender, tutorEmail, tutorFaculty, tutorSalary);
        return String.format("%-84s %s", toString, courseToString());
    }

}
