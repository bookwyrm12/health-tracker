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
    
    
    //---------------------------------------
    // Constructors
    //---------------------------------------
    
    /**
     * Create a new DBConnection.
     */
    public DBConnection() {
        getConnectionConfig("config/config.properties");
        //initConnection();
    }
    
    
    //---------------------------------------
    // Class methods
    //---------------------------------------
    
    /**
     * Get & load database connection properties from config file.
     * @param fileName config file path
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
    
    /**
     * Initialize DB connection.
     */
    public void initConnection() {
        String query = "SELECT VERSION()";
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = this.ds.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            rs = pst.executeQuery(query);
            
            if (rs.next()) {
                String version = rs.getString(1);
                System.out.println(version);
            }
            
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } finally {
            doClose(rs, conn);
        }
    }
    
    /**
     * Initialize DB.
     * @param fileName schema file path
     */
    public void initDB(String fileName) {
        executeSqlFile(fileName);
    }
    
    /**
     * Seed DB.
     * @param fileName seed file path
     */
    public void seedDB(String fileName) {
        executeSqlFile(fileName);
    }
    
    /**
     * Execute sql script from file.
     * @param fileName sql file path
     */
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
        execute(query);
    }
    
    /**
     * Execute a Mysql query.
     * @param query 
     */
    public void execute(String query) {
        Connection conn = null;
        try {
            conn = this.ds.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            boolean isResult = pst.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } finally {
            doClose(null, conn);
        }
    }
    
    /**
     * Execute a Mysql query statement.
     * @param query
     * @return ResultSet with query result(s)
     */
    public ResultSet executeQuery(String query) {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = this.ds.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            rs = pst.executeQuery(query);
            return rs;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        
        return null;
    }
    
    /**
     * Execute a Mysql update or insert statement.
     * @param query
     * @return int indicating # of rows updated
     */
    public int executeUpdate(String query) {
        Connection conn = null;
        int primaryKey = 0;
        try {
            conn = this.ds.getConnection();
            PreparedStatement pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = pst.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            if (affectedRows == 0) {
                throw new SQLException("Creating record failed; no rows affected.");
            }
            try (ResultSet genKeys = pst.getGeneratedKeys()) {
                if (genKeys.next()) {
                    primaryKey = genKeys.getInt(1);
                } else {
                    throw new SQLException("Creating record failed; no ID obtained.");
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } finally {
            doClose(null, conn);
        }
        
        return primaryKey;
    }
    
    /**
     * Close open Connection or ResultSet.
     * @param rs
     * @param conn
     * @throws SQLException 
     */
    public void doClose(ResultSet rs, Connection conn) {
        if (rs != null) try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (conn != null) try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
