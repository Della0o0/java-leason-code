package annieholo.top;

import annieholo.top.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {
    //
    /**
     *
     *
     */
    @Test
    public void testUpdate() throws Exception{
        // 1注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2获取数据库链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web01", "root", "123456");


        // 3获取SQL语句执行对象

        Statement statement = connection.createStatement();
        // 4执行SQL

        int i = statement.executeUpdate("update user set age = 80 where id = 1");
        System.out.println("SQL执行完毕影响的记录数为：" + i);

        // 5释放资源

        statement.close();
        connection.close();
    }

    @Test
    public void testSelect(){
        String URL = "jdbc:mysql://localhost:3306/web01";
        String USER = "root";
        String PASSWORD = "123456";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT id, username, name, password, age FROM user WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, "daqiao");
            stmt.setString(2, "123456");

            rs = stmt.executeQuery();

            while (rs.next()){
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getInt("age")
                );
                System.out.println(user);
            }
        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
    }
}

