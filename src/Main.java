import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Вы желаете войти в систему 'Пропер'? (y/n) ");
		String answer = scanner.nextLine();
		if(Objects.equals(answer, "y")) {
			boolean isAuthorize = Authorization.authorization();
			if (isAuthorize) {
				Proper.properWelcome();
				Proper.properOpenSystem(true);
			}
		}
	}
}