import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee/employee.service';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-funciones',
  templateUrl: './funciones.component.html',
  styleUrls: ['./funciones.component.css']
})
export class FuncionesComponent implements OnInit {
  data: Employee = new Employee();
  numero: number = 0;

  constructor(private employeeService: EmployeeService) { }
  
  ngOnInit(): void {
    
  }

  obtenerSalarioMasAlto(): void {
    this.numero = 0;
    this.employeeService.getHighestSalary().subscribe(
      (data: any) => {
        this.data = data;
      },
      (error) => {
        console.error('Error al obtener datos', error);
      }
    );
  }

  obtenerEmpleadoJoven(): void {
    this.numero = 0;
    this.employeeService.getLowerAge().subscribe(
      (data: any) => {
        this.data = data;
      },
      (error) => {
        console.error('Error al obtener datos', error);
      }
    );
  }

  obtenerUltimoMes(): void {
    this.data = new Employee();
    this.employeeService.getCountLastMonth().subscribe(
      (data: any) => {
        this.numero = Number(data.message);
      },
      (error) => {
        console.error('Error al obtener datos', error);
      }
    );
  }

}
