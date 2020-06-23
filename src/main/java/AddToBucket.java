import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddToBucket {
    private static final Logger logger = LoggerFactory.getLogger(AddToBucket.class.getName());

    public static void addToBucket(Connection connection) {
        try {
            PreparedStatement preparedStatementt = connection.prepareStatement(
                    "UPDATE parts SET price = ? WHERE id = ?");
                preparedStatementt.setInt(1, 99);
                preparedStatementt.setInt(2,2);


        } catch (SQLException throwables) {
            logger.error("SQLException");
        }
    }
}
