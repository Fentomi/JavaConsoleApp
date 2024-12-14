import java.sql.*;
import java.util.Properties;

class DBSettings {
    protected static boolean DEBUG_ENABLED = true;
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
        } catch (SQLException | ClassNotFoundException _) {
            if(DBSettings.DEBUG_ENABLED) System.out.println("[DEBUG] Ошибка в подключении к базе данных.");
        }
    }

    public ResultSet select(String sqlCommand) {
        try {
            initConnect();
            return statement.executeQuery(sqlCommand);
        } catch (SQLException _) {
            if(DBSettings.DEBUG_ENABLED) System.out.println("[DB_ERROR] Не удалось выполнить запрос " + sqlCommand);
            return null;
        }
    }
    public void insert(String sqlCommand) {
        try {
            initConnect();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException _) {
            if(DBSettings.DEBUG_ENABLED) System.out.println("[DB_ERROR] Не удалось выполнить запрос " + sqlCommand);
        }
    }
    public void delete(String sqlCommand) {
        try {
            initConnect();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException _) {
            if(DBSettings.DEBUG_ENABLED) System.out.println("[DB_ERROR] Не удалось выполнить запрос " + sqlCommand);
        }
    }
    public ResultSet put(String sqlCommand) {
        try {
            initConnect();
            return statement.executeQuery(sqlCommand);
        } catch (Exception _) {
            if(DBSettings.DEBUG_ENABLED) System.out.println("[DB_ERROR] Не удалось выполнить запрос " + sqlCommand);
            return null;
        }
    }
}