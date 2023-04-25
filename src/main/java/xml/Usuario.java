/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author eli
 */
/*
En una nueva clase, llamada Usuario que incluya el método main: 

    Pregunta al usuario que factura desea leer de las posibles que haya en “./facturascsv”. Para ello muestra un listado con las que haya en la carpeta y luego pregunta la factura a leer.
    Una vez leída la factura desde el fichero y creado el objeto factura con los datos correspondientes, muestra por pantalla.
    Borra el archivo de la factura leída del directorio "facturascsv" y muestra el contenido del directorio, para comprobar que ya no existe el archivo.
 */
public class Usuario {

    public static void main(String[] args) {
        // Pregunta al usuario que factura desea leer de las posibles que haya en “./facturascsv”. 
        //Para ello muestra un listado con las que haya en la carpeta y luego pregunta la factura a leer.
        String directorio = "./facturascsv";
        String[] listaContenido = ServiciosFichero.contenidoDirectorio(directorio).toArray(String[]::new);
        String opcion = JOptionPane.showInputDialog(null, "Elige la factura a leer", "Leer Factura", JOptionPane.QUESTION_MESSAGE, null, listaContenido, listaContenido[0]).toString();
        // Una vez leída la factura desde el fichero y creado el objeto factura con los datos correspondientes, muestra por pantalla.
        Factura nuevafactura = crearFactura(opcion, directorio);
        System.out.println(nuevafactura);
        // Borra el archivo de la factura leída del directorio "facturascsv" y muestra el contenido del directorio, para comprobar que ya no existe el archivo.
        borrarElemento("./%s/%s".formatted(directorio, opcion));
        ServiciosFichero.contenidoDirectorio(directorio).forEach(System.out::println);
        
    }
    
    public static void borrarElemento(String ruta) {
        Path file = Paths.get(ruta);
        try {
            Files.delete(file);
        } catch (NoSuchFileException nsfe) {
            System.out.println("No se puede borrar " + ruta + " porque no existe");
        } catch (DirectoryNotEmptyException dnee) {
            System.out.println("No se puede borrar el directorio porque no está vacío");
        } catch (IOException e) {
            System.out.println("Problema borrando el elemento " + ruta);
        }
    }

    public static Factura crearFactura(String nombre, String directorio) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("./%s/%s".formatted(directorio,nombre)), StandardCharsets.UTF_8);
            // lee el archivo y crea un array con los atributos, luego llama a crear vehiculo y crea el vehiculo
            // se le pasa por parametro la i (la i representa el tipo de vehiculo porque sabemos que el primer fichero son todo turismos el segundo deportivos y el tercero furgonetas)
            String[] datos = lines.get(0).split(";");
            
            return new Factura(datos[0], LocalDate.parse((datos[2])), datos[1], Double.parseDouble(datos[3]));
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        }

        return null;

    }
};
