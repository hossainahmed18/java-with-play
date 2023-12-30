import { Component, OnInit } from '@angular/core';
import { ApiService } from './services/api.services';
import { ApiResponseForStudents } from './interfaces/ApiResponseForStudents';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
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
    });
  }

  public downloadExcelFile(): void {
    if (this.studentsRecords && this.studentsRecords.excelFileAsBase64) {
      const excelContent = atob(this.studentsRecords.excelFileAsBase64);

      const blob = new Blob([this.s2ab(excelContent)], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
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

  private s2ab(s: string): ArrayBuffer {
    const buf = new ArrayBuffer(s.length);
    const view = new Uint8Array(buf);
    for (let i = 0; i !== s.length; ++i) {
      view[i] = s.charCodeAt(i) & 0xFF;
    }
    return buf;
  }
}
