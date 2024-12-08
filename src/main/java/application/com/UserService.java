package application.com;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public static boolean tryAuthorization(String input_login, String input_password) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select user_login, user_password from todo_user where user_login='"+input_login+"' and user_password = '"+input_password+"';";
        ResultSet table = DatabaseController.put(sqlCommand);
        table.beforeFirst();
        return table.next();
    }
    public static ResultSet getUserInfo(String input_login, String input_password) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select user_login, user_password, user_id from todo_user where user_login='"+input_login+"' and user_password = '"+input_password+"';";
        ResultSet table = DatabaseController.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
}