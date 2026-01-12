import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生业务管理类：封装所有数据库操作
 */
public class StudentManager {
    // 数据库连接对象
    private DatabaseConnection dbConnection = new DatabaseConnection();

    /**
     * 添加学生信息
     * @param student 学生对象
     * @return 成功返回true，失败返回false
     */
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students(id, name, gender, class_name, math_score, java_score) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getClassName());
            pstmt.setDouble(5, student.getMathScore());
            pstmt.setDouble(6, student.getJavaScore());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据ID查询单个学生
     * @param studentId 学生ID
     * @return 找到返回Student对象，未找到返回null
     */
    public Student queryStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("class_name"),
                        rs.getDouble("math_score"),
                        rs.getDouble("java_score")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询所有学生
     * @return 学生列表
     */
    public List<Student> queryAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("class_name"),
                        rs.getDouble("math_score"),
                        rs.getDouble("java_score")
                );
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    /**
     * 根据ID删除学生
     * @param studentId 学生ID
     * @return 成功返回true，失败返回false
     */
    public boolean deleteStudentById(int studentId) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 计算所有学生的高数平均分
     * @return 高数平均分
     */
    public double calculateMathAvgScore() {
        String sql = "SELECT AVG(math_score) AS avg_math FROM students";
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("avg_math");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * 计算所有学生的Java平均分
     * @return Java平均分
     */
    public double calculateJavaAvgScore() {
        String sql = "SELECT AVG(java_score) AS avg_java FROM students";
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("avg_java");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}