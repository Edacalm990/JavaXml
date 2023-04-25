/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.time.LocalDate;

/**
 *
 * @author eli
 */
/*
En una nueva clase llamada LeerXML, que tenga el método main(), 
procede a leer el fichero XML generado en el directorio "xml"  
y muestra el resultado por pantalla. 

En una nueva clase, llamada Usuario que incluya el método main: 

    Pregunta al usuario que factura desea leer de las posibles que haya en “./facturascsv”. 
    Para ello muestra un listado con las que haya en la carpeta y luego pregunta la factura a leer.
    Una vez leída la factura desde el fichero y creado el objeto factura con los datos correspondientes, muestra por pantalla.
    Borra el archivo de la factura leída del directorio "facturascsv" y muestra el contenido del directorio, 
    para comprobar que ya no existe el archivo.
 */
public class LeerXML {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        // Crea el contexto JAXB 
        JAXBContext contexto = JAXBContext.newInstance(CatalogoFactura.class);
        // Crea el objeto Unmarshaller
        Unmarshaller um = contexto.createUnmarshaller();

        // Llama al método de unmarshalling
        CatalogoFactura catalogo = (CatalogoFactura) um.unmarshal(new File("./xml/facturas.xml"));

        ArrayList<Factura> listaFactura = catalogo.getListaFactura();

        listaFactura.forEach(System.out::println);
    }

    public static ArrayList<Factura> leerfactura(String ruta) {
        ArrayList<Factura> listaFactura= new ArrayList<>();
        try {
            // Crea el contexto JAXB 
            JAXBContext contexto = JAXBContext.newInstance(CatalogoFactura.class);
            // Crea el objeto Unmarshaller
            Unmarshaller um = contexto.createUnmarshaller();

            // Llama al método de unmarshalling
            CatalogoFactura catalogo = (CatalogoFactura) um.unmarshal(new File(ruta));

            listaFactura = catalogo.getListaFactura();

            listaFactura.forEach(System.out::println);
        } catch (Exception e) {
        }
        return listaFactura;
    }
}
