package testcase;

import com.mycompany.oopbasedpayrollsystem.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class MYSQLDatabaseTest {

    @Test
    void testGetConnection() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");
        } catch (Exception e) {
            fail("Failed to establish database connection: " + e.getMessage());
        }
    }
}