package tools;

import java.util.HashSet;
import java.util.Objects;

/*
 * 学生的数据结构，基于名字和学号具有唯一性
 */
public class Student {
    public static HashSet<Student> students = new HashSet<>();
    public String name;
    public String num;
    public int grade;

    public Student(String name, String num, int grade) {
        this.name = name;
        this.num = num;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return name.equals(student.name) && num.equals(student.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, num);
    }

    public Object[] toArray() {
        return new Object[] { num, name, String.valueOf(grade) };
    }
}
