package org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.beans;

import org.wso2.carbon.mdm.mobileservices.windowspc.common.Constants;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoveryRequest")
@SuppressWarnings("unused")
public class DiscoveryRequest implements Serializable {

    @XmlElement(name = "EmailAddress", required = true, namespace = Constants.DISCOVERY_SERVICE_TARGET_NAMESPACE)
    private String emailAddress;

    @XmlElement(name = "RequestVersion", namespace = Constants.DISCOVERY_SERVICE_TARGET_NAMESPACE)
    private String requestVersion;

    @XmlElement(name = "DeviceType", namespace = Constants.DISCOVERY_SERVICE_TARGET_NAMESPACE)
    private String deviceType;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRequestVersion() {
        return requestVersion;
    }

    public void setRequestVersion(String requestVersion) {
        this.requestVersion = requestVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

}