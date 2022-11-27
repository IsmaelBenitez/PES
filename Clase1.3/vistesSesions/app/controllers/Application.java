package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	static String username;

	@Before
    	static void connectedUser() {
	   username = session.get("user");
    	}
    

        public static void index() {
           if(username != null) {
               //renderText("Ususari "+ username + " connectat.");
		//Tenim una sessió oberta amb l'uausi "username"
		renderArgs.put("userConnected",username);
        	List<Producte> p = Producte.findAll();
		renderArgs.put("products",p);
		renderTemplate("Application/Productes.html");
           }
           renderTemplate("Application/MainPage.html");
        }

        public static void login(){
	   renderTemplate("Application/login.html");
        }

        public static void LoginData(User usuari){
     
           User u = User.find("byFullnameAndPassword",usuari.fullname, usuari.password).first();
	   if(u!=null){
	     //iniciem la sessio d'usuari
        
		session.put("user", usuari.fullname);
		renderArgs.put("userConnected",usuari.fullname);
        	List<Producte> p = Producte.findAll();
        
		renderArgs.put("products",p);
		renderTemplate("Application/Productes.html");
	   }else{
	     //les dades del login són incorrectes 
	     renderArgs.put("missatgeError","El login o el password són incorrectes");
	     renderTemplate("Application/MainPage.html");
	   }

        }

        public static void TancaSessio(){
	   session.clear();
	   renderTemplate("Application/MainPage.html");	
        }

        public static void register(){
	   renderText("Registrat");
        }

        public static void compra (Long id, int quantity){
	    //renderText("id " +id);
	    Producte p = Producte.findById(id);
	    renderText("Comprarem "+ quantity + "kilos del producte "+p.nom);

        }
}
