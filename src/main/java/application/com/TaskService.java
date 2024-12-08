package application.com;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskService {
    public static String getTasksForUser(int userId) throws SQLException, ClassNotFoundException {
        ResultSet table = DatabaseController.put(
                "select jsonb_agg(json_build_object('task_id', task_id,'task_name', task_name,'task_status', task_status)) from todo_task where user_id=" + userId + ";");
        String result = "";
        while (table.next()) {
            for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
                result = table.getString(i);
            }
        }
        return result;
    }
}