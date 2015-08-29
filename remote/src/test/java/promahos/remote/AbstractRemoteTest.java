
package promahos.remote;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.charset.Charset;

import promahos.commons.exceptions.PromahosAppException;

/**
 * Created by rbazua on 23/03/2015.
 */
public class AbstractRemoteTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    public class MockRemote extends AbstractRemote{
    }

    @Test
    public void instanceTest(){
        AbstractRemote remote = new MockRemote();
    }

    @Test
    public void succesfulConnectTest() throws PromahosAppException, IOException {
        String expected = new String("{\"INFO\":\"Felicidades. Te has conectado. Ñ-ñ-Á-É-Í-Ó-Ú-á-é-í-ó-ú-\"}".getBytes(Charset.forName("ISO-8859-1")));
         AbstractRemote remote = new MockRemote();
         String result = remote.connectSingleResult(new RemoteQueryBuilder().httpMethod("GET").build());
         Assert.assertEquals(expected, result);
    }

    @Test(expected = RuntimeException.class)
    public void notFoundErrorTest() throws IOException, PromahosAppException {
        AbstractRemote remote = new MockRemote();
        String result = remote.connectSingleResult(new RemoteQueryBuilder().httpMethod("GET").route("/doesntexist").build());
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("NOT_FOUND: 404");
    }

    @Test(expected = RuntimeException.class)
    public void serverErrorTest()throws IOException, PromahosAppException{
        AbstractRemote remote = new MockRemote();
        String result = remote.connectSingleResult(new RemoteQueryBuilder().httpMethod("GET").route("/serverError").build());
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("SERVER_ERROR: 500");
    }

    @Test(expected = PromahosAppException.class)
    public void customErrorTest()throws IOException, PromahosAppException{
        AbstractRemote remote = new MockRemote();
        String result = remote.connectSingleResult(new RemoteQueryBuilder().httpMethod("GET").route("/customError").build());
        thrown.expect(PromahosAppException.class);
        thrown.expectMessage("CUSTOM");
    }
}
