package Task2;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@SuppressWarnings("deprecation")
public class HackBulgariaCourses {

    public static void main(String[] args) throws MalformedURLException, IOException, JSONException {

        HttpGet request = new HttpGet("https://hackbulgaria.com/api/students/");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String entityContents = EntityUtils.toString(entity);
        String pattern = "";
        String[] jObjects = entityContents.split("\"}, ");
        for (int i = 0; i < jObjects.length; i++) {
            pattern = jObjects[i];
            if (pattern.startsWith("[")) {
                pattern = jObjects[i].substring(1);
            }

            pattern = pattern + "\"}, ";
            JSONObject obj = new JSONObject(pattern);
            JSONArray arr = obj.getJSONArray("courses");
            if (arr.length() > 1) {
                
                System.out.println(++i + " " + obj.getString("name") + obj.getString("courses") + "\n");
            }

        }
    }

}
