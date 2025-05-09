package com.app_crud.model;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario {
    private int id;
    private String tipoId;
    private IntegerProperty numId = new SimpleIntegerProperty();
    private String nombre;
    private String apellido;
    private String estadoCivil;
    private String sexo;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
    
    private List<Familia> familia;

    //Metodos de propiedad para TableView
    public StringProperty nombreProperty(){
        return new SimpleStringProperty(nombre);
    }

    public StringProperty apellidoProperty(){
        return new SimpleStringProperty(apellido);
    }

    public IntegerProperty identificacionProperty(){
        return numId;
    }

    public StringProperty estadoProperty(){
        return new SimpleStringProperty(estadoCivil);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipoId() {
        return tipoId;
    }
    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public int getNumId(){
        return numId.get();
    }

    public void setNumId(int numId){
        this.numId.set(numId);
    }

    public IntegerProperty getNumIdProperty() {
        return numId;
    }
    public void setNumIdProperty(IntegerProperty numId) {
        this.numId = numId;
    }



    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEstadoCivil() {
        return estadoCivil;
    }
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public List<Familia> getFamilia() {
        return familia;
    }
    public void setFamilia(List<Familia> familia) {
        this.familia = familia;
    }
    public Funcionario(int id, String tipoId, IntegerProperty numId, String nombre, String apellido, String estadoCivil,
            String sexo, String direccion, String telefono, LocalDate fechaNacimiento, List<Familia> familia) {
        this.id = id;
        this.tipoId = tipoId;
        this.numId = numId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.familia = familia;
    }

    public Funcionario() {
    }

    
}
