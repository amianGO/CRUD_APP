package com.app_crud.controller;

import com.app_crud.dao.FuncionarioDAO;
import com.app_crud.model.Funcionario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormFuncionarioController {

    private FuncionarioController funcionarioController;

    

    public void setFormFuncionarioController(FuncionarioController controller) {
        this.funcionarioController = controller;
    }

    @FXML private TextField txtNombre, txtNumId, txtTelefono, txtDireccion, txtApellido;
    @FXML private ComboBox<String> comboTipoId, comboEstadoCivil, comboSexo;
    @FXML private DatePicker fechaNacimiento;


    @FXML
    public void initialize(){
        comboTipoId.getItems().addAll("CC","TI","CE");
        comboEstadoCivil.getItems().addAll("Soltero", "Casado", "Viudo", "Divorciado");
        comboSexo.getItems().addAll("Masculino", "Femenino");
    }

    public void guardarFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setNombre(txtNombre.getText());
        funcionario.setApellido(txtApellido.getText());
        funcionario.setTipoId(comboTipoId.getValue());
        funcionario.setNumId(Integer.parseInt(txtNumId.getText()));
        funcionario.setTelefono(txtTelefono.getText());
        funcionario.setDireccion(txtDireccion.getText());
        funcionario.setFechaNacimiento(fechaNacimiento.getValue());
        funcionario.setEstadoCivil(comboEstadoCivil.getValue());
        funcionario.setSexo(comboSexo.getValue());
        System.out.println("Guardando funcionario: " + txtNombre.getText());

        FuncionarioDAO dao = new FuncionarioDAO();
        boolean guardado = dao.agregarFuncionario(funcionario);

        if (guardado) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Funcionario Guardado Exitosamente");
            alert.showAndWait();

            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.close();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hubo un error al guardadr.");
            alert.showAndWait();
        }


        
    }
}
