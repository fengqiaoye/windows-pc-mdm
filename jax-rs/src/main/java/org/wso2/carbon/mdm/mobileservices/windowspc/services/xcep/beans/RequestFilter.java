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
    protected FilterOIDCollection policyOIDs;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the policyOIDs property.
     *
     * @return possible object is
     * {@link FilterOIDCollection }
     */
    public FilterOIDCollection getPolicyOIDs() {
        return policyOIDs;
    }

    /**
     * Sets the value of the policyOIDs property.
     *
     * @param value allowed object is
     *              {@link FilterOIDCollection }
     */
    public void setPolicyOIDs(FilterOIDCollection value) {
        this.policyOIDs = value;
    }

    /**
     * Gets the value of the any property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link org.w3c.dom.Element }
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

}
