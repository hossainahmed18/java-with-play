
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ApiResponseForStudents } from '../interfaces/ApiResponseForStudents';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiBaseUrl = "http://localhost:9000" 

  httpOptions = {
    headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
    }),
  };
  constructor(private http: HttpClient) {}
  getStudents(){
    return this.http.get<ApiResponseForStudents>(this.apiBaseUrl+'/students', this.httpOptions);
  }
}