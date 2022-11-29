/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASSES;

/**
 *
 * @author MSI
 */
public class Medecin extends Utilisateur {
    private int id;
    private String specialite;

    public Medecin(int id, String specialite, String nom, String prenom, String adresse, int numTel) {
        super(nom, prenom, adresse, numTel);
        this.id = id;
        this.specialite = specialite;
    }

    public  int getId() {
        return id;
    }

    public String getSpecialite() {
        return specialite;
    }
    
    
}
