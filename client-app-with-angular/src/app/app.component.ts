import { Component, OnInit } from '@angular/core';
import { ApiService } from './services/api.services';
import { ApiResponseForStudents } from './interfaces/ApiResponseForStudents';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'client-app-angular';
  studentsRecords: ApiResponseForStudents | undefined;
  loadingDisabled = false;
 
  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
  }
  loadStudents() {
    this.apiService.getStudents().subscribe((apiResponseForStudents: ApiResponseForStudents) => {
      this.studentsRecords = apiResponseForStudents;
      this.loadingDisabled = true;
    })
  }

  public downloadCsvFile(): void {

    if (this.studentsRecords) {
      const csvContent = atob(this.studentsRecords.csvfileAsBase64);
      const blob = new Blob([csvContent], { type: 'text/csv' });
      const downloadLink = document.createElement('a');
      downloadLink.href = URL.createObjectURL(blob);
      downloadLink.download = this.studentsRecords.fileName;
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
      this.loadingDisabled = false;
      this.studentsRecords = undefined;
    }

  }
}