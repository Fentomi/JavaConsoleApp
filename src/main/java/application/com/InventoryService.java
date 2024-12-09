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
    public static void takeInventory(String equipmentName, Integer equipmentCount) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select inv.inventory_id from inventory inv, equipment eq where inv.equipment_id = eq.equipment_id and eq.equipment_name = '"+equipmentName+"';";
        ResultSet table = DatabaseController.select(sqlCommand);
        table.beforeFirst();
        table.next();
        int inventoryId = Integer.parseInt(table.getString("inventory_id"));

        sqlCommand = "insert into taken_inventory(person_id, inventory_id, equipment_count) values ("+CurrentUser.personId+", "+inventoryId+", "+equipmentCount+");";
        DatabaseController.insert(sqlCommand);
    }
    public static void reduceCountInventory(String equipmentName, Integer equipmentTakenCount) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select inv.inventory_id, inv.equipment_count from inventory inv, equipment eq where inv.equipment_id = eq.equipment_id and eq.equipment_name = '"+equipmentName+"';";
        ResultSet table = DatabaseController.select(sqlCommand);
        table.beforeFirst();
        table.next();
        int inventoryId = Integer.parseInt(table.getString("inventory_id"));
        int equipmentTableCount = Integer.parseInt(table.getString("equipment_count"));


    }
}
