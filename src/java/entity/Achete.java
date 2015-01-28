/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author J00ke
 */
public class Achete implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="ACHETEID") // lien avec la bdd
    private String aid;
    private String nom;
 
    
}
