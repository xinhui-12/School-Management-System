package dao;

import adt.*;
import entity.*;

/**
 *
 * @author Chin Tzer Khae
 */
public class ProgrammeInitializer {

//  Method to return a collection of with hard-coded entity values
    public static ListInterface<Programme> initializeProgrammes() {
        ListInterface<Programme> programmeList = new ArrayList<>();

        programmeList.add(new Programme("RST", "Bachelor of Computer Science (Honours) in Interactive Software Technology"));
        programmeList.add(new Programme("REI", "Bachelor of Information Systems (Honours) in Enterprise Information Systems"));
        programmeList.add(new Programme("RDS", "Bachelor of Computer Science (Honours) in Data Science"));
        programmeList.add(new Programme("RSD", "Bachelor of Information Technology (Honours) in Software Systems Development"));
        programmeList.add(new Programme("RSW", "Bachelor of Software Engineering (Honours)"));
        programmeList.add(new Programme("RIT", "Bachelor of Information Technology (Honours) in Internet Technology"));
        programmeList.add(new Programme("RIS", "Bachelor of Information Technology (Honours) in Information Security"));

        return programmeList;
    }

    public static ListInterface<ProgTutorialGroup> initializeProgTutGroup() {
        ListInterface<ProgTutorialGroup> programmeTutGroupList = new ArrayList<>();

        programmeTutGroupList.add(new ProgTutorialGroup("RST", "G1", "Group 1", "Mr Lim"));
        programmeTutGroupList.add(new ProgTutorialGroup("RST", "G2", "Group 2", "Ms Kim"));
        programmeTutGroupList.add(new ProgTutorialGroup("RDS", "G1", "Group 2", "Mr Tom"));
        programmeTutGroupList.add(new ProgTutorialGroup("RSD", "G1", "Group 2", "Ms Das"));

        return programmeTutGroupList;
    }

}
