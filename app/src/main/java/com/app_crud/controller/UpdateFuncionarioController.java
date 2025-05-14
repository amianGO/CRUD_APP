package com.app_crud.controller;

import java.io.IOException;
import java.util.List;

import com.app_crud.dao.FamiliaDAO;
import com.app_crud.dao.FuncionarioDAO;
import com.app_crud.model.Familia;
import com.app_crud.model.Funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UpdateFuncionarioController {

    @FXML private TextField txtNombre, txtNumId, txtTelefono, txtDireccion, txtApellido;
    @FXML private ComboBox<String> comboTipoId, comboEstadoCivil, comboSexo;
    @FXML private DatePicker fechaNacimiento;
    
    @FXML private TableColumn<Familia, Void> colEliminar;

    @FXML private TableView<Familia> tableFamilia;
    @FXML private TableColumn<Familia, String> colNombre, colParentesco, colSexo;

    private Funcionario funcionario;
    private FamiliaDAO familiaDAO = new FamiliaDAO();

    @FXML
    public void initialize(){
        comboTipoId.getItems().addAll("CC","TI","CE");
        comboEstadoCivil.getItems().addAll("Soltero", "Casado", "Viudo", "Divorciado");
        comboSexo.getItems().addAll("Masculino", "Femenino");

        colNombre.setCellValueFactory(cell -> cell.getValue().nombreProperty());
        colParentesco.setCellValueFactory(cell -> cell.getValue().parentescoProperty());
        colSexo.setCellValueFactory(cell -> cell.getValue().sexoProperty()); 

        tableFamilia.setOnMouseClicked(this::handleClick);

        cargarFamiliares();
        columnaEliminar();

    }

    private void cargarFamiliares(){
        if (funcionario != null) {
            List<Familia> familiares = familiaDAO.obtenerFamiliares(funcionario.getId());
            ObservableList<Familia> familiaList = FXCollections.observableArrayList(familiares);
            tableFamilia.setItems(familiaList);
        }
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
        cargarFamiliares();
    }


    @FXML
    private void abrirFormularioFamilia(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form_familia.fxml"));
            Parent root = loader.load();
            FormFamiliaController formFamilia = loader.getController();
            formFamilia.setFuncionarioController(this);
            formFamilia.setFuncionario(funcionario); // <-- Linea Clave

            Stage stage = new Stage();
            stage.setTitle("Crear Familiar");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            initialize();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir Formulario" + e.getMessage());
            alert.showAndWait();
        }
    }

    private void handleClick(MouseEvent event){
        if (event.getClickCount() == 2) {
            Familia seleccionado = tableFamilia.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                abrirEditorFamilia(seleccionado);
            }
        }
    }

    @FXML
    private void abrirEditorFamilia(Familia familia){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit_fam_form.fxml"));
            Parent root = loader.load();
            FormFamiliaController formFamilia = loader.getController();
            formFamilia.setFuncionarioController(this);
            formFamilia.setFuncionario(funcionario); // <-- Linea Clave
            formFamilia.setFamiliarSeleccionado(familia);

            Stage stage = new Stage();
            stage.setTitle("Crear Familiar");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            initialize();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al abrir Formulario" + e.getMessage());
            alert.showAndWait();
        }
    }

    public void columnaEliminar(){
        Callback<TableColumn<Familia, Void>, TableCell<Familia, Void>> cellFactory = new Callback<>(){

            @Override
            public TableCell<Familia, Void> call(final TableColumn<Familia, Void> param){
                return new TableCell<>(){
                    private final Button btn = new Button("Eliminar");
                    {
                        btn.setOnAction(event -> {
                            Familia familia = getTableView().getItems().get(getIndex());
                            eliminarFamilia(familia);
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

    public void eliminarFamilia(Familia familia){
        FamiliaDAO familiaDAO = new FamiliaDAO();
        boolean eliminado = familiaDAO.eliminarFamilia(familia.getId());

        if (eliminado) {
            tableFamilia.getItems().remove(familia);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Familiar Eliminado Exitosamente");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hubo un error al Elimnar.");
            alert.showAndWait();
            System.out.println("No se pudo eliminar el Familiar");
        }
    }
    
}
