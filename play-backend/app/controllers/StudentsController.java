package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class StudentsController extends Controller {

    @Inject
    public StudentsController() {
    }

    public Result getStudents() {
        List<Student> studentsList = generateSampleStudents();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCount(studentsList.size());
        apiResponse.setStudents(studentsList);

        return ok(Json.toJson(apiResponse));
    }

    private List<Student> generateSampleStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Doe", 25));
        students.add(new Student(2, "Jane", "Smith", 22));
        students.add(new Student(3, "Bob", "Johnson", 30));

        return students;
    }
    public static class Student {
        private int id;
        private String firstName;
        private String lastName;
        private int age;

        public Student() {
        }

        public Student(int id, String firstName, String lastName, int age) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
    public static class ApiResponse {
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
}
