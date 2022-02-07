package Examples;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GetRequestWithParameters {
    private static org.apache.log4j.Logger LOG = Logger.getLogger(GetRequestWithParameters.class);

    public static void main(String[] args) throws URISyntaxException, IOException {
        BasicConfigurator.configure();
        /*Request info:
         * url=https://www.metaweather.com/api/location/search/?query=london";*/
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https")
                .setHost("www.metaweather.com")
                .setPath("/api/location/search")
                .setParameter("query", "London");
        URI uri = builder.build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpGet);
    }
}
