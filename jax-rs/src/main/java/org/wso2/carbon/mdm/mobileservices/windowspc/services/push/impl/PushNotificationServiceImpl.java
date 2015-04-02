package org.wso2.carbon.mdm.mobileservices.windowspc.services.push.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.mdm.mobileservices.windowspc.common.Constants;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.push.PushNotificationService;

import org.wso2.carbon.mdm.mobileservices.windowspc.services.push.beans.OAuthToken;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.push.util.WNSAuthenticator;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

public class PushNotificationServiceImpl implements PushNotificationService{
    @Resource
    private WebServiceContext context;

    private static Log log = LogFactory.getLog(PushNotificationServiceImpl.class);

    @Override
    public Response pushInit() {
        ServletContext ctx = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

        String payload = "dummy";
        String notificationType = "wns/raw";
        String contentType = "application/octet-stream";
        String channelURI = (String)ctx.getAttribute("channelURI");
        pushToWns(Constants.PUSH.CLIENT_SECRET, Constants.PUSH.SID, channelURI, payload, notificationType, contentType);
        return Response.ok().build();
    }

    public static void pushToWns(String secret, String sId, String uri, String payload, String notificationType, String contentType) {

        try {
            OAuthToken authToken = WNSAuthenticator.getAccessToken(secret, sId);

            String response = WNSAuthenticator.sendPost(uri, payload, contentType,
                    notificationType, authToken.getAccessToken());
            log.debug(response);

        } catch (UnsupportedEncodingException e){
            String msg = "Url Encoding error occured";
            log.error(msg,e);
            e.printStackTrace();
        } catch(MalformedURLException e){
            String msg = "Malformed Url included";
            log.error(msg,e);
            e.printStackTrace();
        }
    }
}
