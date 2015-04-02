package org.wso2.carbon.mdm.mobileservices.windowspc.services.sts.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.mdm.mobileservices.windowspc.common.exceptions.AuthenticationException;
import org.wso2.carbon.mdm.mobileservices.windowspc.common.exceptions.WindowsDeviceEnrolmentException;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.sts.SecurityTokenService;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.sts.beans.Credentials;
import org.wso2.carbon.user.api.UserRealm;
import org.wso2.carbon.user.api.UserStoreException;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;

import javax.ws.rs.core.Response;

public class SecurityTokenServiceImpl implements SecurityTokenService {
    private static Log log = LogFactory.getLog(SecurityTokenServiceImpl.class);
    private static final String DELIMITER = "@";
    private static final int USER_SEGMENT = 0;
    private static final int DOMAIN_SEGMENT = 1;

    @Override
    public Response federatedAuthenticate(Credentials credentials) throws WindowsDeviceEnrolmentException {

        String domainUser = credentials.getUsername();
        String[] domainUserArray = domainUser.split(DELIMITER);
        String user = domainUserArray[USER_SEGMENT];
        String domain = domainUserArray[DOMAIN_SEGMENT];
        domain = "";
        String password = credentials.getPassword();

        try {
            if(authenticate(user, password, domain)){
                return Response.ok().entity("123456789123456789").build();
            }
            else{
                String msg = "Authentication failure due to incorrect credentials.";
                log.error(msg);
                return Response.status(403).entity("Authentication failure").build();
            }
        } catch (AuthenticationException e) {
            String msg = "Failure occurred in user authentication process.";
            log.error(msg);
            throw new WindowsDeviceEnrolmentException(msg);
        }
    }

    private boolean authenticate(String username, String password, String tenantDomain) throws
            AuthenticationException {

        try {
            PrivilegedCarbonContext.startTenantFlow();
            PrivilegedCarbonContext ctx = PrivilegedCarbonContext.getThreadLocalCarbonContext();
            ctx.setTenantDomain(MultitenantConstants.SUPER_TENANT_DOMAIN_NAME);
            ctx.setTenantId(MultitenantConstants.SUPER_TENANT_ID);
            RealmService realmService = (RealmService) ctx.getOSGiService(RealmService.class, null);

            if (realmService == null) {
                String msg = "RealmService not initialized.";
                log.error(msg);
                throw new AuthenticationException(msg);
            }

            int tenantId;
            if (tenantDomain == null || tenantDomain.trim().isEmpty()) {
                tenantId = MultitenantConstants.SUPER_TENANT_ID;
            } else {
                tenantId = realmService.getTenantManager().getTenantId(tenantDomain);
            }

            if (tenantId == MultitenantConstants.INVALID_TENANT_ID) {
                String msg = "Invalid tenant domain " + tenantDomain;
                log.error(msg);
                throw new AuthenticationException(msg);
            }
            UserRealm userRealm = realmService.getTenantUserRealm(tenantId);

            return userRealm.getUserStoreManager().authenticate(username, password);
        } catch (UserStoreException e) {
            String msg = "User store is not initialized.";
            log.error(msg, e);
            throw new AuthenticationException(msg, e);
        } finally {
            PrivilegedCarbonContext.endTenantFlow();
        }
    }
}
