import java.util.List;
import java.util.Scanner;

/**
 * 主程序：控制台交互界面
 */
public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        // 循环菜单
        while (true) {
            System.out.println("\n===== 学生信息管理系统 =====");
            System.out.println("1. 添加学生");
            System.out.println("2. 根据ID查询学生");
            System.out.println("3. 查询所有学生");
            System.out.println("4. 删除学生");
            System.out.println("5. 计算成绩平均分");
            System.out.println("0. 退出系统");
            System.out.print("请选择操作（输入序号）：");

            // 处理输入异常
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // 吸收换行符
            } catch (Exception e) {
                System.out.println("输入错误！请输入数字序号！");
                scanner.nextLine(); // 清空错误输入
                continue;
            }

            // 菜单逻辑
            switch (choice) {
                case 1:
                    // 添加学生
                    System.out.println("\n----- 添加学生 -----");
                    System.out.print("请输入学生ID：");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("请输入学生姓名：");
                    String name = scanner.nextLine();
                    System.out.print("请输入学生性别：");
                    String gender = scanner.nextLine();
                    System.out.print("请输入学生班级：");
                    String className = scanner.nextLine();
                    System.out.print("请输入高数成绩：");
                    double mathScore = scanner.nextDouble();
                    System.out.print("请输入Java成绩：");
                    double javaScore = scanner.nextDouble();

                    Student student = new Student(id, name, gender, className, mathScore, javaScore);
                    boolean addSuccess = manager.addStudent(student);
                    if (addSuccess) {
                        System.out.println("学生添加成功！");
                    } else {
                        System.out.println("学生添加失败（ID重复或数据库异常）！");
                    }
                    break;

                case 2:
                    // 根据ID查询
                    System.out.println("\n----- 根据ID查询学生 -----");
                    System.out.print("请输入要查询的学生ID：");
                    int queryId = scanner.nextInt();
                    Student queryStudent = manager.queryStudentById(queryId);
                    if (queryStudent != null) {
                        System.out.println("查询结果：");
                        System.out.println("ID：" + queryStudent.getId());
                        System.out.println("姓名：" + queryStudent.getName());
                        System.out.println("性别：" + queryStudent.getGender());
                        System.out.println("班级：" + queryStudent.getClassName());
                        System.out.println("高数成绩：" + queryStudent.getMathScore());
                        System.out.println("Java成绩：" + queryStudent.getJavaScore());
                    } else {
                        System.out.println("未找到该ID的学生！");
                    }
                    break;

                case 3:
                    // 查询所有学生
                    System.out.println("\n----- 所有学生列表 -----");
                    List<Student> allStudents = manager.queryAllStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println("暂无学生数据！");
                    } else {
                        for (Student s : allStudents) {
                            System.out.println("ID：" + s.getId() + " | 姓名：" + s.getName() + " | 班级：" + s.getClassName() +
                                    " | 高数：" + s.getMathScore() + " | Java：" + s.getJavaScore());
                        }
                    }
                    break;

                case 4:
                    // 删除学生
                    System.out.println("\n----- 删除学生 -----");
                    System.out.print("请输入要删除的学生ID：");
                    int deleteId = scanner.nextInt();
                    boolean deleteSuccess = manager.deleteStudentById(deleteId);
                    if (deleteSuccess) {
                        // 新增：补充“数据已从数据库移除”的说明
                        System.out.println("学生删除成功（数据已从数据库移除）！");
                    } else {
                        System.out.println("学生删除失败（ID不存在或数据库异常）！");
                    }
                    break;

                case 5:
                    // 计算平均分
                    System.out.println("\n----- 成绩平均分 -----");
                    double mathAvg = manager.calculateMathAvgScore();
                    double javaAvg = manager.calculateJavaAvgScore();
                    System.out.println("高数平均分：" + String.format("%.2f", mathAvg));
                    System.out.println("Java平均分：" + String.format("%.2f", javaAvg));
                    break;

                case 0:
                    // 退出系统
                    System.out.println("感谢使用，系统退出！");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("无效序号！请输入0-5之间的数字！");
            }
        }
    }
}