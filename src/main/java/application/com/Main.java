package application.com;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Вход в систему 'Пропер'...");
		authorization();

		System.out.println("Добро пожаловать в меню системы 'Пропер', " + CurrentUser.userLogin + '.');
		properSystem();
	}
	private static void authorization() throws SQLException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введите ваш логин: ");
		String userLogin = scanner.nextLine();
		System.out.print("Введите ваш пароль: ");
		String userPassword = scanner.nextLine();

		boolean isAuthorize = UserService.tryAuthorization(userLogin, userPassword);
		if (!isAuthorize) {
			System.out.print("Неверный логин или пароль. Попробуйте ещё раз.");
			authorization();
		} else {
			ResultSet user = UserService.getUserInfo(userLogin, userPassword);

			CurrentUser.userLogin = user.getString("user_login");
			CurrentUser.userPassword = user.getString("user_password");
			CurrentUser.userId = Integer.valueOf(user.getString("user_id"));
		}
	}
	private static void properSystem() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Что желаете? (Просмотреть инвентарь, Взять инвентарь, Сдать инвентарь, Выйти из системы)");
		String userAnswer = scanner.nextLine();

		if (Objects.equals(userAnswer, "Просмотреть инвентарь")) inventoryPanelOpen();
		else if (Objects.equals(userAnswer, "Взять инвентарь")) takeInventoryPanelOpen();
		else if (Objects.equals(userAnswer, "Сдать инвентарь")) passInventoryPanelOpen();
		else if (Objects.equals(userAnswer, "Выйти из системы")) quitSystem();
		else { System.out.println("Ошибка ввода, повторите попытку"); properSystem(); }
	}
	private static void inventoryPanelOpen() {
		System.out.println("Просматриваем инвентарь...");
		properSystem();
	}
	private static void takeInventoryPanelOpen() {
		System.out.println("Берем что-то из инвентаря...");
		properSystem();
	}
	private static void passInventoryPanelOpen() {
		System.out.println("Сдаем инвентарь...");
		properSystem();
	}
	private static void quitSystem() {
		System.out.println("Бай-бай");
	}
}