package py.com.local.datapar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionMYSQL {

    private final String url = "jdbc:mysql://194.195.218.49:3306/gakkoo_mo1";
    private final String user = "gakkoo_mo1";
    private final String password = "H.ZXjjQydfq5rzTcrsH78";
    public static String PGSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static Connection con;
    public static Statement st;
    public static PreparedStatement pstm;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName(PGSQL_DRIVER);
        con = DriverManager.getConnection(url, user, password);
        return con;
    }

    public Statement getStatement() throws SQLException, ClassNotFoundException {
        st = getConnection().createStatement();
        return st;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        pstm = getConnection().prepareStatement(sql);
        return pstm;
    }
}
