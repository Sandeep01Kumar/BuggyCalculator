import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserService {

    public void getUser(Connection con, String id) throws Exception {

        // SECURITY FIX (CWE-89 SQL Injection): the caller-supplied 'id' is bound
        // as a parameter via PreparedStatement instead of being concatenated into
        // the SQL string, so it can never alter the query structure.
        String query = "SELECT * FROM users WHERE id = ?";

        // ROBUSTNESS FIX (CWE-772 Resource Leak): try-with-resources guarantees
        // the PreparedStatement is closed even if execution throws. Closing the
        // statement also closes any ResultSet it produced (JDBC contract), so the
        // result is not consumed here, preserving the method's original behavior.
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeQuery();
        }
    }

    public void savePassword(String password) {
        // SECURITY FIX (CWE-532/CWE-312 Sensitive Information Exposure): the
        // plaintext 'System.out.println(password)' was removed so the secret is
        // never written to stdout/logs. A production implementation must persist a
        // salted one-way hash (e.g., PBKDF2/BCrypt); that persistence layer is out
        // of scope for this fixture.
    }
}
