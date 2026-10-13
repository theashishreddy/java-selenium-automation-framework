package com.ashish.qa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseValidationTest {
    @Test
    public void orderRecordShouldMatchExpectedValues() throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:orders;DB_CLOSE_DELAY=-1");
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE orders (order_id INT PRIMARY KEY, customer_name VARCHAR(80), total DECIMAL(10,2), status VARCHAR(20))");
            statement.execute("INSERT INTO orders VALUES (1001, 'Demo Customer', 1499.50, 'PAID')");

            try (ResultSet rs = statement.executeQuery(
                    "SELECT customer_name, total, status FROM orders WHERE order_id = 1001")) {
                Assert.assertTrue(rs.next(), "Expected order row was not found");
                Assert.assertEquals(rs.getString("customer_name"), "Demo Customer");
                Assert.assertEquals(rs.getBigDecimal("total").toPlainString(), "1499.50");
                Assert.assertEquals(rs.getString("status"), "PAID");
                Assert.assertFalse(rs.next(), "Expected exactly one matching order row");
            }
        }
    }
}
