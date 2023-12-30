
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiBaseUrl = "http://localhost:9000" 

  httpOptions = {
    headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
    }),
    responseType: 'text' as 'json', 
  };
  constructor(private http: HttpClient) {}
  getStudents(){
    return this.http.get<string>(this.apiBaseUrl+'/students', this.httpOptions);
  }
}