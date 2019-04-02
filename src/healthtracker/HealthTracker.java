import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class HealthTracker {
    
    static Scanner scanner = new Scanner(System.in);                   /* Init scanner. */
    static User user;                                                  /* User of the program. */

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        //---------------------------------------
        // Initialization
        //---------------------------------------
        
//        getConfig("config.properties");                         /* Get & load properties from config file. */
        ScreenOption nextScreen = ScreenOption.MAIN_MENU;       /* Next screen to display. */
        
        System.out.println("*********************************");
        System.out.println("************ WELCOME ************");
        System.out.println("*********************************");
        
        while(nextScreen != ScreenOption.EXIT) {
            switch(nextScreen) {
                case MAIN_MENU:
                    
                    //---------------------------------------
                    // 0. Main Menu
                    //---------------------------------------
                    
                    nextScreen = mainMenu();                 break;
                    
                case USER_NEW:
                    
                    //---------------------------------------
                    // 1. New User Input
                    //---------------------------------------
                    
                    nextScreen = userNew();                     break;
                    
                case USER_VIEW:
                    
                    //---------------------------------------
                    // 2. User Information Menu
                    //---------------------------------------
                    
                    nextScreen = userView();                    break;
                    
                case DIARY_MENU:
                    
                    //---------------------------------------
                    // 3. Diary (Food) Menu
                    //---------------------------------------
                    
                    nextScreen = diaryMenu();                   break;
                    
                case DIARY_VIEW:
                    
                    //---------------------------------------
                    // 4. Diary (Food) Report
                    //---------------------------------------
                    
                    nextScreen = diaryReport();                 break;
                    
                case DIARY_NEW_ENTRY:
                    
                    //---------------------------------------
                    // 5. New Diary Entry Input
                    //---------------------------------------
                    
                    nextScreen = diaryNewEntry();               break;
                    
                case ACTIVITY_MENU:
                    
                    //---------------------------------------
                    // 6. Activity Menu
                    //---------------------------------------
                    
                    nextScreen = activityMenu();                break;
                    
                case ACTIVITY_LOG_VIEW:
                    
                    //---------------------------------------
                    // 7. Activity Log Report
                    //---------------------------------------
                    
                    nextScreen = activityReport();              break;
                    
                case ACTIVITY_NEW:
                    
                    //---------------------------------------
                    // 8. New Activity Input
                    //---------------------------------------
                    
                    nextScreen = activityNew();                 break;
                    
                case EXIT_CONFIRM:
                    
                    //---------------------------------------
                    // 9. Exit Confirmation
                    //---------------------------------------
                    
                    nextScreen = exitConfirmation();            break;
                    
                default:
                    nextScreen = ScreenOption.EXIT;             break;
            }
        }
        
        System.out.println();
        System.out.println("Good-bye!");
    }
    
    /**
     * Get & load properties from config file
     */
    private static void getConfig(String fileName) throws Exception {
        FileInputStream propFile = new FileInputStream(fileName);
        Properties p = new Properties(System.getProperties());
        p.load(propFile);
        System.setProperties(p);
    }
    
    /**
     * Main Menu.
     */
    private static ScreenOption mainMenu() {
        
        //---------------------------------------
        // 0. Main Menu
        //---------------------------------------
        
//        if (user == null) {
//            return ScreenOption.USER_NEW;
//        }
        
        System.out.println();
        System.out.println("--- MAIN MENU ---");
        System.out.println();
        System.out.println("Please make a selection:");
        System.out.println("(F) Food Diary");
        System.out.println("(A) Activity Log");
        System.out.println("(I) My Information");
        System.out.println("(Q) Exit Application");
        System.out.println();
        
        String selection;
        
        while(true) {
            selection = scanner.nextLine().toLowerCase();
            switch(selection) {
                case "f":
                    return ScreenOption.DIARY_MENU;
                case "a":
                    return ScreenOption.ACTIVITY_MENU;
                case "i":
                    return ScreenOption.USER_VIEW;
                case "q":
                    return ScreenOption.EXIT_CONFIRM;
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }
    
    /**
     * Create a new user (input screen).
     */
    private static ScreenOption userNew() {
        
        //---------------------------------------
        // 1. New User Input
        //---------------------------------------
        
        // TODO
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * View user information.
     */
    private static ScreenOption userView() {
        
        //---------------------------------------
        // 2. User Information Menu
        //---------------------------------------
        
        // TODO
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Diary Menu.
     */
    private static ScreenOption diaryMenu() {
        
        //---------------------------------------
        // 3. Diary (Food) Menu
        //---------------------------------------
        
        // TODO
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Diary Report.
     */
    private static ScreenOption diaryReport() {
        
        //---------------------------------------
        // 4. Diary (Food) Report
        //---------------------------------------
        
        // TODO
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Create a new diary entry (input screen).
     */
    private static ScreenOption diaryNewEntry() {
        
        //---------------------------------------
        // 5. New Diary Entry Input
        //---------------------------------------
        
        // TODO
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Activity Menu.
     */
    private static ScreenOption activityMenu() {
        
        //---------------------------------------
        // 6. Activity Menu
        //---------------------------------------
        
        // TODO
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Activity Report.
     */
    private static ScreenOption activityReport() {
        
        //---------------------------------------
        // 7. Activity Log Report
        //---------------------------------------
        
        // TODO
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Create a new activity.
     */
    private static ScreenOption activityNew() {
        
        //---------------------------------------
        // 8. New Activity Input
        //---------------------------------------
        
        // TODO
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Exit Confirmation
     */
    private static ScreenOption exitConfirmation() {
        
        //---------------------------------------
        // 9. Exit Confirmation
        //---------------------------------------
        
        System.out.println();
        System.out.println("Are you sure you want to quit?");
        System.out.println();
        System.out.println("Please make a selection:");
        System.out.println("(Y) Yes, exit application");
        System.out.println("(N) No, go back");
        System.out.println();
        
        String selection;
        
        while(true) {
            selection = scanner.nextLine().toLowerCase();
            switch(selection) {
                case "y":
                    return ScreenOption.EXIT;
                case "n":
                    return ScreenOption.MAIN_MENU;
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }
}