import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Abstract class between java objects and SQL records.
 * @author April Nickel
 */
public abstract class DBRecord {
    
    //---------------------------------------
    // Class (static) variables
    //---------------------------------------
    
    private static DBConnection db = new DBConnection();
    
    
    //---------------------------------------
    // Class (static) methods
    //---------------------------------------
    
    /**
     * Perform a Mysql update statement.
     * @param query
     * @return int indicating # of rows updated
     */
    public static int dbUpdate(String query) {
        int rowsUpdated = DBRecord.db.executeUpdate(query);
        return rowsUpdated;
    }
    
    /**
     * Perform a Mysql select statement.
     * @param query
     * @return ResultSet with query result(s)
     */
    public static ResultSet dbQuery(String query) {
        ResultSet rs = DBRecord.db.executeQuery(query);
        return rs;
    }
    
    /**
     * Retrieve all rows from a table.
     * @param tableName
     * @return ResultSet with query results
     */
    public static ResultSet getAllFromDB(String tableName) {
        String query = "SELECT * FROM " + tableName;
        ResultSet rs = dbQuery(query);
        return rs;
    }
    
    
    //---------------------------------------
    // Class instance methods
    //---------------------------------------
    
    /**
     * Given a Java date, convert to the Mysql date string format.
     * @param date
     * @return String in Mysql date string format
     */
    public String convertToMysqlDate(Date date) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String mysqlDate = sdf.format(date);
        return mysqlDate;
    }
    
    /**
     * Given a Java LocalDate, convert to the Mysql date string format.
     * @param date
     * @return String in Mysql date string format
     */
    public String convertToMysqlDate(LocalDate date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String mysqlDate = sdf.format(date);
        return mysqlDate;
    }
    
    /**
     * Create a new record in the database.
     * @param tableName: table name to insert record into
     *  ex: "person"
     * @param colNames: column names to update - must have same length as values
     *  ex: "food_id, person_id, quantity, updated_at"
     * @param values: values to update - must have same length as colNames
     *  ex: "34, 12, 1, 2019-04-01 08:55:00"
     */
    public void createRecord(String tableName, String colNames, String values) {
        String query;
        if (colNames != null && !colNames.equals("")) {
            query = "INSERT INTO " + tableName + " VALUES ( " + values + " );";
        } else {
            query = "INSERT INTO " + tableName + " ( " + colNames + 
                " ) VALUES ( " + values + " );";
        }
        int rowsUpdated = dbUpdate(query);
    }
    
    /**
     * Update an existing record in the database.
     * @param tableName: table name to update record in
     *  ex: "person"
     * @param PKCol: primary key column name
     *  ex: "person_id"
     * @param PKValue: primary key value of row to update
     *  ex: "1"
     * @param colNames: column names to update - must have same length as values
     *  ex: "food_id, person_id, quantity, updated_at"
     * @param values: values to update - must have same length as colNames
     *  ex: "34, 12, 1, 2019-04-01 08:55:00"
     */
    public void updateRecord(String tableName, String PKCol, String PKValue, String[] colNames, String[] values) {
        this.db = new DBConnection();
        String query = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < colNames.length; i++) {
            query += colNames[i] + " = " + values[i];
            if (i < colNames.length - 1) {
                query += " , ";
            }
        }
        query += " WHERE " + PKCol + " = " + PKValue + ";";
        int rowsUpdated = dbUpdate(query);
    }
}