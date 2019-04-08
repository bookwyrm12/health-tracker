import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author April Nickel
 */
public class HealthTracker {
    
    static Scanner scanner = new Scanner(System.in);            /* Init scanner. */
    static User user = null;                                    /* User of the program. */

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        //---------------------------------------
        // Initialization
        //---------------------------------------
        
        DBConnection db = new DBConnection();                   /* Initialize DB connection. */
        db.initDB("health-tracker/db/schema.sql");              /* Initialize DB tables. */
        db.seedDB("health-tracker/db/seed.sql");                /* Seed DB test data. */
        
        ScreenOption nextScreen = ScreenOption.MAIN_MENU;       /* Next screen to display. */
        
        String welcome = "*********************************%n" +
                         "************ WELCOME ************%n" +
                         "*********************************%n";
        printFormat(welcome);
        
        while(nextScreen != ScreenOption.EXIT) {
            switch(nextScreen) {
                case MAIN_MENU:
                    
                    //---------------------------------------
                    // 0. Main Menu
                    //---------------------------------------
                    
                    nextScreen = mainMenu();                    break;
                    
                case USER_SELECT:
                    
                    //---------------------------------------
                    // x. Select User
                    //---------------------------------------
                    
                    nextScreen = userSelect();                  break;
                    
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
        
        printFormat("%nGood-bye!%n");
        System.exit(0);
    }
    
    /**
     * Select an existing user (input screen).
     */
    private static ScreenOption userSelect() {
        
        //---------------------------------------
        // x. Select User
        //---------------------------------------
        
        int selectUser = 0;
        ArrayList<User> users = User.getAll();
        
        if (users.isEmpty()) {
            return ScreenOption.USER_NEW;
        } else if (users.size() == 1) {
            selectUser = users.get(0).getId();
        } else {
            // TODO
            //      show list of users (with id as int)
            //      input int to select user
            //      check that int matches the id of a user in users
        }
        
        user = User.find(selectUser);
        
        return ScreenOption.MAIN_MENU;
    }
    
    /**
     * Main Menu.
     */
    private static ScreenOption mainMenu() {
        
        //---------------------------------------
        // 0. Main Menu
        //---------------------------------------
        
        if (user == null) {
            return ScreenOption.USER_SELECT;
        }
        
        String menu = "%n--- MAIN MENU ---%n" +
                      "%nPlease make a selection:%n" +
                      "(F) Food Diary%n" +
                      "(A) Activity Log%n" +
                      "(I) My Information%n" +
                      "(Q) Exit Application%n";
        printFormat(menu);
        
        String selection;
        
        while(true) {
            selection = getInputString(null).toLowerCase();
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
                    printFormat("Please enter a valid option.%n");
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
        
        boolean run = true;
        boolean makeSelection = true;
        String selection, name, email, gender, activity_level, birth_date;
        int year, month, date;
        float weight, weight_goal, height, calories_goal;
        
        while(run) {
            String msg = "%n--- USER: NEW USER ---%n";
            printFormat(msg);
            
            msg = "%n-Personal info-%n%n";
            printFormat(msg);
            
            name        = getInputString("Name:");
            email       = getInputString("Email:");
            gender      = getInputString("Gender:");
            year        = getInputInt("Birth year (1919-2019):",   "Please enter a valid integer between 1919-2019.", 1919, 2019);
            month       = getInputInt("Birth month (1-12):",       "Please enter a valid integer between 1-12.", 1, 12);
            date        = getInputInt("Birth date (1-31):",        "Please enter a valid integer between 1-31.", 1, 31);
            birth_date  = String.format("%d-%02d-%02d", year, month, date);
            
            msg = "%n-Health info-%n%n";
            printFormat(msg);
            
            height          = getInputFloat("Height:",             "Please enter a valid numerical value (0 is acceptable)");
            weight          = getInputFloat("Weight:",             "Please enter a valid numerical value (0 is acceptable)");
            weight_goal     = getInputFloat("Weight Goal:",        "Please enter a valid numerical value (0 is acceptable)");
            calories_goal   = getInputFloat("Daily Calorie Goal:", "Please enter a valid numerical value (0 is acceptable)");
            
            msg = "%n---%n" +
                  "%nWould you like to save your information?%n" +
                  "Name: %s%n" +
                  "Email: %s%n" +
                  "Gender: %s%n" +
                  "Birth Date: %s%n" +
                  "Height: %.2f%n" +
                  "Weight: %.2f%n" +
                  "Weight Goal: %.2f%n" +
                  "Daily Calorie goal: %.0f%n" +
                  "%n(Y) Yes, save entry%n" +
                  "(N) No, enter new information%n" +
                  "(B) No, Back to Main Menu%n" +
                  "(Q) No, Exit Application%n";
            printFormat(msg, name, email, gender, birth_date, height, weight, weight_goal, calories_goal);
            
            while(makeSelection) {
                selection = getInputString(null).toLowerCase();
                switch(selection) {
                    case "y":
                        User u = new User(name, email, gender, weight, weight_goal, height, calories_goal, null);
                        u.setBirthDate(birth_date);
                        u.saveUser(true);
                        printFormat("%nUser info saved.%n");
                        makeSelection = false;
                        run = false;
                        break;
                    case "n":
                        printFormat("%nUser info discarded.%n");
                        makeSelection = false;
                        break;
                    case "b":
                        printFormat("%nUser info discarded.%n");
                        return ScreenOption.MAIN_MENU;
                    case "q":
                        printFormat("%nUser info discarded.%n");
                        return ScreenOption.EXIT_CONFIRM;
                    default:
                        printFormat("Please enter a valid option.%n");
                }
            }
        }
        
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
        
        String menu = "%n--- DIARY: MENU ---%n" +
                      "%nPlease make a selection:%n" +
                      "(N) Add New Diary Entry%n" +
                      "(H) View Diary Entries%n" +
                      "(B) Back to Main Menu%n" +
                      "(Q) Exit Application%n";
        printFormat(menu);
        
        String selection;
        
        while(true) {
            selection = getInputString(null).toLowerCase();
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
                    printFormat("Please enter a valid option.%n");
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
        
        boolean run = true;
        boolean makeSelection = true;
        String selection, filter = "d", displayNum = "10";
        
        while(run) {
            printFormat("%n--- DIARY: ENTRIES ---%n%n");
            
            printFormat(user.getDiaryReport(filter, displayNum));
            
            String menu = "%nPlease make a selection:%n" +
                          "%nChange Filter:  (D) By Day | (M) By Month | (Y) By Year%n" +
                          "Display:  (10) | (25) | (50) | (100)%n" +
                          "%n(N) Add New Diary Entry%n" +
                          "(B) Back to Main Menu%n" +
                          "(Q) Exit Application%n";
            printFormat(menu);
            
            makeSelection = true;
            while(makeSelection) {
                selection = getInputString(null).toLowerCase();
                switch(selection) {
                    case "d":
                    case "m":
                    case "y":
                        filter = selection;
                        makeSelection = false;
                        break;
                    case "10":
                    case "25":
                    case "50":
                    case "100":
                        displayNum = selection;
                        makeSelection = false;
                        break;
                    case "n":
                        return ScreenOption.DIARY_NEW_ENTRY;
                    case "b":
                        return ScreenOption.MAIN_MENU;
                    case "q":
                        return ScreenOption.EXIT_CONFIRM;
                    default:
                        printFormat("Please enter a valid option.%n");
                }
            }
        }
        
        return ScreenOption.DIARY_MENU;
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
            String msg = "%n--- DIARY: NEW ENTRY ---%n";
            printFormat(msg);
            
            food        = getInputString("Food:");
            
            quantity    = getInputFloat("Quantity consumed:",   "Please enter a valid numerical value.");
            calories    = getInputFloat("Calories:",            "Please enter a valid numerical value (0 is acceptable)");
            carbs       = getInputFloat("Carbs:",               "Please enter a valid numerical value (0 is acceptable)");
            proteins    = getInputFloat("Proteins:",            "Please enter a valid numerical value (0 is acceptable)");
            fats        = getInputFloat("Fats:",                "Please enter a valid numerical value (0 is acceptable)");
            
            msg = "%n---%n" +
                  "%nWould you like to save this diary entry?%n" +
                  "%nFood: %s%n" +
                  "Quantity: %.0f%n" +
                  "Calories: %.0f%n" +
                  "Carbs: %.1f%n" +
                  "Proteins: %.1f%n" +
                  "Fats: %.1f%n" +
                  "%n(Y) Yes, save entry%n" +
                  "(N) No, enter new information%n" +
                  "(B) No, Back to Main Menu%n" +
                  "(Q) No, Exit Application%n";
            printFormat(msg, food, quantity, calories, carbs, proteins, fats);
            
            while(makeSelection) {
                selection = getInputString(null).toLowerCase();
                switch(selection) {
                    case "y":
                        Food newFood = new Food(food, calories, carbs, proteins, fats, true);
                        user.addDiaryEntry(newFood, quantity);
                        printFormat("%nEntry saved.%n");
                        makeSelection = false;
                        run = false;
                        break;
                    case "n":
                        printFormat("%nEntry discarded.%n");
                        makeSelection = false;
                        break;
                    case "b":
                        return ScreenOption.MAIN_MENU;
                    case "q":
                        return ScreenOption.EXIT_CONFIRM;
                    default:
                        printFormat("Please enter a valid option.%n");
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
//        String menu = "%n--- ACTIVITIES: MENU ---%n" +
//                      "%nPlease make a selection:%n" +
//                      "(N) Add New Activity%n" +
//                      "(H) View Activity History%n" +
//                      "(B) Back to Main Menu%n" +
//                      "(Q) Exit Application%n";
//        printFormat(menu);
//        
//        String selection;
//        
//        while(true) {
//            selection = getInputString(null).toLowerCase();
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
//                    printFormat("Please enter a valid option.%n");
//            }
//        }
        
        String msg = "%n--- ACTIVITIES ---%n" +
                     "%nThis module is under development. Please try again later!%n";
        printFormat(msg);
        
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
        
        String menu = "%n--- EXIT CONFIRMATION ---%n" +
                      "%nAre you sure you want to quit?%n" +
                      "%nPlease make a selection:%n" +
                      "(Y) Yes, exit application%n" +
                      "(N) No, go back%n";
        printFormat(menu);
        
        String selection;
        
        while(true) {
            selection = getInputString(null).toLowerCase();
            switch(selection) {
                case "y":
                    return ScreenOption.EXIT;
                case "n":
                    return ScreenOption.MAIN_MENU;
                default:
                    printFormat("Please enter a valid option.%n");
            }
        }
    }
    
    //---------------------------------------
    // Helper Methods
    //---------------------------------------
    
    /**
     * Print a string with formatting options.
     * @param msg
     * @param args
     */
    private static void printFormat(String msg, Object ... args) {
        System.out.printf(msg, args);
    }
    
    /**
     * Get user input.
     * @param prompt optional prompt to print
     * @return String
     */
    private static String getInputString(String prompt) {
        if (prompt != null) {
            printFormat(prompt + "%n");
        }
        String input = scanner.nextLine();
        return input;
    }
    
    /**
     * Get user input.
     * @param prompt optional prompt to print
     * @param errMsg error prompt to print
     * @return float
     */
    private static float getInputFloat(String prompt, String errMsg) {
        float input = 0;
        if (prompt != null) {
            printFormat(prompt + "%n");
        }
        while(true) {
            if (scanner.hasNextFloat()) {
                input = scanner.nextFloat();
                scanner.nextLine();
                break;
            } else {
                System.out.println(errMsg);
            }
        }
        return input;
    }
    
    /**
     * Get user input.
     * @param prompt optional prompt to print
     * @param errMsg error prompt to print
     * @param min optional min value allowed
     * @param max optional max value allowed
     * @return int
     */
    private static int getInputInt(String prompt, String errMsg, Integer min, Integer max) {
        int input = 0;
        boolean printErr = false;
        if (prompt != null) {
            printFormat(prompt + "%n");
        }
        while(true) {
            if (printErr) printFormat(errMsg + "%n");
            
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
                if (min != null && max != null) {
                    if (input >= min && input <= max) break;
                } else if (min != null) {
                    if (input >= min) break;
                } else if (max != null) {
                    if (input <= max) break;
                } else {
                    break;
                }
            }
            printErr = true;
        }
        return input;
    }
}