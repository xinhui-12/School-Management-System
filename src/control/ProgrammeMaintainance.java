package control;

import adt.*;
import boundary.*;
import dao.*;
import entity.*;
import utility.*;
import java.util.Scanner;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 *
 * @author Chin Tzer Khae
 */
//report copy and paste whole control file code
public class ProgrammeMaintainance {

    private ListInterface<Programme> programmeList = new ArrayList<>();
    private final ProgrammeMaintainanceUI programmeUI = new ProgrammeMaintainanceUI();
    private ListInterface<ProgTutorialGroup> programmeTutGroupList = new ArrayList<>();

    public ProgrammeMaintainance() {
        programmeList = ProgrammeInitializer.initializeProgrammes();
        programmeTutGroupList = ProgrammeInitializer.initializeProgTutGroup();
    }

    //multiple action based on choice
    public void runProgrammeMaintainance() {
        int choice = 0;
        do {
            choice = ProgrammeMaintainanceUI.getMenuChoice();
            switch (choice) {
                case 1:  //View Programme List
                    listAllProgrammes();
                    break;
                case 2: //Add Programme
                    //TODO
                    addNewProgramme();
                    break;
                case 3://amend == edit programme
                    amendProgramme();
                    break;
                case 4://Remove Programme
                    removeProgramme();
                    break;
                case 5:// Find Programme by ID or Name
                    findProgramme();
                    break;
                case 6://Add tutorial group to a programme
                    addTutGroupToProgramme();
                    break;
                case 7://Remove tutorial group from a programme
                    removeTutGroupFromProgramme();
                    break;
                case 8://List all tutorial group for a programme
                    listAllTutorialGroupForAProgramme();
                    break;
                case 9:
                    generateReport();
                    break;
                case 0://exit
                    break;
                default:
                    System.out.println("Your input is invalid. \nEnter again.");
                    break;
            }
        } while (choice != 0);
    }

    //Spacing between each two digit number and one digit number
    String spacing = "   ";
    int actionCounter, listCounter = 0, addCounter = 0;
    int removeCounter = 0, amendCounter = 0, findCounter = 0;

    //List All Programme
    public void listAllProgrammes() {
        System.out.println("\nList of Programmes:\n");
        System.out.println("No.  ID        Name");

        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            if (i == 10) {
                spacing = "  ";
            }
            // example output [ 1.   RST   Name_Here]
            System.out.printf(i + ".%s%-9s %s \n", spacing,
                    programmeList.getEntry(i).getProgrammeId(),
                    programmeList.getEntry(i).getProgrammeName());
        }
        listCounter++;
        MessageUI.emptyRecord(programmeList.isEmpty());
        MessageUI.successExecute();
    }

    //Add new Programme
    public void addNewProgramme() {
        Programme newProgramme = programmeUI.inputProgrammeDetails();
        boolean duplicate = programmeList.contains(newProgramme);
        if (duplicate) {
            System.out.println("It was duplicated details!");
            MessageUI.failedExecute();
        } else {
            programmeList.add(newProgramme);
            MessageUI.successExecute();
        }
        addCounter++;
    }

    //Remove Programme
    public void removeProgramme() {
        // Ask for input
        System.out.println("\nEnter the ID. or Name of Programme you want to remove.");
        System.out.println("WARNING! This will remove ALL item that matches!");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //counter counts if there are anything was removed inside.
        int counter = 0;
        //getNumberOfEntries should >=1
        //search one by one, from last of array to head

        //as getNumberOfEntries() decrease over time, when user decided to delete 
        //everything, the i will becomer a larger number than getNumberOfEntries. 
        //using i++ will cause error once i > getNumberOfEntries()
        //since the original entry at i(getEntry(i)) is not there anymore.
        for (int i = programmeList.getNumberOfEntries(); i >= 1; i--) {
            //setup a new entry
            Programme programme = programmeList.getEntry(i);
            //if matches, remove the programme
            //first part need input to match the id of programm
            //second part need input to contain the keyword of programme
            if (programmeUI.containsInput(programme.getProgrammeId(), input)
                    || programmeUI.containsInput(programme.getProgrammeName(), input)) {

                //print out that it is successfully removed
                System.out.printf("\nSuccessfully removed %s-%s!\n",
                        programme.getProgrammeId(),
                        programme.getProgrammeName());
                //if id matches, remove the whole item
                programmeList.remove(i);

                //count if input was present
                counter++;
                removeCounter++;
            }
        }
        //remove related tutorial group

        for (int j = programmeTutGroupList.getNumberOfEntries(); j >= 1; j--) {

            //setup new entry
            ProgTutorialGroup progTutorialGroup = programmeTutGroupList.getEntry(j);

            //if matches, remove the tutorial group
            //Upper Part need input to match the id of Programme
            //Lower part need input to match the id or contains the name of Tutorial Group
            //Both First part need input to match the id of programme
            //Both Second part need input to contain the keyword of programme
            if (programmeUI.containsInput(progTutorialGroup.getProgrammeId(), input)
                    || programmeUI.containsInput(programmeList.getEntry(j).getProgrammeId(), input)
                    || programmeUI.containsInput(programmeList.getEntry(j).getProgrammeName(), input)) {

                //print out that it is successfully removed
                System.out.printf("\nSuccessfully removed %s %s!\n",
                        progTutorialGroup.getProgrammeId(),
                        progTutorialGroup.getTutGrpName());

                //remove the tutorial group
                programmeTutGroupList.remove(j);

                //count if input was present
                counter++;
                removeCounter++;
            }
        }

        if (counter == 0) {
            System.out.printf("\nProgramme ID(%s) was not inside the record\n", input);
        } else {
            System.out.printf("\nItem of %d was removed\n\n", counter);
        }
        MessageUI.successExecute();

    }

    //Amend Programme 
    public void amendProgramme() {
        // ask for input
        System.out.println("\nEnter the Name or ID. of Programme you want to amend.");
        System.out.println("WARNING! This will amends ALL item that matches!");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //ask for new id and name
        System.out.println("\nEnter the New ID.");
        String id = scanner.nextLine();

        System.out.println("\nEnter the New Name.");
        String name = scanner.nextLine();

        int counter = 0;
        //getNumberOfEntries should >=1
        //the Name and ID of programme was changed to new Name and ID
        //search one by one, from head of array to last
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            //setup a new entry 
            Programme programme = programmeList.getEntry(i);

            //if matches, change the name and id
            //first part need input to match the id of programme
            //second part need input to contain the keyword of programme
            if (programmeUI.containsInput(programme.getProgrammeId(), input)
                    || programmeUI.containsInput(programme.getProgrammeName(), input)) {

                //print out that it is successfully amend
                System.out.printf("\nSuccessfully amend %s %s!\n",
                        programme.getProgrammeId(),
                        programme.getProgrammeName());
                //amend to new id and name
                programme.setProgrammeId(id);
                programme.setProgrammeName(name);

                //count if input was present
                counter++;
                amendCounter++;
            }

        }
        //counter counts if there are anything was changed inside.
        if (counter == 0) {
            System.out.printf("\nProgramme ID(%s) was not inside the record", id);
        } else {
            System.out.printf("\nItem of %d was changed\n\n", counter);
        }
        MessageUI.successExecute();
    }

    //Find Programme
    public void findProgramme() {
        // ask for input
        System.out.println("\nEnter the ID or Keyword of Programme you want to find.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //counter counts if there was input found inside the system. 0 = no present
        int counter = 0;
        //getNumberOfEntries should >=1
        //search one by one, from head of array to last
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {

            //setup a new entry
            Programme programme = programmeList.getEntry(i);

            //if matches, print the no. , id and name
            //first part need input to match the id of programme
            //second part need input to contain the keyword of programme
            if (programmeUI.containsInput(programme.getProgrammeId(), input)
                    || programmeUI.containsInput(programme.getProgrammeName(), input)) {
                System.out.println("\nFound!");
                System.out.println("The Number of item, No: " + i);
                System.out.println("The ID of item, ID: " + programme.getProgrammeId());
                System.out.println("The Name of item, Name: " + programme.getProgrammeName());

                //count if input was present
                counter++;
                findCounter++;
            }
        }
        if (counter == 0) {
            System.out.printf("\nProgramme ID or Name (%s) was not found inside the system", input);
        }

    }

    //Add new Tutorial Group to Programmes
    public void addTutGroupToProgramme() {
        ProgTutorialGroup newProgrammeTutGroupList = programmeUI.inputProgTutorialGroupDetails();
        boolean duplicate = programmeTutGroupList.contains(newProgrammeTutGroupList);
        if (duplicate) {
            System.out.println("It was duplicated details!");
            MessageUI.failedExecute();
        } else {
            programmeTutGroupList.add(newProgrammeTutGroupList);
            MessageUI.successExecute();
        }
        addCounter++;
    }

    //Remove Tutorial Group to Programmes
    public void removeTutGroupFromProgramme() {
        // ask for input, find by ID or Name
        System.out.println("\nEnter the ID of Programme you want to find.");
        Scanner scanner = new Scanner(System.in);
        String progInput = scanner.nextLine();

        System.out.println("\nEnter the ID or Name of Tutorial Group you want to remove.");
        String tutInput = scanner.nextLine();

        //counter counts if the input was found inside the system. 0 = no present
        int counter = 0;
        //getNumberOfEntries should >=1 
        //search one by one, from last of array to head
        for (int i = programmeTutGroupList.getNumberOfEntries(); i >= 1; i--) {

            //setup new entry
            ProgTutorialGroup progTutorialGroup = programmeTutGroupList.getEntry(i);

            //if matches, remove the tutorial group
            //Upper Part need input to match the id of Programme
            //Lower part need input to match the id or contains the name of Tutorial Group
            //Both First part need input to match the id of programme
            //Both Second part need input to contain the keyword of programme
            if (programmeUI.containsInput(progTutorialGroup.getProgrammeId(), progInput)
                    || programmeUI.containsInput(progTutorialGroup.getTutGrpId(), progInput)
                    || programmeUI.containsInput(progTutorialGroup.getTutGrpName(), progInput)) {

                //print out that it is successfully removed
                System.out.printf("\nSuccessfully removed %s %s!\n",
                        progTutorialGroup.getProgrammeId(),
                        progTutorialGroup.getTutGrpName());

                //remove the tutorial group
                programmeTutGroupList.remove(i);

                //count if input was present
                counter++;
                removeCounter++;
            }
        }
        if (counter == 0) {
            System.out.println("\nThe item was not found inside the system");
        }
        MessageUI.successExecute();

    }

    //List All tutorial groups for a Programmes
    public int listAllTutorialGroupForAProgramme() {
        // ask for input, find by ID
        System.out.println("\nEnter the ID of Programme you want to find.");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();

        System.out.println("\nList of Tutorial Group:\n");
        System.out.println("No.  ID    Group ID   Group Name  Tutor");

        //counter counts if there was input found inside the system. 0 = no present
        int counter = 0;
        //getNumberOfEntries should >=1 
        //search one by one, from head of array to last
        for (int i = 1; i <= programmeTutGroupList.getNumberOfEntries(); i++) {
            //setup a new entry
            ProgTutorialGroup progTutorialGroup = programmeTutGroupList.getEntry(i);

            //if matches, list out everything
            //example output,1.   RSD   G1   Group 2  Ms Kim
            if (i == 10) {
                spacing = "  ";
            }

            if (programmeUI.containsInput(progTutorialGroup.getProgrammeId(), id)) {
                counter++;
                System.out.printf(counter + ".%s%-5s %-10s %-11s %-3s \n", spacing,
                        progTutorialGroup.getProgrammeId(),
                        progTutorialGroup.getTutGrpId(),
                        progTutorialGroup.getTutGrpName(),
                        progTutorialGroup.getTutor());

                //count if input was present
                

            }

        }
        listCounter++;
        if (counter == 0) {
            System.out.printf("\nProgramme ID(%s) was not found inside the system", id);
        }

        if (programmeTutGroupList.isEmpty()) {
            System.out.println("No record of Programmes!");
        }
        MessageUI.successExecute();
        return counter;
    }

    //Generate Report
    //int actionCounter, listCounter = 0,  addCounter = 0;
    //int removeCounter = 0, amendCounter = 0, findCounter = 0;
    public void generateReport() {
        actionCounter = listCounter + addCounter + removeCounter + amendCounter + findCounter;
        System.out.println("Programme Management System Report");
        System.out.println("==================================");
        dateAndTime();
        System.out.println("");
        System.out.println("");
        System.out.printf("There are total of %d programme in this system\n", programmeList.getNumberOfEntries());
        System.out.printf("There are total of %d tutorial group in this system\n", programmeTutGroupList.getNumberOfEntries());
        System.out.printf("%d Listing Action was performed\n", listCounter);
        System.out.printf("%d Adding Action was performed\n", addCounter);
        System.out.printf("%d Removing Action was performed\n", removeCounter);
        System.out.printf("%d Amending Action was performed\n", amendCounter);
        System.out.printf("%d Finding Action was performed\n", findCounter);
        System.out.printf("Total number of %d actions was performed\n", actionCounter);
        System.out.println("");
    }

    public void dateAndTime() {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
        String formattedDate = dateNow.format(dateFormat);
        System.out.println(formattedDate);
    }
}
