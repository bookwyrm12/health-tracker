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
        this.entries = new ArrayList<>();
    }
    
    
    //---------------------------------------
    // Class methods
    //---------------------------------------
    
    /**
     * Add an entry to the Diary.
     * @param food
     * @param quantity
     */
    public void addEntry(Food food, float quantity) {
        DiaryEntry entry = new DiaryEntry(food, quantity);
        this.entries.add(entry);
    }
    
    /**
     * Retrieve Diary entry.
     * @return DiaryEntry
     */
    public DiaryEntry getEntry(int id) {
        // TODO
        DiaryEntry entry = new DiaryEntry(new Food(""), 0);
        return entry;
    }
    
    /**
     * Update Diary entry.
     * @param entry
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
     * Save all Diary Entries.
     * TODO future: Optimization: only updates Diary Entries that haven't been saved already
     * @return boolean
     */
    public boolean saveAllEntries() {
        boolean success = false;
        // TODO
        return success;
    }
}
