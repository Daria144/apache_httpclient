package Examples;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GetRequestWithoutParameters {
    private static org.apache.log4j.Logger LOG = Logger.getLogger(GetRequestWithoutParameters.class);

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        String url = "https://github.com/";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url); //OTMe4aeM, 4TO MeTOA 3anpoca GET
        CloseableHttpResponse response = httpclient.execute(httpGet);
        LOG.info("Status code:" + response.getStatusLine().getStatusCode());
        LOG.info("Reason phrase: " + response.getStatusLine().getReasonPhrase());
        for (Header header : response.getAllHeaders()) {
            LOG.info(header.getName() + ":" + header.getValue());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String data = IOUtils.toString(entity.getContent(), "utf8");
                LOG.info("Data:" + data);
            } else {
                LOG.info("Empty response");
            }
        }
    }
}
