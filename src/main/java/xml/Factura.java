/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xml;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.DoubleStream;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author eli
 */
/*
Crea la clase Factura, con los siguientes atributos: código único (String), fechaEmisión (LocalDate), 
descripción (String) y totalImporteFactura (double). Crea un constructor por defecto y otro parametrizado para la clase. 
El constructor por defecto inicializa los atributos de forma aleatoria, siguiendo los siguientes criterios:

    El código único se implementará usando un contador de instancias.
    La fecha de emisión será la actual del sistema.
    La descripción será un string aleatorio.
    El importe total estará entre 100€ y 1000€. Para asignar este importe debes generar un DoubleStream 
de tamaño 1 usando el método doubles(int tamañoStream, double valorInicial, double valorFinal) de la clase Random.
*/


public class Factura {
    private static int contador=0;
    private static Random random = new Random();

    public static void setContador(int aContador) {
        contador = aContador;
    }

    public static void setRandom(Random aRandom) {
        random = aRandom;
    }

    private String codigoUnico;
    private LocalDate fechaEmision;
    private String descripcion;
    private double totalImporteFactura;

    public Factura() {
        this.codigoUnico = String.valueOf(Factura.contador++);
        this.fechaEmision = LocalDate.now();
        this.descripcion = RandomStringUtils.randomAlphabetic(10);
        this.totalImporteFactura = random.doubles(1, 100, 1001).toArray()[0];
    }

    public Factura(String codigoUnico, LocalDate fechaEmision, String descripcion, double totalImporteFactura) {
        this.codigoUnico = codigoUnico;
        this.fechaEmision = fechaEmision;
        this.descripcion = descripcion;
        this.totalImporteFactura = totalImporteFactura;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTotalImporteFactura() {
        return totalImporteFactura;
    }
    
    public static Random getRandom() {
        return random;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTotalImporteFactura(double totalImporteFactura) {
        this.totalImporteFactura = totalImporteFactura;
    }

    @Override
    public String toString() {
        return "%s;%s;%s;%s".formatted(this.codigoUnico,this.descripcion,this.fechaEmision,this.totalImporteFactura);
    }
    
    
    
    
}
