public class Student {
    // 1. 先定义对应students表的成员属性（
    private Integer studentId;   // 学生ID
    private String name;         // 姓名
    private String gender;       // 性别
    private String className;    // 班级
    private Double mathScore;    // 高数成绩
    private Double javaScore;    // Java成绩



    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMathScore() {
        return mathScore;
    }

    public void setMathScore(Double mathScore) {
        this.mathScore = mathScore;
    }

    public Double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(Double javaScore) {
        this.javaScore = javaScore;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}