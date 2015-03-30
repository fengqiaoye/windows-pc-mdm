/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.mdm.mobileservices.windowspc.services.xcep.beans;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateEnrollmentPolicy", propOrder = {
		"policyOIDReference",
		"cAs",
		"attributes",
		"any"
})
public class CertificateEnrollmentPolicy {

	protected int policyOIDReference;
	@XmlElement(required = true, nillable = true)
	protected CAReferenceCollection cAs;
	@XmlElement(required = true)
	protected Attributes attributes;
	@XmlAnyElement(lax = true)
	protected List<Object> any;

	public int getPolicyOIDReference() {
		return policyOIDReference;
	}

	public void setPolicyOIDReference(int value) {
		this.policyOIDReference = value;
	}

	public CAReferenceCollection getCAs() {
		return cAs;
	}

	public void setCAs(CAReferenceCollection value) {
		this.cAs = value;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes value) {
		this.attributes = value;
	}

	public List<Object> getAny() {
		if (any == null) {
			any = new ArrayList<Object>();
		}
		return this.any;
	}

}
