
package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="stateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FINISHED"/>
 *     &lt;enumeration value="CURRENT"/>
 *     &lt;enumeration value="REGISTERING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "stateType", namespace = "http://javaops.ru")
@XmlEnum
public enum StateType {

    FINISHED,
    CURRENT,
    REGISTERING;

    public String value() {
        return name();
    }

    public static StateType fromValue(String v) {
        return valueOf(v);
    }

}
