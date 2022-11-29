/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication8;
import CONTROLLERS.*;




import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author MSI
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Hyperlink acc_create;

    @FXML
    private Hyperlink acc_create1;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_singup;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField su_email;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_username;

    @FXML
    private TextField username;
    
    @FXML
    private Button bntreserver;
    
    
    @FXML
    private Button suivNormal;
    
    //suivnormale
    @FXML
    private Button confirmer;

    @FXML
    private Label m;

    @FXML
    private Label m1;

    @FXML
    private Label m2;

    @FXML
    private TextField pmax;

    @FXML
    private TextField pmin;

    @FXML
    private TextField poids;

    @FXML
    private TextField taille;
    float IMC;
    
    //Database tools
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    public Connection ConnectDb() {
        try{
            connect=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java","root","");
            return (Connection) connect;
            
        }catch (Exception e) { e.printStackTrace();}
        return null;
          
    } 
    
    public void signUp(ActionEvent event) {
        connect=ConnectDb();
        try {
            String sql ="insert into abonné values(?,?,?)";
            statement=connect.prepareStatement(sql);
            statement.setString(1,su_username.getText());
            statement.setString(2,su_password.getText());
            statement.setString(3,su_email.getText());
            statement.execute();            
            JOptionPane.showMessageDialog(null ," successfully create new account!","Message" ,JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e) { e.printStackTrace();}
    }
    
    public void login(ActionEvent event) {
        connect= (Connection) ConnectDb();
        try {
            String sql ="select * from abonné where username= ? and password =?";
            statement = connect.prepareStatement(sql);
            statement.setString(1,username.getText());
            statement.setString(2,password.getText());
            result = statement.executeQuery();
            Alert alert;
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert (AlertType.ERROR);
                alert.setTitle(" Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                
            }
            
            else if(result.next()) {
                //then if the username and password that you are entered thiss area will be run after the system check if correct
                JOptionPane.showMessageDialog(null ," successfully login!","Message" ,JOptionPane.INFORMATION_MESSAGE);
                login_btn.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            
             Scene scene = new Scene(root);
             Stage stage =new Stage();
             stage.setScene(scene);
             stage.setTitle("Simple Dashboard");
             stage.show();
            } 
            else {
                JOptionPane.showMessageDialog(null ," Wrong Username/Password","Message" ,JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) { e.printStackTrace();}
    }
    
    public void changeForm (ActionEvent event){
    if (event.getSource()==acc_create1) {
        signup_form.setVisible(false);
        login_form.setVisible(true);
        
    }
    else if (event.getSource()==acc_create) {
            signup_form.setVisible(true);
        login_form.setVisible(false);
        }
    
}
    public void Reserver_Rdv(ActionEvent event ) throws IOException {
        bntreserver.getScene().getWindow().hide();
        Parent root = FXMLLoader.load (getClass().getResource("ReserverUnRDV.fxml"));
         Scene scene = new Scene(root);
            Stage stage =new Stage();
             stage.setScene(scene);
            
             //stage.setTitle("S");
             stage.show();
      
  
          
    }
    
    public void suivie_normale(ActionEvent event ) throws IOException{
        
    suivNormal.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Suivie normale.fxml"));
         Scene scene = new Scene(root);
            Stage stage =new Stage();
             stage.setScene(scene);
             //stage.setTitle("S");
             stage.show();
  
          
    }
    
    public void res_suivie_normale(ActionEvent event ) throws IOException{
          
        float t= Float.parseFloat(taille.getText());
        float p= Float.parseFloat(poids.getText());
        
        
        float p1= Float.parseFloat(pmax.getText());
        float p2= Float.parseFloat(pmin.getText());
        
        
        IMC = p/(t*t); 
          
        
        m.setText(Float.toString(IMC));
        
          if(IMC<18.5){
             m1.setText(" Sous Poids ");
        }
        else if(IMC<24.9 && IMC>18.5 ){
           m1.setText("Poids Normal ");
        }    
        else if( IMC>25 && IMC<29.9){
            m1.setText(" Sur poids ");             

        } else if( IMC>30 && IMC<34.9){
            m1.setText(" Obésité ");       
        }
        else
             m1.setText(" Obésité severe "); 
          
          
          
          
          if (p1<10 && p2<6) {
           m2.setText(" votre tension est faible ! ");
            }
            else if (p1<=12 && p2<=8) {
                     m2.setText("votre tension est optimale ");
                    }
            else if (p1 <13 && p2<8.5) {
              m2.setText("votre tension est normale ");
            }
            else if (p1>14 && p1<15.9 && p2>9 && p2<9.9) {
                m2.setText("Degré 1 : hypertension légère");
            }
            else if (p1>16 && p1<17.9 && p2>10 && p2<10.9) {
                m2.setText("Degré 2 : hypertension modérée ");
            }
            else   {
                m2.setText("Degré 3 : hypertension sévère !!!!! ");
            }
          
        
          
        
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
