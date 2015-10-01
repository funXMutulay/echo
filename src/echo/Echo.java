/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echo;


import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
/**
 *
 * @author mombju
 */
public class Echo extends Application {

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("echo.fxml"));
        Scene  scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
 /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }   

}