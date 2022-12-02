package models;

import play.db.jpa.Model;
import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import javax.persistence.Entity;
@Entity
public class Mensaje extends Model {


    public double fecha_hora;

    public String texto;

    public Mensaje(double fecha_hora, String texto){

        this.fecha_hora=fecha_hora;
        this.texto=texto;
        return ;

    }
    @ManyToOne
    public clase propietario;

    @ManyToOne
    public Form forum;

}