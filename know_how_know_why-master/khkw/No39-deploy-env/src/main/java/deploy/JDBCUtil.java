package deploy;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flinkdb?characterEncoding=utf-8&useSSL=false", "root", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from abcd;");
            int row = resultSet.getRow();
            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
