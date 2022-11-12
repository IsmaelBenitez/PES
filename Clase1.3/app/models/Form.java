package models;

import play.db.jpa.Model;
import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import javax.persistence.Entity;
@Entity
public class Form extends Model {

    public String titulo;
    public String asunto_tema;

    public Form(String titulo, String asunto_tema){
        this.titulo=titulo;

        this.asunto_tema=asunto_tema;
        return ;
    }
    @OneToMany(mappedBy = "forum")
    public List <Mensaje> menj2 = new ArrayList<Mensaje>();

    @OneToOne(mappedBy = "form")
    public Asignatura asigna;

    @ManyToMany

    public List <clase> personas = new ArrayList<clase>();
}
