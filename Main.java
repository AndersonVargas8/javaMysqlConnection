import java.util.List;

class Main{
    public static void main(String[] args){
        UserDAO userDao = new UserDAO(Database.getConnection());

        userDao.createUser(new User(0, "user1", "user1@example.com", 2));
        List<UserDAO> users = userDao.getUsers();

        for (UserDAO user : users) {
            System.out.println(user.toString());
        }
    }
}