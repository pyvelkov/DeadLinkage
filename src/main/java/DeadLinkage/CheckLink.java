package DeadLinkage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static DeadLinkage.AnsiColors.*;

public class CheckLink {
  public static void checkLink(String linkUrl, String argPrintFlag) {
    try {
      // check for email links
      if (linkUrl.startsWith("mailto:")) {
        linkUrl = linkUrl.replace("mailto:", "");
      }

      URL url = new URL(linkUrl);
      HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
      httpURLConnect.setRequestMethod("HEAD");
      httpURLConnect.connect();
      httpURLConnect.setInstanceFollowRedirects(true);

      // good links
      if ("--good".equals(argPrintFlag)) {
        goodLink(linkUrl, httpURLConnect);
        // bad links
      } else if ("--bad".equals(argPrintFlag)) {
        badLink(linkUrl, httpURLConnect);
        // all links
      } else if ("--all".equals(argPrintFlag)) {
        allLinks(linkUrl, httpURLConnect);
      } else {
        System.out.println("No Links Found");
        throw new IllegalStateException("Unexpected value: " + argPrintFlag);
      }
    } catch (IOException ignored) {
    }
  }

  public static String goodLink(String linkUrl, HttpURLConnection httpURLConnect)
      throws IOException {
    try {
      if (httpURLConnect.getResponseCode() >= 200 && httpURLConnect.getResponseCode() <= 226) {
        System.out.println(
            GREEN
                + linkUrl
                + "   ---->   "
                + "["
                + WHITE
                + GREEN_BACKGROUND
                + HttpURLConnection.HTTP_OK
                + RESET
                + GREEN
                + "] - "
                + httpURLConnect.getResponseMessage()
                + RESET);
        return (linkUrl + " " + httpURLConnect.HTTP_OK);
      }
    } catch (IOException e) {
    }
    return (linkUrl + " " + httpURLConnect.getResponseCode());
  }

  public static String badLink(String linkUrl, HttpURLConnection httpURLConnect)
      throws IOException {
    try {
      if (httpURLConnect.getResponseCode() >= 400 && httpURLConnect.getResponseCode() <= 420) {
        System.out.println(
            RED
                + linkUrl
                + "   ---->   "
                + "["
                + WHITE
                + RED_BACKGROUND
                + HttpURLConnection.HTTP_NOT_FOUND
                + RESET
                + RED
                + "] - "
                + httpURLConnect.getResponseMessage()
                + RESET);
        return (linkUrl + " " + httpURLConnect.HTTP_NOT_FOUND);
      }
      if (httpURLConnect.getResponseCode() >= 500 && httpURLConnect.getResponseCode() <= 599) {
        System.out.println(
            RED
                + linkUrl
                + "   ---->   "
                + "["
                + WHITE
                + RED_BACKGROUND
                + HttpURLConnection.HTTP_INTERNAL_ERROR
                + RESET
                + RED
                + "] - "
                + httpURLConnect.getResponseMessage()
                + RESET);
        return (linkUrl + " " + httpURLConnect.HTTP_INTERNAL_ERROR);
      }
    } catch (IOException e) {
    }
    return (linkUrl + " " + httpURLConnect.getResponseCode());
  }

  public static String allLinks(String linkUrl, HttpURLConnection httpURLConnect) throws IOException {
    try {
      if (httpURLConnect.getResponseCode() >= 200 && httpURLConnect.getResponseCode() <= 226) {
        System.out.println(
            GREEN
                + linkUrl
                + "   ---->   "
                + "["
                + WHITE
                + GREEN_BACKGROUND
                + HttpURLConnection.HTTP_OK
                + RESET
                + GREEN
                + "] - "
                + httpURLConnect.getResponseMessage()
                + RESET);
        return (linkUrl + " " + httpURLConnect.HTTP_OK);
      } else if (httpURLConnect.getResponseCode() >= 300
          && httpURLConnect.getResponseCode() <= 308) {
        System.out.println(
            WHITE
                + linkUrl
                + "   ---->   "
                + "["
                + WHITE
                + RED_BACKGROUND
                + HttpURLConnection.HTTP_MOVED_PERM
                + RESET
                + WHITE
                + "] - "
                + httpURLConnect.getResponseMessage()
                + RESET);
        return (linkUrl + " " + httpURLConnect.HTTP_MOVED_PERM);
      } else if (httpURLConnect.getResponseCode() >= 400
          && httpURLConnect.getResponseCode() <= 420) {
        System.out.println(
            RED
                + linkUrl
                + "   ---->   "
                + "["
                + WHITE
                + RED_BACKGROUND
                + HttpURLConnection.HTTP_NOT_FOUND
                + RESET
                + RED
                + "] - "
                + httpURLConnect.getResponseMessage()
                + RESET);
        return (linkUrl + " " + httpURLConnect.HTTP_NOT_FOUND);
      } else if (httpURLConnect.getResponseCode() >= 500
          && httpURLConnect.getResponseCode() <= 599) {
        System.out.println(
            RED
                + linkUrl
                + "   ---->   "
                + "["
                + WHITE
                + RED_BACKGROUND
                + HttpURLConnection.HTTP_INTERNAL_ERROR
                + RESET
                + RED
                + "] - "
                + httpURLConnect.getResponseMessage()
                + RESET);
        return (linkUrl + " " + httpURLConnect.HTTP_INTERNAL_ERROR);
      } else {
        System.out.println(
            WHITE + linkUrl + "   ---->   " + httpURLConnect.getResponseMessage() + RESET);
      }
    } catch (IOException e) {
    }
    return (linkUrl + " " + httpURLConnect.getResponseMessage());
  }

  public static boolean jsonLinkLogic(
      ArrayList<String> jsonLinks,
      ArrayList<String> ignoreLinks,
      String argPrintFlag,
      WebDriver driver) {
    int postCounter = 1;
    boolean exitCode = true;
    for (String jsonLink : jsonLinks) {
      System.out.println("Post: " + postCounter);
      driver.get(jsonLink);
      List<WebElement> links = driver.findElements(By.tagName("a"));
      for (WebElement link : links) {
        try {
          WebElement elem = link;

          String url = elem.getAttribute("href");
          for (String ignoreLink : ignoreLinks) {
            if (url.startsWith(ignoreLink)) {
              url = url.replace(ignoreLink, "");
            }
          }
          checkLink(url, argPrintFlag);
          exitCode = true;
        } catch (Exception ExceptionExit) {
          exitCode = false;
        }
      }
      postCounter++;
    }
    return exitCode;
  }

  public static boolean checkLinkLogic(
      ArrayList<String> ignoreLinks, String argPrintFlag, WebDriver driver) {
    List<WebElement> links = driver.findElements(By.tagName("a"));
    boolean exitCode = true;
    for (int i = 0; i < links.size(); i++) {
      try {
        WebElement elem = links.get(i);

        String url = elem.getAttribute("href");

        for (String ignoreLink : ignoreLinks) {
          if (url.startsWith(ignoreLink)) {
            url = url.replace(ignoreLink, "");
          }
        }
        checkLink(url, argPrintFlag);
        exitCode = true;
      } catch (Exception ExceptionExit) {
        exitCode = false;
      }
    }
    return exitCode;
  }
}
