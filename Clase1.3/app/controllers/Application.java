package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
/*
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

                Asignatura asignatura = new Asignatura("");
        asignatura.nombre="PES";
        asignatura.save();
        clase usuario2= new clase("victor",20,"qwerr");

        usuario2.asig.add(asignatura);
        usuario2.save();

       // forumPES.asigna=asignatura;
       // asignatura.form= forumPES;

        asignatura.alumnos.add(usuario2);
        asignatura.save();

        clase usuario3= new clase("pau",22,"4321");
        Form forumCET= new Form("CET","practica");
        forumCET.save();
        usuario3.save();
        usuario3.form.add(forumCET);
        forumCET.personas.add(usuario3);

        Form forumCET2= new Form("CET","practica");
        forumCET2.save();
        usuario2.save();
        usuario2.form.add(forumCET2);
        forumCET2.personas.add(usuario2);


        asignatura.save();
        forumCET.save();
        //mensaje.save();
        usuario3.save();


        Asignatura asignatura2 = new Asignatura("");
        asignatura2.nombre="PES";
        asignatura2.save();
        clase usuario4= new clase("paco",20,"987");

        usuario4.asig.add(asignatura);
        usuario4.save();
        asignatura2.alumnos.add(usuario4);
        asignatura2.save();

*/

        //Prueba relación 1:N


        //Creo dos asignaturas y sus foros
        Asignatura PES =new Asignatura("PES");
        Asignatura ERF =new Asignatura("ERF");
        Form FPES = new Form("PES","practica");
        Form FERF = new Form("ERF","ads");


        //Meto a tres personas
        clase Ismael =new clase("Ismael",21,"hola");
        clase Xavier =new clase("Xavier",21,"hola");
        clase Victoria =new clase("Victoria",21,"hola");

        //Las personas hacen asignaturas
        Ismael.asig.add(PES);
        Ismael.asig.add(ERF);
        Xavier.asig.add(PES);
        Xavier.asig.add(ERF);
        Victoria.asig.add(PES);

        PES.alumnos.add(Ismael);
        PES.alumnos.add(Xavier);
        PES.alumnos.add(Victoria);

        ERF.alumnos.add(Ismael);
        ERF.alumnos.add(Xavier);

        Ismael.save();
        Xavier.save();
        Victoria.save();

        FPES.save();
        FERF.save();
        PES.save();
        ERF.save();

        PES.form=FPES;
        ERF.form=FERF;
        FPES.asigna=PES;
        FERF.asigna=ERF;

        FPES.personas.add(Ismael);
        FPES.personas.add(Victoria);
        FERF.personas.add(Xavier);

        FPES.save();
        FERF.save();
        PES.save();
        ERF.save();

        //Escribimos un mensaje
        Mensaje mensaje=new Mensaje( 22.33, "Hola que tal");
        mensaje.propietario=Ismael;
        mensaje.forum=FPES;
        mensaje.save();
        Ismael.menj.add(mensaje);
        FPES.menj2.add(mensaje);

        Mensaje mensaje2=new Mensaje( 22.33, "Muy bien");
        mensaje2.propietario=Victoria;
        mensaje2.forum=FPES;
        mensaje2.save();
        Ismael.menj.add(mensaje2);
        FPES.menj2.add(mensaje2);


        Mensaje mensaje3=new Mensaje( 22.33, "ADS es muy útil");
        mensaje3.propietario=Xavier;
        mensaje3.forum=FERF;
        mensaje3.save();
        Xavier.menj.add(mensaje3);
        FERF.menj2.add(mensaje3);

        Xavier.save();
        FERF.save();

        render();
    }

    public void Register (String nombre, int edad, String password) {
       clase cl = clase.find("byNombreAndEdadAndPassword",nombre,edad,password).first();
       if(cl==null){
           new clase(nombre,edad,password).save();
           renderText("registat " + nombre +" "+ edad +" "+ password);
       }
       else
       {
           if(cl.nombre.equals(nombre))
           {
               renderText("ja existeix aquesta persona"+ " " +cl.edad+ " " +cl.password+ " xd");
           }
           else{
               new clase(nombre,edad,password).save();
               renderText("client registat "+ cl.nombre);
           }
       }

       // clase c= clase.findBynombre(nombre);
        //renderText(cl.nombre);
        /*List<clase> cl = clase.all().fetch(100);
        for(clase m :cl) {

        if (m.nombre==null){
            //new clase(nombre,edad,password).save();
            //renderText("Client register");
        }
        else{
            String Texto=" ";
                String nombre_1=m.nombre;
                if (nombre_1 == nombre){
                    Texto = Texto + m.nombre + ": ya está registrado"+ "\n";
                renderText(Texto);
            }
            else
                {
                    new clase(nombre,edad,password).save();
                    renderText("Client registat "+nombre_1+ " "+ nombre);
                }
            }
        }*/
    }

    public void Login (String nombre, String password) {
        clase cl = clase.find("byNombreAndPassword",nombre,password).first();
        if (cl==null){
            renderText("Regístrate");
        }
        else{
            if(cl.nombre.equals(nombre) && cl.password.equals(password) )
            {
                renderText("Login con éxito "+ cl.nombre+ " " +cl.password);
            }
            else{
                renderText("Regístrate");
            }

        }
    }

    public void UsuariosForo(String asunto){
        List<Form> F = Form.find("byAsunto_tema", asunto).fetch(100);
        if (F==null){
            renderText("no hay personas hablando de este tema");
        }
        else{
            int usuarios=0;
            for(Form m :F) {
                for (clase n : m.personas)
                    usuarios = usuarios + 1;
            }
            renderText("hay "+ usuarios +" personas hablando de este tema ");
            }
    }

    public void Buscar_Personas_Asignatura (String nombre) {
        List<Asignatura> asignatura = Asignatura.find("byNombre", nombre).fetch(100);

        //renderText(nombre);
        //Asignatura asig = Asignatura.find("byNombre", nombre).first();
        if (asignatura==null){
            renderText("No hay nadie haciendo esa asignatura "+ nombre);
        }
        else{
            String Texto=" ";
            //esto no va porque no se meterme en la otra tabla
            for(Asignatura n: asignatura) {
                for (clase m : n.alumnos) {
                    Texto = Texto + m.nombre + " ";
                }
            }
            renderText("Hay estas personas haciendo la asignatura:"+ Texto);
        }
    }

    public void DatosForo(String titulo,String asignatura){
        if(titulo.equals(" ")){
            Asignatura a =Asignatura.find("byNombre",asignatura).first();
            if(a!=null){
                Form Foro=a.form;
                if(Foro!=null){
                    String Texto =" ";
                    for(Mensaje m: Foro.menj2)
                        Texto=Texto+" "+m.propietario.nombre+": "+m.texto+"\n";
                    renderText(Texto);
                }
                renderText("Esta asignatura no tiene forum");
            }
            renderText("No existe esta asignatura");
        }
        else{
            Form Foro =Form.find("byTitulo",titulo).first();
            if(Foro!=null){
                String Texto =" ";
                for(Mensaje m: Foro.menj2)
                    Texto=Texto+" "+m.propietario.nombre+": "+m.texto+"\n";
                renderText(Texto);
            }
            renderText("Este foro no existe");

        }
    }
    public void AsignaturasAlumno(String nombre){
        clase Alumno =clase.find("byNombre",nombre).first();
        if(Alumno!=null){
            String Texto = Alumno.nombre+" hace las siguientes asignaturas: \n";
            for(Asignatura x: Alumno.asig)
                Texto=Texto+" "+x.nombre;
            renderText(Texto);
        }
        renderText("Alumno no encontrado");
    }
    public void ExisteForo (String nombreAsignatura){
        Asignatura a = Asignatura.find("byNombre", nombreAsignatura).first();
        if (a!=null){
            Form f = a.form;
            if (f!=null){
                renderText("El foro de "+nombreAsignatura+" es "+f.titulo);
            } else{
                renderText("No existe el foro");
            }
        } else {
            renderText("No existe la asignatura");
        }
    }
    public void ForumsByAsunto(String asunto){
        Form f = Form.find("byAsunto_tema",asunto).first();
        if (f!=null){
            renderText("El foro de "+asunto+" es "+f.titulo);
        } else {
            renderText("No existe el foro");
        }
    }
}