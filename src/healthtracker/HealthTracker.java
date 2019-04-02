import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class HealthTracker {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        //---------------------------------------
        // Initialization
        //---------------------------------------
        
        getConfig("config.properties");             /* Get & load properties from config file. */
        Scanner scanner = new Scanner(System.in);   /* Init scanner. */
        
        
        //---------------------------------------
        // 0. Welcome Menu
        //---------------------------------------
        
        
        //---------------------------------------
        // 1. User Information Menu
        //---------------------------------------
        
        
        //---------------------------------------
        // 2. Diary (Food) Menu
        //---------------------------------------
        
        
        //---------------------------------------
        // 3. Activity Menu
        //---------------------------------------
        
        
    }
    
    /**
     * Get & load properties from config file
     */
    private static void getConfig(String fileName) throws Exception {
        FileInputStream propFile = new FileInputStream(fileName);
        Properties p = new Properties(System.getProperties());
        p.load(propFile);
        System.setProperties(p);
    }
}
