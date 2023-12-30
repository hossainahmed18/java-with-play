import { Component, OnInit } from '@angular/core';
import { ApiService } from './services/api.services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'client-app-angular';
  studentsName = "";
  csvFileBase64 = "Rmlyc3QgTmFtZSxMYXN0IE5hbWUsQWdlLElECkpvaG4sRG9lLDI1LDEKSmFuZSxTbWl0aCwyMiwyCkJvYixKb2huc29uLDMwLDMKQWxleCxDYXJyZXksMjQsNApKYWNrLEZpbGwsMjQsNQo=";

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
   // this.loadStudents();
   this.downloadCsvFile();
  }
  loadStudents() {
    this.apiService.getStudents().subscribe((studentsNameFromApi: string) => {
      this.studentsName = studentsNameFromApi;
    })
  }

  private downloadCsvFile(): void {
    if (!this.csvFileBase64) {
      return;
    }

    // Decode base64 to CSV content
    const csvContent = atob(this.csvFileBase64);

    // Convert CSV content to a Blob
    const blob = new Blob([csvContent], { type: 'text/csv' });

    // Create a download link
    const downloadLink = document.createElement('a');
    downloadLink.href = URL.createObjectURL(blob);
    downloadLink.download = 'students.csv';

    // Append the link to the document
    document.body.appendChild(downloadLink);

    // Trigger a click event to start the download
    downloadLink.click();

    // Remove the link from the document
    document.body.removeChild(downloadLink);
  }
}