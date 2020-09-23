import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class DeadLinkTest {

    //colors
    //text
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String WHITE = "\033[0;37m";
    public static final String RESET = "\033[0m";
    //bgd
    public static final String RED_BACKGROUND = "\033[41m";
    public static final String GREEN_BACKGROUND = "\033[42m";

    public static void main(String[] args)
    {
        WebDriver driver=new ChromeDriver();

        if (args.length == 0){
            driver.quit();
            helpMessage();
            //System.exit(0);
        }
        else if (args[0].equals("-v")){
            driver.quit();
            versionMessage();
            //System.exit(0);
        }
        else if(args[0].equals("-f") && args[1].endsWith(".html")){
            /*using relative path============================================*/
            Path pathFile = Paths.get(args[1]);
            driver.get(pathFile.toUri().toString());
        }
        else if(args[0].equals("-u") && args[1].contains("http")){
            /*using website=================================================*/
            driver.get(args[1]);
        }
        else{
            driver.quit();
            helpMessage();
            //System.exit(0);
        }

        driver.manage().window().maximize();

        List<WebElement> links=driver.findElements(By.tagName("a"));


        for(int i = 0; i < links.size(); i++)
        {
            WebElement elem = links.get(i);

            String url = elem.getAttribute("href");

            checkLink(url);
        }
        driver.quit();
    }

    private static void versionMessage() {
        System.out.println(RED_BACKGROUND + "*******************************************************************************" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*                                   VERSION                                   *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*******************************************************************************" + RESET + " ");

        System.out.println(RED_BACKGROUND + "* Name: Dead Linkage                                                          *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* Version: 0.1.6 + 2 features                                                 *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*******************************************************************************" + RESET + " ");
        System.out.println();
        System.exit(0);
    }

    private static void helpMessage() {
        System.out.println(RED_BACKGROUND + "*******************************************************************************" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*                                 HELP MENU                                   *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*******************************************************************************" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* Please enter an argument after command!                                     *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*                                                                             *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* The command arguments accepted are as follow:                               *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*                                                                             *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* arg1: " + GREEN + RED_BACKGROUND + "-u" + RESET + RED_BACKGROUND + "," + GREEN + RED_BACKGROUND + " -f" + RESET + RED_BACKGROUND + " arg2: URL, index.html                                          *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* arg1 takes the format either URL '-u' or local file in current dir '-f'     *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* arg2 depending on format of arg1 takes (url or file) ending with '.html'    *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*                                                                             *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* e.g.                                                                        *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*                                                                             *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* java -jar DeadLinkage.jar -u https://github.com/pyvelkov                    *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* java -jar DeadLinkage.jar -f index.html                                     *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*                                                                             *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* To check current program version:                                           *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "* java -jar DeadLinkage.jar -v                                                *" + RESET + " ");
        System.out.println(RED_BACKGROUND + "*******************************************************************************" + RESET);
        System.out.println();
        System.exit(0);
    }

    public static void checkLink(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);

            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();

            httpURLConnect.setRequestMethod("HEAD");

            //use this so I don't get my ip blacklisted for scraping too much
            //httpURLConnect.setConnectTimeout(1000);

            httpURLConnect.connect();

            if(httpURLConnect.getResponseCode() == 200)
            {
                System.out.println(GREEN + linkUrl + "   ---->   " + "[" + WHITE + GREEN_BACKGROUND + HttpURLConnection.HTTP_OK + RESET + GREEN + "] - " + httpURLConnect.getResponseMessage() + RESET);
            }
            /*else if(httpURLConnect.getResponseCode()>=300 && httpURLConnect.getResponseCode()<=399){
                System.out.println(RED + linkUrl + "   ---->   " + WHITE + RED_BACKGROUND + httpURLConnect.getResponseMessage() +  " - " + HttpURLConnection.HTTP_MOVED_PERM + RESET);
            }*/
            else if(httpURLConnect.getResponseCode() >= 400 && httpURLConnect.getResponseCode() <= 499)
            {
                System.out.println(RED + linkUrl + "   ---->   " + "[" + WHITE + RED_BACKGROUND + HttpURLConnection.HTTP_NOT_FOUND + RESET + RED + "] - " + httpURLConnect.getResponseMessage() + RESET);
            }
            else{
                System.out.println(WHITE + linkUrl + "   ---->   " + httpURLConnect.getResponseMessage() +  RESET);
            }
        } catch (Exception e) {
            System.err.println("OOPS");
        }
    }
}