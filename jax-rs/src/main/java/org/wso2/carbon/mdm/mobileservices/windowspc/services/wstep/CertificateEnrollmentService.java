package org.wso2.carbon.mdm.mobileservices.windowspc.services.wstep;

import org.wso2.carbon.mdm.mobileservices.windowspc.common.Constants;
import org.wso2.carbon.mdm.mobileservices.windowspc.common.exceptions.WindowsDeviceEnrolmentException;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.wstep.beans.AdditionalContext;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.wstep.beans.RequestSecurityTokenResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.SOAPBinding;

@WebService(targetNamespace = Constants.CertificateEnrollment.DEVICE_ENROLLMENT_SERVICE_TARGET_NAMESPACE, name = "wstep")
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface CertificateEnrollmentService {

    @RequestWrapper(localName = "RequestSecurityToken", targetNamespace = Constants
            .CertificateEnrollment.WS_TRUST_TARGET_NAMESPACE)
    @WebMethod(operationName = "RequestSecurityToken")
    @ResponseWrapper(localName = "RequestSecurityTokenResponseCollection", targetNamespace =
            Constants.CertificateEnrollment.WS_TRUST_TARGET_NAMESPACE)
    public void requestSecurityToken(
            @WebParam(name = "TokenType", targetNamespace = Constants.CertificateEnrollment.WS_TRUST_TARGET_NAMESPACE)
            String tokenType,
            @WebParam(name = "RequestType", targetNamespace = Constants.CertificateEnrollment.WS_TRUST_TARGET_NAMESPACE)
            String requestType,
            @WebParam(name = "BinarySecurityToken", targetNamespace = Constants
                    .CertificateEnrollment.WS_SECURITY_TARGET_NAMESPACE)
            String binarySecurityToken,
            @WebParam(name = "AdditionalContext", targetNamespace = Constants
                    .CertificateEnrollment.SOAP_AUTHORIZATION_TARGET_NAMESPACE)
            AdditionalContext additionalContext,
            @WebParam(mode = WebParam.Mode.OUT, name = "RequestSecurityTokenResponse",
                    targetNamespace = Constants.CertificateEnrollment.WS_TRUST_TARGET_NAMESPACE)
            javax.xml.ws.Holder<RequestSecurityTokenResponse> response) throws
            WindowsDeviceEnrolmentException;
}