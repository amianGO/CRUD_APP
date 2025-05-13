package com.app_crud.model;

import java.time.LocalDate;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Familia {
    private int id;
    private Funcionario funcionario;
    private String nombre;
    private String parentesco;
    private String sexo;
    private LocalDate fechaNacimiento;
    
    public Familia() {
    }

    public Familia(int id, Funcionario funcionario, String nombre, String parentesco, String sexo,
            LocalDate fechaNacimiento) {
        this.id = id;
        this.funcionario = funcionario;
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Metodos de Propiedad para el TableView
    public StringProperty nombreProperty(){
        return new SimpleStringProperty(nombre);
    }

    public StringProperty parentescoProperty(){
        return new SimpleStringProperty(parentesco);
    }

    public StringProperty sexoProperty(){
        return new SimpleStringProperty(sexo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    
}
