package DeadLinkage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadIgnoreFile {
  public static ArrayList<String> loadFileIgnore(String fileName) {
    ArrayList<String> result = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (!line.startsWith("#") && (!line.startsWith("http://") || !line.startsWith("https://")))
          result.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }
}
