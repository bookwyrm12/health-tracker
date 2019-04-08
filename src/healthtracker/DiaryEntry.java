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
        saveEntry(true);
        this.saved = true;
    }
    
    /**
     * Create a Diary Entry with specified date/time.
     * @param food
     * @param quantity
     * @param updated
     */
    public DiaryEntry(Food food, float quantity, Date updated) {
        this.food = food;
        this.quantity = quantity;
        this.updated = updated;
        saveEntry(true);
        this.saved = true;
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
     * Get quantity.
     * @return float quantity
     */
    public float getQuantity() {
        return this.quantity;
    }
    
    /**
     * Set quantity.
     * @param quantity
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Update a Diary Entry in the DB.
     */
    public void updateEntry() {
        // TODO: future
    }
    
    /**
     * Create/update a Diary Entry record in DB.
     * @param newRecord indicates creating a new record (false if updating existing record)
     */
    public void saveEntry(boolean newRecord) {
        String[] colNames = {"food_id", "person_id", "quantity", "updated_at"};
        String[] values = {String.valueOf(this.food.getId()), "1",
            String.valueOf(this.quantity), convertToMysqlDate(this.updated)};
        if (newRecord)
            this.id = createRecord("food_log", colNames, values);
        else
            updateRecord("food_log", "food_log_id", String.valueOf(this.id), colNames, values);
    }
}
