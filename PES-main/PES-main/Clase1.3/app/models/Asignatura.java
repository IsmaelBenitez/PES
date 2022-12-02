package models;

import play.db.jpa.Model;
import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import javax.persistence.Entity;
@Entity
public class Asignatura extends Model {

    public String nombre;

    public Asignatura(String nombre){
        this.nombre=nombre;
        return ;
    }
    @ManyToMany
    public List <clase> alumnos = new ArrayList<clase>();

    @OneToOne
    public Form form;
}
