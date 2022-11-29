/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication8;

import CLASSES.Medecin;
import com.mysql.cj.xdevapi.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.event.Event; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseButton; 
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ReserverUnRDVController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private TableColumn<Medecin, String> adrescol;

    @FXML
    private TableColumn<Medecin, Integer> idMed;

    @FXML
    private Label labelspec;

    @FXML
    private TableColumn<Medecin, String> nom;

    @FXML
    private TableColumn<Medecin, String> prenom;

    @FXML
    private ComboBox<String> spec;

    @FXML
    private TableColumn<Medecin, String> speccol;

    @FXML
    private TableColumn<Medecin, String> telcol;
     @FXML
    private TableView<Medecin> tableview;
     
      @FXML
    private ComboBox<String> comboadr;

    @FXML
    private Label labeladr;
    
     @FXML
    private Text outPut;
     
     @FXML
    private Button confbtn;
     
      @FXML
    private DatePicker dateRDV;

    
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
    
public void select(ActionEvent event ) throws IOException{
        
         
          labelspec.setText(spec.getValue());  
          
          
       String sql ="select * from medecin where specialite like '"+spec.getValue()+"'";
       ObservableList<Medecin> listData = FXCollections.observableArrayList();
       connect=ConnectDb();
       try{
              prepare = connect.prepareStatement(sql);
             result = prepare.executeQuery();
             Medecin medData = null;
             while(result.next()) {
                 medData= new Medecin (result.getInt("id"),result.getString("specialite"), result.getString("nom"),
                 result.getString("prenom"),result.getString("adresse"), result.getInt("numTel"));
                 listData.add(medData);
             }
      
           
           tableview.setItems(listData);
           
       } catch (Exception e) { e.printStackTrace();}
           
    }


public void selectADR(ActionEvent event ) throws IOException{
        
         
          labeladr.setText(comboadr.getValue());  
          
          
       String sql ="select * from medecin where adresse like '"+comboadr.getValue()+"'";
       ObservableList<Medecin> listData = FXCollections.observableArrayList();
       connect=ConnectDb();
       try{
              prepare = connect.prepareStatement(sql);
             result = prepare.executeQuery();
             Medecin medData = null;
             while(result.next()) {
                 medData= new Medecin (result.getInt("id"),result.getString("specialite"), result.getString("nom"),
                 result.getString("prenom"),result.getString("adresse"), result.getInt("numTel"));
                 listData.add(medData);
             }
      
           
           tableview.setItems(listData);
           
       } catch (Exception e) { e.printStackTrace();}
           
    }
Integer index;


    public void getItem(MouseEvent event) {
        
        
        index =tableview.getSelectionModel().getSelectedIndex();
        if (index<=-1) {return;}
        outPut.setText(idMed.getCellData(index).toString());
     
 
    }
    
    public void confirmerrdv(ActionEvent event ) throws IOException{
        
    confbtn.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("confirmerRDV.fxml"));
        
         Scene scene = new Scene(root);
            Stage stage =new Stage();
             stage.setScene(scene);
             //stage.setTitle("S");
             stage.show();
             
       /*try{
              prepare = connect.prepareStatement(sql);
             result = prepare.executeQuery();
             Medecin medData = null;
             while(result.next()) {
                 medData= new Medecin (result.getInt("id"),result.getString("specialite"), result.getString("nom"),
                 result.getString("prenom"),result.getString("adresse"), result.getInt("numTel"));
                 listData.add(medData);
             }
      
           
           tableview.setItems(listData);
           
       } catch (Exception e) { e.printStackTrace();}*/
             String sql ="insert into rendez_vous  (numRDV,idMed,nom,prenom,dateRDV)" +"values(?,?,?,?,?)";
             index =tableview.getSelectionModel().getSelectedIndex();
            //String sql ="insert into rendez_vous values (1,"+idMed.getCellData(index).toString()+","+nom.getCellData(index).toString()+","+prenom.getCellData(index).toString()+","+datepick.getValue().toString()+")" ;
            //String sql ="insert into rendez_vous values (1,"+idMed.getCellData(index).toString()+",'"+nom.getCellData(index).toString()+"','"+prenom.getCellData(index).toString()+"','"+datepick.getValue().toString()+"')" ;
            connect=ConnectDb();
                try{
              prepare = connect.prepareStatement(sql);
              
        Integer numRDV=1;
              prepare.setInt(1, ++numRDV);
              prepare.setInt(2, idMed.getCellData(index));
              prepare.setString(3, nom.getCellData(index));
              prepare.setString(4, prenom.getCellData(index));
              prepare.setString(5, dateRDV.getValue().toString());
              
                } catch (Exception e) { e.printStackTrace();}
             
  
          
    }
                      
          /* connect= (Connection) ConnectDb();
        ObservableList<medecin> list =FXCollections.observableArrayList();
        try {
            String sql ="select * from medecin ";
            statement = connect.prepareStatement(sql);
            result = statement.executeQuery();
            while(result.next()) {
                list.add(new medecin(Integer.parseInt(result.getString("idmed")),result.getString("nom"),result.getString("prenom"),result.getString("numtel"),result.getString("spécialité"),result.getString("adresse")));
                
            } 
           
        } catch (Exception e) { e.printStackTrace();}
        
        data.setItems(list);*/
      
        
     
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] specialites={"generale","pediatre","cardiologue"};
          spec.getItems().addAll(specialites);
          
          String[] adresses={"Tunis","Menzel_Temime","Nabeul"};
          comboadr.getItems().addAll(adresses);
          
          idMed.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        telcol.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        adrescol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        speccol.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        
        
    }    
    
}
