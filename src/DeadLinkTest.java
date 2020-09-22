import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DeadLinkTest {

    public static void main(String[] args)
    {
        WebDriver driver=new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://github.com/pyvelkov1234");

        List<WebElement> links=driver.findElements(By.tagName("a"));


        for(int i=0;i<links.size();i++)
        {

            WebElement elem= links.get(i);

            String url=elem.getAttribute("href");

            checkLink(url);

        }
        driver.quit();
    }

    public static void checkLink(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);

            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();

            httpURLConnect.setRequestMethod("GET");

            //use this so I don't get my ip blacklisted for scraping too much
            //httpURLConnect.setConnectTimeout(3000);

            httpURLConnect.connect();

            if(httpURLConnect.getResponseCode()==200)
            {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
            }
            if(httpURLConnect.getResponseCode()>=400)
            {
                System.err.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
                //System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }
    }




}