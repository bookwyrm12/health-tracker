/**
 *
 * @author April Nickel
 */
public class Food {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private int id;                              /**  */
    private String name;                         /**  */
    private String type;                         /**  */
    private float numCalories;                   /**  */
    private float numCarbs;                      /**  */
    private float numProteins;                   /**  */
    private float numFats;                       /**  */
    private boolean saved;                       /**  */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create a new Food.
     */
    public Food(String name) {
        this.name = name;
    }
    
    /**
     * Create a new Food.
     */
    public Food(String name, float numCalories, float numCarbs, float numProteins, float numFats) {
        this.name = name;
        this.numCalories = numCalories;
        this.numCarbs = numCarbs;
        this.numProteins = numProteins;
        this.numFats = numFats;
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
     * Get type.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Set type.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Get number of calories.
     */
    public float getCalories() {
        return this.numCalories;
    }
    
    /**
     * Set number of calories.
     */
    public void setCalories(float numCalories) {
        this.numCalories = numCalories;
    }
    
    /**
     * Get amount of carbohydrates.
     */
    public float getCarbs() {
        return this.numCarbs;
    }
    
    /**
     * Set amount of carbohydrates.
     */
    public void setCarbs(float numCarbs) {
        this.numCarbs = numCarbs;
    }
    
    /**
     * Get amount of proteins.
     */
    public float getProteins() {
        return this.numProteins;
    }
    
    /**
     * Set amount of proteins.
     */
    public void setProteins(float numProteins) {
        this.numProteins = numProteins;
    }
    
    /**
     * Get amount of fats.
     */
    public float getFats() {
        return this.numFats;
    }
    
    /**
     * Set amount of fats.
     */
    public void setFats(float numFats) {
        this.numFats = numFats;
    }
}
