package DeadLinkage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonReader {
  public static ArrayList<String> jsonCheckLink() {
    ArrayList<String> result = new ArrayList<>();
    String inline = "";
    String baseURL = "http://localhost:3000/posts/";
    try {
      URL url = new URL(baseURL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();

      int responsecode = conn.getResponseCode();

      if (responsecode != 200) throw new RuntimeException("HttpResponseCode: " + responsecode);
      else {
        Scanner sc = new Scanner(url.openStream());
        while (sc.hasNext()) {
          inline += sc.nextLine();
        }
        sc.close();
      }

      JSONParser parser = new JSONParser();
      JSONArray jarr = (JSONArray) parser.parse(inline);

      // {"id":"403b73803f","url":"/posts/403b73803f"}
      for (int i = 0; i < jarr.size(); i++) {
        JSONObject jsonObj = (JSONObject) jarr.get(i);
        String ID = (String) jsonObj.get("id");
        result.add(baseURL + ID);
      }

      // disconnect connection
      conn.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
