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
    public static void passInventory(String takenInventoryId) throws SQLException, ClassNotFoundException {
        String sqlCommand = "delete from taken_inventory where taken_inventory_id="+takenInventoryId+";";
        DatabaseController.delete(sqlCommand);
    }
    public static void reduceCountInventory(String equipmentName, Integer equipmentTakenCount) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select inv.inventory_id, inv.equipment_count from inventory inv, equipment eq where inv.equipment_id = eq.equipment_id and eq.equipment_name = '"+equipmentName+"';";
        ResultSet table = DatabaseController.select(sqlCommand);
        table.beforeFirst();
        table.next();
        int inventoryId = Integer.parseInt(table.getString("inventory_id"));
        int equipmentTableCount = Integer.parseInt(table.getString("equipment_count"));

        sqlCommand = "update inventory set equipment_count="+String.valueOf(equipmentTableCount-equipmentTakenCount)+" where inventory_id="+inventoryId+";";
        DatabaseController.insert(sqlCommand);
    }
    public static void increaseCountInventory(String equipmentName, Integer equipmentTakenCount) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select inv.inventory_id, inv.equipment_count from inventory inv, equipment eq where inv.equipment_id = eq.equipment_id and eq.equipment_name = '"+equipmentName+"';";
        ResultSet table = DatabaseController.select(sqlCommand);
        table.beforeFirst();
        table.next();
        int inventoryId = Integer.parseInt(table.getString("inventory_id"));
        int equipmentTableCount = Integer.parseInt(table.getString("equipment_count"));

        sqlCommand = "update inventory set equipment_count="+String.valueOf(equipmentTableCount+equipmentTakenCount)+" where inventory_id="+inventoryId+";";
        DatabaseController.insert(sqlCommand);
    }
    public static ResultSet getTakenInventory() throws SQLException, ClassNotFoundException {
        String sqlCommand = "select tk_inv.taken_inventory_id, eq.equipment_name, tk_inv.equipment_count from taken_inventory tk_inv, inventory inv, equipment eq where tk_inv.inventory_id = inv.inventory_id and inv.equipment_id = eq.equipment_id and tk_inv.person_id = "+CurrentUser.personId+";";
        ResultSet table = DatabaseController.select(sqlCommand);
        table.beforeFirst();
        return table;
    }
    public static ResultSet getTakenInventoryInfo(String takenInvnetoryId) throws SQLException, ClassNotFoundException {
        String sqlCommand = "select tk_inv.equipment_count, eq.equipment_name from taken_inventory tk_inv, equipment eq, inventory inv where taken_inventory_id="+takenInvnetoryId+" and tk_inv.inventory_id=inv.inventory_id and inv.equipment_id=eq.equipment_id;";
        ResultSet table = DatabaseController.select(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
}
