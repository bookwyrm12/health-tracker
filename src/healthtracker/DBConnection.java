import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author April Nickel
 */
public class DBConnection {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private MysqlDataSource ds;                                                 /**  */
    private Connection conn;                                                    /**  */
    private String driver;                                                      /**  */
    private String url;                                                         /**  */
    private String user;                                                        /**  */
    private String password;                                                    /**  */
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create a new DBConnection.
     */
    public DBConnection() {
        getConnectionConfig("health-tracker/config.properties");
        initConnection();
        initDB("health-tracker/db/schema.sql");
        seedDB("health-tracker/db/seed.sql");
    }
    
    
    //---------------------------------------
    // Class methods
    //---------------------------------------
    
    /**
     * Get & load database connection properties from config file.
     */
    private void getConnectionConfig(String fileName) {
        Properties props = new Properties();
        try (FileInputStream propFile = new FileInputStream(fileName)) {
            props.load(propFile);
        } catch (IOException ex) {
            System.out.println("Failed to load config from " + fileName);
            System.out.println("pwd: " + System.getProperty("user.dir"));
        }
        
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl(props.getProperty("mysql.url"));
        ds.setUser(props.getProperty("mysql.username"));
        ds.setPassword(props.getProperty("mysql.password"));
        this.ds = ds;
    }
    
    public void initConnection() {
        String query = "SELECT VERSION()";
        try {
            Connection conn = this.ds.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery(query);
            
            if (rs.next()) {
                String version = rs.getString(1);
                System.out.println(version);
            }
            
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        
        this.conn = conn;
    }
    
    public void initDB(String fileName) {
        executeSqlFile(fileName);
    }
    
    public void seedDB(String fileName) {
        executeSqlFile(fileName);
    }
    
    public void executeSqlFile(String fileName) {
        // Reading a file to a string logic from:
        // https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
            
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        String query = contentBuilder.toString();
        
        try {
            Connection conn = this.ds.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            boolean isResult = pst.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
}
