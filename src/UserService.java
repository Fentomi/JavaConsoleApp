import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private static final DatabaseController database = new DatabaseController();

    public boolean tryAuthorization(String input_login, String input_password) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select user_login, user_password from users where user_login='"+input_login+"' and user_password = '"+input_password+"';";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        return table.next();
    }
    public ResultSet getUserInfo(String input_login, String input_password) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select user_login, user_password, user_id from users where user_login='"+input_login+"' and user_password = '"+input_password+"';";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
    public void setCurrentUser(Integer userId) throws SQLException, ClassNotFoundException {
        ResultSet user = UserService.getUserInfo(userId);
        CurrentUser.userId = Integer.valueOf(user.getString("user_id"));
        CurrentUser.userLogin = user.getString("user_login");
        CurrentUser.userPassword = user.getString("user_password");

        ResultSet person = UserService.getPersonInfo(userId);
        CurrentUser.personId = Integer.valueOf(person.getString("person_id"));
        CurrentUser.personName = person.getString("person_name");
        CurrentUser.personSurname = person.getString("person_surname");
        CurrentUser.personLastname = person.getString("person_lastname");

        Integer roleId = Integer.valueOf(person.getString("role_id"));
        ResultSet role = UserService.getRoleInfo(roleId);
        CurrentUser.roleId = Integer.valueOf(role.getString("role_id"));
        CurrentUser.roleName = role.getString("role_name");
    }
    public static ResultSet getUserInfo(Integer userId) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select user_login, user_password, user_id from users where user_id="+userId+";";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
    public static ResultSet getPersonInfo(Integer userId) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select * from person where user_id="+userId+";";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
    public static ResultSet getRoleInfo(Integer roleId) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select * from roles where role_id="+roleId+";";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
}