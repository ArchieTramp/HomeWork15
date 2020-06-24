import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class FilterSorter {
    private static final Logger logger = LoggerFactory.getLogger(FilterSorter.class.getName());
    public static void filterSorter(Connection connection) {
          try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM skilltoys WHERE forage >= ?");
            preparedStatement.setInt(1, 12);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                logger.info(resultSet.getString("name") + " "
                        + resultSet.getInt("price") + " RUB " + "for "
                        + resultSet.getString("forage") + " age");
                   }
        } catch (SQLException throwables) {

              logger.error("SQLException");
        }
    }
}
