package org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
        "policyID",
        "policyFriendlyName",
        "nextUpdateHours",
        "policiesNotChanged",
        "policies",
        "any"
})
public class Response {

    @XmlElement(required = true)
    protected String policyID;
    @XmlElement(required = true, nillable = true)
    protected String policyFriendlyName;
    @XmlElement(required = true, type = Long.class, nillable = true)
    @XmlSchemaType(name = "unsignedInt")
    protected Long nextUpdateHours;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean policiesNotChanged;
    @XmlElement(required = true, nillable = true)
    protected PolicyCollection policies;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    public String getPolicyFriendlyName() {
        return policyFriendlyName;
    }

    public void setPolicyFriendlyName(String policyFriendlyName) {
        this.policyFriendlyName = policyFriendlyName;
    }

    public Long getNextUpdateHours() {
        return nextUpdateHours;
    }

    public void setNextUpdateHours(Long nextUpdateHours) {
        this.nextUpdateHours = nextUpdateHours;
    }

    public Boolean getPoliciesNotChanged() {
        return policiesNotChanged;
    }

    public void setPoliciesNotChanged(Boolean policiesNotChanged) {
        this.policiesNotChanged = policiesNotChanged;
    }

    public PolicyCollection getPolicies() {
        return policies;
    }

    public void setPolicies(PolicyCollection policies) {
        this.policies = policies;
    }

    public List<Object> getAny() {
        return any;
    }

    public void setAny(List<Object> any) {
        this.any = any;
    }
}
