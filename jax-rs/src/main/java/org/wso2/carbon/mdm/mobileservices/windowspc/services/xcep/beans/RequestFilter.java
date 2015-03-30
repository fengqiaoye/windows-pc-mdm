package org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestFilter", propOrder = {
        "policyOIDs",
        "any"
})
public class RequestFilter {
    @XmlElement(required = true, nillable = true)
    protected FIlterOIDCollection policyOIDs;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    public FIlterOIDCollection getPolicyOIDs() {
        return policyOIDs;
    }

    public void setPolicyOIDs(FIlterOIDCollection policyOIDs) {
        this.policyOIDs = policyOIDs;
    }

    public List<Object> getAny() {
        return any;
    }

    public void setAny(List<Object> any) {
        if(any == null){
            any = new ArrayList<Object>();
        }
        this.any = any;
    }
}
