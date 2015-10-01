/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class EnterController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
   private Label connLabel;
   @FXML
   private Label insLabel;
   @FXML
   private Label alloLabel;
   @FXML
   private Label statusConLabel;
   @FXML
   private Label emailLabel;
   @FXML
   private Label passLabel;
   @FXML
   private Label confirmPassLabel;
   @FXML
   private TextField idConChamp;
   @FXML
   private TextField passConChamp;
   @FXML
   private TextField alloChamp;
   @FXML
   private TextField emailChamp;
   @FXML
   private TextField passChamp;
   @FXML
   private TextField confirmPassChamp;
   @FXML
   private Button enterButton;
   @FXML
   private Button inscriptionButton;
   @FXML
   private void enterKingdom(ActionEvent event)throws Exception{
       
        // TODO
   if(idConChamp.getText().equals("mmdukb") && passConChamp.getText().equals("mmdukb")){
        Parent parent = FXMLLoader.load(getClass().getResource("/dedikast/dedikast.xml"));
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setTitle("Decikast Kingdom");
        stage.show();
    }    else{
        statusConLabel.setText("Combinaison invalide , réessayez ou inscrivez vous ! ");
    }     
   
   
   }    
    @FXML
   private void registerCustomer(ActionEvent event)throws Exception{
       
       
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
