import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接管理工具类
 * 负责获取、关闭 MySQL 数据库连接
 */
public class DatabaseConnection {
    // 数据库连接配置（根据你的 MySQL 环境修改）
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; // 你的 MySQL 用户名
    private static final String PASSWORD = "123456"; // 你的 MySQL 密码

    // 私有化构造方法，防止外部创建实例（单例模式）
    private DatabaseConnection() {}

    /**
     * 获取数据库连接
     * @return Connection 数据库连接对象
     * @throws SQLException 连接失败时抛出异常
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // MySQL 8.x 无需手动加载驱动（Driver 类会自动注册）
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("数据库连接失败！");
            e.printStackTrace();
            throw e; // 抛出异常让调用方处理
        }
        return conn;
    }

    /**
     * 关闭数据库资源（ResultSet + PreparedStatement + Connection）
     * @param rs 结果集对象
     * @param pstmt 预处理语句对象
     * @param conn 连接对象
     */
    public static void closeResources(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        // 关闭结果集
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭预处理语句
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭连接
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重载：关闭数据库资源（PreparedStatement + Connection）（无结果集时使用）
     * @param pstmt 预处理语句对象
     * @param conn 连接对象
     */
    public static void closeResources(PreparedStatement pstmt, Connection conn) {
        closeResources(null, pstmt, conn);
    }

    // 测试连接是否正常
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("数据库连接成功！");
            }
        } catch (SQLException e) {
            System.err.println("数据库连接失败！");
            e.printStackTrace();
        }
    }
}