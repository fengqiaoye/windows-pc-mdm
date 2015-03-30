package org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.mdm.mobileservices.windowspc.common.Constants;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.CertificateEnrollmentPolicyService;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans.*;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.SOAPBinding;

@WebService(endpointInterface = Constants.CertificateEnrollmentPolicy.CERTIFICATE_ENROLLMENT_POLICY_SERVICE_ENDPOINT,
        targetNamespace = Constants.CertificateEnrollmentPolicy.CERTIFICATE_ENROLLMENT_POLICY_SERVICE_TARGET_NAMESPACE)
@Addressing(enabled = true, required = true)
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public class CertificateEnrollmentPolicyServiceImpl implements CertificateEnrollmentPolicyService {

    private static Log logger = LogFactory.getLog(CertificateEnrollmentPolicyServiceImpl.class);

    /**
     * This method implements the MS-XCEP protocol for certificate enrollment policy service.
     *
     * @param client        - Included lastUpdate and preferredLanguage tags
     * @param requestFilter - Policy constrain tag
     * @param response      - Response which includes minimal key length, hash algorithm, policy
     *                         schema, policy OID reference
     * @param CACollection  - Contains the issuers for the certificate enrollment policies
     * @param OIDCollection - Contains the collection of OIDs for the response
     */
    @Override
    public void getPolicies(Client client, RequestFilter requestFilter,
                            Holder<Response> response, Holder<CACollection> CACollection,
                            Holder<OIDCollection> OIDCollection) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enrolment certificate policy end point was triggered by device.");
        }

        Response responseElement = new Response();
        OIDCollection OIDCollectionElement = new OIDCollection();
        CACollection CACollectionElement = new CACollection();

        PolicyCollection policyCollectionElement = new PolicyCollection();

        CertificateEnrollmentPolicy certEnrollmentPolicyElement = new CertificateEnrollmentPolicy();
        Attributes attributeElement = new Attributes();
        PrivateKeyAttributes privateKeyAttributeElement = new PrivateKeyAttributes();

        privateKeyAttributeElement.
                setMinimalKeyLength(Constants.CertificateEnrollmentPolicy.MINIMAL_KEY_LENGTH);

        attributeElement.setPolicySchema(Constants.CertificateEnrollmentPolicy.POLICY_SCHEMA);
        attributeElement.setPrivateKeyAttributes(privateKeyAttributeElement);
        attributeElement.setHashAlgorithmOIDReference(Constants.CertificateEnrollmentPolicy.
                HASH_ALGORITHM_OID_REFERENCE);
        certEnrollmentPolicyElement.setPolicyOIDReference(Constants.CertificateEnrollmentPolicy.
                OID_REFERENCE);
        certEnrollmentPolicyElement.setAttributes(attributeElement);
        policyCollectionElement.getPolicy().add(certEnrollmentPolicyElement);
        responseElement.setPolicies(policyCollectionElement);
        response.value = responseElement;

        OID OIDElement = new OID();
        OIDElement.setValue(Constants.CertificateEnrollmentPolicy.OID);
        OIDElement.setGroup(Constants.CertificateEnrollmentPolicy.OID_GROUP);
        OIDElement.setOIDReferenceID(Constants.CertificateEnrollmentPolicy.OID_REFERENCE_ID);
        OIDElement.setDefaultName(Constants.CertificateEnrollmentPolicy.OID_DEFAULT_NAME);

        OIDCollectionElement.getOID().add(OIDElement);
        CACollection.value = CACollectionElement;
        OIDCollection.value = OIDCollectionElement;
    }
}

