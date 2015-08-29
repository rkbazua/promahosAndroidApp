package promahos.remote;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import promahos.commons.exceptions.PromahosAppException;

/**
 * Created by rbazua on 17/03/2015.
 */
public abstract class AbstractRemote implements IRemote {
    @Override
    public String connectSingleResult(RemoteQuery query) throws PromahosAppException, IOException {
        String result = "";
        HttpURLConnection conn = null;
        BufferedReader br = null;
        try {
            URL url = new URL(query.getCompleteRoute());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(query.getHttpMethod());
            conn.setRequestProperty("Accept", "application/json");

            StringBuilder inputParams = new StringBuilder();
            if (query.getParams() != null && !query.getParams().isEmpty()) {
                for (Map.Entry<String, String> param : query.getParams().entrySet()) {
                    inputParams.append(param.getKey()).append("=").append(param.getValue()).append("&");
                }
                inputParams.deleteCharAt(inputParams.length()-1);
            }

            if(conn.getRequestMethod().equals("POST")){
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.write(inputParams.toString().getBytes("UTF-8"));
                wr.flush();
                wr.close();
            }

            if (conn.getResponseCode() == 404) {
                throw new RuntimeException("NOT_FOUND: 404");
            } else if(conn.getResponseCode() == 500){
                throw new RuntimeException("SERVER_ERROR: 500");
            } else if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code: "
                        + conn.getResponseCode());
            }

            br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "ISO-8859-1"));

            String line = "";
            StringBuilder output = new StringBuilder();
            char[] buffer = new char[1000];
            while ((br.read(buffer)) != -1) {
                output.append(buffer);
            }

            result = output.toString();

            if(result!=null){
                result = result.replaceAll("\\u0000", "")
                .replace("\\u00d1", new String("Ñ".getBytes("ISO-8859-1")))
                .replace("\\u00f1", new String("ñ".getBytes("ISO-8859-1")))
                .replace("\\u00c1", new String("Á".getBytes("ISO-8859-1")))
                .replace("\\u00c9", new String("É".getBytes("ISO-8859-1")))
                .replace("\\u00cd", new String("Í".getBytes("ISO-8859-1")))
                .replace("\\u00d3", new String("Ó".getBytes("ISO-8859-1")))
                .replace("\\u00da", new String("Ú".getBytes("ISO-8859-1")))
                .replace("\\u00e1", new String("á".getBytes("ISO-8859-1")))
                .replace("\\u00e9", new String("é".getBytes("ISO-8859-1")))
                .replace("\\u00ed", new String("í".getBytes("ISO-8859-1")))
                .replace("\\u00f3", new String("ó".getBytes("ISO-8859-1")))
                .replace("\\u00fa", new String("ú".getBytes("ISO-8859-1")))
                ;
                System.out.println(result);
            }

            if(result!=null && result.contains("{\"ERROR\":")){
                String errorMessage = result.replace("{\"ERROR\":", "").replace("}","").replaceAll("\"","");
                throw new PromahosAppException(errorMessage);
            }
            conn.disconnect();
        }finally {
            if(conn != null){conn.disconnect();}
            if (br != null) {br.close();}
        }
        return result;
    }
}
