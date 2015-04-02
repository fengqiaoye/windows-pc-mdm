package org.wso2.carbon.mdm.mobileservices.windowspc.services.push;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/push")
public interface PushNotificationService {

    @GET
    @Path("/init")
    Response pushInit();
}
