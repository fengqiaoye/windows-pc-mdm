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

package org.wso2.carbon.mdm.mobileservices.windowspc.services.syncml.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wso2.carbon.device.mgt.common.DeviceManagementException;
import org.wso2.carbon.mdm.mobileservices.windowspc.common.Constants;
import org.wso2.carbon.mdm.mobileservices.windowspc.common.exceptions.FileOperationException;
import org.wso2.carbon.mdm.mobileservices.windowspc.common.exceptions.WindowsDeviceEnrolmentException;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.push.impl.PushNotificationServiceImpl;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.syncml.SyncmlService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.wso2.carbon.device.mgt.common.Device;
import org.wso2.carbon.device.mgt.common.DeviceManagementConstants;

/**
 * Implementing class of SyncmlImpl interface.
 */
public class SyncmlServiceImpl implements SyncmlService {

    private static final String SYNCML_FIRST_MESSAGE = "1";
    private static final String SYNCML_SECOND_MESSAGE = "2";
    private static String CONFIGURATION_STATUS = "0";    //me
    private static final int SYNCML_MESSAGE_POSITION = 0;
    private static final int SYNCML_ITEM_DATA_POSITION = 1;
    private static final String VENDOR = "vendor";
    private static final String MODEL = "model";
    private static final String DEVID = "DevId";
    private static final String LANG = "lang";


    private static String channelURI = "";

    private enum DevicePropertyIndex {
        DEVICE_ID(2),
        DEVICE_MANUFACTURER(3),
        DEVICE_MODEL(4),
        DEVICE_LANGUAGE(5);
        private final int itemPosition;

        private DevicePropertyIndex(final int itemPosition) {
            this.itemPosition = itemPosition;
        }

        public int getValue() {
            return this.itemPosition;
        }
    }

    private static Log log = LogFactory.getLog(SyncmlServiceImpl.class);

    /**
     * This method resolves the Syncml messages received through device and send the
     * response accordingly.
     *
     * @param request - Syncml request comes through the device
     * @return - Syncml response generated for the request
     */

    @Override
    public Response getInitialResponse(Document request) throws
            FileOperationException, DeviceManagementException, WindowsDeviceEnrolmentException {

        Node headerNode = request.getElementsByTagName(Constants.SyncML.SYNC_ML).item(SYNCML_MESSAGE_POSITION).
                getFirstChild();
        Node bodyNode = request.getElementsByTagName(Constants.SyncML.SYNC_ML).item(SYNCML_MESSAGE_POSITION).
                getChildNodes().item(SYNCML_ITEM_DATA_POSITION);
        NodeList nodeListHeader = headerNode.getChildNodes();
        NodeList nodeListBody = bodyNode.getChildNodes();

        String targetURI = null;
        String sourceURI = null;
        String msgID = null;
        String devId;
        String manufacturer;
        String devMod;
        String devLang;

        for (int i = 0; i < nodeListHeader.getLength(); i++) {
            Node node = nodeListHeader.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                String nodeName = node.getNodeName();

                if (Constants.SyncML.SYNCML_MSG_ID.equals(nodeName)) {
                    msgID = node.getTextContent().trim();
                    if (log.isDebugEnabled()) {
                        log.debug("Request SyncML message ID: " + msgID);
                    }
                }
                if (Constants.SyncML.SYNCML_MESSAGE_ONE.equals(msgID)) {
                    if (Constants.SyncML.SYNCML_TARGET.equals(nodeName)) {
                        targetURI = node.getFirstChild().getTextContent().trim();
                    } else if (Constants.SyncML.SYNCML_SOURCE.equals(nodeName)) {
                        sourceURI = node.getFirstChild().getTextContent().trim();
                    }
                }
            }
        }

        for (int i = 0; i < nodeListBody.getLength(); i++) {
            Node node = nodeListBody.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                String nodeName = node.getNodeName();

                if ((Constants.SyncML.SYNCML_MESSAGE_ONE.equals(msgID)) &&
                        (Constants.SyncML.SYNCML_REPLACE.equals(nodeName))) {

                    NodeList childNodes = node.getChildNodes();

                    devId = childNodes.item(DevicePropertyIndex.DEVICE_ID.getValue()).getTextContent();
                    manufacturer = childNodes.item(DevicePropertyIndex.DEVICE_MANUFACTURER.getValue()).getTextContent();
                    devMod = childNodes.item(DevicePropertyIndex.DEVICE_MODEL.getValue()).getTextContent();
                    devLang = childNodes.item(DevicePropertyIndex.DEVICE_LANGUAGE.getValue()).getTextContent();

                    Device generatedDevice =
                            generateDevice(DeviceManagementConstants.MobileDeviceTypes.
                                    MOBILE_DEVICE_TYPE_WINDOWS, devId, manufacturer, devMod, devLang);
//                    try {
//                        SyncmlUtils.getDeviceManagementService()
//                                .enrollDevice(generatedDevice);
//                    } catch (DeviceManagementException e) {
//                        String msg = "Exception while getting Device Management Service.";
//                        log.error(msg, e);
//                        throw new WindowsDeviceEnrolmentException(msg, e);
//                    }
// catch (DeviceManagementServiceException e) {
//						String msg = "Exception while enrolling device after receiving data.";
//						log.error(msg, e);
//						throw new DeviceManagementServiceException(msg, e);
//					}
                } else if (msgID.equals("3") && (Constants.SyncML.SYNCML_RESULTS.equals(nodeName))) {
                    NodeList nodeList = request.getElementsByTagName("Data");
                    CONFIGURATION_STATUS = nodeList.item(4).getTextContent();
                } else if (msgID.equals("4") && (Constants.SyncML.SYNCML_RESULTS.equals(nodeName))) {
                    NodeList nodeList = request.getElementsByTagName("Data");
//                    String channelURI = nodeList.item(5).getTextContent();
                    channelURI = nodeList.item(5).getTextContent();
//                    setChannelURI(channelURI);
                    log.debug(channelURI);
                }
            }
        }
        String response = prepareResponse(msgID, targetURI, sourceURI);
        return Response.ok().entity(response).build();
    }

    /**
     * This method is used to generate and return Device object from the received information at
     * the Syncml step.
     *
     * @param deviceID     - Unique device ID received from the Device
     * @param manufacturer - Device Manufacturer name
     * @param model        - Device Model
     * @return - Generated device object
     */
    //TODO:use composite type
    private Device generateDevice(String type, String deviceID, String manufacturer,String model, String lang) {

        Device generatedDevice = new Device();

        Device.Property DevManProperty = new Device.Property();
        DevManProperty.setName(VENDOR);
        DevManProperty.setValue(manufacturer);

        Device.Property DevModProperty = new Device.Property();
        DevModProperty.setName(MODEL);
        DevModProperty.setValue(model);

        Device.Property DevIdProperty = new Device.Property();
        DevModProperty.setName(DEVID);
        DevModProperty.setValue(deviceID);

        Device.Property DevLangProperty = new Device.Property();
        DevModProperty.setName(LANG);
        DevModProperty.setValue(deviceID);

        List<Device.Property> propertyList = new ArrayList<Device.Property>();
        propertyList.add(DevManProperty);
        propertyList.add(DevModProperty);
        propertyList.add(DevIdProperty);
        propertyList.add(DevLangProperty);

        generatedDevice.setDeviceIdentifier(deviceID);
        generatedDevice.setProperties(propertyList);
        generatedDevice.setType(type);

        return generatedDevice;
    }

    /**
     * This method prepares the SyncML response.
     *
     * @param msgID     - Incoming message ID
     * @param targetURI - Target URI in SyncML message
     * @param sourceURI - Sourse URI in SyncML message
     * @return - Returns the SyncML response as a String
     * @throws FileOperationException
     */
    private String prepareResponse(String msgID, String targetURI, String sourceURI) throws
            FileOperationException {

        String response = null;
        String responseFilePath;
        File responseFile;
        try {
            if (SYNCML_FIRST_MESSAGE.equals(msgID)) {
                responseFile = new File(getClass().getClassLoader().getResource(Constants.SyncML.
                        SYNCML_RESPONSE).getFile());
                responseFilePath = responseFile.getPath();
                response = new String(Files.readAllBytes(Paths.get(responseFilePath)));
                if ((targetURI != null) && (sourceURI != null)) {
                    response = response.replaceAll(Constants.SyncML.SYNCML_SOURCE_URI, targetURI);
                    response = response.replaceAll(Constants.SyncML.SYNCML_TARGET_URI, sourceURI);
                }
            } else if (SYNCML_SECOND_MESSAGE.equals(msgID)) {
                responseFile = new File(getClass().getClassLoader().getResource(Constants.SyncML.
                        SYNCML_SECOND_RESPONSE).getFile());
                responseFilePath = responseFile.getPath();
                response = new String(Files.readAllBytes(Paths.get(responseFilePath)));

                response = response.replaceAll(Constants.SyncML.MSG_ID, msgID);
                if ((targetURI != null) && (sourceURI != null)) {
                    response = response.replaceAll(Constants.SyncML.SYNCML_SOURCE_URI, targetURI);
                    response = response.replaceAll(Constants.SyncML.SYNCML_TARGET_URI, sourceURI);
                }

            } else if (msgID.equals("3") && CONFIGURATION_STATUS.equals("0")) {
                responseFile = new File(getClass().getClassLoader().getResource(Constants.SyncML.
                        SYNCML_PROCEED_RESPONSE).getFile());
                responseFilePath = responseFile.getPath();
                response = new String(Files.readAllBytes(Paths.get(responseFilePath)));
                response = response.replaceAll(Constants.SyncML.MSG_ID, msgID);
            } else if (msgID.equals("3") && CONFIGURATION_STATUS.equals("1")) {
                responseFile = new File(getClass().getClassLoader().getResource(Constants.SyncML.
                        SYNCML_SESSION_STATUS_POLL_RESPONSE).getFile());
                responseFilePath = responseFile.getPath();
                response = new String(Files.readAllBytes(Paths.get(responseFilePath)));
                response = response.replaceAll(Constants.SyncML.MSG_ID, msgID);
            } else if (msgID.equals("4")) {
                responseFile = new File(getClass().getClassLoader().getResource(Constants.SyncML.
                        SYNCML_SESSION_STATUS_POLL_RESPONSE).getFile());
                responseFilePath = responseFile.getPath();
                response = new String(Files.readAllBytes(Paths.get(responseFilePath)));
                response = response.replaceAll(Constants.SyncML.MSG_ID, msgID);
            } else if (msgID.equals("5")) {
                responseFile = new File(getClass().getClassLoader().getResource(Constants.SyncML.
                        SYNCML_SESSION_STATUS_POLL_RESPONSE).getFile());
                responseFilePath = responseFile.getPath();
                response = new String(Files.readAllBytes(Paths.get(responseFilePath)));
                response = response.replaceAll(Constants.SyncML.MSG_ID, msgID);
            } else if (msgID.equals("6")) {
                responseFile = new File(getClass().getClassLoader().getResource(Constants.SyncML.
                        SYNCML_PUSH_EXEC_RESPONSE).getFile());
                responseFilePath = responseFile.getPath();
                response = new String(Files.readAllBytes(Paths.get(responseFilePath)));
                response = response.replaceAll(Constants.SyncML.MSG_ID, msgID);
                if (!channelURI.equals("404")) {
                    String payload = "dummy";
                    String notificationType = "wns/raw";
                    String contentType = "application/octet-stream";
//                    String channelURI = getChannelURI();
                    PushNotificationServiceImpl.pushToWns(Constants.PUSH.CLIENT_SECRET, Constants.PUSH.SID, channelURI, payload, notificationType, contentType);
                }
            }
        } catch (IOException e) {
            String msg = "Syncml response file cannot be read.";
            log.error(msg, e);
            throw new FileOperationException(msg, e);
        }
        return response;
    }

    @Resource
    private WebServiceContext context;

    private void setChannelURI(String channelURI) {
        ServletContext ctx = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        ctx.setAttribute("channelURI", channelURI);
    }

    private String getChannelURI() {
        ServletContext ctx = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        String channelURI = (String) ctx.getAttribute("channelURI");
        return channelURI;
    }
}
