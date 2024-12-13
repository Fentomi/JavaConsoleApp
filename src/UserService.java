import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private static final DatabaseController database = new DatabaseController();

    public boolean tryAuthorization(String input_login, String input_password) throws SQLException {
        String sqlCommand = "select user_login, user_password from users where user_login='"+input_login+"' and user_password = '"+input_password+"';";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        return table.next();
    }
    public ResultSet getUserInfo(String input_login, String input_password) throws SQLException {
        String sqlCommand = "select user_login, user_password, user_id from users where user_login='"+input_login+"' and user_password = '"+input_password+"';";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
    public void setCurrentUser(Integer userId) throws SQLException {
        ResultSet user = UserService.getUserInfo(userId);
        CurrentUser.setUserId(Integer.valueOf(user.getString("user_id")));
        CurrentUser.setUserLogin(user.getString("user_login"));
        CurrentUser.setUserPassword(user.getString("user_password"));

        ResultSet person = UserService.getPersonInfo(userId);
        CurrentUser.setPersonId(Integer.valueOf(person.getString("person_id")));
        CurrentUser.setPersonName(person.getString("person_name"));
        CurrentUser.setPersonSurname(person.getString("person_surname"));
        CurrentUser.setPersonLastname(person.getString("person_lastname"));

        Integer roleId = Integer.valueOf(person.getString("role_id"));
        ResultSet role = UserService.getRoleInfo(roleId);
        CurrentUser.setRoleId(Integer.valueOf(role.getString("role_id")));
        CurrentUser.setRoleName(role.getString("role_name"));
    }
    public static ResultSet getUserInfo(Integer userId) throws SQLException {
        String sqlCommand = "select user_login, user_password, user_id from users where user_id="+userId+";";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
    public static ResultSet getPersonInfo(Integer userId) throws SQLException {
        String sqlCommand = "select * from person where user_id="+userId+";";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
    public static ResultSet getRoleInfo(Integer roleId) throws SQLException {
        String sqlCommand = "select * from roles where role_id="+roleId+";";
        ResultSet table = database.put(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
}