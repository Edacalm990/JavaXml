/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

// Clase XmlAdapter permite mapear tipos de datos que JAXB no sabe como gestionar a la hora de 
// realizar las operaciones de marshal y unmarshal
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
