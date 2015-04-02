package org.wso2.carbon.mdm.mobileservices.windowspc.services.push.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.mdm.mobileservices.windowspc.services.push.beans.OAuthToken;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class WNSAuthenticator {

    private static Log log = LogFactory.getLog(WNSAuthenticator.class);

    private static final String AUTHENTICATION_URL = "https://login.live.com/accesstoken.srf";

    public static OAuthToken getAccessToken(String secret, String sId) throws UnsupportedEncodingException, MalformedURLException {

        String urlEncodedSecret = URLEncoder.encode(secret, "UTF-8");
        String urlEncodedSid = URLEncoder.encode(sId, "UTF-8");

        String body = getMessegeBody(urlEncodedSecret, urlEncodedSid);
        URL url = new URL(AUTHENTICATION_URL);
        String jsonResponse = sendPost(url, body);

        return getOAuthTokenFromJson(jsonResponse);
    }

    public static String sendPost(String uri, String body, String contentType, String notificationType, String accessToken){

        DataOutputStream writer;
        BufferedReader input;
        StringBuffer response = new StringBuffer();
        String pushResponse = null;
        try{
            URL channelUri = new URL(uri);
            HttpsURLConnection connection = (HttpsURLConnection) channelUri.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("X-WNS-Type", notificationType);
            connection.setRequestProperty("Authorization","Bearer "+accessToken);
            connection.setRequestProperty("Content-Length", "5");
            connection.setDoOutput(true);

            writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(body);
            writer.flush();
            writer.close();
            Map<String, List<String>> map = connection.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                pushResponse = "Key : " + entry.getKey() +
                        " ,Value : " + entry.getValue();
                log.debug(pushResponse);
            }
            input = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;


            while ((inputLine = input.readLine()) != null) {
                response.append(inputLine);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pushResponse;
    }

    private static OAuthToken getOAuthTokenFromJson(String jsonString) {
        Gson gson = new GsonBuilder().create();
        OAuthToken authToken = gson.fromJson(jsonString, OAuthToken.class);
        return authToken;
    }

    private static String getMessegeBody(String encodedSecret, String encodedSid) {

        String messegeBody = "grant_type=client_credentials&client_id=" + encodedSid + "&client_secret=" + encodedSecret + "&scope=notify.windows.com";
        return messegeBody;
    }

    private static String sendPost(URL url, String body) {

        String jsonResponse = null;
        DataOutputStream writer;
        BufferedReader input;

        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Host", "https://login.live.com");
            connection.setRequestProperty("Content-Length", "211");

            connection.setDoOutput(true);
            writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(body);
            writer.flush();
            writer.close();

            input = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = input.readLine()) != null) {
                response.append(inputLine);
            }
            input.close();
            jsonResponse = response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return jsonResponse;
    }
}
