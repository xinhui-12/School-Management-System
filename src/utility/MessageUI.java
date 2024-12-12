package utility;

public class MessageUI {

    // Lee Xin Hui && Kimberly
    public static void displayInvalidChoiceMessage() {
        System.out.println("\n ** Invalid choice **");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system...");
    }

    public static void displaySuccessfullMessage() {
        System.out.println("\n** Successfull done **");
    }

    public static void displayFailureMessage() {
        System.out.println("\n** Failed **");
    }

    public static void displayNotFoundMessage() {
        System.out.println("\n** Record Not Found **");
    }
    
    public static void displayUnableFunctionMessage() {
        System.out.println("\n** No Record To Remove **");
    }

    // Lee Seng Wai
    public static void displayAddStudentSuccessfulMessage() {
    System.out.println("\nStudent has been added successfully!");
  }
  
  public static void displayFailToAddStudentMessage() {
    System.out.println("\nFailed to add student!");
  }
  
  public static void displayRemoveStudentSuccessfulMessage() {
    System.out.println("\nStudent has been removed successfully!");
  }
  
  public static void displayFailToRemoveStudentMessage() {
    System.out.println("\nFailed to remove student!");
  }
  
  public static void displayChangeTutorialGroupSuccessfulMessage() {
    System.out.println("\nStudent has changed tutorial group successfully!");
  }
  
  public static void displayFailToChangeTutorialGroupMessage() {
    System.out.println("\nFailed to change tutorial group!");
  } 
   
  public static void displayStudentNotFoundMessage() {
    System.out.println("\nStudent not found!");
  } 

    // Chin Tzer Khae
    public static void mainMenu() {
        System.out.println("\n\nWelcome to Programme Management System");
        System.out.println("==========================================\n");
        System.out.println("1. View Existing Programme");
        System.out.println("2. Add Programme");
        System.out.println("3. Amend Programme");
        System.out.println("4. Remove Programme");
        System.out.println("5. Find Programme Id or Name");
        System.out.println("6. Add tutorial group to a programme");
        System.out.println("7. Remove tutorial group from a programme");
        System.out.println("8. List All tutorial group for a programme");
        System.out.println("9. Generate Report");
        System.out.println("0. Exit Program");
        System.out.println("\nSelect your opinion (0-9)");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void successExecute() {
        System.out.println("\n\n============================");
        System.out.println("Command Succesfully Executed");
        System.out.println("============================");
    }
    
    public static void failedExecute() {
        System.out.println("\n\n==========================");
        System.out.println("Command Failed to Executed");
        System.out.println("==========================");
    }

    public static void emptyRecord(boolean emptyList){
        if(emptyList){
            System.out.println("\nNo record of Programmes!");
        }
    }
}
