import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/appjava";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful");
        }catch(SQLException e){
            System.out.println("Connection failed");
            e.printStackTrace();
        }

        return connection;
    }
}
