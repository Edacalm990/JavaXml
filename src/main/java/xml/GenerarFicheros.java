/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



/**
 *
 * @author eli
 */
/*
Crea una clase, llamada GenerarFicheros, con un método main() y realiza lo siguiente:

    Crea 50 facturas usando el constructor por defecto, guárdalas en una lista y muéstralas por pantalla. 
    Crea las carpetas "csv" y "xml" colgando del directorio raíz del proyecto, si no están creadas, mediante código.
    Guarda los datos de todas las facturas de la lista, en un fichero de texto llamado facturas.csv, dentro del directorio “./csv”, separando los campos por punto y coma (;).
    Guarda los datos de todas las facturas de la lista, en un fichero XML llamado facturas.xml, dentro del directorio “./xml”.
    En una carpeta “./facturascsv” crea un archivo csv por cada factura que haya en la lista. El archivo se llamará igual que el código de la factura y contendrá los datos de la factura, separando los campos por el carácter (;).

En una nueva clase llamada LeerXML, que tenga el método main(), procede a leer el fichero XML generado en el directorio "xml"  y muestra el resultado por pantalla. 
*/
public class GenerarFicheros {
    public static void main(String[] args) throws JAXBException {
        // Crea 50 facturas usando el constructor por defecto, guárdalas en una lista y muéstralas por pantalla.
        ArrayList<Factura> listaFacturas= new ArrayList();
        for (int i = 0; i < 50; i++) {
            listaFacturas.add(new Factura());
        }
        listaFacturas.forEach(System.out::println);
        // Crea las carpetas "csv" y "xml" colgando del directorio raíz del proyecto, si no están creadas, mediante código.
        crearDirectorio("./csv");
        crearDirectorio("./xml");
        // Guarda los datos de todas las facturas de la lista, en un fichero de texto llamado facturas.csv, 
        // dentro del directorio “./csv”, separando los campos por punto y coma (;).
        crearFichero(crearString(listaFacturas), "./csv/facturas.csv");
        //  Guarda los datos de todas las facturas de la lista, en un fichero XML llamado facturas.xml, dentro del directorio “./xml”.
        crearXML(listaFacturas);
        
       // En una carpeta “./facturascsv” crea un archivo csv por cada factura que haya en la lista. 
       // El archivo se llamará igual que el código de la factura y contendrá los datos de la factura, separando los campos por el carácter (;).
       crearDirectorio("./facturascsv");
        for (Factura factura : listaFacturas) {
            String nombre=factura.getCodigoUnico();
            crearFichero(factura.toString(), "./facturascsv/%s.csv".formatted(nombre));
        }
        
    }
    
    public static String crearString(ArrayList<Factura> listaFacturas){
        String tmp="";
        for (Factura factura : listaFacturas) {
            tmp+=factura.toString();
            tmp+="\n";
        }
        return tmp;
    }
    
    
    public static void crearXML(ArrayList<Factura> listaFacturas){
    //crearFichero(listaFacturas.toString(), "./csv/fcaturas.csv");
        CatalogoFactura catalogo= new CatalogoFactura();
        catalogo.setLista(listaFacturas);
        catalogo.setDescripcion("Mi catalogo");
        try {
             // Crea el contexto JAXB. Se encarga de definir los objetos 
        JAXBContext contexto = JAXBContext.newInstance(CatalogoFactura.class);
        
        // El contexto JAXB permite crear un objeto Marshaller, que sirve para
        // generar la estructura del fichero XML 
        // El proceso de pasar objetos Java (CatalogoFactura) a ficheros XML 
        // se conoce como "marshalling" o "serialización"
        Marshaller serializador = contexto.createMarshaller();
        
        // Especificamos que la propiedad del formato de salida
        // del serializador sea true, lo que implica que el formato se 
        // realiza con indentación y saltos de línea
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Llamando al método de serialización marshal (sobrecargado) se pueden
        // serializar objetos java en formato XML y volcarlos donde necesitemos:
        // consola, ficheros. El proceso consiste en que el contexto es el 
        // encargo de leer los objetos java, pasarlos al serializador y éste 
        // crear la salida de serialización
        
        // Serialización y salida por consola
        //serializador.marshal(catalogo, System.out);

        // Volcado al fichero xml
        serializador.marshal(catalogo, new File("./xml/facturas.xml"));
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }

    
    public static void crearDirectorio(String ruta) {

        Path directory = Paths.get(ruta);
        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException faee) {
            System.out.println("No se puede crear " + ruta + " porque ya existe");
        } catch (AccessDeniedException ade) {
            System.out.println("No tiene permisos para crear " + ruta);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio " + ruta);
            System.out.println("Seguramente la ruta está mal escrita o no existe");
        }

    }
    
        // crea un fichero con un String de datos y el nombre
     public static void crearFichero(String datos, String nombreFichero) {

        try {
            Files.write(Paths.get(nombreFichero), datos.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println("Error creando el fichero");
        }
    }
}
