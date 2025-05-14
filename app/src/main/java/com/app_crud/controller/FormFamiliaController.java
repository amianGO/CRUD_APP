package com.app_crud.controller;

import com.app_crud.dao.FamiliaDAO;
import com.app_crud.model.Familia;
import com.app_crud.model.Funcionario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormFamiliaController {
    
    private UpdateFuncionarioController updateFuncionarioController;

    public void setFuncionarioController(UpdateFuncionarioController controller){
        this.updateFuncionarioController = controller;
    }

    public void setFuncionario(Funcionario funcionario){
        this.funcionario = funcionario;
    }

    @FXML private TextField txtNombre, txtParetesco;
    @FXML private ComboBox<String> comboSexo;
    @FXML private DatePicker fechaNacimiento;
    private Funcionario funcionario;

    @FXML
    public void initialize(){
        comboSexo.getItems().addAll("Masculino", "Femenino");
    }

    public void guardarFamiliar(){
        Familia familia = new Familia();
        familia.setFuncionario(funcionario);
        familia.setNombre(txtNombre.getText());
        familia.setParentesco(txtParetesco.getText());
        familia.setSexo(comboSexo.getValue());
        familia.setFechaNacimiento(fechaNacimiento.getValue());

        System.out.println("Guardando Familiar " + txtNombre.getText());

        FamiliaDAO familiaDAO = new FamiliaDAO();
        boolean guardado = familiaDAO.agregarFamiliar(funcionario, familia);

        if (guardado) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Familia Guardada Exitosamente");
            alert.showAndWait();

            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.close();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hubo un error al guardadar.");
            alert.showAndWait();
        }
    }
}
