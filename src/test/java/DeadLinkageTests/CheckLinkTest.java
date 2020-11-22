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
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest({URL.class})
public class CheckLinkTest {

  @Test
  public void goodLink() throws Exception {
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
    Assert.assertEquals(expectedStr, CheckLink.goodLink(url, huc));
  }

  @Test
  public void badLink() throws Exception {
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
    // make sure that bad link is returning what it is supposed to (400 - 420) or (500 - 599)
    assertThat(CheckLink.badLink(url, huc), anyOf(is(expectedStr404), is(expectedStr500)));
  }
}
