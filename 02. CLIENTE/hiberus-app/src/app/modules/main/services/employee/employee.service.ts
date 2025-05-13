import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../../models/employee.model';
import { Message } from '../../dto/messaje.dto';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8081/employee';

  constructor(private http: HttpClient) { }

  add(id: number, dato: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.baseUrl}/create/${id}`, dato);
  }

  delete(codigo: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${codigo}`);
  }

  getHighestSalary(): Observable<Employee> {
    return this.http.get<Employee>(`${this.baseUrl}/highestSalary`);
  }

  getLowerAge(): Observable<Employee> {
    return this.http.get<Employee>(`${this.baseUrl}/lowerAge`);
  }

  getCountLastMonth(): Observable<Message> {
    return this.http.get<Message>(`${this.baseUrl}/countLastMonth`);
  }
  getAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/getAll`);
  }
}
