package application.com;

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
        System.out.println("Добро пожаловать в меню системы 'Пропер', " + CurrentUser.personSurname + ' ' + CurrentUser.personName + '.');
    }
    public static void properOpenSystem(boolean showMenu) {
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
                properOpenSystem(false);
            }
        }
    }
    private static void showInventory() {
        System.out.println("Просматриваем инвентарь..");
        properOpenSystem(false);
    }
    private static void takeInventoryPanelOpen() {
        System.out.println("Берем что-то из инвентаря...");
        properOpenSystem(false);
    }
    private static void showTakenInventory() {
        System.out.println("Берем что-то из взятого инвентаря...");
        properOpenSystem(false);
    }
    private static void showPassInventory() {
        System.out.println("Берем что-то из сданного инвентаря...");
        properOpenSystem(false);
    }
    private static void passInventoryPanelOpen() {
        System.out.println("Сдаем инвентарь...");
        properOpenSystem(false);
    }
    private static void quitSystem() {
        System.out.println("Бай-бай");
    }
}
