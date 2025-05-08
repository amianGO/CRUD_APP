package com.app_crud.app;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.app_crud.db.Conection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    private static final Logger logger = LogManager.getLogger(Main.class);
    @Override
    public void start(Stage stage) throws Exception{
        
        logger.info("Iniciando App JavaFX");

        Conection.Conexion();

        Parent root = FXMLLoader.load(getClass().getResource("/view/empleados.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Administracion de Empleados");
        stage.show();
    
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
}