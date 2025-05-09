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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


public class FuncionarioController {
    
    @FXML
    private TableView<Funcionario> tableViewFuncionarios;

    @FXML
    private TableColumn<Funcionario, String> colNombre, colApellido, colEstado;

    @FXML
    private TableColumn<Funcionario, Integer> colIdentificacion;

    @FXML
    private TableColumn<Funcionario, Void> colEliminar;


    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    //Metodo para cargar los funcionarios desde la base de datos
    public void initialize(){

        ObservableList<Funcionario> funcionariosList = FXCollections.observableArrayList(
            funcionarioDAO.obtenerFuncionarios()
        );
        
        colNombre.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colApellido.setCellValueFactory(cell -> cell.getValue().apellidoProperty());
        colIdentificacion.setCellValueFactory(cell -> cell.getValue().identificacionProperty().asObject());
        colEstado.setCellValueFactory(cell -> cell.getValue().estadoProperty());

        tableViewFuncionarios.setItems(funcionariosList);

        tableViewFuncionarios.setOnMouseClicked(this::handleClick);

        configurarColumnaEliminar();

    }

    private void configurarColumnaEliminar(){
        Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario,Void>> cellFactory = new Callback<>(){

            @Override
            public TableCell<Funcionario,Void> call(final TableColumn<Funcionario,Void> param){
                return new TableCell<>(){
                    private final Button btn = new Button("Eliminar");

                    {
                        btn.setOnAction(event -> {
                            Funcionario funcionario = getTableView().getItems().get(getIndex());
                            eliminarFuncionario(funcionario);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty){
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        colEliminar.setCellFactory(cellFactory);
    }
    

    public void eliminarFuncionario(Funcionario funcionario){
        FuncionarioDAO dao = new FuncionarioDAO();
        boolean eliminado = dao.eliminarFuncionario(funcionario.getId());

        if (eliminado) {
            tableViewFuncionarios.getItems().remove(funcionario);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Funcionario Eliminado Exitosamente");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hubo un error al Elimnar.");
            alert.showAndWait();
            System.out.println("No se pudo eliminar el Funcionario");
        }
    }

    private void handleClick(MouseEvent event){
        if (event.getClickCount() == 2) {
            Funcionario seleccionado = tableViewFuncionarios.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                abrirFormularioEditar(seleccionado);
            }
        }
    }

    private void abrirFormularioEditar(Funcionario funcionario){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit_fun_form.fxml"));
            Parent root = loader.load();

            UpdateFuncionarioController controller = loader.getController();
            controller.setFuncionario(funcionario);

            Stage stage = new Stage();
            stage.setTitle("Actualizar Funcionario");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            //Actualizamos la tabla al cerrar
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir el Formulario");
            alert.showAndWait();
        }
    }

    @FXML
    private void abrirFormularioFuncionario(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_funcionario.fxml"));
            Parent root = loader.load();

            FormFuncionarioController formController = loader.getController();
            formController.setFormFuncionarioController(this);

            Stage stage = new Stage();

            stage.setTitle("Registrar Funcionarios");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            initialize();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir el formulario: " + e.getMessage());
            alert.showAndWait();
        }
    }
    /* 
    public void cargarFuncionarios(){
        FuncionarioDAO dao = new FuncionarioDAO();
        List<Funcionario> lista = dao.obtenerFuncionarios();
        ObservableList<Funcionario> data = FXCollections.observableArrayList(lista);
        tableViewFuncionarios.setItems(data);
    }
    */
    
}
