package models;

import play.db.jpa.Model;
import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import javax.persistence.Entity;
@Entity
public class Form extends Model {

    public String titulo;

    public int usuarios; // cambiar el int por el long
    public String asunto_tema;

    public Form(String titulo, int usuarios, String asunto_tema){
        this.titulo=titulo;
        this.usuarios=usuarios;
        this.asunto_tema=asunto_tema;
        return ;
    }
    @OneToMany(mappedBy = "forum")
    public List <Mensaje> menj2 = new ArrayList<Mensaje>();

    @OneToOne(mappedBy = "form")
    public Asignatura asigna;
}
