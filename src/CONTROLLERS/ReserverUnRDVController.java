/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CONTROLLERS;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

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
    private ComboBox<?> addressbnt;

    @FXML
    private Label adresse;

    @FXML
    private Label date;

    @FXML
    private DatePicker datebnt;

    @FXML
    private ComboBox<?> specialBTn;

    @FXML
    private Label specialte;
    
    private String[] addSpecialite={"général" , "cardilogue" , "pédiatre"};
    public void addSpecialiteType (ActionEvent event){
    List <String> list = new ArrayList();
    for(String data :addSpecialite) {
        list.add(data);
    }
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
