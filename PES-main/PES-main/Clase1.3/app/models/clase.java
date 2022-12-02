package models;

import play.db.jpa.Model;
import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import javax.persistence.Entity;
@Entity
public class clase extends Model {

        public String nombre;
        public int edad;
        public String password;

        public clase(String nombre, int edad, String password){
            this.nombre=nombre;
            this.edad=edad;
            this.password=password;
            return ;
        }

        @ManyToMany(mappedBy = "alumnos")

        public List <Asignatura> asig = new ArrayList<Asignatura>();

        @OneToMany(mappedBy = "propietario")
        public List <Mensaje> menj = new ArrayList<Mensaje>();

        @ManyToMany(mappedBy = "personas")

        public List <Form> form = new ArrayList<Form>();
}
