package dao;

import adt.*;
import entity.*;

/**
 *
 * @author Xin Hui
 */
public class TutorInitializer {

    public static ListInterface<Tutor> initializeTutor() {
        ListInterface<Tutor> tutorList = new ArrayList<>();
        ListInterface<Course> courseList = CourseInitializer.initializeCourse();
        
        ListInterface<Course> courseListNew1 = new ArrayList<>();
        courseListNew1.add(courseList.getEntry(1));
        courseListNew1.add(courseList.getEntry(2));
        tutorList.add(new Tutor("T0001", "Alice", "Female", "alice@gmail.com", "FOCS", 3000, courseListNew1));
        
        ListInterface<Course> courseListNew2 = new ArrayList<>();
        courseListNew2.add(courseList.getEntry(6));
        tutorList.add(new Tutor("T0002", "Brenda", "Female", "brenda@gmail.com", "FAFB", 3500, courseListNew2));
        
        ListInterface<Course> courseListNew3 = new ArrayList<>();
        courseListNew3.add(courseList.getEntry(3));
        courseListNew3.add(courseList.getEntry(4));
        courseListNew3.add(courseList.getEntry(5));
        tutorList.add(new Tutor("T0003", "Clora", "Female", "clora@gmail.com", "FOCS", 3000, courseListNew3));
        
        ListInterface<Course> courseListNew4 = new ArrayList<>();
        courseListNew4.add(courseList.getEntry(4));
        courseListNew4.add(courseList.getEntry(5));
        tutorList.add(new Tutor("T0004", "Daniel", "Male", "daniel@gmail.com", "FOCS", 5000, courseListNew4));
        
        tutorList.add(new Tutor("T0005", "Ember", "Female", "ember@gmail.com", "CUPS", 3000));
        return tutorList;
    }
}
