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

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
   // this.loadStudents();
  }
  loadStudents() {
    this.apiService.getStudents().subscribe((studentsNameFromApi: string) => {
      this.studentsName = studentsNameFromApi;
    })
  }
}