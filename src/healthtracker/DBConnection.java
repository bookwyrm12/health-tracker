import java.sql.*;

/**
 *
 * @author April Nickel
 */
public class DBConnection {
    
    //---------------------------------------
    // Static variables
    //---------------------------------------
    
    private static String host = System.getProperty("jdbcHost");                /**  */
    private static String dbName = System.getProperty("jdbcName");              /**  */
    private static String user = System.getProperty("jdbcUsername");            /**  */
    private static String password = System.getProperty("jdbcPassword");        /**  */
    
    private static String url = "jdbc:mysql://" + host + "/" + dbName;          /**  */
    private static String driver = "com.mysql.jdbc.Driver";                     /**  */
    
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    private Connection conn;                                                    /**  */
    
    
    //---------------------------------------
    // Class methods
    //---------------------------------------
    
    public Connection getConnection() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
        }
        return this.conn;
    }
}
