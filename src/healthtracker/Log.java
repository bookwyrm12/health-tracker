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
    private int current;                         /** The id of the current log entry. */
    
    
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
    public int getCurrent() {
        return this.current;
    }
    
    /**
     * Set current log entry id.
     * @param id
     */
    public void setCurrent(int id) {
        this.current = id;
    }
    
}
