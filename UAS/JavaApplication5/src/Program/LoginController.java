/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Program;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private TextField txtemail;
    
    @FXML
    private PasswordField txtpass;
    
    
    @FXML
    private AnchorPane hallogin;
    
    @FXML
    private AnchorPane halregis;
    
    @FXML
    private Label txtregis;
    
    @FXML
    private Label txtregis1;
    
    Alert alert;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    @FXML
    private ImageView background1;
    
    @FXML
    private Button btnlogin1;
    
    @FXML
    private Label label1;
    
    @FXML
    private TextField txtuser1;
    
    @FXML
    private PasswordField txtpass1;
    
    @FXML
    private PasswordField txtpass11;
    
    @FXML
    private AnchorPane loginpage;
    
    @FXML
    private void login(ActionEvent event) {
        String query = "SELECT * FROM akun WHERE username = ? AND password = ?";
        connect = Database.connectDB();
        try{
            prepare = connect.prepareStatement(query);
            prepare.setString(1, txtuser.getText());
            prepare.setString(2, txtpass.getText());
            result = prepare.executeQuery();
            if(result.next()){
                if(txtuser.getText().equals(result.getString(2)) && txtpass.getText().equals(result.getString(4))){
                    btnlogin.getScene().getWindow().hide();
                    
                    Parent root;
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DatabasePage.fxml"));
                    root = loader.load();
                    DatabasePageController controller = loader.getController();

                    controller.SetUsername(txtuser.getText());
                    
                    Scene scene = new Scene(root,600,400);
                    Stage stage = new Stage();
                    stage.setTitle("Home");
                    stage.setScene(scene);
                    
                    clear();
                    
                    stage.show();
                   
                }
            }else{
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Login Gagal");
                alert.setHeaderText(null);
                alert.setContentText("Username/Password Salah");
                alert.showAndWait();
            }    
        }catch (Exception e){
            e.printStackTrace();
        }
       
    }
    
    @FXML
    private void menuregis(MouseEvent event) {
        FadeTransition fade = new FadeTransition();  
        TranslateTransition slide = new TranslateTransition();
        fade.setDuration(Duration.seconds(0.8));
        fade.setFromValue(10);  
        fade.setToValue(0); 
        fade.setNode(hallogin);
        slide.setDuration(Duration.seconds(1));
        slide.setNode(hallogin);
        slide.setToY(-400);
        slide.play();
        fade.play();
        regisgeser();
        clear();
    }
    
    private void regisgeser(){
        FadeTransition fade = new FadeTransition();  
        halregis.setVisible(true);
        fade.setFromValue(0);  
        fade.setToValue(10); 
        fade.setNode(halregis);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(halregis);
        slide.setToY(-400);
        slide.play();
        fade.play();
    }
    
    @FXML
    private void menulogin(MouseEvent event){
        FadeTransition fade = new FadeTransition();  
        TranslateTransition slide = new TranslateTransition();
        fade.setDuration(Duration.seconds(0.8));
        fade.setFromValue(10);  
        fade.setToValue(0); 
        fade.setNode(halregis);
        slide.setDuration(Duration.seconds(1));
        slide.setNode(halregis);
        slide.setToY(0);
        slide.play();
        fade.play();
        logingeser();
        clear();
    }
    
    private void logingeser(){
        FadeTransition fade = new FadeTransition();  
        TranslateTransition slide = new TranslateTransition();
        fade.setFromValue(0);  
        fade.setToValue(10); 
        fade.setNode(hallogin);
        slide.setDuration(Duration.seconds(1));
        slide.setNode(hallogin);
        slide.setToY(0);
        slide.play();
        fade.play();
    }
    
    private boolean cek(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(txtuser1.getText().isEmpty()){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Nama Harus Diisi");
            alert.showAndWait();
            txtuser1.requestFocus();
            return false;
        }else if (txtemail.getText().isEmpty()){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Email Harus Diisi");
            alert.showAndWait();
            txtemail.requestFocus();
            return false;
        }else if (txtpass1.getText().isEmpty()){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Password Harus Diisi");
            alert.showAndWait();
            txtpass1.requestFocus();
            return false;
        }else if (!txtpass11.getText().equals(txtpass1.getText())){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Password konfirmasi tidak sama");
            alert.showAndWait();
            txtpass11.requestFocus();
            return false;
        }else{
            return true;
        }
    }
           
    @FXML
    private void registrasi(ActionEvent event) throws IOException, SQLException{
        if(cek() == false){
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        String selectQuery = "select * from akun where username=? OR email=?";
        connect = Database.connectDB();

        prepare = connect.prepareStatement(selectQuery);
        prepare.setString(1,txtuser1.getText());
        prepare.setString(2, txtemail.getText());
        result = prepare.executeQuery();

        //Mengecek apakah username dan email duplikat
        if(result.next()){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Username atau Email Telah Ada");
            alert.showAndWait();
            txtemail.requestFocus();
            return;
        }
        
        String insertQuery = "Insert into akun(username, email, password) values (?,?,?)";
        connect = Database.connectDB();

        prepare = connect.prepareStatement(insertQuery);
        prepare.setString(1, txtuser1.getText());
        prepare.setString(2, txtemail.getText());
        prepare.setString(3, txtpass1.getText());
        prepare.executeUpdate();

        alert.setTitle("Pesan Informasi");
        alert.setContentText("Registrasi Akun Berhasil");
        alert.showAndWait();
        
        FadeTransition fade = new FadeTransition();  
        TranslateTransition slide = new TranslateTransition();
        fade.setDuration(Duration.seconds(0.8));
        fade.setFromValue(10);  
        fade.setToValue(0); 
        fade.setNode(halregis);
        slide.setDuration(Duration.seconds(1));
        slide.setNode(halregis);
        slide.setToY(0);
        slide.play();
        fade.play();
        logingeser();
    }
    
    public void clear(){
        txtuser.clear();
        txtpass.clear();
        txtuser1.clear();
        txtpass1.clear();
        txtemail.clear();
        txtpass11.clear();
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
