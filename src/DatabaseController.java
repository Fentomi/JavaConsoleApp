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

    private void initConnect() {
        try {
            Class.forName("org.postgresql.Driver");

            Properties authorization = new Properties();
            authorization.put("user", DBSettings.DB_LOGIN);
            authorization.put("password", DBSettings.DB_PASSWORD);
            Connection connection = DriverManager.getConnection(DBSettings.DB_URL, authorization);

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void closeConnect() {
        try {
            statement.close();
        } catch (Exception _) { }
    }

    public ResultSet select(String sqlCommand) {
        try {
            initConnect();
            ResultSet table = statement.executeQuery(sqlCommand);
            closeConnect();
            return table;
        } catch (Exception _) {
            return null;
        }
    }
    public void insert(String sqlCommand) {
        try {
            initConnect();
            statement.executeQuery(sqlCommand);
            closeConnect();
        } catch (Exception _) { }
    }
    public void delete(String sqlCommand) {
        try {
            initConnect();
            statement.executeQuery(sqlCommand);
            closeConnect();
        } catch (Exception _) { }
    }
    public ResultSet put(String sqlCommand) {
        try {
            initConnect();
            ResultSet table = statement.executeQuery(sqlCommand);
            closeConnect();
            return table;
        } catch (Exception _) {
            return null;
        }
    }
}