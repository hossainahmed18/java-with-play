package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import models.Student;
import models.ApiResponse;

@Singleton
public class StudentsController extends Controller {

    @Inject
    public StudentsController() {
    }

    public Result getStudents() {
        List<Student> students = generateSampleStudents();

        Workbook workbook = createExcelWorkbook(students);
        String excelFileBase64 = workbookToBase64(workbook);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());
        String sanitizedDate = formattedDate.replaceAll("[:T-]", "_");
        String fileName = sanitizedDate + "_students.xlsx";
        return ok(Json.toJson(new ApiResponse(students.size(), excelFileBase64, fileName)));
    }

    private Workbook createExcelWorkbook(List<Student> students) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"First Name", "Last Name", "Age", "ID"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        for (int i = 0; i < students.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Student student = students.get(i);

            Cell firstNameCell = row.createCell(0);
            firstNameCell.setCellValue(student.getFirstName());

            Cell lastNameCell = row.createCell(1);
            lastNameCell.setCellValue(student.getLastName());

            Cell ageCell = row.createCell(2);
            ageCell.setCellValue(student.getAge());

            Cell idCell = row.createCell(3);
            idCell.setCellValue(student.getId());
        }

        return workbook;
    }
    private String workbookToBase64(Workbook workbook) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            return Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error converting workbook to base64", e);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
            }
        }
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
