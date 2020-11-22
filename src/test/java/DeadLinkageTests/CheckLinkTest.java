package DeadLinkageTests;

import DeadLinkage.CheckLink;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({URL.class})
public class CheckLinkTest {

  @Test
  public void goodLink() throws Exception {
    System.err.println("Testing goodLink ...");
    String url = "http://www.google.ca";
    URL u = PowerMockito.mock(URL.class);
    PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(u);
    HttpURLConnection huc = PowerMockito.mock(HttpURLConnection.class);
    PowerMockito.when(u.openConnection()).thenReturn(huc);
    PowerMockito.when(huc.getResponseCode()).thenReturn(200);
    PowerMockito.when(huc.getResponseMessage())
        .thenReturn("200 - This is a mocked test on google.ca");

    String expectedStr = (url + " " + huc.HTTP_OK);

    // make sure the mocked connection is an instance of the HttpUrlConnection object
    assertTrue(u.openConnection() instanceof HttpURLConnection);
    // make sure that goodLink is returning what it is supposed to (200 - 226)
    assertEquals(expectedStr, CheckLink.goodLink(url, huc));
  }

  @Test
  public void badLink() throws Exception {
    System.err.println("Testing badLink ...");
    URL u = PowerMockito.mock(URL.class);
    String url = "http://www.google.ca";
    PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(u);
    HttpURLConnection huc = PowerMockito.mock(HttpURLConnection.class);
    PowerMockito.when(u.openConnection()).thenReturn(huc);
    PowerMockito.when(huc.getResponseCode()).thenReturn(404);
    PowerMockito.when(huc.getResponseMessage())
        .thenReturn("404 - This is a mocked test on google.ca");

    String expectedStr404 = (url + " " + huc.HTTP_NOT_FOUND);
    String expectedStr500 = (url + " " + huc.HTTP_INTERNAL_ERROR);

    // make sure the mocked connection is an instance of the HttpUrlConnection object
    assertTrue(u.openConnection() instanceof HttpURLConnection);
    // make sure that bad link is returning (400 - 420)
    assertEquals(expectedStr404, CheckLink.badLink(url, huc));
    // make sure that bad link is returning (500 - 599)
    PowerMockito.when(huc.getResponseCode()).thenReturn(500);
    PowerMockito.when(huc.getResponseMessage())
        .thenReturn("500 - This is a mocked test on google.ca");
    assertEquals(expectedStr500, CheckLink.badLink(url, huc));
  }

  @Test
  public void allLinks() throws Exception {
    System.err.println("Testing allLinks ...");
    URL u = PowerMockito.mock(URL.class);
    String url = "http://www.google.ca";
    PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(u);
    HttpURLConnection huc = PowerMockito.mock(HttpURLConnection.class);
    PowerMockito.when(u.openConnection()).thenReturn(huc);

    assertTrue(u.openConnection() instanceof HttpURLConnection);
    // 200
    PowerMockito.when(huc.getResponseCode()).thenReturn(200);
    PowerMockito.when(huc.getResponseMessage())
        .thenReturn("200 - This is a mocked test on google.ca");
    String expectedStr200 = (url + " " + huc.HTTP_OK);
    assertEquals(expectedStr200, CheckLink.allLinks(url, huc));

    // 300
    PowerMockito.when(huc.getResponseCode()).thenReturn(300);
    PowerMockito.when(huc.getResponseMessage())
        .thenReturn("300 - This is a mocked test on google.ca");
    String expectedStr300 = (url + " " + huc.HTTP_MOVED_PERM);
    assertEquals(expectedStr300, CheckLink.allLinks(url, huc));
    // 404
    PowerMockito.when(huc.getResponseCode()).thenReturn(404);
    PowerMockito.when(huc.getResponseMessage())
        .thenReturn("404 - This is a mocked test on google.ca");
    String expectedStr404 = (url + " " + huc.HTTP_NOT_FOUND);
    assertEquals(expectedStr404, CheckLink.allLinks(url, huc));
    //500
    PowerMockito.when(huc.getResponseCode()).thenReturn(500);
    PowerMockito.when(huc.getResponseMessage())
            .thenReturn("500 - This is a mocked test on google.ca");
    String expectedStr500 = (url + " " + huc.HTTP_INTERNAL_ERROR);
    assertEquals(expectedStr500, CheckLink.allLinks(url, huc));
  }
}
