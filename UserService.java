import java.sql.Connection;
import java.sql.Statement;

public class UserService {

    public void getUser(Connection con, String id) throws Exception {

        Statement stmt = con.createStatement();

        String query = "SELECT * FROM users WHERE id='" + id + "'";

        stmt.executeQuery(query); // SQL Injection
    }

    public void savePassword(String password) {

        System.out.println(password); // Sensitive Information Logging

    }
}
