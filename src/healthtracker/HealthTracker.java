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
        
        DBConnection db = new DBConnection();                   /* Initialize & seed DB. */
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
        System.exit(0);
    }
    
    /**
     * Main Menu.
     */
    private static ScreenOption mainMenu() {
        
        //---------------------------------------
        // 0. Main Menu
        //---------------------------------------
        
        // TODO: temporarily disabled until userNew() is complete
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
        
        System.out.println();
        System.out.println("--- DIARY: MENU ---");
        System.out.println();
        System.out.println("Please make a selection:");
        System.out.println("(N) Add New Diary Entry");
        System.out.println("(H) View Diary Entries");
        System.out.println("(B) Back to Main Menu");
        System.out.println("(Q) Exit Application");
        System.out.println();
        
        String selection;
        
        while(true) {
            selection = scanner.nextLine().toLowerCase();
            switch(selection) {
                case "n":
                    return ScreenOption.DIARY_NEW_ENTRY;
                case "h":
                    return ScreenOption.DIARY_VIEW;
                case "b":
                    return ScreenOption.MAIN_MENU;
                case "q":
                    return ScreenOption.EXIT_CONFIRM;
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
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
        
        boolean run = true;
        boolean makeSelection = true;
        String selection, food;
        float calories, carbs, proteins, fats, quantity;
        
        while(run) {
            System.out.println();
            System.out.println("--- DIARY: NEW ENTRY ---");
            System.out.println();
            
            System.out.println("Food:");
            food = scanner.nextLine();

            System.out.println("Quantity consumed:");
            while(true) {
                if (scanner.hasNextFloat()) {
                    quantity = scanner.nextFloat();
                    break;
                } else {
                    System.out.println("Please enter a valid numerical value.");
                }
            }

            System.out.println("Calories:");
            while(true) {
                if (scanner.hasNextFloat()) {
                    calories = scanner.nextFloat();
                    break;
                } else {
                    System.out.println("Please enter a valid numerical value (0 is acceptable)");
                }
            }

            System.out.println("Carbs:");
            while(true) {
                if (scanner.hasNextFloat()) {
                    carbs = scanner.nextFloat();
                    break;
                } else {
                    System.out.println("Please enter a valid numerical value (0 is acceptable)");
                }
            }

            System.out.println("Proteins:");
            while(true) {
                if (scanner.hasNextFloat()) {
                    proteins = scanner.nextFloat();
                    break;
                } else {
                    System.out.println("Please enter a valid numerical value (0 is acceptable)");
                }
            }

            System.out.println("Fats:");
            while(true) {
                if (scanner.hasNextFloat()) {
                    fats = scanner.nextFloat();
                    break;
                } else {
                    System.out.println("Please enter a valid numerical value (0 is acceptable)");
                }
            }

            System.out.println();
            System.out.println("Would you like to save this diary entry?");
            System.out.println();
            System.out.println("Food: " + food);
            System.out.printf("Quantity: %.0f%n", quantity);
            System.out.printf("Calories: %.0f%n", calories);
            System.out.printf("Carbs: %.1f%n", carbs);
            System.out.printf("Proteins: %.1f%n", proteins);
            System.out.printf("Fats: %.1f%n", fats);
            System.out.println();
            System.out.println("(Y) Yes, save entry");
            System.out.println("(N) No, enter new information");
            System.out.println("(B) No, Back to Main Menu");
            System.out.println("(Q) No, Exit Application");
            
            scanner.next();
            while(makeSelection) {
                selection = scanner.nextLine().toLowerCase();
                System.out.println(selection);
                switch(selection) {
                    case "y":
                        Food newFood = new Food(food, calories, carbs, proteins, fats);
                        user.addDiaryEntry(newFood, quantity);
                        System.out.println();
                        System.out.println("Entry saved.");
                        System.out.println();
                        makeSelection = false;
                        run = false;
                        break;
                    case "n":
                        System.out.println();
                        System.out.println("Entry discarded.");
                        System.out.println();
                        makeSelection = false;
                        break;
                    case "b":
                        return ScreenOption.MAIN_MENU;
                    case "q":
                        return ScreenOption.EXIT_CONFIRM;
                    default:
                        System.out.println("Please enter a valid option.");
                }
            }
        }
        
        return ScreenOption.DIARY_MENU;
    }
    
    /**
     * Activity Menu.
     */
    private static ScreenOption activityMenu() {
        
        //---------------------------------------
        // 6. Activity Menu
        //---------------------------------------
        
        // TODO: temporarily disabled; Activity module not included in MVP
//        System.out.println();
//        System.out.println("--- ACTIVITIES: MENU ---");
//        System.out.println();
//        System.out.println("Please make a selection:");
//        System.out.println("(N) Add New Activity");
//        System.out.println("(H) View Activity History");
//        System.out.println("(B) Back to Main Menu");
//        System.out.println("(Q) Exit Application");
//        System.out.println();
//        
//        String selection;
//        
//        while(true) {
//            selection = scanner.nextLine().toLowerCase();
//            switch(selection) {
//                case "n":
//                    return ScreenOption.ACTIVITY_NEW;
//                case "h":
//                    return ScreenOption.ACTIVITY_LOG_VIEW;
//                case "b":
//                    return ScreenOption.MAIN_MENU;
//                case "q":
//                    return ScreenOption.EXIT_CONFIRM;
//                default:
//                    System.out.println("Please enter a valid option.");
//            }
//        }

        System.out.println();
        System.out.println("--- ACTIVITIES ---");
        System.out.println();
        System.out.println("This module is under development. Please try again later!");
        System.out.println();
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Activity Report.
     */
    private static ScreenOption activityReport() {
        
        //---------------------------------------
        // 7. Activity Log Report
        //---------------------------------------
        
        // TODO: Activity module not included in MVP
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Create a new activity.
     */
    private static ScreenOption activityNew() {
        
        //---------------------------------------
        // 8. New Activity Input
        //---------------------------------------
        
        // TODO: Activity module not included in MVP
        
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
        System.out.println("--- EXIT CONFIRMATION ---");
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