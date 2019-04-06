import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        initDB();
        seedDB();
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
            System.out.println(ex);
        }
        
        this.conn = conn;
    }
    
    public void initDB() {
        // TODO
    }
    
    public void seedDB() {
        // TODO
    }
}
