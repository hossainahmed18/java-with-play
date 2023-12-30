package models;

public class ApiResponse {
    private int numberOfStudents;
    private String excelFileAsBase64;
    private String fileName;

    public ApiResponse(int numberOfStudents, String excelFileAsBase64, String fileName) {
        this.numberOfStudents = numberOfStudents;
        this.excelFileAsBase64 = excelFileAsBase64;
        this.fileName = fileName;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getExcelFileAsBase64() {
        return excelFileAsBase64;
    }

    public String getFileName() {
        return fileName;
    }
}