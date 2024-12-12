/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import adt.*;
import entity.*;

/**
 *
 * @author KIMBERLEY LIEW YAN TUNG
 */
public class CourseInitializer {

    public static ListInterface<Course> initializeCourse() {
        ListInterface<Course> courseList = new ArrayList<>();
        ListInterface<Programme> programmeList = ProgrammeInitializer.initializeProgrammes();

        ListInterface<Programme> newPorgrammeList1 = new ArrayList<>();
        newPorgrammeList1.add(programmeList.getEntry(1));

        courseList.add(new Course("BACS2224", "Computer Game Programming", "FOCS", 4, newPorgrammeList1));

        ListInterface<Programme> newPorgrammeList2 = new ArrayList<>();
        newPorgrammeList2.add(programmeList.getEntry(1));
        newPorgrammeList2.add(programmeList.getEntry(3));

        courseList.add(new Course("BACS2063", "Data Structure and Algorithms", "FOCS", 4, newPorgrammeList2));

        ListInterface<Programme> newPorgrammeList3 = new ArrayList<>();
        newPorgrammeList3.add(programmeList.getEntry(1));
        newPorgrammeList3.add(programmeList.getEntry(6));
        newPorgrammeList3.add(programmeList.getEntry(7));

        courseList.add(new Course("BACS2053", "Object-Oriented Analysis And Design", "FOCS", 3, newPorgrammeList3));

        ListInterface<Programme> newProgrammeList4 = new ArrayList<>();
        newProgrammeList4.add(programmeList.getEntry(1));
        newProgrammeList4.add(programmeList.getEntry(4));
        newProgrammeList4.add(programmeList.getEntry(5));
        newProgrammeList4.add(programmeList.getEntry(6));

        courseList.add(new Course("BACS2163", "Software Engineering", "", 3, newProgrammeList4));

        ListInterface<Programme> newProgrammeList5 = new ArrayList<>();
        newProgrammeList5.add(programmeList.getEntry(1));
        newProgrammeList5.add(programmeList.getEntry(2));
        newProgrammeList5.add(programmeList.getEntry(3));
        newProgrammeList5.add(programmeList.getEntry(5));
        newProgrammeList5.add(programmeList.getEntry(7));
        courseList.add(new Course("BACS2042", "Research Methods", "FAFB", 2, newProgrammeList5));

        courseList.add(new Course("BAMS2614", "Mathematics For Games Technology", "FOCS", 4));


        return courseList;
    }
}
