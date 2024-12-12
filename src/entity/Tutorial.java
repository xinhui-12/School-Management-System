/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Seng Wai
 */
public class Tutorial {

    private int tutorialGroup;      //  G1 - G4
    private String programmeCode;   //  RST
    private String faculty;         //  FOCS
    private ListInterface<Student> studentList = new ArrayList<>();
    
    public Tutorial() {
        
    }
    
    public Tutorial(int tutorialGroup, String programmeCode, String faculty) {
        this.tutorialGroup = tutorialGroup;
        this.programmeCode = programmeCode;
        this.faculty = faculty;
    }
    
    public Tutorial(int tutorialGroup, String programmeCode, String faculty, ListInterface<Student> studentList) {
        this.tutorialGroup = tutorialGroup;
        this.programmeCode = programmeCode;
        this.faculty = faculty;
        this.studentList = studentList;
    }

    public int getTutorialGroup() {
        return tutorialGroup;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public String getFaculty() {
        return faculty;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setTutorialGroup(int tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return String.format("%-14d %-10s %-10s", tutorialGroup, programmeCode, faculty);
    }    
}
