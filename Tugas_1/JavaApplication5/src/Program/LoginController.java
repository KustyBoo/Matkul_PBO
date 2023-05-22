/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Program;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Asus
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button btnlogin;
    
    @FXML
    private TextField txtuser;
    
    @FXML
    private PasswordField txtpass;
    
    @FXML
    private ImageView background;
    
    @FXML
    private Label txtregis;
    
    String user = "Lix";
    String pass = "123";
    
    Alert alert;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(txtuser.getText().equals(user) && txtpass.getText().equals(pass)){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Masuk");
            alert.showAndWait();  
        } else{
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username/Password Salah");
            alert.showAndWait();   
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
        @Override
        public void run() {
            txtuser.requestFocus();
        }
    });
    }    
    
}
