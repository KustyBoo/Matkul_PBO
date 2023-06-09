/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Program;

import Produk.pakaian;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DatabasePageController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    @FXML
    private ImageView background1;
    @FXML
    private TextField txtcari;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnhps;
    @FXML
    private Button btnlogout;

    @FXML
    private TableView<pakaian> TabelP;
    @FXML
    private TableColumn<pakaian, String> NamaP;
    @FXML
    private TableColumn<pakaian, String> JenisP;
    @FXML
    private TableColumn<pakaian, String> UkuranP;
    @FXML
    private TableColumn<pakaian, String> BrandP;
    @FXML
    private TableColumn<pakaian, Integer> StokP;
    
    Alert alert;
    
    ObservableList<pakaian> cloth;
    
    @FXML
    private TextField txtnama;
    @FXML
    private ComboBox<String> cbjenis;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnlogout1;
    @FXML
    private AnchorPane halutama;
    @FXML
    private AnchorPane haltambah;
    @FXML
    private RadioButton radioS;
    @FXML
    private RadioButton radioM;
    @FXML
    private RadioButton radioL;
    @FXML
    private RadioButton radioXL;
    @FXML
    private RadioButton radioXXL;
    @FXML
    private TextField txtstok;
    @FXML
    private RadioButton radioXS;
    @FXML
    private ComboBox<String> cbbrand;
    
    final ObservableList<String> jenis = FXCollections.observableArrayList("Baju", "Celana", "Sweater", "Jaket", "Dalaman");
    final ObservableList<String> brand = FXCollections.observableArrayList("Nike", "Louis Vuitton", "Adidas", "Giordano", "Uniqlo");
    @FXML
    private Label labeltambahdata;
    @FXML
    private Label lblnama;
    
    private String nama;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load_data("");
        TabelP.getSelectionModel().select(0);
        cbjenis.setItems(jenis);
        cbbrand.setItems(brand);
    }    

    ButtonType yes = new ButtonType("Ya", ButtonBar.ButtonData.OK_DONE);
    ButtonType no = new ButtonType("Tidak", ButtonBar.ButtonData.CANCEL_CLOSE);
    
    @FXML
    private void logout(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.WARNING,"Yakin ingin Logout?", yes, no);
        alert.setTitle("Logout");
        Optional<ButtonType> iyah = alert.showAndWait();
        if(iyah.orElse(no) == yes){
            btnlogout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }   
    }
    
    private void load_data(String cari){
        try{
            connect = Database.connectDB();
            String SQL = "select * from baju where nama like '%" + cari + "%' OR jenis like '%" + cari + "%' OR brand like '%" + cari + "%' OR ukuran like '%" + cari + "%' OR stok like '%" + cari + "%'";
            prepare = connect.prepareStatement(SQL);
            result = prepare.executeQuery(SQL);
            
            this.cloth = FXCollections.observableArrayList();

            while(result.next()){
                pakaian pakean = new pakaian(
                    result.getString("nama"),
                    result.getString("jenis"),
                    result.getString("ukuran"),
                    result.getString("brand"),
                    result.getInt("stok")
                );
                cloth.add(pakean);
            }

            NamaP.setCellValueFactory(new PropertyValueFactory<>("nama"));
            JenisP.setCellValueFactory(new PropertyValueFactory<> ("jenis"));
            UkuranP.setCellValueFactory(new PropertyValueFactory<> ("ukuran"));
            BrandP.setCellValueFactory(new PropertyValueFactory<> ("brand"));
            StokP.setCellValueFactory(new PropertyValueFactory<> ("stok"));

            TabelP.setItems(cloth);
        }
        catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("FAILED TO LOAD DATA!");
            alert.setContentText("Load Data Produk Gagal");
            alert.showAndWait();
        }
    }

    @FXML
    private void kembali(ActionEvent event) {
        FadeTransition fade = new FadeTransition();  
        TranslateTransition slide = new TranslateTransition();
        fade.setDuration(Duration.seconds(0.5));
        fade.setFromValue(10);  
        fade.setToValue(0); 
        fade.setNode(haltambah);
        slide.setDuration(Duration.seconds(1));
        slide.setNode(haltambah);
        slide.setToX(600);
        slide.play();
        fade.play();
        clrForm();
        tambahgeser();
        TabelP.getSelectionModel().select(0);
    }
    
    private void tambahgeser(){
        FadeTransition fade = new FadeTransition();  
        TranslateTransition slide = new TranslateTransition();
        fade.setDuration(Duration.seconds(0.8));
        fade.setNode(halutama);
        fade.setFromValue(0);  
        fade.setToValue(10); 
        slide.setDuration(Duration.seconds(1));
        slide.setNode(halutama);
        slide.setToX(0);
        slide.play();
        fade.play();
    }

    @FXML
    private void tambah(ActionEvent event) {
        txtnama.setPromptText( "Nama");
        txtstok.setPromptText( "Stok");
        load_data("");
        TabelP.getSelectionModel().select(0);
        clrForm();
        
        FadeTransition fade = new FadeTransition();  
        TranslateTransition slide = new TranslateTransition();
        fade.setDuration(Duration.seconds(0.8));
        fade.setFromValue(10);  
        fade.setToValue(0); 
        fade.setNode(halutama);
        slide.setDuration(Duration.seconds(1));
        slide.setNode(halutama);
        slide.setToX(-600);
        slide.play();
        fade.play();
        
        labeltambahdata.setText("Tambah Data");
        btnsimpan.setText("Simpan");
        utamageser();
    }
    
    private void utamageser(){
        FadeTransition fade = new FadeTransition();  
        haltambah.setVisible(true);
        TranslateTransition slide = new TranslateTransition();
        fade.setDuration(Duration.seconds(0.5));
        fade.setNode(haltambah);
        fade.setFromValue(0);  
        fade.setToValue(10); 
        slide.setDuration(Duration.seconds(1));
        slide.setNode(haltambah);
        slide.setToX(-600);
        slide.play();
        fade.play();
    }

    private boolean cek(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(txtnama.getText().isEmpty()){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Nama Harus Diisi");
            alert.showAndWait();
            txtnama.requestFocus();
            return false;
        }else if (cbjenis.getValue().equals("Jenis")){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Jenis Harus Diisi");
            alert.showAndWait();
            cbjenis.requestFocus();
            return false;
        }else if (!radioXS.isSelected() && !radioS.isSelected() && !radioM.isSelected() &&
                !radioL.isSelected() && !radioXL.isSelected() && !radioXXL.isSelected()){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Ukuran Harus Diisi");
            alert.showAndWait();
            return false;
        }else if (cbbrand.getValue().equals("Brand")){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Brand Harus Diisi");
            alert.showAndWait();
            cbjenis.requestFocus();
            return false;
        }else if(txtstok.getText().isEmpty()){
            alert.setTitle("Pesan Informasi");
            alert.setContentText("Stok Harus Diisi");
            alert.showAndWait();
            txtstok.requestFocus();
            return false;
        }else{
            return true;
        }
    }
    
    @FXML
    private void simpan(ActionEvent event) throws SQLException {
        connect = Database.connectDB();
        pakaian pakean = TabelP.getSelectionModel().getSelectedItem();
        Integer id = 0;
        String ambil_id = "select id from baju where nama = ?";
        prepare = connect.prepareStatement(ambil_id);
        prepare.setString(1, pakean.getNama());
        result = prepare.executeQuery();
        if(result.next()){
            id = result.getInt("id");
        }
        if(labeltambahdata.getText().equals("Tambah Data")){
            try{
                if(cek() == false){
                    return;
                }
                if (!result.next()){
                    String ukuran = "";
                    int stok = Integer.parseInt(txtstok.getText());
                    if(radioXS.isSelected()){
                        ukuran = "XS";
                    }
                    if(radioS.isSelected()){
                        ukuran += " S";
                    }
                    if(radioM.isSelected()){
                        ukuran += " M";
                    }
                    if(radioL.isSelected()){
                        ukuran += " L";
                    }
                    if(radioXL.isSelected()){
                        ukuran += " XL";
                    }
                    if(radioXXL.isSelected()){
                        ukuran += " XXL";
                    }

                    connect = Database.connectDB();
                    String query = "insert into baju(nama, jenis, ukuran, brand, stok) values (?,?,?,?,?)";

                    prepare = connect.prepareStatement(query);

                    prepare.setString(1, txtnama.getText());
                    prepare.setString(2, cbjenis.getValue());
                    prepare.setString(3, ukuran);
                    prepare.setString(4, cbbrand.getValue());
                    prepare.setInt(5, Integer.parseInt(txtstok.getText()));

                    prepare.executeUpdate();

                    clrForm();
                    
                    TabelP.getItems().clear();
                    load_data("");
                    btnlogout1.fire();
                    TabelP.getSelectionModel().select(0);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Tambah Berhasil");
                    alert.setContentText("Produk Berhasil di Tambah");
                    alert.showAndWait();

                    
                } else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Duplikat Nama Produk");
                    alert.setContentText("Add Data Produk Gagal");
                    alert.showAndWait();
                }
            } catch(Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setContentText("Add Data Produk Gagal (Ada Error Coy)");
                alert.showAndWait();
            }
        } else{
            
            if(txtnama.getText().equals("")){
                txtnama.setText(pakean.getNama());
            }
            if(txtstok.getText().equals("")){
                String stokz = Integer.toString(pakean.getStok());
                txtstok.setText(stokz);
            }
            String ukuran = "";
            int stok = Integer.parseInt(txtstok.getText());
            if(radioXS.isSelected()){
                ukuran = "XS";
            }
            if(radioS.isSelected()){
                ukuran += " S";
            }
            if(radioM.isSelected()){
                ukuran += " M";
            }
            if(radioL.isSelected()){
                ukuran += " L";
            }
            if(radioXL.isSelected()){
                ukuran += " XL";
            }
            if(radioXXL.isSelected()){
                ukuran += " XXL";
            }
            
            String query = "update baju set nama=?,jenis=?,ukuran=?,brand=?,stok=? where id=?";

            prepare = connect.prepareStatement(query);

            prepare.setString(1, txtnama.getText());
            prepare.setString(2, cbjenis.getValue());
            prepare.setString(3, ukuran);
            prepare.setString(4, cbbrand.getValue());
            prepare.setInt(5, Integer.parseInt(txtstok.getText()));
            prepare.setInt(6, id);

            prepare.executeUpdate();

            clrForm();
            TabelP.getItems().clear();
            load_data("");
            btnlogout1.fire();
            TabelP.getSelectionModel().select(0);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Berhasil");
            alert.setContentText("Produk Berhasil di Edit");
            alert.showAndWait();
            
        }
        
    }
    
    private void clrForm(){
        txtnama.clear();
        txtstok.clear();
        cbjenis.setValue(null);
        cbbrand.setValue(null);
        cbjenis.setValue("Jenis");
        cbbrand.setValue("Brand");
        radioXS.setSelected(false);
        radioS.setSelected(false);
        radioM.setSelected(false);
        radioL.setSelected(false);
        radioXL.setSelected(false);
        radioXXL.setSelected(false);
    }

    @FXML
    private void hapus(ActionEvent event) throws SQLException {
        ButtonType yes = new ButtonType("Ya", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Tidak", ButtonBar.ButtonData.CANCEL_CLOSE);
        pakaian pakean = TabelP.getSelectionModel().getSelectedItem();
            
        Integer id = 0;
        String ambil_id = "select id from baju where nama = ?";
        prepare = connect.prepareStatement(ambil_id);
        prepare.setString(1, pakean.getNama());
        result = prepare.executeQuery();
        if(result.next()){
            id = result.getInt("id");
        }
        
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Yakin ingin menghapus "+ pakean.getNama(),
                yes,
                no);
        
        alert.setTitle("Menghapus data");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            connect = Database.connectDB();
            String query = "delete from baju where id=?";

            prepare = connect.prepareStatement(query);
            prepare.setInt(1, id);
            prepare.executeUpdate();
            
            TabelP.getItems().clear();
            load_data("");
            TabelP.getSelectionModel().select(0);
            
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Hapus Berhasil");
            alert1.setContentText("Produk Berhasil di Hapus");
            alert1.showAndWait();
        }
    }

    @FXML
    private void edit(ActionEvent event) {
        pakaian pakean = TabelP.getSelectionModel().getSelectedItem();
        
        txtnama.setPromptText( "Nama : " + pakean.getNama());
        txtstok.setPromptText( "Stok : " +Integer.toString(pakean.getStok()));
        
        String ukuranP = pakean.getUkuran();
        if(ukuranP.contains("XS")){
            radioXS.setSelected(true);
        }
        if(ukuranP.contains("S")){
            radioS.setSelected(true);
        }
        if(ukuranP.contains("M")){
            radioM.setSelected(true);
        }
        if(ukuranP.contains("L")){
            radioL.setSelected(true);
        }
        if(ukuranP.contains("XL")){
            radioXL.setSelected(true);
        }
        if(ukuranP.contains("XXL")){
            radioXXL.setSelected(true);
        }

        cbjenis.setValue(pakean.getJenis());
        cbbrand.setValue(pakean.getBrand());
        
        labeltambahdata.setText("Update Data");
        btnsimpan.setText("Update");
        
        FadeTransition fade = new FadeTransition();  
        TranslateTransition slide = new TranslateTransition();
        fade.setDuration(Duration.seconds(0.8));
        fade.setFromValue(10);  
        fade.setToValue(0); 
        fade.setNode(halutama);
        slide.setDuration(Duration.seconds(1));
        slide.setNode(halutama);
        slide.setToX(-600);
        slide.play();
        fade.play();
        utamageser();
    }

    @FXML
    private void search(KeyEvent event) {
        load_data(txtcari.getText());
        TabelP.getSelectionModel().select(0);
    }
    
    public void SetUsername(String namauser){
        lblnama.setText("");
        this.nama = namauser;
        lblnama.setText(nama);
    }

    
}
