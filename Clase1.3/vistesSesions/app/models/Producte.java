package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Producte extends Model {
 
    public String nom;
    public int preu;
    public int stock;
    
    public Producte(String n, int p, int s) {
        this.nom = n;
        this.preu = p;
        this.stock = s;
    }
 
}
