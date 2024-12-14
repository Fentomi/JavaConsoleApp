import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		System.out.println("Попытка входа в систему 'Пропер'..");
		boolean isAuthorize = Authorization.authorization();
		if (isAuthorize) {
			Proper.properWelcome();
			Proper.properOpenSystem(true);
		}
	}
}