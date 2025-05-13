/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     12/5/2025 23:56:00                           */
/*==============================================================*/


alter table EMPLOYEE
   drop constraint FK_EMPLOYEE_PERTENECE_DEPARTAM;

drop table DEPARTAMENT cascade constraints;

drop index PERTENECE_TIENE_FK;

drop table EMPLOYEE cascade constraints;

/*==============================================================*/
/* Table: DEPARTAMENT                                           */
/*==============================================================*/
create table DEPARTAMENT 
(
   DEPARTAMENT_ID       INTEGER              not null,
   DEPARTAMENT_NAME     VARCHAR2(30)         not null,
   DEPARTAMENT_STATUS   CHAR(1)              not null,
   constraint PK_DEPARTAMENT primary key (DEPARTAMENT_ID)
);

/*==============================================================*/
/* Table: EMPLOYEE                                              */
/*==============================================================*/
create table EMPLOYEE 
(
   EMPLOYEE_ID          INTEGER              not null,
   DEPARTAMENT_ID       INTEGER              not null,
   EMPLOYEE_NOMBRE      VARCHAR2(20)         not null,
   EMPLOYEE_LAST_NAME   VARCHAR2(20)         not null,
   AGE                  INTEGER              not null,
   SALARY               BINARY_DOUBLE,
   ROL                  VARCHAR2(20),
   INIT_DATE            DATE,
   END_DATE             DATE,
   EMPLOYEE_STATUS      CHAR(1)              not null,
   constraint PK_EMPLOYEE primary key (EMPLOYEE_ID)
);

/*==============================================================*/
/* Index: PERTENECE_TIENE_FK                                    */
/*==============================================================*/
create index PERTENECE_TIENE_FK on EMPLOYEE (
   DEPARTAMENT_ID ASC
);

alter table EMPLOYEE
   add constraint FK_EMPLOYEE_PERTENECE_DEPARTAM foreign key (DEPARTAMENT_ID)
      references DEPARTAMENT (DEPARTAMENT_ID);
      
--SECUENCIAS
CREATE SEQUENCE UDEPARTAMENT START WITH 1 INCREMENT BY 1;
ALTER TABLE DEPARTAMENT MODIFY (DEPARTAMENT_ID DEFAULT UDEPARTAMENT.NEXTVAL);

CREATE SEQUENCE UEMPLOYEE START WITH 1 INCREMENT BY 1;
ALTER TABLE EMPLOYEE MODIFY (EMPLOYEE_ID DEFAULT UEMPLOYEE.NEXTVAL);