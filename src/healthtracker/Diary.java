import java.util.ArrayList;

/**
 * A diary contains log entries about food consumed.
 * @author April Nickel
 */
public class Diary {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private String name;                         /** The diary name. */
    private ArrayList<DiaryEntry> entries;       /** The diary entries. */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create an empty Diary.
     */
    public Diary() {
        this.entries = new ArrayList<DiaryEntry>();
    }
    
    
    //---------------------------------------
    // Class methods
    //---------------------------------------
    
    /**
     * Add an entry to the Diary.
     */
    public void addEntry(Food food, float quantity) {
        DiaryEntry entry = new DiaryEntry(food, quantity);
        this.entries.add(entry);
    }
    
    /**
     * Retrieve Diary entry.
     */
    public DiaryEntry getEntry() {
        // TODO
        DiaryEntry entry = new DiaryEntry(new Food(""), 0);
        return entry;
    }
    
    /**
     * Update Diary entry.
     */
    public void editEntry(DiaryEntry entry) {
        // TODO
        // show current food value & amount value
        // prompt to change food value or amount value
        // prompt to update or keep old date value
        // save over old entry
    }
    
    /**
     * Delete Diary entry.
     */
    public void deleteEntry() {
        // TODO
    }
    
    /**
     * Save Diary.
     * Future optimization: only updates Diary Entries that haven't been saved already
     */
    public boolean saveEntry() {
        boolean success = false;
        // TODO
        return success;
    }
}
