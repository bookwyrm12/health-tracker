import java.util.Date;

/**
 * 
 * @author April Nickel
 */
public class DiaryEntry extends DBRecord {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private int id;                              /**  */
    private Food food;                           /**  */
    private float quantity;                      /**  */
    private Date updated;                        /**  */
    private boolean saved;                       /** Indicates if record is saved to disk. */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create a Diary Entry.
     * @param food
     * @param quantity
     */
    public DiaryEntry(Food food, float quantity) {
        this.food = food;
        this.quantity = quantity;
        this.updated = new Date();
        this.saved = false;
    }
    
    /**
     * Create a Diary Entry with specified date/time.
     * @param food
     * @param updated
     * @param quantity
     */
    public DiaryEntry(Food food, Date updated, float quantity) {
        // TODO: later
    }
    
    /**
     * Create a Diary Entry & immediately save to disk.
     * @param food
     * @param quantity
     * @param save
     */
    public DiaryEntry(Food food, float quantity, boolean save) {
        this.food = food;
        this.quantity = quantity;
        this.updated = new Date();
        this.saved = this.createEntry();
    }
    
    
    //---------------------------------------
    // Class instance methods
    //---------------------------------------
    
    /**
     * Get id.
     * @return int id
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
     * Update a Diary Entry in the DB.
     */
    public void updateEntry() {
        // TODO: future
    }
    
    /**
     * Create a Diary Entry in the DB.
     */
    private boolean createEntry() {
        String tableName = "food_log";
        String colNames = "food_id, person_id, quantity, updated_at";
        String updatedStr = convertToMysqlDate(this.updated);
        String values = String.valueOf(this.food.getId()) + " , " + "1" +
            " , " + String.valueOf(this.quantity) + " , " + updatedStr;
        createRecord(tableName, colNames, values);
        return true;
    }
    
}
