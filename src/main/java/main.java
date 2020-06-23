/**
 * Основной метод для работы с JDBC
 *
 * @author Artur Gilyazov
 */


import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;


/**
 * main класс в котром все и происходит, не стал разбрасывать по разным
 */
public class main {

    private static final Logger logger = LoggerFactory.getLogger(main.class.getName());
    public static void main(String[] args) {

    try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "20612")) {
            JDBC.newJDBC(connection);
            AddToStore.addToStore(connection);
            FilterSorter.filterSorter(connection);
            AddToBucket.addToBucket(connection);

        } catch (SQLException throwables) {
        logger.error("SQLException");
    }
    }
}

