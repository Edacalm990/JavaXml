/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xml;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eli
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogoFactura {


    // XmLElementWrapper define un contenedor para la lista 
    // de muebles
    @XmlElementWrapper(name = "catalogo")

    // XmlElement establece el nombre de los elementos
    // Cada elemento de la lista llevará esta etiqueta en el fichero xml
    @XmlElement(name = "factura")
    private ArrayList<Factura> listaFactura;

    private String descripcion;

    public ArrayList<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setLista(ArrayList<Factura> lista) {
        this.listaFactura = lista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
