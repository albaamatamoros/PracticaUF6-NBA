import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    //10.94.255.99 Santi PhpMyAdmin 3336
    //nba_2023-24
    //root mas

    //192.168.56.103 BD mysql 3306
    //perepi pastanaga
    //nba
    public static final String URL = "jdbc:mysql://192.168.56.103:3306/nba";
    public static final String USER = "perepi";
    public static final String PASSWORD = "pastanaga";
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from equips");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}