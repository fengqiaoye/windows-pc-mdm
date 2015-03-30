package org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterOIDCollection", propOrder = {
        "oid"
})
public class FIlterOIDCollection {

    @XmlElement(required = true)
    protected List<String> oid;

    public List<String> getOid() {
        if (oid == null) {
            oid = new ArrayList<String>();
        }
        return this.oid;
    }
}
