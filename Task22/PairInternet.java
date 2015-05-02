package Task2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class PairInternet {

    public static Map InternetPair() throws ClientProtocolException, IOException, JSONException {
        HttpGet request = new HttpGet("https://hackbulgaria.com/api/checkins/");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String entityContents = EntityUtils.toString(entity);
        String pattern = "";

        Map<String, Integer> map = new HashMap<String, Integer>();

        String[] jObjects = entityContents.split("\"}, ");
        for (int i = 0; i < jObjects.length; i++) {
            pattern = jObjects[i];
            if (pattern.startsWith("[")) {
                pattern = jObjects[i].substring(1);
            }
            int t = 0;
            pattern = pattern + "\"}, ";
            JSONObject obj = new JSONObject(pattern);
            if (!map.containsKey(obj.getString("student_name"))) {
                map.put(obj.getString("student_name"), t);

            }

            if (map.containsKey(obj.getString("student_name"))) {
                Integer value = (Integer) map.get(obj.getString("student_name"));
                map.put(obj.getString("student_name"), value + 1);
            }

        }
        return map;

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {
        Map map = PairInternet.InternetPair();
        ArrayList as = new ArrayList(map.entrySet());

        Collections.sort(as, new Comparator() {
            public int compare(Object o1, Object o2) {
                Map.Entry e1 = (Map.Entry) o1;
                Map.Entry e2 = (Map.Entry) o2;
                Integer first = (Integer) e1.getValue();
                Integer second = (Integer) e2.getValue();
                return second.compareTo(first);
            }
        });
        Iterator i = as.iterator();
        while (i.hasNext()) {
            System.out.println((Map.Entry) i.next());
        }
    }
}
