package promahos.remote;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.charset.Charset;

import promahos.rbazua.commons.dto.Usuario;
import promahos.rbazua.commons.exceptions.PromahosAppException;

/**
 * Created by rbazua on 25/03/2015.
 */
public class AuthRemoteTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void instantiate(){
        AbstractRemote auth = new AuthRemote();
    }

    @Test
    public void failedAuthenticationTest() throws PromahosAppException, IOException {
        String expected = new String("Falló la autenticación".getBytes(Charset.forName("ISO-8859-1")));
        thrown.expectMessage(expected);
        thrown.expect(PromahosAppException.class);
        AuthRemote auth = new AuthRemote();
        Usuario usuario = auth.authenticate("emailinexistente", "passwordNoValido");
    }

    @Test
    public void invalidParamsAuthenticationTest() throws PromahosAppException, IOException {
        String expected = new String("Parámetros no válidos".getBytes(Charset.forName("ISO-8859-1")));
        thrown.expectMessage(expected);
        thrown.expect(PromahosAppException.class);
        AuthRemote auth = new AuthRemote();
        Usuario usuario = auth.authenticate("emailinexistente", null);
    }

    @Test
    public void correctAuthenticationTest() throws PromahosAppException, IOException {
        AuthRemote auth = new AuthRemote();
        Usuario usuario = auth.authenticate("rkbazua@gmail.com", "hola123");
        usuario.getRol();
    }
}
