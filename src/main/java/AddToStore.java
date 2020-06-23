import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddToStore {
    private static final Logger logger = LoggerFactory.getLogger(AddToStore.class.getName());
    public static void addToStore(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO boardgame (name, price, creator)\n"
                    + "VALUES\n"
                    + "('Evo', 150, 'HWG')"
            );

            } catch (SQLException throwables) {
            logger.error("SQLException");
        }
    }
}
