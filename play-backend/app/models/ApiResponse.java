package models;
import java.util.List;

public class ApiResponse {
    private int count;
    private List<Student> students;

    public ApiResponse() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}