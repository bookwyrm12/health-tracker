import java.util.ArrayList;

/**
 *
 * @author April Nickel
 */
public class Log {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private String name;                         /** The log name. */
    private ArrayList<LogEntry> entries;         /** The log entries. */
    private Integer current;                     /** The id of the current log entry. */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create an empty log.
     * @param name of log
     */
    public Log(String name) {
        this.name = name;
        this.entries = new ArrayList<>();
    }
    
    
    //---------------------------------------
    // Class instance methods
    //---------------------------------------
    
    /**
     * Get current/latest log entry id.
     * @return int current log entry id
     */
    public Integer getCurrent() {
        return this.current;
    }
    
    /**
     * Set current log entry id.
     * @param id
     */
    public void setCurrent(Integer id) {
        this.current = id;
    }
    
}
