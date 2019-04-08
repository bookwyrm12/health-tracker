import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author April Nickel
 */
public class Food extends DBRecord {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private int id;                              /**  */
    private String name;                         /**  */
    private String type;                         /**  */
    private float calories;                      /**  */
    private float carbs;                         /**  */
    private float proteins;                      /**  */
    private float fats;                          /**  */
    private boolean saved;                       /**  */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create a new Food.
     */
    public Food() {
        // empty food object
    }
    
    /**
     * Create a new Food.
     * @param name
     */
    public Food(String name, boolean save) {
        this.name = name;
        if (save)
            saveRecord(true);
    }
    
    /**
     * Create a new Food.
     * @param name
     * @param calories
     * @param carbs
     * @param proteins
     * @param fats
     */
    public Food(String name, float calories, float carbs, float proteins, float fats, boolean save) {
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
        if (save)
            saveRecord(true);
    }
    
    
    //---------------------------------------
    // Class (static) methods
    //---------------------------------------
    
    /**
     * Get Food object from DB given the id.
     * @param id of Food to retrieve
     * @return Food
     */
    public static Food find(int id) {
        ResultSet rs = getResultSet("food", "food_id", String.valueOf(id), null, null, null);
        Food f = new Food();
        
        try {
            if (rs.next()) {
                f = new Food(rs.getString("name"), false);
                f.setId(rs.getInt("food_id"));
                f.setType(rs.getString("type"));
                f.setCalories(rs.getFloat("calories"));
                f.setCarbs(rs.getFloat("carbs"));
                f.setProteins(rs.getFloat("proteins"));
                f.setFats(rs.getFloat("fats"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doClose(rs);
        }
        
        return f;
    }
    
    //---------------------------------------
    // Class instance methods
    //---------------------------------------
    
    /**
     * Get id.
     * @return int
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
     * @return String
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
     * Get type.
     * @return String
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Set type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Get number of calories.
     * @return float
     */
    public float getCalories() {
        return this.calories;
    }
    
    /**
     * Set number of calories.
     * @param calories
     */
    public void setCalories(float calories) {
        this.calories = calories;
    }
    
    /**
     * Get amount of carbohydrates.
     * @return float
     */
    public float getCarbs() {
        return this.carbs;
    }
    
    /**
     * Set amount of carbohydrates.
     * @param carbs
     */
    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }
    
    /**
     * Get amount of proteins.
     * @return float
     */
    public float getProteins() {
        return this.proteins;
    }
    
    /**
     * Set amount of proteins.
     * @param proteins
     */
    public void setProteins(float proteins) {
        this.proteins = proteins;
    }
    
    /**
     * Get amount of fats.
     * @return float
     */
    public float getFats() {
        return this.fats;
    }
    
    /**
     * Set amount of fats.
     * @param fats
     */
    public void setFats(float fats) {
        this.fats = fats;
    }
    
    /**
     * Create/update a Food record in DB.
     * @param newRecord indicates creating a new record (false if updating existing record)
     */
    public void saveRecord(boolean newRecord) {
        String[] colNames = {"name", "type", "calories", "carbs", "proteins", "fats"};
        String[] values = {this.name, this.type, String.valueOf(this.calories),
            String.valueOf(this.carbs), String.valueOf(this.proteins), String.valueOf(this.fats)};
        if (newRecord)
            this.id = createRecord("food", colNames, values);
        else
            updateRecord("food", "food_id", String.valueOf(this.id), colNames, values);
    }
}
