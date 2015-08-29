package promahos.remote;

import java.util.Map;

/**
 * Created by rbazua on 23/03/2015.
 */
public class RemoteQueryBuilder {
    private RemoteQuery query;

    public RemoteQueryBuilder() {
        this.query = new RemoteQuery();
    }

    public RemoteQueryBuilder protocol(String protocol){
        query.setProtocol(protocol);
        return this;
    }

    public RemoteQueryBuilder host(String host){
        query.setHost(host);
        return this;
    }

    public RemoteQueryBuilder port(String port){
        query.setPort(port);
        return this;
    }

    public RemoteQueryBuilder route(String route){
        query.setRoute(route);
        return this;
    }

    public RemoteQueryBuilder httpMethod(String httpMethod){
        query.setHttpMethod(httpMethod);
        return this;
    }

    public RemoteQueryBuilder payload(String payload){
        query.setPayload(payload);
        return this;
    }

    public RemoteQueryBuilder params(Map<String,String> params){
        query.setParams(params);
        return this;
    }

    public RemoteQuery build(){
        return this.query;
    }
}
