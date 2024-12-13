import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Properties;

class DBSettings {
    protected static String DB_LOGIN = "admin";
    protected static String DB_PASSWORD = "admin";
    protected static String DB_URL = "jdbc:postgresql://localhost:5432/klining";
}

public class DatabaseController {
    private Statement statement;

    public ResultSet select(String sqlCommand) throws SQLException, ClassNotFoundException {
        initConnect();
        return statement.executeQuery(sqlCommand);
    }
    public void insert(String sqlCommand) throws SQLException, ClassNotFoundException {
        initConnect();
        statement.executeQuery(sqlCommand);
    }
    public void delete(String sqlCommand) throws SQLException, ClassNotFoundException {
        initConnect();
        statement.executeQuery(sqlCommand);
    }
    public ResultSet put(String sqlCommand) throws SQLException, ClassNotFoundException {
        initConnect();
        return statement.executeQuery(sqlCommand);
    }
    private void initConnect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Properties authorization = new Properties();
        authorization.put("user", DBSettings.DB_LOGIN);
        authorization.put("password", DBSettings.DB_PASSWORD);
        Connection connection = DriverManager.getConnection(DBSettings.DB_URL, authorization);

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
    }
}