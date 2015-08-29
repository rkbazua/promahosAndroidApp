package promahos.remote;

import java.util.Map;

/**
 * Created by rbazua on 23/03/2015.
 */
public class RemoteQuery {

    private String protocol = "http";
    private String host = "localhost";
    private String port = "80";
    private String route = "/";
    private String httpMethod = "GET";
    private String payload;
    private Map<String, String> params;

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCompleteRoute(){
        return new StringBuilder(protocol).append("://").append(host).append(":").append(port).append("/").append(route.replace("/", "")).toString();
    }
}
