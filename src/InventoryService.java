import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryService {
    private static final DatabaseController database = new DatabaseController();

    public Integer getCountInventoryByName(String equipmentName) {
        try {
            String sqlCommand = "select inv.equipment_count from inventory inv, equipment eq where inv.equipment_id = eq.equipment_id and eq.equipment_name = '"+equipmentName+"';";
            ResultSet table = database.select(sqlCommand);
            table.beforeFirst();
            table.next();
            return Integer.parseInt(table.getString("equipment_count"));
        } catch (SQLException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
        return 0;
    }
    public ResultSet getAllInventory() {
        try {
            String sqlCommand = """
                select eq.equipment_name, inv.equipment_count, eq.equipment_desc\s
                	from inventory inv, equipment eq\s
                	where inv.equipment_id = eq.equipment_id;
                """;
            ResultSet table = database.select(sqlCommand);
            table.beforeFirst();
            return table;
        } catch (SQLException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            return null;
        }
    }
    public void takeInventory(String equipmentName, Integer equipmentCount) throws SQLException{
        String sqlCommand = "select inv.inventory_id from inventory inv, equipment eq where inv.equipment_id = eq.equipment_id and eq.equipment_name = '"+equipmentName+"';";
        ResultSet table = database.select(sqlCommand);
        table.beforeFirst();
        table.next();
        int inventoryId = Integer.parseInt(table.getString("inventory_id"));

        sqlCommand = "insert into taken_inventory(person_id, inventory_id, equipment_count) values ("+CurrentUser.getPersonId()+", "+inventoryId+", "+equipmentCount+");";
        database.insert(sqlCommand);
    }
    public void passInventory(String takenInventoryId) throws SQLException {
        String sqlCommand = "delete from taken_inventory where taken_inventory_id="+takenInventoryId+";";
        database.delete(sqlCommand);
    }
    public void reduceCountInventory(String equipmentName, Integer equipmentTakenCount) throws SQLException {
        String sqlCommand = "select inv.inventory_id, inv.equipment_count from inventory inv, equipment eq where inv.equipment_id = eq.equipment_id and eq.equipment_name = '"+equipmentName+"';";
        ResultSet table = database.select(sqlCommand);
        table.beforeFirst();
        table.next();
        int inventoryId = Integer.parseInt(table.getString("inventory_id"));
        int equipmentTableCount = Integer.parseInt(table.getString("equipment_count"));

        sqlCommand = "update inventory set equipment_count="+(equipmentTableCount-equipmentTakenCount)+" where inventory_id="+inventoryId+";";
        database.insert(sqlCommand);
    }
    public void increaseCountInventory(String equipmentName, Integer equipmentTakenCount) throws SQLException {
        String sqlCommand = "select inv.inventory_id, inv.equipment_count from inventory inv, equipment eq where inv.equipment_id = eq.equipment_id and eq.equipment_name = '"+equipmentName+"';";
        ResultSet table = database.select(sqlCommand);
        table.beforeFirst();
        table.next();
        int inventoryId = Integer.parseInt(table.getString("inventory_id"));
        int equipmentTableCount = Integer.parseInt(table.getString("equipment_count"));

        sqlCommand = "update inventory set equipment_count="+(equipmentTableCount+equipmentTakenCount)+" where inventory_id="+inventoryId+";";
        database.insert(sqlCommand);
    }
    public ResultSet getTakenInventory() {
        try {
            String sqlCommand = "select tk_inv.taken_inventory_id, eq.equipment_name, tk_inv.equipment_count from taken_inventory tk_inv, inventory inv, equipment eq where tk_inv.inventory_id = inv.inventory_id and inv.equipment_id = eq.equipment_id and tk_inv.person_id = "+CurrentUser.getPersonId()+";";
            ResultSet table = database.select(sqlCommand);
            table.beforeFirst();
            return table;
        } catch (SQLException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            return null;
        }
    }
    public ResultSet getTakenInventoryInfo(String takenInvnetoryId) throws SQLException {
        String sqlCommand = "select tk_inv.equipment_count, eq.equipment_name from taken_inventory tk_inv, equipment eq, inventory inv where taken_inventory_id="+takenInvnetoryId+" and tk_inv.inventory_id=inv.inventory_id and inv.equipment_id=eq.equipment_id;";
        ResultSet table = database.select(sqlCommand);
        table.beforeFirst();
        table.next();
        return table;
    }
}
