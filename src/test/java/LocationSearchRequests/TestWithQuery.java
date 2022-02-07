package LocationSearchRequests;

import Examples.GetRequestWithParameters;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestWithQuery {
    private static org.apache.log4j.Logger LOG = Logger.getLogger(TestWithQuery.class);


    @Test
    public  void getRequestWithQuery() throws URISyntaxException, IOException {
        BasicConfigurator.configure();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https")
                .setHost("www.metaweather.com")
                .setPath("/api/location/search")
                .setParameter("query","san");
        URI uri = builder.build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpGet);

        DocumentContext docCtx = JsonPath.parse(response.getEntity().getContent());
        JSONArray jsonArray=docCtx.read("$..title");
        System.out.println(jsonArray.toString());
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        Assert.assertNotEquals(response.getEntity().getContentLength(),0);
        Assert.assertTrue(jsonArray.toString().contains("san"));
    }
    @Test
    public  void getRequestWithLattlong() throws URISyntaxException, IOException {
        BasicConfigurator.configure();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https")
                .setHost("www.metaweather.com")
                .setPath("/api/location/search")
                .setParameter("lattlong","22.56,10.66");
        URI uri = builder.build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        DocumentContext docCtx = JsonPath.parse(response.getEntity().getContent());
        JSONArray jsonArray=docCtx.read("$..title");
        Assert.assertEquals(jsonArray.size(),10);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        Assert.assertNotEquals(response.getEntity().getContentLength(),0);
    }

    @Test
    public  void getRequestWithNoParameters() throws IOException {
        BasicConfigurator.configure();
        String url = "https://www.metaweather.com/api/location/1105779/";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        LOG.info("Status code:" + response.getStatusLine().getStatusCode());
        LOG.info("Reason phrase: " + response.getStatusLine().getReasonPhrase());
        DocumentContext docCtx = JsonPath.parse(response.getEntity().getContent());
        JSONArray jsonArray=docCtx.read("$..parent.title");
        System.out.println(jsonArray.toString());
        boolean titleConatinsTitle = jsonArray.toString().contains("[\"Australia\"]");
        Assert.assertTrue(titleConatinsTitle);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        Assert.assertNotEquals(response.getEntity().getContentLength(),0);

    }
}
