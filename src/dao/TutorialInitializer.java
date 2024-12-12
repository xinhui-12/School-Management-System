/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import adt.ArrayList;
import adt.ListInterface;
import dao.StudentInitializer;
import entity.Tutorial;

/**
 *
 * @author sengw
 */
public class TutorialInitializer {

    public static ListInterface<Tutorial> initializeTutorial() {
        ListInterface<Tutorial> TutorialList = new ArrayList<>();
        TutorialList.add(new Tutorial(1, "RSW", "FOAS", StudentInitializer.tutorialGroup1()));
        TutorialList.add(new Tutorial(2, "RST", "FOAS", StudentInitializer.tutorialGroup2()));
        TutorialList.add(new Tutorial(3, "RSD", "FOCS", StudentInitializer.tutorialGroup3()));
        TutorialList.add(new Tutorial(4, "RST", "FOCS", StudentInitializer.tutorialGroup4()));

        return TutorialList;
    }
}
