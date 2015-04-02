package org.wso2.carbon.mdm.mobileservices.windowspc.services.sts;

import org.wso2.carbon.mdm.mobileservices.windowspc.common.exceptions.WindowsDeviceEnrolmentException;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.sts.beans.Credentials;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bst")
public interface SecurityTokenService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/authentication")
    Response federatedAuthenticate(Credentials credentials) throws WindowsDeviceEnrolmentException;
}
