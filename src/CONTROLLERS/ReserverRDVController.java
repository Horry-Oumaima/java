/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CONTROLLERS;

import CLASSES.Medecin;
import com.mysql.cj.xdevapi.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ReserverRDVController implements Initializable {
      
    @FXML
    private TableView<Medecin> addmedecinTableView;
    @FXML
    private TableColumn <Medecin,String> adresscol;
    @FXML
    private ComboBox<?>adrs;
    @FXML
    private TextField idMed;
     @FXML
    private TableColumn<Medecin,String> idMedcol;

    @FXML
    private TextField nom;

    @FXML
    private TableColumn<Medecin,String> nomcol;

    @FXML
    private TextField prenom;

    @FXML
    private TableColumn<Medecin,String> prenomcol;

    @FXML
    private ComboBox<?> specialite;
    @FXML
    private TableColumn<Medecin,String> specia_col;

    @FXML
    private TextField tel;

    @FXML
    private TableColumn<Medecin,String> telcol;
    
  
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public Connection ConnectDb() {
        try{
            connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java","root","");
            return (Connection) connect;
            
        }catch (Exception e) { e.printStackTrace();}
        return null;
          
    } 
    public void AjouterMedecin() {
        String sql ="insert into medecin values (id,nom,prenom,numTel,adresse,spécialite" +"value(?,?,?,?,?,?)";
        connect=ConnectDb();
                try{
              prepare = connect.prepareStatement(sql);
              prepare.setString(1, idMed.getText());
              prepare.setString(2, nom.getText());
              prepare.setString(3, prenom.getText());
              prepare.setString(4, tel.getText());
              prepare.setString(5, (String)adrs.getSelectionModel().getSelectedItem());
              prepare.setString(6, (String)specialite.getSelectionModel().getSelectedItem());
                } catch (Exception e) { e.printStackTrace();}
    }
    public ObservableList<Medecin> addmedecinListData() {
       String sql ="select * from medecin";
       ObservableList<Medecin> listData = FXCollections.observableArrayList();
       connect=ConnectDb();
       try{
              prepare = connect.prepareStatement(sql);
             result = prepare.executeQuery();
             Medecin medData = null;
             while(result.next()) {
                 medData= new Medecin (result.getInt("id"),result.getString("specialite"), result.getString("nom"),
                 result.getString("prenom"),result.getString("adresse"), result.getInt("numTel"));
             }
      
           listData.add(medData);
       } catch (Exception e) { e.printStackTrace();}
            return listData;
    }
    private ObservableList<Medecin> ajoutermedecinList;
    public void ajouterMedecinshowListdata() {
        ajoutermedecinList= addmedecinListData();
        idMedcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        telcol.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        adresscol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        specia_col.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        

        addmedecinTableView.setItems(ajoutermedecinList);
        
        
        
    }
    
    public void selectMedecin() {
        Medecin medecin =addmedecinTableView.getSelectionModel().getSelectedItem();
        int num =addmedecinTableView.getSelectionModel().getSelectedIndex();
        if(num -1 < -1) {return;}
        idMed.setText(String.valueOf(medecin.getId()));
        nom.setText(String.valueOf(medecin.getNom()));
        prenom.setText(String.valueOf(medecin.getPrenom()));
        tel.setText(String.valueOf(medecin.getNumTel()));
        

        
        
    }
    
    
      
    

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
