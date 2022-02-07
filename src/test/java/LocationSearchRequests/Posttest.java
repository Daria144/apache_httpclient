package LocationSearchRequests;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.BasicConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Posttest {

    @Test
    public void createUser() throws IOException {
        BasicConfigurator.configure();
        String url = "https://reqres.in/api/users";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url);
        String jsonBody = "{\n" +
                "    \"name\": \"dasha\",\n" +
                "    \"job\": \"autotest\"\n" +
                "}";
        StringEntity stringEntity=new StringEntity(jsonBody);
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Accept","application/json");
        httpPost.setHeader("Content-type","application/json");
        CloseableHttpResponse response = httpClient.execute(httpPost);


//        DocumentContext docCtx = JsonPath.parse(response.getEntity().getContent());
//        JSONArray jsonArray=docCtx.read("$..id");
//        System.out.println(jsonArray.toString());
//        HttpGet httpGet = new HttpGet(url);
//        CloseableHttpResponse responseGet = httpClient.execute(httpGet);
//        DocumentContext docCtxGet = JsonPath.parse(responseGet.getEntity().getContent());
//        JSONArray jsonArrayGet=docCtxGet.read("$..data.id");
//        System.out.println(jsonArrayGet.toString());


    }

    @Test
    public void unsuccessfulLoginUser() throws IOException {
        BasicConfigurator.configure();
        String url = "https://reqres.in/api/register";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url);
        String json ="{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}";
        httpPost.setHeader("Accept","application/json");
        httpPost.setHeader("Content-type","application/json");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),400);
        DocumentContext docCtx = JsonPath.parse(response.getEntity().getContent());
        JSONArray jsonArray=docCtx.read("$..error");
        Assert.assertEquals(jsonArray.toString(),"[\"Missing email or username\"]");

    }



}
