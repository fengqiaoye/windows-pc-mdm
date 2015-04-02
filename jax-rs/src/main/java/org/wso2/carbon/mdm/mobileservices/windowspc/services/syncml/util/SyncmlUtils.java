package org.wso2.carbon.mdm.mobileservices.windowspc.services.syncml.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
//import org.wso2.carbon.device.mgt.core.operation.mgt.OperationManagerImpl;
import org.wso2.carbon.device.mgt.core.service.DeviceManagementService;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;

/**
 * Class for generate Device object from the received data.
 */
public class SyncmlUtils {

	private static Log log = LogFactory.getLog(SyncmlUtils.class);

	/**
	 * This method returns Device Management Object for certain tasks such as Device enrollment etc.
	 *
	 * @return DeviceManagementServiceObject
	 */
	public static DeviceManagementService getDeviceManagementService() {
		try {
			DeviceManagementService deviceManagementService;
			PrivilegedCarbonContext.startTenantFlow();
			PrivilegedCarbonContext context = PrivilegedCarbonContext.getThreadLocalCarbonContext();
			context.setTenantDomain(MultitenantConstants.SUPER_TENANT_DOMAIN_NAME);
			context.setTenantId(MultitenantConstants.SUPER_TENANT_ID);
			deviceManagementService = (DeviceManagementService) context.getOSGiService(
					DeviceManagementService.class, null);
			return deviceManagementService;
		} finally {
			PrivilegedCarbonContext.endTenantFlow();
		}
	}

//	public static OperationManagerImpl getOperationManagementService() {
//		try {
//			OperationManagerImpl operationManager;
//			PrivilegedCarbonContext.startTenantFlow();
//			PrivilegedCarbonContext ctx = PrivilegedCarbonContext.getThreadLocalCarbonContext();
//			ctx.setTenantDomain(MultitenantConstants.SUPER_TENANT_DOMAIN_NAME);
//			ctx.setTenantId(MultitenantConstants.SUPER_TENANT_ID);
//			operationManager = (OperationManagerImpl) ctx.getOSGiService(OperationManagerImpl.class, null);
//
//			if (operationManager == null) {
//				String msg = "Operation management service is not initialized";
//				log.error(msg);
//			}
//			return operationManager;
//		}
//		finally{
//			PrivilegedCarbonContext.endTenantFlow();
//		}
//	}
}
