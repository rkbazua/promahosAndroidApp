package promahos.remote;

import java.io.IOException;

import promahos.commons.exceptions.PromahosAppException;
import promahos.commons.exceptions.PromahosAppException;

/**
 * Created by rbazua on 17/03/2015.
 */
public interface IRemote {
    String connectSingleResult(RemoteQuery query) throws PromahosAppException, IOException, PromahosAppException, promahos.commons.exceptions.PromahosAppException;
}
