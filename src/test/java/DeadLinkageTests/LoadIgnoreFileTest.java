package DeadLinkageTests;

import DeadLinkage.LoadIgnoreFile;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class LoadIgnoreFileTest {

    //LoadIgnoreFile lif = new LoadIgnoreFile();
  @Test
  public void loadFileIgnore() throws IOException {
    String filePassed = "ignore-urls.txt";

    ArrayList<String> returned;

    returned = LoadIgnoreFile.loadFileIgnore(filePassed);

      for (String s : returned) {
          //System.out.println(s);
          assertTrue(s.startsWith("http://") || s.startsWith("https://"));
      }
  }
}
