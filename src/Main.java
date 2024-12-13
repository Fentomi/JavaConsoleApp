import java.sql.SQLException;

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