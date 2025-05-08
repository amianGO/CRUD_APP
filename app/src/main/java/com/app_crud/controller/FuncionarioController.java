package com.app_crud.controller;

import java.io.IOException;

import com.app_crud.dao.FuncionarioDAO;
import com.app_crud.model.Funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FuncionarioController {
    
    @FXML
    private TableView<Funcionario> tableViewFuncionarios;

    @FXML
    private TableColumn<Funcionario, String> colNombre, colApellido, colIdentificacion, colEstado;


    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    //Metodo para cargar los funcionarios desde la base de datos
    public void initialize(){
        ObservableList<Funcionario> funcionariosList = FXCollections.observableArrayList(
            funcionarioDAO.obtenerFuncionarios()
        );
        
        colNombre.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colApellido.setCellValueFactory(cell -> cell.getValue().apellidoProperty());
        colIdentificacion.setCellValueFactory(cell -> cell.getValue().identificacionProperty());
        colEstado.setCellValueFactory(cell -> cell.getValue().estadoProperty());

        tableViewFuncionarios.setItems(funcionariosList);

        tableViewFuncionarios.setOnMouseClicked(this::handleClick);
    
    }

    private void handleClick(MouseEvent event){
        Funcionario seleccionado = tableViewFuncionarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            System.out.println("Funcionario: " + seleccionado.getNombre());
        }
    }

    @FXML
    private void abrirFormularioFuncionario(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_funcionario.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            stage.setTitle("Registrar Funcionarios");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir el formulario: " + e.getMessage());
            alert.showAndWait();
        }
    }

    
}
