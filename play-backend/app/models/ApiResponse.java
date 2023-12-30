// models/ApiResponse.java
package models;

import java.util.List;

public class ApiResponse {
    private int count;
    private List<Student> students;
    private String csvFileBase64;

    public ApiResponse(int count, List<Student> students, String csvFileBase64) {
        this.count = count;
        this.students = students;
        this.csvFileBase64 = csvFileBase64;
    }

    public int getCount() {
        return count;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getCsvFileBase64() {
        return csvFileBase64;
    }
}