package org.wso2.carbon.mdm.mobileservices.windowspc.common;

public final class Constants {

    public static final String DISCOVERY_SERVICE_ENDPOINT =
            "org.wso2.carbon.mdm.mobileservices.windowspc.services.discovery.DiscoveryService";
    //Services' target namespaces
    public static final String DISCOVERY_SERVICE_TARGET_NAMESPACE =
            "http://schemas.microsoft.com/windows/management/2012/01/enrollment";

    //Servlet Context attributes names
    public static final String CONTEXT_WAP_PROVISIONING_FILE = "WAP_PROVISIONING_FILE";
    public static final String CONTEXT_MDM_PASSWORD = "MDM_PASSWORD";
    public static final String CONTEXT_MDM_PRIVATE_KEY_PASSWORD = "MDM_PRIVATE_KEY_PASSWORD";
    public static final String CONTEXT_COMMON_NAME = "COMMON_NAME";
    public static final String CONTEXT_NOT_BEFORE_DATE = "NOT_BEFORE_DATE";
    public static final String CONTEXT_NOT_AFTER_DATE = "NOT_AFTER_DATE";

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

    public final class CertificateEnrollmentPolicy {
        public static final String REQUEST_WRAPPER_CLASS_NAME =
                "com.microsoft.schemas.windows.pki._2009._01.enrollmentpolicy.GetPolicies";
        public static final String ENROLLMENT_POLICY_TARGET_NAMESPACE =
                "http://schemas.microsoft.com/windows/pki/2009/01/enrollmentpolicy";
        public static final String CERTIFICATE_ENROLLMENT_POLICY_SERVICE_ENDPOINT =
                "org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep" +
                        ".CertificateEnrollmentPolicyService";
        public static final String CERTIFICATE_ENROLLMENT_POLICY_SERVICE_TARGET_NAMESPACE =
                "http://schemas.microsoft.com/windows/pki/2009/01/enrollmentpolicy";

        public static final int MINIMAL_KEY_LENGTH = 2048;
        public static final int POLICY_SCHEMA = 3;
        public static final int HASH_ALGORITHM_OID_REFERENCE = 0;
        public static final int OID_REFERENCE = 0;
        public static final String OID = "1.3.14.3.2.29";
        public static final String OID_DEFAULT_NAME = "szOID_OIWSEC_sha1RSASign";
        public static final int OID_GROUP = 1;
        public static final int OID_REFERENCE_ID = 0;
    }

    public final class CertificateEnrollment {
        public static final String SOAP_AUTHORIZATION_TARGET_NAMESPACE =
                "http://schemas.xmlsoap.org/ws/2006/12/authorization";
        public static final String WS_SECURITY_TARGET_NAMESPACE =
                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        public static final String WS_TRUST_TARGET_NAMESPACE =
                "http://docs.oasis-open.org/ws-sx/ws-trust/200512";
        public static final String DEVICE_ENROLLMENT_SERVICE_TARGET_NAMESPACE =
                "http://schemas.microsoft.com/windows/pki/2009/01/enrollment/RSTRC";
        public static final String CERTIFICATE_ENROLLMENT_SERVICE_ENDPOINT =
                "org.wso2.carbon.mdm.mobileservices.windowspc.services.wstep" +
                        ".CertificateEnrollmentService";
        public static final String TOKEN_TYPE =
                "http://schemas.microsoft.com/5.0.0" +
                        ".0/ConfigurationManager/Enrollment/DeviceEnrollmentToken";
        public static final String PARM = "parm";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        public static final String VALUE_TYPE =
                "http://schemas.microsoft.com/5.0.0" +
                        ".0/ConfigurationManager/Enrollment/DeviceEnrollmentProvisionDoc";
        public static final String ENCODING_TYPE =
                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0" +
                        ".xsd#base64binary";
        public static final String WSO2_MDM_JKS_FILE = "wso2mdm.jks";
        public static final String CA_CERT = "cacert";
        public static final String X_509 = "X.509";
        public static final String PROPERTIES_XML = "properties.xml";
        public static final String MDM_PASSWORD = "emmpassword";
        public static final String MDM_PRIVATE_KEY_PASSWORD = "emmprivatekeypassword";
        public static final String WAP_PROVISIONING_XML = "wap-provisioning.xml";
        public static final String PROVIDER = "BC";
        public static final String ALGORITHM = "SHA1withRSA";
        public static final String JKS = "JKS";
        public static final String SECURITY = "Security";
        public static final String WSS_SECURITY_UTILITY =
                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0" +
                        ".xsd";
        public static final String TIMESTAMP_ID = "Id";
        public static final String TIMESTAMP_U = "u";
        public static final String TIMESTAMP = "Timestamp";
        public static final String TIMESTAMP_0 = "_0";
        public static final String CREATED = "Created";
        public static final String EXPIRES = "Expires";
        public static final String UTF_8 = "utf-8";
        public static final String CONTENT_LENGTH = "Content-Length";


    }
}
