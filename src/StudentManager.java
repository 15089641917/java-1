import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    // 1. 添加学生信息到数据库
    public boolean addStudent(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO students(name, gender, class_name, math_score, java_score) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            // 给SQL占位符赋值
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());
            // 执行插入，返回受影响行数
            int rows = pstmt.executeUpdate();
            return rows > 0; // 插入成功返回true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeResources(pstmt, conn);
        }
    }

    // 2. 根据ID查询学生信息
    public Student getStudentById(int studentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            rs = pstmt.executeQuery();
            // 封装查询结果到Student对象
            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class_name"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResources(rs, pstmt, conn);
        }
        return student;
    }

    // 3. 显示所有学生信息
    public List<Student> getAllStudents() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // 遍历结果集，封装成Student列表
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class_name"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResources(rs, pstmt, conn);
        }
        return studentList;
    }

    // 4. 计算学生各科目平均分
    public void calculateAverageScore() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT AVG(math_score) AS math_avg, AVG(java_score) AS java_avg FROM students";

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                double mathAvg = rs.getDouble("math_avg");
                double javaAvg = rs.getDouble("java_avg");
                System.out.println("高数科目平均分：" + String.format("%.1f", mathAvg));
                System.out.println("Java科目平均分：" + String.format("%.1f", javaAvg));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResources(rs, pstmt, conn);
        }
    }

    // 测试方法（可直接运行）
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // 测试添加学生
        Student student = new Student();
        student.setName("赵六");
        student.setGender("男");
        student.setClassName("2024级软件工程2班");
        student.setMathScore(85.0);
        student.setJavaScore(90.5);
        boolean addSuccess = manager.addStudent(student);
        System.out.println("添加学生是否成功：" + addSuccess);

        // 测试查询所有学生
        System.out.println("\n所有学生信息：");
        List<Student> students = manager.getAllStudents();
        for (Student s : students) {
            System.out.println("ID：" + s.getStudentId() + "，姓名：" + s.getName() + "，班级：" + s.getClassName() + "，高数：" + s.getMathScore() + "，Java：" + s.getJavaScore());
        }

        // 测试按ID查询
        System.out.println("\n查询ID=1的学生：");
        Student s = manager.getStudentById(1);
        if (s != null) {
            System.out.println("姓名：" + s.getName() + "，性别：" + s.getGender());
        }

        // 测试计算平均分
        System.out.println("\n各科目平均分：");
        manager.calculateAverageScore();
    }
}