package org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.impl;

import org.wso2.carbon.mdm.mobileservices.windowspc.common.Constants;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.DiscoveryService;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.beans.DiscoveryRequest;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.beans.DiscoveryResponse;

import javax.jws.WebService;
import javax.ws.rs.core.Response;
import javax.xml.ws.BindingType;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.SOAPBinding;

@WebService(endpointInterface = Constants.DISCOVERY_SERVICE_ENDPOINT, targetNamespace = Constants
        .Discovery.DISCOVERY_SERVICE_TARGET_NAMESPACE)
@Addressing(enabled = true, required = true)
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public class DiscoveryServiceImpl implements DiscoveryService{

    @Override
    public void discover(DiscoveryRequest request, Holder<DiscoveryResponse> response) {

        DiscoveryResponse discoveryResponse = new DiscoveryResponse();
        discoveryResponse.setAuthPolicy(Constants.Discovery.AUTH_POLICY);
        discoveryResponse.setAuthenticationServiceUrl(Constants.Discovery.AUTHENTICATION_SERVICE_URL);
        discoveryResponse.setEnrollmentPolicyServiceUrl(
                Constants.Discovery.CERTIFICATE_ENROLLMENT_POLICY_SERVICE_URL);
        discoveryResponse.setEnrollmentServiceUrl(
                Constants.Discovery.CERTIFICATE_ENROLLMENT_SERVICE_URL);

        response.value = discoveryResponse;
    }


    @Override
    public Response discoverGet() {
        return Response.ok().build();
    }
}
