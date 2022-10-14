package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {

        clase x= new clase("xavi",21,"contraseña").save();
        Mensaje x2= new Mensaje(23.54,"Hola que tal").save();
        Form x3= new Form("Cafereria",30,"Cierre de la caferetia").save();
        Asignatura x4= new Asignatura("PES").save();

        //Relación 1:N bidireccional
        clase usuario= new clase("isma",20,"1234");
        Mensaje mensaje=new Mensaje(21,"He comido carne");
        Form forumPES= new Form("PES",30,"examen");

        forumPES.save();
        mensaje.save();
        forumPES.menj2.add(mensaje);
        usuario.menj.add(mensaje);
        usuario.save();


        mensaje.forum=forumPES;
        mensaje.propietario= usuario;


        //Prueba relación 1:N
        Asignatura asignatura = new Asignatura("");
        asignatura.nombre="PES";
        asignatura.save();
        clase usuario2= new clase("victoria",20,"qwerr");

        usuario2.asig.add(asignatura);
        usuario2.save();

        forumPES.asigna=asignatura;
        asignatura.form= forumPES;

        asignatura.alumnos.add(usuario2);

        //asignatura.propietario= usuario2;
        //asignatura.save();

        render();
    }

    public void Register (String nombre, int edad, String password) {
        clase cl = clase.find("byNombreAndEdadAndPassword",nombre,edad,password).first();
        if (cl==null){
            new clase(nombre,edad,password).save();
            renderText("Client register");
        }
        else{
            renderText("Client ya existeix");
        }
    }

    public void Login (String nombre, String password) {
        clase cl = clase.find("byNombreAndPassword",nombre,password).first();
        if (cl==null){
            renderText("Regístrate");
        }
        else{
            renderText("Login con éxito");
        }
    }

}