package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.mvc.results.RenderTemplate;

public class Application extends Controller {
    static String username;
    @Before
        static void connectedUser() {
        username = session.get("user");
    }

        public static void index() {
            if(username != null) {
                renderText("Ususari "+ username + " connectat.");
                //Tenim una sessió oberta amb l'uausi "username"
                renderArgs.put("userConnected",username);
            }
            renderTemplate("Application/login.html");
        }

        public void Register (String nombre, int edad, String password) {
           clase cl = clase.find("byNombreAndEdadAndPassword",nombre,edad,password).first();
           if(cl==null){
               new clase(nombre,edad,password).save();
               session.put("user", nombre);
               renderArgs.put("userConnected",nombre);
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
                   session.put("user", nombre);
                   renderArgs.put("userConnected",nombre);
                   renderText("client registat "+ cl.nombre);
               }
           }
        }

        public void Login (String nombre, String password) {
            clase cl = clase.find("byNombreAndPassword",nombre,password).first();
            if (cl==null){
                renderText("Regístrate");
            }
            else{
                if(cl.nombre.equals(nombre) && cl.password.equals(password) )
                {
                    session.put("user", nombre);
                    renderArgs.put("userConnected",nombre);
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
        public void DarseBaja(clase Usuario){
            clase A =clase.find("byNombreAndPassword",Usuario.nombre,Usuario.password).first();
            if(A==null)
                renderText("El usuario no existia");
            else{
                if(A.menj.size()>0) {//Borramos la  lista de mensajes de A
                    for (Mensaje m : A.menj) {
                        Mensaje x =Mensaje.find("byTexto",m.texto).first();
                        x.delete();
                    }
                }
                if(A.asig.size()>0){
                    for(Asignatura a:A.asig){
                        Asignatura x = Asignatura.find("byNombre",a.nombre).first();
                        x.alumnos.remove(A);
                        x.save();

                    }
                }
                if(A.form.size()>0) {
                    for (Form f : A.form) {
                        Form x;
                        if (f.titulo != null)
                            x = Form.find("byTitulo", f.titulo).first();
                        else
                            x = Form.find(("byAsunto_tema"), f.asunto_tema).first();
                        x.personas.remove(A);
                        x.save();

                    }
                }
                A.delete();
                renderText("Usuario eiminid@");
            }
        }
        public void EnviarRegistrarse(){renderTemplate("Application/Registro.html");}
    public static void TancaSessio(){
        session.clear();
        renderTemplate("Application/login.html");
    }
}