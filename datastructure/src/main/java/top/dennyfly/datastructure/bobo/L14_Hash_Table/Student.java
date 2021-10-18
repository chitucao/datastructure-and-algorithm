package top.dennyfly.datastructure.bobo.L14_Hash_Table;

import java.util.Objects;

/**
 * @author DennyFly
 * @since 2021/10/17 16:40
 * 自定义hashCode和equals的实现
 */
public class Student {
    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;
        hash = hash * B + ((Integer) grade).hashCode();
        hash = hash * B + ((Integer) cls).hashCode();
        hash = hash * B + firstName.hashCode();
        hash = hash * B + lastName.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Student another = (Student) o;
        return this.grade == another.grade && this.cls == another.cls && Objects.equals(this.firstName, another.firstName) && Objects.equals(this.lastName, another.lastName);
    }
}
