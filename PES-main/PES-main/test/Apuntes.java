public class Apuntes {
/*

    Hibernate implementation:
    -Unidireccional: està relacionat en una entitat o en l'altra
    -Bidireccional: Relació esà en totes dues entitats
    Hiernate crea una taula per clase i una taula per clau foranes per cada relació
    el mappedBy siempre va en el N de la relacion 1:N

    ------------------------------------------------------------------------------------------------------------
       14/10/22 Peticiones
    el local host:9000 ejecuta el index

    Si queremos ejecutar otra cosa se usa el GET:

    GET : locahost:9000/Application/index (ejecutas index que esta en applicación) ? parametro=x
             IP    Port    Servei     lo que quieres ejecutar                      ?  lo que quieras buscar
        ejemplo:

        IP:9000/Clients/show?id=1

    Post : No es posible desde el navegador

    Hacer una reserca:

    Client c= Client.findByID(1) (busacr la información del cliente con el ID = 1 y guaradrla en la variable c)
    List<Post> post = Post.all().fetch(100) (crear una lista con la información desde el ID al 100)

       ejemplo comanda:
                clase cl = clase.find("byNombreAndEdadAndPassword",nombre,edad,password).first();
                List<clase> cl = clase.find("byEdadLessTham",18).fecth();

    para aumentar el rango de un número, en vez de poner int ID, pones long ID

*/

}
