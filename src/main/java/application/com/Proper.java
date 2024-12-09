package application.com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Proper {
    public static void properWelcome() {
        String proper =
                """
                    ⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉
                    |    WELCOME TO PROPER SYSTEM       |
                    ⌊___________________________________」
                """;
        System.out.print(proper);
        System.out.println("Добро пожаловать в меню системы 'Пропер', " + CurrentUser.personSurname + ' ' + CurrentUser.personName + ". Ваша роль в системе: " + CurrentUser.roleName + '.');
    }
    public static void properOpenSystem(boolean showMenu) throws SQLException, ClassNotFoundException {
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
            case "/Просмотреть инвентарь" -> {
                showInventory();
                properOpenSystem(false);
            }
            case "/Записать инвентарь на себя" -> {
                takeInventoryPanelOpen();
                properOpenSystem(false);
            }
            case "/Просмотреть записанный инвентарь" -> {
                showTakenInventory();
                properOpenSystem(false);
            }
            case "/Сдать инвентарь" -> {
                passInventoryPanelOpen();
                properOpenSystem(false);
            }
            case "/Просмотреть сданный инвентарь" -> {
                showPassInventory();
                properOpenSystem(false);
            }
            case "/Выйти из системы" -> quitSystem();
            case null, default -> {
                System.out.println("Ошибка ввода, повторите попытку.");
                properOpenSystem(false);
            }
        }
    }
    private static void showInventory() throws SQLException, ClassNotFoundException {
        System.out.println("Просматриваем инвентарь..");
        ResultSet inventory = InventoryService.getAllInventory();

        java.sql.ResultSetMetaData metaData = inventory.getMetaData();
        int columnCount = metaData.getColumnCount();

        System.out.println("| Название оборудования | Количество | Описание |");
        System.out.println("-------------------------------------------------");
        int stringLenght = 0;
        while (inventory.next()) {
            System.out.print("| ");

            stringLenght = 0;
            for (int i = 1; i <= columnCount; i++) {
                String string = inventory.getString(i) + " | ";
                System.out.print(string);
                stringLenght += string.length();
            }
            System.out.println();
        }
        for (int j = 1; j <= stringLenght; j++) {
            System.out.print('-');
        }
        System.out.println();

    }
    private static void takeInventoryPanelOpen() throws SQLException, ClassNotFoundException {
        showInventory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Что хотите взять: ");
        String eqipmentName = scanner.nextLine();
        System.out.print("Сколько хотите взять: ");
        int equipmentCount = Integer.parseInt(scanner.nextLine());

        try {
            InventoryService.takeInventory(eqipmentName, equipmentCount);
        } catch(SQLException exception) {

            System.out.println("Успешно. Вы можете посмотреть взятый инвентарь через команду /Просмотреть записанный инвентарь");
        } catch (Exception _) { }

    }
    private static void showTakenInventory() throws SQLException, ClassNotFoundException {
        System.out.println("Берем что-то из взятого инвентаря...");
    }
    private static void showPassInventory() throws SQLException, ClassNotFoundException {
        System.out.println("Берем что-то из сданного инвентаря...");
    }
    private static void passInventoryPanelOpen() throws SQLException, ClassNotFoundException {
        System.out.println("Сдаем инвентарь...");
    }
    private static void quitSystem() {
        System.out.println("Бай-бай");
    }
}
