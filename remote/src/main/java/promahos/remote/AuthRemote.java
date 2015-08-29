package promahos.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import promahos.commons.dto.Usuario;
import promahos.commons.exceptions.PromahosAppException;

/**
 * Created by rbazua on 25/03/2015.
 */
public class AuthRemote extends AbstractRemote{
    public Usuario authenticate(String email, String pass) throws PromahosAppException, IOException {
        Map<String,String> parameters = new HashMap<>();
        if(email!=null){
            parameters.put("email",email);
        }
        if(pass!=null){
            parameters.put("password", pass);
        }

        RemoteQuery remoteQuery = new RemoteQueryBuilder()
                .host("localhost")
                .port("80")
                .httpMethod("POST")
                .protocol("http")
                .params(parameters)
                .route("auth")
                .build();

        String jsonResult = connectSingleResult(remoteQuery);
        Gson gson = new GsonBuilder().create();
        Usuario usuario = gson.fromJson(jsonResult, Usuario.class);
        return usuario;
    }
}
