package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import models.Student;
import models.ApiResponse;

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
        students.add(new Student(4, "Alex", "Carrey", 24));
        students.add(new Student(5, "Jack", "Fill", 24));

        return students;
    }
}
