import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

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
        // Get & load properties from config file
        getConfig("config.properties");
        
        // TODO code application logic here
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
