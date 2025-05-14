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

    private Familia familiaSeleccionado;

    public void setFamiliarSeleccionado(Familia familia){
        this.familiaSeleccionado = familia;
        cargarCampos();
    }

    public void setFuncionarioController(UpdateFuncionarioController controller){
        this.updateFuncionarioController = controller;
    }

    public void setFuncionario(Funcionario funcionario){
        this.funcionario = funcionario;
    }

    @FXML private TextField txtNombre, txtParentesco;
    @FXML private ComboBox<String> comboSexo;
    @FXML private DatePicker fechaNacimiento;
    private Funcionario funcionario;
    

    @FXML
    public void initialize(){
        comboSexo.getItems().addAll("Masculino", "Femenino");
    }

    public void guardarFamiliar(){
        FamiliaDAO familiaDAO = new FamiliaDAO();

        if (familiaSeleccionado != null) {
            //Modo Edicion
            familiaSeleccionado.setNombre(txtNombre.getText());
            familiaSeleccionado.setParentesco(txtParentesco.getText());
            familiaSeleccionado.setSexo(comboSexo.getValue());
            familiaSeleccionado.setFechaNacimiento(fechaNacimiento.getValue());

            boolean actualizado = familiaDAO.editFamiliar(familiaSeleccionado);

            if (actualizado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Familiar Actualizado" + txtNombre.getText());
                alert.showAndWait();
            }
        } else {
            Familia familia = new Familia();
            familia.setFuncionario(funcionario);
            familia.setNombre(txtNombre.getText());
            familia.setParentesco(txtParentesco.getText());
            familia.setSexo(comboSexo.getValue());
            familia.setFechaNacimiento(fechaNacimiento.getValue());

            System.out.println("Guardando Familiar " + txtNombre.getText());

            
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
        
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();

    }

    private void cargarCampos(){
        txtNombre.setText(familiaSeleccionado.getNombre());
        txtParentesco.setText(familiaSeleccionado.getParentesco());
        comboSexo.setValue(familiaSeleccionado.getSexo());
        fechaNacimiento.setValue(familiaSeleccionado.getFechaNacimiento());   
    }
}
