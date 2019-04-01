import java.util.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author April Nickel
 */
public class User {
    
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
    private float height_goal;                   /**  */
    private String activity_level;               /**  */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create a new User.
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    
    //---------------------------------------
    // Class methods
    //---------------------------------------
    
    /**
     * Get name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Set name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get email.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Set email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Get gender.
     */
    public String getGender() {
        return this.gender;
    }
    
    /**
     * Set gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Get age.
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
     */
    public void setBirthDate(LocalDate birth_date) {
        this.birth_date = birth_date;
    }
    
    /**
     * Get current weight.
     */
    public float getWeight() {
        return this.weight;
    }
    
    /**
     * Set current weight.
     */
    public void setWeight(float weight) {
        this.weight = weight;
        // TODO: Save new weight_log as well
    }
    
    /**
     * Get weight goal.
     */
    public float getWeightGoal() {
        return this.weight_goal;
    }
    
    /**
     * Set weight goal.
     */
    public void setWeightGoal(float goal) {
        this.weight_goal = goal;
    }
    
    /**
     * Get current height.
     */
    public float getHeight() {
        return this.height;
    }
    
    /**
     * Set current height.
     */
    public void setHeight(float height) {
        this.height = height;
        // TODO: Save new height_log as well
    }
    
    /**
     * Get height goal.
     */
    public float getHeightGoal() {
        return this.height_goal;
    }
    
    /**
     * Set height goal.
     */
    public void setHeightGoal(float goal) {
        this.height_goal = goal;
    }
    
    /**
     * Get activity level.
     */
    public String getActivityLevel() {
        return this.activity_level;
    }
    
    /**
     * Set activity level.
     */
    public void setActivityLevel(String activity_level) {
        this.activity_level = activity_level;
    }
}
