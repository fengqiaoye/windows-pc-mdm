package org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Client", propOrder = {
        "lastUpdate",
        "preferredLanguage",
        "any"
})
public class Client {

    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    @XmlElement(required = true, nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String preferredLanguage;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(XMLGregorianCalendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return any;
    }

    public void setAny(List<Object> any) {
        this.any = any;
    }
}
