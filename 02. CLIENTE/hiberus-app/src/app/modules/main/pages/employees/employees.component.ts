import { Component, OnInit } from '@angular/core';
import { Employee } from '../../models/employee.model';
import { EmployeeService } from '../../services/employee/employee.service';
import { DepartmentService } from '../../services/department/department.service';
import { Department } from '../../models/department.model';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit  {
  empleados: Employee[] = [];
  departamentosObject: Department[] = [];
  mostrarFormulario = false;

  departamentos = ['Recursos Humanos', 'TI', 'Contabilidad', 'Ventas'];

  nuevoEmpleado: Employee = new Employee();

  constructor(private employeeService: EmployeeService, private departmentService: DepartmentService) { }

  ngOnInit(): void {
    this.obtenerEmpleados();
    this.obtenerDepartamentos();
    
  }

  obtenerEmpleados(): void {
    this.employeeService.getAll().subscribe(
      (data: any) => {
        this.empleados = data;
      },
      (error) => {
        console.error('Error al obtener datos', error);
      }
    );
  }

  obtenerDepartamentos(): void {
    this.departmentService.getAll().subscribe(
      (data: any) => {
        this.departamentosObject = data;
      },
      (error) => {
        console.error('Error al obtener datos', error);
      }
    );
  }

  guardarEmpleado(): void {
    if (!this.nuevoEmpleado.name || !this.nuevoEmpleado.last_name || !this.nuevoEmpleado.age || !this.nuevoEmpleado.salary || !this.nuevoEmpleado.init_date || !this.nuevoEmpleado.end_date || !this.nuevoEmpleado.status) {
      //this.notificacion.mostrarNotificacion('Factor','validacion');
      return;
    }

    this.employeeService.add(this.nuevoEmpleado.departamento, this.nuevoEmpleado).subscribe(
      (response) => {
        this.obtenerEmpleados();
        this.cerrarFormulario();
      },
      (error) => {
        console.error('Error al guardar el factor de riesgo:', error);
      }
    );
  }

  /*guardarEmpleado() {
    const nuevo = {
      ...this.nuevoEmpleado
    };

    this.empleados.push(nuevo);
    this.cerrarFormulario();
  }*/

  cerrarFormulario() {
    this.mostrarFormulario = false;
    this.nuevoEmpleado = new Employee();
  }
}
