import java.util.Date;

/**
 *
 * @author April Nickel
 */
public class LogEntry {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private int id;                              /**  */
    private Date updated;                        /**  */
    private boolean isCurrent;                   /**  */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    public LogEntry() {
        this.updated = new Date();
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
}
