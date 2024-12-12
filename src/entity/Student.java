/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Seng Wai
 */
public class Student {

    private String studentID;
    private String studentName;
    private String studentGender;
    private String studentEmail;
    private int studentEntryYear;
    
    public Student(String studentID) {
        this.studentID = studentID;
    }  
    
    public Student(String studentID, String studentName, String studentGender, String studentEmail, int studentEntryYear) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.studentEmail = studentEmail;
        this.studentEntryYear = studentEntryYear;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender (String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public int getStudentEntryYear() {
        return studentEntryYear;
    }

    public void setStudentEntryYear(int studentEntryYear) {
        this.studentEntryYear = studentEntryYear;
    }

    public boolean compareTo(Student otherStudent) {
        if (this.studentID.equalsIgnoreCase(otherStudent.studentID) || this.studentName.equalsIgnoreCase(otherStudent.studentName)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s %-20s %-5d", studentID, studentName, studentGender, studentEmail, studentEntryYear);
    }
}
