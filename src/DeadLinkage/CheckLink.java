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
            //check for email links
            if (linkUrl.startsWith("mailto:")) {
                linkUrl = linkUrl.replace("mailto:", "");
            }

            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setRequestMethod("HEAD");
            httpURLConnect.connect();
            httpURLConnect.setInstanceFollowRedirects(true);

            //good links
            switch (argPrintFlag) {
                case "--good":
                    goodLink(linkUrl, httpURLConnect);
                    break;
                //bad links
                case "--bad":
                    badLink(linkUrl, httpURLConnect);
                    break;
                //all links
                case "--all":
                    allLinks(linkUrl, httpURLConnect);
                    break;
                default:
                    System.out.println("No Links Found");
                    throw new IllegalStateException("Unexpected value: " + argPrintFlag);
            }
        } catch (IOException ignored) {
        }
    }

    private static void goodLink(String linkUrl, HttpURLConnection httpURLConnect) {
        try {
            if (httpURLConnect.getResponseCode() >= 200 && httpURLConnect.getResponseCode() <= 226) {
                System.out.println(GREEN + linkUrl + "   ---->   " + "[" + WHITE + GREEN_BACKGROUND + HttpURLConnection.HTTP_OK + RESET + GREEN + "] - " + httpURLConnect.getResponseMessage() + RESET);
            }
        } catch (IOException e) { }
    }

    private static void badLink(String linkUrl, HttpURLConnection httpURLConnect){
        try {
            if (httpURLConnect.getResponseCode() >= 400 && httpURLConnect.getResponseCode() <= 420) {
                System.out.println(RED + linkUrl + "   ---->   " + "[" + WHITE + RED_BACKGROUND + HttpURLConnection.HTTP_NOT_FOUND + RESET + RED + "] - " + httpURLConnect.getResponseMessage() + RESET);
            }
            if (httpURLConnect.getResponseCode() >= 500 && httpURLConnect.getResponseCode() <= 599) {
                System.out.println(RED + linkUrl + "   ---->   " + "[" + WHITE + RED_BACKGROUND + HttpURLConnection.HTTP_INTERNAL_ERROR + RESET + RED + "] - " + httpURLConnect.getResponseMessage() + RESET);
            }
        }
        catch (IOException e) { }
    }

    private static void allLinks(String linkUrl, HttpURLConnection httpURLConnect){
        try {
            if (httpURLConnect.getResponseCode() >= 200 && httpURLConnect.getResponseCode() <= 226) {
                System.out.println(GREEN + linkUrl + "   ---->   " + "[" + WHITE + GREEN_BACKGROUND + HttpURLConnection.HTTP_OK + RESET + GREEN + "] - " + httpURLConnect.getResponseMessage() + RESET);
            } else if (httpURLConnect.getResponseCode() >= 300 && httpURLConnect.getResponseCode() <= 308) {
                System.out.println(WHITE + linkUrl + "   ---->   " + "[" + WHITE + RED_BACKGROUND + HttpURLConnection.HTTP_MOVED_PERM + RESET + WHITE + "] - " + httpURLConnect.getResponseMessage() + RESET);
            } else if (httpURLConnect.getResponseCode() >= 400 && httpURLConnect.getResponseCode() <= 420) {
                System.out.println(RED + linkUrl + "   ---->   " + "[" + WHITE + RED_BACKGROUND + HttpURLConnection.HTTP_NOT_FOUND + RESET + RED + "] - " + httpURLConnect.getResponseMessage() + RESET);
            } else if (httpURLConnect.getResponseCode() >= 500 && httpURLConnect.getResponseCode() <= 599) {
                System.out.println(RED + linkUrl + "   ---->   " + "[" + WHITE + RED_BACKGROUND + HttpURLConnection.HTTP_INTERNAL_ERROR + RESET + RED + "] - " + httpURLConnect.getResponseMessage() + RESET);
            } else {
                System.out.println(WHITE + linkUrl + "   ---->   " + httpURLConnect.getResponseMessage() + RESET);
            }
        } catch (IOException e) { }
    }

    public static boolean JsonLinkLogic(ArrayList<String> jsonLinks, ArrayList<String> ignoreLinks, String argPrintFlag, WebDriver driver){
        int postCounter = 1;
        boolean exitCode = true;
        for (String jsonLink : jsonLinks){
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

    public static boolean checkLinkLogic(ArrayList<String> ignoreLinks, String argPrintFlag, WebDriver driver){
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
