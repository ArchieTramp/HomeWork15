/**
 * Работа с JDBC
 *
 * @author Artur Gilyazov
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для постоянного создания и заполнения базы, дабы исключить ошибки, которые могут испортить базу
 */
public class JDBC {
    private JDBC() {

    }
    private static final Logger logger = LoggerFactory.getLogger(JDBC.class.getName());

    public static void newJDBC(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("-- Database: postgres\n "
                    + "DROP TABLE IF EXISTS boardgame;"
                    + "DROP TABLE IF EXISTS parts;"
                    + "DROP TABLE IF EXISTS skilltoys;"
                    + "\n"
                    + "CREATE TABLE boardgame (\n"
                    + "id bigserial primary key,\n"
                    + "name varchar(100) NOT NULL, \n"
                    + "price integer NOT NULL, \n"
                    + "creator varchar(100) NOT NULL);"
                    + "\n"
                    + "INSERT INTO boardgame (name, price, creator)\n"
                    + "VALUES\n"
                    + "('GoT', 4000, 'HW'),\n"
                    + "('Munchkin', 2000, 'SJG'),\n"
                    + "('Monopoly', 2500, 'Hasbro'),\n"
                    + "('Hive', 1000, 'gen24');"
                    + "\n"
                    + "CREATE TABLE parts (\n"
                    + "id bigserial primary key,\n"
                    + "name varchar(100) NOT NULL, \n"
                    + "price integer NOT NULL, \n"
                    + "howMany integer NOT NULL);"
                    + "\n"
                    + "INSERT INTO parts (name, price, howMany)\n"
                    + "VALUES\n"
                    + "('Mipl', 100, 500),\n"
                    + "('Dice d6', 50, 100),\n"
                    + "('Dice d20', 200, 30);"
                    + "\n"
                    + "CREATE TABLE skilltoys (\n"
                    + "id bigserial primary key,\n"
                    + "name varchar(100) NOT NULL, \n"
                    + "price integer NOT NULL, \n"
                    + "forAge integer NOT NULL);"
                    + "\n"
                    + "INSERT INTO skilltoys (name, price, forAge)\n"
                    + "VALUES\n"
                    + "('Yoyo', 500, 14),\n"
                    + "('Diabolo', 1000, 18),\n"
                    + "('Pogostick', 1200, 16);\n"
                    + "\n");

        } catch (SQLException throwables) {
            logger.error("SQLException");
        }
    }
}
