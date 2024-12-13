import java.sql.*;
import java.util.Properties;

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
        authorization.put("user", "admin");
        authorization.put("password", "admin");

        String url = "jdbc:postgresql://localhost:5432/klining";
        Connection connection = DriverManager.getConnection(url, authorization);

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
    }
}