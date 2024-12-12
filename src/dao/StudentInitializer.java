package dao;

import adt.*;
import entity.Student;

/**
 *
 * @author Seng Wai
 */
public class StudentInitializer {

    public static ListInterface<Student> tutorialGroup1() {
        ListInterface<Student> StudentList = new ArrayList<>();
        StudentList.add(new Student("S001", "Anna", "Female", "anna@hotmail.com", 2022));
        StudentList.add(new Student("S002", "Britney", "Female", "britney@hotmail.com", 2021));
        StudentList.add(new Student("S003", "Cindy", "Female", "cindy@hotmail.com", 2023));
        StudentList.add(new Student("S004", "Dan", "Male", "dan@hotmail.com", 2021));
        StudentList.add(new Student("S005", "Emily", "Female", "emily@hotmail.com", 2021));
        StudentList.add(new Student("S006", "Frana", "Female", "frana@hotmail.com", 2022));
        StudentList.add(new Student("S007", "Gabby", "Female", "gabby@hotmail.com", 2022));
        StudentList.add(new Student("S008", "Holland", "Male", "holland@hotmail.com", 2023));
        StudentList.add(new Student("S009", "Ian", "Male", "ian@hotmail.com", 2021));
        StudentList.add(new Student("S010", "Johnson", "Male", "johnson@hotmail.com", 2021));

        return StudentList;
    }

    public static ListInterface<Student> tutorialGroup2() {
        ListInterface<Student> StudentList = new ArrayList<>();
        StudentList.add(new Student("S011", "Amantha", "Female", "amantha@hotmail.com", 2023));
        StudentList.add(new Student("S012", "Bobby", "Male", "bobby@hotmail.com", 2022));
        StudentList.add(new Student("S013", "Clara", "Female", "clara@hotmail.com", 2021));
        StudentList.add(new Student("S014", "Dominic", "Male", "dominic@hotmail.com", 2021));
        StudentList.add(new Student("S015", "Emmanuel", "Male", "emmanuel@hotmail.com", 2022));
        StudentList.add(new Student("S016", "Fallen", "Male", "fallen@hotmail.com", 2023));
        StudentList.add(new Student("S017", "Gus", "Male", "gus@hotmail.com", 2022));
        StudentList.add(new Student("S018", "Harry", "Male", "harry@hotmail.com", 2023));
        StudentList.add(new Student("S019", "Ivy", "Female", "ivy@hotmail.com", 2022));
        StudentList.add(new Student("S020", "Jasper", "Male", "jasper@hotmail.com", 2022));

        return StudentList;
    }

    public static ListInterface<Student> tutorialGroup3() {
        ListInterface<Student> studentList = new ArrayList<>();
        studentList.add(new Student("S021", "Coddy", "Male", "coddy@hotmail.com", 2022));
        studentList.add(new Student("S022", "Dylan", "Male", "dylan@hotmail.com", 2023));
        studentList.add(new Student("S023", "Eason", "Male", "eason@hotmail.com", 2023));
        studentList.add(new Student("S024", "Grace", "Female", "grace@hotmail.com", 2022));
        studentList.add(new Student("S025", "Joey", "Female", "joey@hotmail.com", 2023));
        studentList.add(new Student("S026", "Kate", "Female", "kate@hotmail.com", 2022));
        studentList.add(new Student("S027", "Larry", "Male", "larry@hotmail.com", 2022));
        studentList.add(new Student("S028", "Miller", "Male", "miller@hotmail.com", 2023));
        studentList.add(new Student("S029", "Nealan", "Male", "nealan@hotmail.com", 2021));
        studentList.add(new Student("S030", "Panther", "Male", "panther@hotmail.com", 2023));
        
        return studentList;
    }

    public static ListInterface<Student> tutorialGroup4() {
        ListInterface<Student> studentList = new ArrayList<>();
        studentList.add(new Student("S031", "Amelia", "Female", "amelia@hotmail.com", 2023));
        studentList.add(new Student("S032", "Benny", "Male", "benny@hotmail.com", 2023));
        studentList.add(new Student("S033", "Charlie", "Male", "charlie@hotmail.com", 2023));
        studentList.add(new Student("S034", "Damian", "Male", "damian@hotmail.com", 2023));
        studentList.add(new Student("S035", "Ember", "Female", "ember@hotmail.com", 2023));
        studentList.add(new Student("S036", "Freya", "Female", "freya@hotmail.com", 2023));
        studentList.add(new Student("S037", "Gina", "Female", "gina@hotmail.com", 2023));
        studentList.add(new Student("S038", "Hazard", "Male", "hazard@hotmail.com", 2023));
        studentList.add(new Student("S039", "Ivan", "Male", "ivan@hotmail.com", 2023));
        studentList.add(new Student("S040", "Joseph", "Male", "joseph@hotmail.com", 2023));

        return studentList;
    }
}
