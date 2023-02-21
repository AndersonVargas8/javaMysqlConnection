import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO{
    private final Connection connection;

    private int id;
    private String name;
    private String email;
    private String role;

    public UserDAO(Connection connection){
        this.connection = connection;
    }

    public UserDAO(){
        this.connection = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String rol) {
        this.role = rol;
    }

    

    @Override
    public String toString() {
        return "UserDAO [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + "]";
    }

    /*CRUD METHODS */
    public void createUser(User user){
        String sql = "INSERT INTO users(name, email, role_id) VALUES (?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setInt(3, user.getRole_id());
                statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("User creation failed");
            e.printStackTrace();
        }
    }

    public List<UserDAO> getUsers(){
        List<UserDAO> users = new ArrayList<>();
        String sql = "SELECT u.id, u.name, u.email, r.name AS rol FROM users u INNER JOIN roles r ON u.role_id = r.id";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                UserDAO user = new UserDAO();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("rol"));
                users.add(user);
            }
        }catch(SQLException e){
            System.out.println("User retrieval failed");
            e.printStackTrace();
        }
        return users;
    }
}