package application.com;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Вход в систему 'Пропер'...");
		authorization();

		String proper =
				"""
					⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉
					|    WELCOME TO PROPER SYSTEM       |
					⌊___________________________________」
				""";
		System.out.print(proper);
		System.out.println("Добро пожаловать в меню системы 'Пропер', " + CurrentUser.userLogin + '.');
		properSystem(true);
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
	private static void properSystem(boolean showMenu) {
		if (showMenu) {
			String menu = """
				Команды просмотра инвентаря:
				⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉
				| /Просмотреть инвентарь |
				⌊________________________」
				Команды записи инвентаря:
				⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉ ⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉
				| /Записать инвентарь на себя | | /Просмотреть записанный инвентарь |
				⌊_____________________________」 ⌊__________________________________」
				Команды сдачи инвентаря:
				⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉
				| /Сдать инвентарь |
				⌊__________________」
				Команды Выхода из системы:
				⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉
				| /Выйти из системы |
				⌊___________________」
				""";
			System.out.println();
			System.out.println(menu);
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите команду: ");
		String userAnswer = scanner.nextLine();

        switch (userAnswer) {
            case "/Просмотреть инвентарь" -> showInventory();
			case "/Записать инвентарь на себя" -> takeInventoryPanelOpen();
			case "/Просмотреть записанный инвентарь" -> showTakenInventory();
            case "/Сдать инвентарь" -> passInventoryPanelOpen();
			case "/Просмотреть сданный инвентарь" -> showPassInventory();
            case "/Выйти из системы" -> quitSystem();
            case null, default -> {
                System.out.println("Ошибка ввода, повторите попытку.");
                properSystem(false);
            }
        }
	}
	private static void showInventory() {
		System.out.println("Просматриваем инвентарь...");
		properSystem(false);
	}
	private static void takeInventoryPanelOpen() {
		System.out.println("Берем что-то из инвентаря...");
		properSystem(false);
	}
	private static void showTakenInventory() {
		System.out.println("Берем что-то из взятого инвентаря...");
		properSystem(false);
	}
	private static void showPassInventory() {
		System.out.println("Берем что-то из сданного инвентаря...");
		properSystem(false);
	}
	private static void passInventoryPanelOpen() {
		System.out.println("Сдаем инвентарь...");
		properSystem(false);
	}
	private static void quitSystem() {
		System.out.println("Бай-бай");
	}
}