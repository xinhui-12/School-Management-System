package entity;

import adt.*;

/**
 *
 * @author KIMBERLEY LIEW YAN TUNG
 */
public class Course {

    private String courseID;
    private String courseName;
    private String courseFaculty;
    private int creditHours;
    private ListInterface<Programme> programmeList = new ArrayList<>();

    public Course() {

    }

    public Course(String courseID, String courseName, String courseFaculty, int creditHours) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseFaculty = courseFaculty;
        this.creditHours = creditHours;

    }

    public Course(String courseID, String courseName, String courseFaculty, int creditHours, ListInterface<Programme> programmeList) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseFaculty = courseFaculty;
        this.creditHours = creditHours;
        this.programmeList = programmeList;

    }

    //for add programme
    public boolean addProgramme(Programme programme) {
        return this.programmeList.add(programme);
    }

    //for removing the programme
    public Programme removeProgramme(int removePosition) {
        return this.programmeList.remove(removePosition);
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseFaculty() {
        return courseFaculty;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public ListInterface<Programme> getProgrammeList() {
        return programmeList;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseFaculty(String courseFaculty) {
        this.courseFaculty = courseFaculty;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setProgrammeList(ListInterface<Programme> programmeList) {
        this.programmeList = programmeList;
    }

    private String proragmmeToString() {
        if (programmeList.isEmpty()) {
            return "N/A";
        } else {
            String programmeToString = String.format("%-5s %-60s", programmeList.getEntry(1).getProgrammeId(), programmeList.getEntry(1).getProgrammeName());
            for (int i = 2; i <= programmeList.getNumberOfEntries(); i++) {
                programmeToString += String.format("\n%-75s %-5s %-60s", "", programmeList.getEntry(i).getProgrammeId(), programmeList.getEntry(i).getProgrammeName());

            }
            return programmeToString;

        }

    }

    @Override
    public String toString() {
        String toString = String.format("%-9s %-38s %-8s %-13d ", courseID, courseName, courseFaculty, creditHours);
        return String.format( "%s %s", toString, proragmmeToString());
//        return ;
    }
}
