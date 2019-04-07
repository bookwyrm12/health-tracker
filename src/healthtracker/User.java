import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author April Nickel
 */
public class User extends DBRecord {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private int id;                              /**  */
    private String name;                         /**  */
    private String email;                        /**  */
    private String gender;                       /**  */
    private LocalDate birth_date;                /**  */
    private float weight;                        /**  */
    private float weight_goal;                   /**  */
    private float height;                        /**  */
    private float calories_goal;                 /**  */
    private String activity_level;               /**  */
    
    private Diary diary;                         /**  */
    private Log weight_log;                      /**  */
    private Log height_log;                      /**  */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create a new blank User.
     */
    public User() {
        this.diary = new Diary();
        this.weight_log = new Log("Weight Log");
        this.height_log = new Log("Height Log");
    }
    
    /**
     * Create a new User.
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.diary = new Diary();
        this.weight_log = new Log("Weight Log");
        this.height_log = new Log("Height Log");
    }
    
    
    //---------------------------------------
    // Class (static) methods
    //---------------------------------------
    
    public static ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        ResultSet rs = getAllFromDB("person");
        User u;
        
        try {
            while (rs.next()) {
                u = new User(rs.getString("name"), rs.getString("email"));
                u.setId(rs.getInt("person_id"));
                u.setGender(rs.getString("gender"));
                u.setBirthDate(rs.getDate("birth_date").toLocalDate());
                u.setWeightGoal(rs.getFloat("goal_weight"));
                u.setCaloriesGoal(rs.getFloat("goal_calories"));
                u.setActivityLevel(rs.getString("activity_level"));
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }
    
    public static User find(int id) {
        // Person
        ResultSet rs = dbQuery("SELECT * FROM person WHERE person_id = " + String.valueOf(id));
        User u = new User();
        
        try {
            if (rs.next()) {
                u = new User(rs.getString("name"), rs.getString("email"));
                u.setId(rs.getInt("person_id"));
                u.setGender(rs.getString("gender"));
                u.setBirthDate(rs.getDate("birth_date").toLocalDate());
                u.setWeightGoal(rs.getFloat("goal_weight"));
                u.setCaloriesGoal(rs.getFloat("goal_calories"));
                u.setActivityLevel(rs.getString("activity_level"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // TODO: Current Height
        // TODO: Current Weight
        // TODO: Height Logs
        // TODO: Weight Logs
        // TODO: Food & Food Logs
        // TODO later: Activities & Activity Logs
        
        return u;
    }
    
    
    //---------------------------------------
    // Class instance methods
    //---------------------------------------
    
    /**
     * Get id.
     * @return int user id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Set id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Get name.
     * @return String user name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Set name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get email.
     * @return String user email
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Set email.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Get gender.
     * @return String user gender
     */
    public String getGender() {
        return this.gender;
    }
    
    /**
     * Set gender.
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Calculate age based on birth date.
     * @return int user age
     */
    public int getAge() {
        LocalDate today = LocalDate.now();
        if ((today != null) && (this.birth_date != null)) {
            return Period.between(this.birth_date, today).getYears();
        } else {
            return 0;
        }
    }
    
    /**
     * Set birth date.
     * @param birth_date
     */
    public void setBirthDate(LocalDate birth_date) {
        this.birth_date = birth_date;
    }
    
    /**
     * Get current weight.
     * @return float user weight
     */
    public float getWeight() {
        return this.weight;
    }
    
    /**
     * Set current weight.
     * @param weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
        // TODO: Save new weight_log as well
    }
    
    /**
     * Get weight goal.
     * @return float user weight goal
     */
    public float getWeightGoal() {
        return this.weight_goal;
    }
    
    /**
     * Set weight goal.
     * @param goal
     */
    public void setWeightGoal(float goal) {
        this.weight_goal = goal;
    }
    
    /**
     * Get current height.
     * @return float user height
     */
    public float getHeight() {
        return this.height;
    }
    
    /**
     * Set current height.
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
        // TODO: Save new height_log as well
    }
    
    /**
     * Get daily calories goal.
     * @return float user calories goal
     */
    public float getCaloriesGoal() {
        return this.calories_goal;
    }
    
    /**
     * Set daily calories goal.
     * @param goal
     */
    public void setCaloriesGoal(float goal) {
        this.calories_goal = goal;
    }
    
    /**
     * Get activity level.
     * @return String user activity level
     */
    public String getActivityLevel() {
        return this.activity_level;
    }
    
    /**
     * Set activity level.
     * @param activity_level
     */
    public void setActivityLevel(String activity_level) {
        this.activity_level = activity_level;
    }
    
    /**
     * Save/update user record in DB.
     */
    public void saveUser() {
        String[] colNames = {"name", "email", "gender", "birth_date", "current_weight_id", 
            "current_height_id", "goal_weight", "goal_calories", "activity_level"};
        String[] values = {this.name, this.email, this.gender, convertToMysqlDate(this.birth_date),
            String.valueOf(this.weight_log.getCurrent()), String.valueOf(this.height_log.getCurrent()),
            String.valueOf(this.weight_goal), String.valueOf(this.calories_goal), String.valueOf(this.activity_level)};
        updateRecord("person", "person_id", String.valueOf(this.id), colNames, values);
    }
    
    /**
     * Add an entry to the user's food diary.
     * @param food
     * @param quantity
     */
    public void addDiaryEntry(Food food, float quantity) {
        this.diary.addEntry(food, quantity);
    }
}
