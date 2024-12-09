package application.com;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryService {
    public static ResultSet getAllInventory() throws SQLException, ClassNotFoundException {
        String sqlCommand = """
                select eq.equipment_name, inv.equipment_count, eq.equipment_desc\s
                	from inventory inv, equipment eq\s
                	where inv.equipment_id = eq.equipment_id;
                """;
        ResultSet table = DatabaseController.select(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }

}
