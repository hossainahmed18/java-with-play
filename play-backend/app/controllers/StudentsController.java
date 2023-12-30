package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import models.Student;
import models.ApiResponse;

@Singleton
public class StudentsController extends Controller {

    @Inject
    public StudentsController() {
    }

    public Result getStudents() {
        List<Student> students = generateSampleStudents();

        StringBuilder csvContent = new StringBuilder();
        csvContent.append("First Name,Last Name,Age,ID\n");

        for (Student student : students) {
            csvContent.append(String.format("%s,%s,%d,%d\n",
                    student.getFirstName(), student.getLastName(), student.getAge(), student.getId()));
        }
        String csvFileBase64 = Base64.getEncoder().encodeToString(csvContent.toString().getBytes(StandardCharsets.UTF_8));

        return ok(Json.toJson(new ApiResponse(students.size(), students, csvFileBase64)));
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
