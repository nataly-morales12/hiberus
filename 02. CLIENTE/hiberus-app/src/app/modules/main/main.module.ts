import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { EmployeesComponent } from './pages/employees/employees.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FuncionesComponent } from './pages/funciones/funciones.component';


@NgModule({
  declarations: [
    EmployeesComponent,
    FuncionesComponent
  ],
  imports: [
    CommonModule,
    MainRoutingModule,
    FormsModule,
    HttpClientModule
  ]
})
export class MainModule { }
