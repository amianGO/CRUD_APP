package com.app_crud.controller;

import com.app_crud.dao.FuncionarioDAO;
import com.app_crud.model.Funcionario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateFuncionarioController {

    @FXML private TextField txtNombre, txtNumId, txtTelefono, txtDireccion, txtApellido;
    @FXML private ComboBox<String> comboTipoId, comboEstadoCivil, comboSexo;
    @FXML private DatePicker fechaNacimiento;

    private Funcionario funcionario;

    @FXML
    public void initialize(){
        comboTipoId.getItems().addAll("CC","TI","CE");
        comboEstadoCivil.getItems().addAll("Soltero", "Casado", "Viudo", "Divorciado");
        comboSexo.getItems().addAll("Masculino", "Femenino");
    }


    private void cargarCampos(){
        txtNumId.setText(String.valueOf(funcionario.getNumId()));
        txtNombre.setText(funcionario.getNombre());
        txtApellido.setText(funcionario.getApellido());
        txtTelefono.setText(funcionario.getTelefono());
        txtDireccion.setText(funcionario.getDireccion());
        comboTipoId.setValue(funcionario.getTipoId());
        comboEstadoCivil.setValue(funcionario.getEstadoCivil());
        comboSexo.setValue(funcionario.getSexo());
        fechaNacimiento.setValue(funcionario.getFechaNacimiento());

    }

    @FXML
    private void actualizarFuncionario(){
        funcionario.setNumId(Integer.parseInt(txtNumId.getText()));
        funcionario.setNombre(txtNombre.getText());
        funcionario.setApellido(txtApellido.getText());
        funcionario.setTelefono(txtTelefono.getText());
        funcionario.setDireccion(txtDireccion.getText());
        funcionario.setTipoId(comboTipoId.getValue());
        funcionario.setEstadoCivil(comboEstadoCivil.getValue());
        funcionario.setSexo(comboSexo.getValue());
        funcionario.setFechaNacimiento(fechaNacimiento.getValue());

        FuncionarioDAO dao = new FuncionarioDAO();
        boolean actualizado = dao.editFuncionario(funcionario);

        Alert alert = new Alert(actualizado ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(actualizado ? "Funcioanrio Actualizado Con exito" : "Error al Actualizar el Funcionario");
        alert.showAndWait();

        if (actualizado) {
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.close();
        }
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        cargarCampos();
    }

    
}
