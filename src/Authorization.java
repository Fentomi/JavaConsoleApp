import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authorization {
    private static final UserService userService = new UserService();

    public static boolean authorization(Integer tryCount) throws SQLException, ClassNotFoundException {
        if(tryCount > 2) {
            System.out.println("Использованы все попытки, вход заблокирован.");
            return false;
        }
        System.out.println("Попытка входа номер "+tryCount);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваш логин: ");
        String userLogin = scanner.nextLine();
        System.out.print("Введите ваш пароль: ");
        String userPassword = scanner.nextLine();

        boolean isAuthorize = userService.tryAuthorization(userLogin, userPassword);
        if (!isAuthorize) {
            System.out.print("Неверный логин или пароль. Попробуйте ещё раз.");
            authorization(tryCount+1);
        } else {
            ResultSet user = userService.getUserInfo(userLogin, userPassword);
            Integer userId = Integer.valueOf(user.getString("user_id"));
            userService.setCurrentUser(userId);
            return true;
        }
        return false;
    }
}
