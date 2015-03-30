package org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep;

import org.wso2.carbon.mdm.mobileservices.windowspc.common.Constants;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans.Client;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans.RequestFilter;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans.Response;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.SOAPBinding;

@WebService(targetNamespace = "http://docs.oasis-open.org/ws-sx/ws-trust/200512", name = "IPolicy")
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public interface CertificateEnrollmentPolicyService {

    @RequestWrapper(localName = "GetPolicies", targetNamespace = Constants.CertificateEnrollmentPolicy.ENROLLMENT_POLICY_TARGET_NAMESPACE, className = Constants.CertificateEnrollmentPolicy.REQUEST_WRAPPER_CLASS_NAME)
    @WebMethod(operationName = "GetPolicies")
    @ResponseWrapper(localName = "GetPoliciesResponse", targetNamespace = Constants.CertificateEnrollmentPolicy.ENROLLMENT_POLICY_TARGET_NAMESPACE, className = Constants.CertificateEnrollmentPolicy.REQUEST_WRAPPER_CLASS_NAME)
    void getPolicies(@WebParam(name = "client", targetNamespace = Constants.CertificateEnrollmentPolicy.ENROLLMENT_POLICY_TARGET_NAMESPACE) Client client, @WebParam(name = "requestFilter", targetNamespace = Constants.CertificateEnrollmentPolicy.ENROLLMENT_POLICY_TARGET_NAMESPACE) RequestFilter requestFilter,
                     @WebParam(mode = WebParam.Mode.OUT, name = "response", targetNamespace = Constants.
                             CertificateEnrollmentPolicy.ENROLLMENT_POLICY_TARGET_NAMESPACE)
                     javax.xml.ws.Holder<Response> response);
}
