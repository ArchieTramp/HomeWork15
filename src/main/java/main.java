import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "20612")) {
            JDBC.newJDBC(connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM boardgame WHERE price >= ?")) {
                preparedStatement.setInt(1, 2500);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.print("id = " + resultSet.getInt("id") + " ");
                        System.out.print("name = " + resultSet.getString("name") + " ");
                        System.out.print("price = " + resultSet.getInt("price") + " ");
                        System.out.print("creator = " + resultSet.getString("creator"));
                        System.out.println();
                    }
                }
            }
            connection.setAutoCommit(false);
            Integer[] localId = new Integer[]{2, 3};
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "update parts set price = 150 where id = ?")) {
                for (Integer arg : localId) {
                    preparedStatement.setInt(1, arg);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
            Integer[] localID2 = new Integer[]{1, 2};
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "update skilltoys set forage = 12 where id = ?")) {
                for (Integer arg2 : localID2) {
                    preparedStatement.setInt(1, arg2);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
            try (Statement statement = connection.createStatement()) {
                for (int i = 1; i < 3; i++) {
                    statement.executeUpdate(
                            "insert into boardgame (name, price, creator)\n"
                                    + "values\n"
                                    + "('Evolution " + i + "', " + i * 250 + ", 'True Games');"
                    );
                }
                Savepoint savepoint = connection.setSavepoint();
                for (int i = 3; i < 5; i++) {
                    statement.executeUpdate("insert into boardgame (name, price, creator)\n"
                            + "values\n"
                            + "('Evolution " + i + "', " + i * 250 + ", 'True Games');");
                }
//                connection.rollback(savepoint);

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }

            connection.commit();
            connection.setAutoCommit(true);


        }
    }
}