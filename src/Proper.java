import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Proper {
    private static final InventoryService inventory = new InventoryService();

    public static void properWelcome() {
        String proper =
                """
                    ⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉
                    |    WELCOME TO PROPER SYSTEM       |
                    ⌊___________________________________」
                """;
        System.out.print(proper);
        System.out.println("Добро пожаловать в меню системы 'Пропер', " + CurrentUser.getPersonSurname() + ' ' + CurrentUser.getPersonName() + ". Ваша роль в системе: " + CurrentUser.getRoleName() + '.');
    }
    public static void properOpenSystem(boolean showMenu) throws SQLException {
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
				Команда просмотра команд:
				⌈¯¯¯¯¯¯¯¯¯¯⌉
				| /Команды |
				⌊__________」
				Команды Выхода из системы:
				⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉ ⌈¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯⌉
				| /Выйти из системы | | /Разлогиниться    |
				⌊___________________」⌊___________________」 
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
                passInventory();
                properOpenSystem(false);
            }
            case "/Команды" -> properOpenSystem(true);
            case "/Выйти из системы" -> quitSystem();
            case "/Разлогиниться" -> logout();
            case null, default -> {
                System.out.println("Ошибка ввода, повторите попытку.");
                properOpenSystem(false);
            }
        }
    }
    private static void showInventory() throws SQLException {
        System.out.println("Просматриваем инвентарь..");
        ResultSet table = inventory.getAllInventory();

        java.sql.ResultSetMetaData metaData = table.getMetaData();
        int columnCount = metaData.getColumnCount();

        System.out.println("| Название оборудования | Количество | Описание |");
        System.out.println("-------------------------------------------------");
        int stringLenght = 0;
        while (table.next()) {
            System.out.print("| ");

            stringLenght = 0;
            for (int i = 1; i <= columnCount; i++) {
                String string = table.getString(i) + " | ";
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
    private static void takeInventoryPanelOpen() throws SQLException {
        showInventory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Что хотите взять: ");
        String equipmentName = scanner.nextLine();
        System.out.print("Сколько хотите взять: ");
        int equipmentCount = Integer.parseInt(scanner.nextLine());

        try {
            Integer availableCountInventory = inventory.getCountInventoryByName(equipmentName);
            if (equipmentCount > availableCountInventory) {
                System.out.println("[ERROR] Не удалось взять инвентарь. Количество инвентаря, что вы хотите взять, больше имеющегося.");
                return;
            }

            inventory.takeInventory(equipmentName, equipmentCount);
            inventory.reduceCountInventory(equipmentName, equipmentCount);
            System.out.println("Успешно. Вы можете посмотреть взятый инвентарь через команду /Просмотреть записанный инвентарь");
        } catch (SQLException exception) {
            System.out.println("[ERROR] Не удалось взять инвентарь. Пожалуйста, перепроверьте введенные данные.");
        }
    }
    private static void showTakenInventory() throws SQLException {
        System.out.println("Посмотрим на то, что я взял..");

        ResultSet table = inventory.getTakenInventory();
        java.sql.ResultSetMetaData metaData = table.getMetaData();
        int columnCount = metaData.getColumnCount();

        System.out.println("| Айди | Название оборудования | Количество |");
        System.out.println("---------------------------------------------");
        int stringLenght = 0;
        while (table.next()) {
            System.out.print("| ");

            stringLenght = 0;
            for (int i = 1; i <= columnCount; i++) {
                String string = table.getString(i) + " | ";
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
    private static void passInventory() throws SQLException {
        try {
            System.out.println("Посмотрим, что можно сдать..");
            showTakenInventory();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Что хотите сдать (Введите Айди): ");
            String takenInventoryId = scanner.nextLine();

            ResultSet table = inventory.getTakenInventoryInfo(takenInventoryId);
            int equipmentTakenCount = Integer.parseInt(table.getString("equipment_count"));
            String equipmentName = table.getString("equipment_name");

            inventory.passInventory(takenInventoryId);
            inventory.increaseCountInventory(equipmentName, equipmentTakenCount);
            System.out.println("Успешно. Вы можете посмотреть списанный инвентарь через команду /Просмотреть записанный инвентарь");
        } catch (SQLException exception) {
            System.out.println("[ERROR] Не удалось сдать инвентарь. Пожалуйста, перепроверьте введенные данные.");
        }
    }
    private static void quitSystem() {
        System.out.println("Бай-бай");
    }
    private static void logout() throws SQLException {
        String[] args = {""};
        Main.main(args);
    }
}
