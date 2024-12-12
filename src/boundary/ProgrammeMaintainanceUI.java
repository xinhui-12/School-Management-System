package boundary;

import entity.*;
import utility.*;
import java.util.Scanner;

public class ProgrammeMaintainanceUI {

    public static int getMenuChoice() {
        //present main menu
        MessageUI.mainMenu();

        // ask for input
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println();

        return choice;
    }

    Scanner scanner = new Scanner(System.in);

    //Get Programmes Id input from user
    public String inputProgrammeId() {
        System.out.println("Enter Programme ID: ");
        String id = scanner.nextLine();
        return id;
    }
    //Get Programmes Name input from user

    public String inputProgrammeName() {
        System.out.println("Enter Programme Name: ");
        String name = scanner.nextLine();
        return name;
    }

    public String inputTutorialGroupID() {
        System.out.println("Enter Tutorial Group ID: ");
        String tutGrpID = scanner.nextLine();
        return tutGrpID;
    }

    public String inputTutorialGroupName() {
        System.out.println("Enter Tutorial Group Name: ");
        String tutGrpName = scanner.nextLine();
        return tutGrpName;
    }

    public String inputTutorName() {
        System.out.println("Enter Tutor Name: ");
        String tutor = scanner.nextLine();
        return tutor;
    }

    //Combine Programmes Id and Name input from user
    public Programme inputProgrammeDetails() {
        String programmeId = inputProgrammeId();
        String programmeName = inputProgrammeName();
        System.out.println();
        return new Programme(programmeId, programmeName);
    }

    //combine all input from user
    public ProgTutorialGroup inputProgTutorialGroupDetails() {
        String programmeId = inputProgrammeId();
        String tutGrpId = inputTutorialGroupID();
        String tutGrpName = inputTutorialGroupName();
        String tutor = inputTutorName();
        return new ProgTutorialGroup(programmeId, tutGrpId, tutGrpName, tutor);
    }

    public boolean containsInput(String getString, String searchText) {
        int getStringLength = getString.length();
        int searchTextLength = searchText.length();

        for (int i = 0; i <= getStringLength - searchTextLength; i++) {
            boolean found = true;
            for (int j = 0; j < searchTextLength; j++) {
                //Ignore upper and lower case
                char stringChar = Character.toUpperCase(getString.charAt(i + j));
                char searchChar = Character.toUpperCase(searchText.charAt(j));
                if (stringChar != searchChar) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }
        return false;
    }

}
