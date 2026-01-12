import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        // 命令行菜单
        System.out.println("===== 学生管理系统 =====");
        System.out.println("1. 添加学生信息");
        System.out.println("2. 根据ID查询学生");
        System.out.println("3. 显示所有学生");
        System.out.println("4. 计算科目平均分");
        System.out.println("5. 退出系统");
        System.out.println("========================");

        while (isRunning) {
            System.out.print("\n请输入操作编号：");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 吸收换行符

            switch (choice) {
                case 1:
                    // 1. 添加学生
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
                    scanner.nextLine(); // 吸收换行符

                    Student student = new Student();
                    student.setName(name);
                    student.setGender(gender);
                    student.setClassName(className);
                    student.setMathScore(mathScore);
                    student.setJavaScore(javaScore);

                    boolean success = manager.addStudent(student);
                    System.out.println(success ? "添加成功！" : "添加失败！");
                    break;

                case 2:
                    // 2. 根据ID查询
                    System.out.print("请输入要查询的学生ID：");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Student queryStudent = manager.getStudentById(id);
                    if (queryStudent != null) {
                        System.out.println("查询结果：");
                        System.out.println("ID：" + queryStudent.getStudentId());
                        System.out.println("姓名：" + queryStudent.getName());
                        System.out.println("性别：" + queryStudent.getGender());
                        System.out.println("班级：" + queryStudent.getClassName());
                        System.out.println("高数成绩：" + queryStudent.getMathScore());
                        System.out.println("Java成绩：" + queryStudent.getJavaScore());
                    }
                    break;

                case 3:
                    // 3. 显示所有学生
                    System.out.println("所有学生信息：");
                    List<Student> students = manager.getAllStudents();
                    for (Student s : students) {
                        System.out.printf("ID：%d，姓名：%s，班级：%s，高数：%.1f，Java：%.1f\n",
                                s.getStudentId(), s.getName(), s.getClassName(),
                                s.getMathScore(), s.getJavaScore());
                    }
                    break;

                case 4:
                    // 4. 计算平均分
                    System.out.println("科目平均分：");
                    manager.calculateAverageScore();
                    break;

                case 5:
                    // 5. 退出系统
                    System.out.println("系统已退出！");
                    isRunning = false;
                    break;

                default:
                    System.out.println("无效的操作编号，请重新输入！");
            }
        }
        scanner.close();
    }
}