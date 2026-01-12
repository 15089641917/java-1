/**
 * 学生实体类：封装学生信息
 */
public class Student {
    // 字段匹配数据库表结构
    private int id;
    private String name;
    private String gender;
    private String className;
    private double mathScore;
    private double javaScore;

    // 全参构造函数
    public Student(int id, String name, String gender, String className, double mathScore, double javaScore) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.mathScore = mathScore;
        this.javaScore = javaScore;
    }

    // 所有字段的getter方法（供外部调用）
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getClassName() {
        return className;
    }

    public double getMathScore() {
        return mathScore;
    }

    public double getJavaScore() {
        return javaScore;
    }
}