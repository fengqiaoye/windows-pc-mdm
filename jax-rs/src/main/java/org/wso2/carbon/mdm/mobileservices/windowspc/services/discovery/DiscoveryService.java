package org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery;

import org.wso2.carbon.mdm.mobileservices.windowspc.common.Constants;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.beans.DiscoveryRequest;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.beans.DiscoveryResponse;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.xml.ws.BindingType;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.SOAPBinding;

/**
 * Interface for Discovery service related operations.
 */
@WebService(targetNamespace = Constants.DISCOVERY_SERVICE_TARGET_NAMESPACE,
        name = "IDiscoveryService")
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface DiscoveryService {

    @POST
    @RequestWrapper(localName = "Discover", targetNamespace = Constants.
            DISCOVERY_SERVICE_TARGET_NAMESPACE)
    @WebMethod(operationName = "Discover")
    @ResponseWrapper(localName = "DiscoverResponse", targetNamespace = Constants.
            DISCOVERY_SERVICE_TARGET_NAMESPACE)
    void discover(
            @WebParam(name = "request", targetNamespace = Constants.DISCOVERY_SERVICE_TARGET_NAMESPACE)
            DiscoveryRequest request,
            @WebParam(mode = WebParam.Mode.OUT, name = "DiscoverResult",
                    targetNamespace = Constants.DISCOVERY_SERVICE_TARGET_NAMESPACE)
            javax.xml.ws.Holder<DiscoveryResponse> response
    );

    @GET
    @WebMethod
    @WebResult() Response discoverGet();

}