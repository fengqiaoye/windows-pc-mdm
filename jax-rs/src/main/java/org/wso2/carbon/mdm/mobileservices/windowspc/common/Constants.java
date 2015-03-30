package org.wso2.carbon.mdm.mobileservices.windowspc.common;

public final class Constants {

    public static final String DISCOVERY_SERVICE_ENDPOINT =
            "org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.DiscoveryService";
    //Services' target namespaces
    public static final String DISCOVERY_SERVICE_TARGET_NAMESPACE =
            "http://schemas.microsoft.com/windows/management/2012/01/enrollment";

    public final class Discovery {

        public static final String DISCOVERY_SERVICE_TARGET_NAMESPACE =
                "http://schemas.microsoft.com/windows/management/2012/01/enrollment";
        public static final String AUTH_POLICY = "Federated";
        public static final String AUTHENTICATION_SERVICE_URL = "https://enterpriseenrollment.wso2.com/ENROLLMENTSERVER/wab";
        public static final String CERTIFICATE_ENROLLMENT_POLICY_SERVICE_URL =
                "https://EnterpriseEnrollment.wso2" +
                        ".com/ENROLLMENTSERVER/PolicyEnrollmentWebservice" +
                        ".svc";
        public static final String CERTIFICATE_ENROLLMENT_SERVICE_URL =
                "https://EnterpriseEnrollment.wso2" +
                        ".com/ENROLLMENTSERVER/DeviceEnrollmentWebservice" +
                        ".svc";
    }

    public final class CertificateEnrollmentPolicy{
        public static final String REQUEST_WRAPPER_CLASS_NAME =
                "com.microsoft.schemas.windows.pki._2009._01.enrollmentpolicy.GetPolicies";
        public static final String ENROLLMENT_POLICY_TARGET_NAMESPACE = "http://schemas.microsoft.com/windows/pki/2009/01/enrollmentpolicy";
    }
}
