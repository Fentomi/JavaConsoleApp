import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authorization {
    private static final UserService userService = new UserService();
    private static Integer tryAutorizationCount = 0;
    public static boolean _isAutorize = false;
    public static String _inputLogin;
    public static String _inputPassword;

    public static boolean authorization() throws SQLException, ClassNotFoundException {
        if(tryAutorizationCount > 2) {
            System.out.println("Использованы все попытки, вход заблокирован.");
            return false;
        }
        System.out.println("Попытка входа номер "+tryAutorizationCount);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваш логин: ");
        _inputLogin = scanner.nextLine();
        System.out.print("Введите ваш пароль: ");
        _inputPassword = scanner.nextLine();

        _isAutorize = userService.tryAuthorization(_inputLogin, _inputPassword);
        if (!_isAutorize) {
            System.out.print("Неверный логин или пароль. Попробуйте ещё раз.");
            tryAutorizationCount += 1;
            authorization();
        } else {
            ResultSet user = userService.getUserInfo(_inputLogin, _inputPassword);
            Integer userId = Integer.valueOf(user.getString("user_id"));
            userService.setCurrentUser(userId);
            return true;
        }
        return false;
    }
}
