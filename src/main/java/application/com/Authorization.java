package application.com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authorization {
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

        boolean isAuthorize = UserService.tryAuthorization(userLogin, userPassword);
        if (!isAuthorize) {
            System.out.print("Неверный логин или пароль. Попробуйте ещё раз.");
            authorization(tryCount+1);
        } else {
            ResultSet user = UserService.getUserInfo(userLogin, userPassword);
            Integer userId = Integer.valueOf(user.getString("user_id"));
            UserService.setCurrentUser(userId);
            return true;
        }
        return false;
    }
}
