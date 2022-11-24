import play.test.*;
import play.jobs.*;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
        // Load default data if the database is empty
        if(clase.count() == 0) {
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

        }


    }
}