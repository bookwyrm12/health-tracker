import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Perform a Mysql insert statement.
     * @param query
     * @return int auto generated primary key of inserted row
     */
    public static int dbInsert(String query) {
        int primaryKey = DBRecord.db.executeUpdate(query);
        return primaryKey;
    }
    
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
    
    /**
     * Retrieve a single value from a single row in the DB.
     * @param tableName
     * @param PKCol
     * @param PKValue
     * @param orderBy
     * @param orderDir
     * @param limit
     * @return ResultSet with value
     */
    public static ResultSet getResultSet(String tableName, String PKCol, String PKValue, String orderBy, String orderDir, String limit) {
        String query = "SELECT * FROM " + tableName + " WHERE " + PKCol + " = " + PKValue;
        if (orderBy != null && !orderBy.equals(""))
            query += " ORDER BY " + orderBy;
        
        if (orderDir != null && !orderDir.equals(""))
            query += " " + orderDir;
        
        if (limit != null && !limit.equals(""))
            query += " LIMIT " + limit;
        
        ResultSet rs = dbQuery(query);
        return rs;
    }
    
    /**
     * Retrieve a single value from a single row in the DB.
     *  EXAMPLE QUERY:
     *      SELECT
     *          month(updated_at) month,
     *          FORMAT(SUM(calories), 2) total_calories,
     *          FORMAT(SUM(carbs), 2) total_carbs,
     *          FORMAT(SUM(proteins), 2) total_proteins,
     *          FORMAT(SUM(fats), 2) total_fats
     *      FROM (
     *          food
     *          INNER JOIN
     *          food_log USING (food_id)
     *      )
     *      WHERE person_id = 3
     *      GROUP BY month(updated_at);
     * @return ResultSet with value
     */
    public static ResultSet getResultSet(String table1, String table2, String joinCol, String PKCol, String PKValue, String groupBy, String groupByCol, String[] sumCols, String limit) {
        String query = "SELECT " + groupBy + "(" + groupByCol + ") " + groupBy + " ,";
        for (int i = 0; i < sumCols.length; i++) {
            query += " FORMAT(SUM(" + sumCols[i] + "), 2) " + sumCols[i];
            
            if (i < sumCols.length - 1)
                query += " , ";
        }
        query += " FROM ( " + table1 + " INNER JOIN " + table2 + " USING (" + joinCol + ") )";
        query += " WHERE " + PKCol + " = " + PKValue;
        query += " GROUP BY " + groupBy + "(" + groupByCol + ")";
        
        if (limit != null && !limit.equals(""))
            query += " LIMIT " + limit;
        
        query += " ;";
        
        ResultSet rs = dbQuery(query);
        return rs;
    }
    
    /**
     * Retrieve a single String value from a single row in the DB.
     * @param tableName
     * @param PKCol
     * @param PKValue
     * @param searchCol
     * @param orderBy
     * @param orderDir
     * @param limit
     * @return String
     */
    public static String getSingleValString(String tableName, String PKCol, String PKValue, String searchCol, String orderBy, String orderDir, String limit) {
        ResultSet rs = getResultSet(tableName, PKCol, PKValue, orderBy, orderDir, limit);
        String value = "";
        
        try {
            if (rs.next()) {
                value = rs.getString(searchCol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doClose(rs);
        }
        
        return value;
    }
    
    /**
     * Retrieve a single Float value from a single row in the DB.
     * @param tableName
     * @param PKCol
     * @param PKValue
     * @param searchCol
     * @param orderBy
     * @param orderDir
     * @param limit
     * @return Float
     */
    public static Float getSingleValFloat(String tableName, String PKCol, String PKValue, String searchCol, String orderBy, String orderDir, String limit) {
        ResultSet rs = getResultSet(tableName, PKCol, PKValue, orderBy, orderDir, limit);
        Float value = null;
        
        try {
            if (rs.next()) {
                value = rs.getFloat(searchCol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doClose(rs);
        }
        
        return value;
    }
    
    /**
     * Close open ResultSet.
     * @param rs
     */
    public static void doClose(ResultSet rs) {
        DBRecord.db.doClose(rs, null);
    }
    
    
    //---------------------------------------
    // Class instance methods
    //---------------------------------------
    
    /**
     * Given a Java date, convert to the Mysql date & time string format.
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        String mysqlDate = date.format(dtf);
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
    public int createRecord(String tableName, String[] colNames, String[] values) {
        String query = "INSERT INTO " + tableName;
        String cols = "", vals = "";
        for (int i = 0; i < values.length; i++) {
            if (colNames != null && !colNames.equals("")) {
                cols += colNames[i];
                if (i < colNames.length - 1) {
                    cols += " , ";
                }
            }
            
            if (values[i] == null)
                vals += values[i];
            else
                vals += "'" + values[i] + "'";
            
            if (i < values.length - 1)
                vals += " , ";
        }
        
        if (colNames != null && !colNames.equals(""))
            query += " ( " + cols + " ) VALUES ( " + vals + " );";
        else
            query += " VALUES ( " + vals + " );";
        
        int primaryKey = dbInsert(query);
        return primaryKey;
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
        String query = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < colNames.length; i++) {
            if (values[i] == null)
                query += colNames[i] + " = " + values[i];
            else
                query += colNames[i] + " = '" + values[i] +"'";
            
            if (i < colNames.length - 1)
                query += " , ";
        }
        query += " WHERE " + PKCol + " = '" + PKValue + "';";
        int rowsUpdated = dbUpdate(query);
    }
}
