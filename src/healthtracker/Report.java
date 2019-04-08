import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class Report extends DBRecord {
    
    //---------------------------------------
    // Class instance variables
    //---------------------------------------
    
    
    //---------------------------------------
    // Class methods
    //---------------------------------------
    
    public <T> String getReport(String table1, String table2, String joinCol, String PKCol, String PKValue, String groupBy, String groupByCol, String[] sumCols, String displayNum) {
        ResultSet rs = getResultSet(table1, table2, joinCol, PKCol, PKValue, groupBy, groupByCol, sumCols, displayNum);
        String report = groupBy;
        for (int i = 0; i < sumCols.length; i++) {
            report += " | " + sumCols[i];

            if (i == sumCols.length - 1)
                report += "%n";
        }
        
        try {
            while (rs.next()) {
                report += rs.getString(groupBy);
                for (int i = 0; i < sumCols.length; i++) {
                    report += " | " + rs.getString(sumCols[i]);

                    if (i == sumCols.length - 1)
                        report += "%n";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doClose(rs);
        }
        
        return report;
    }
}
