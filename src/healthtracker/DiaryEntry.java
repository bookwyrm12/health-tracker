import java.util.Date;

/**
 * 
 * @author April Nickel
 */
public class DiaryEntry {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private Food food;                           /**  */
    private float quantity;                      /**  */
    private Date updated;                        /**  */
    private boolean saved;                       /** Indicates if record is saved to disk. */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create a Diary Entry.
     */
    public DiaryEntry(Food food, float quantity) {
        this.food = food;
        this.quantity = quantity;
        this.updated = new Date();
        this.saved = false;
    }
    
    /**
     * Create a Diary Entry & immediately save to disk.
     */
    public DiaryEntry(Food food, float quantity, boolean save) {
        this.food = food;
        this.quantity = quantity;
        this.updated = new Date();
        this.saved = this.saveEntry();
    }
    
    
    //---------------------------------------
    // Class methods
    //---------------------------------------
    
    public boolean saveEntry() {
        boolean success = false;
        // TODO
        return success;
    }
    
}
