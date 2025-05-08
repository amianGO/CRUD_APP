package com.app_crud.controller;

import com.app_crud.model.Funcionario;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormFuncionarioController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private ComboBox<String> comboTipoId;
    @FXML private TextField txtNumId;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private DatePicker fechaNacimiento;
    @FXML private ComboBox<String> comboEstadoCivil;
    @FXML private ComboBox<String> comboSexo;

    @FXML
    public void initialize(){
        comboTipoId.getItems().addAll("CC","TI","CE");
        comboEstadoCivil.getItems().addAll("Soltero", "Casado", "Viudo", "Divorciado");
        comboSexo.getItems().addAll("Masculino", "Femenino");
    }

    public void guardarFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNombre(txtNombre.getText());
        
        System.out.println("Guardando funcionario: " + txtNombre.getText());

        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
