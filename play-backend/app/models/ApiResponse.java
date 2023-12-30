// models/ApiResponse.java
package models;

import java.util.List;

public class ApiResponse {
    private int numberOfStudents;
    private String csvFileAsBase64;
    private String fileName;

    public ApiResponse(int numberOfStudents, String csvFileAsBase64, String fileName) {
        this.numberOfStudents = numberOfStudents;
        this.csvFileAsBase64 = csvFileAsBase64;
        this.fileName = fileName;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCSVFileAsBase64() {
        return csvFileAsBase64;
    }

    public String getFileName() {
        return fileName;
    }
}