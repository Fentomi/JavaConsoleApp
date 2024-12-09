package application.com;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Попытка входа в систему 'Пропер'..");
		boolean isAuthorize = Authorization.authorization(1);
		if (isAuthorize) {
			Proper.properWelcome();
			Proper.properOpenSystem(true);
		}
	}
}