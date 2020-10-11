import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class DeadLinkTest {
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String WHITE = "\033[0;37m";
    public static final String RESET = "\033[0m";
    public static final String RED_BACKGROUND = "\033[41m";
    public static final String GREEN_BACKGROUND = "\033[42m";

    public static void main(String[] args)
    {
        String argPrintFlag = "";
        WebDriver driver=new ChromeDriver();

        if (args.length == 0){
            driver.quit();
            helpMessage();
        }
        else if (args[0].equals("-v")){
            driver.quit();
            versionMessage();
        }
        //check files
        else if(args[0].equals("-f") || args[0].equals("--f") || args[0].equals("/f") /*&& args[1].endsWith(".html")*/){
            if(args[1].endsWith(".html")) {
                Path pathFile = Paths.get(args[1]);
                driver.get(pathFile.toUri().toString());
                argPrintFlag = "--all";
            }
            //this is if --all urlFlag argument is provided
            else if(args[1].equals("-all") || args[1].equals("--all") || args[1].equals("/all")){
                Path pathFile = Paths.get(args[2]);
                driver.get(pathFile.toUri().toString());
                argPrintFlag = "--all";
            }
            //this is if --bad urlFlag argument is provided
            else if(args[1].equals("-bad") || args[1].equals("--bad") || args[1].equals("/bad")){
                Path pathFile = Paths.get(args[2]);
                driver.get(pathFile.toUri().toString());
                argPrintFlag = "--bad";
            }
            //this is if --bad urlFlag argument is provided
            else if(args[1].equals("-good") || args[1].equals("--good") || args[1].equals("/good")){
                Path pathFile = Paths.get(args[2]);
                driver.get(pathFile.toUri().toString());
                argPrintFlag = "--good";
            }
        }
        //check URLS
        else if(args[0].equals("-u") || args[0].equals("--u") || args[0].equals("/u") /*&& args[1].contains("http")*/){
            //this is the default case if no urlFlag argument is provided
            if(args[1].contains("http")) {
                driver.get(args[1]);
                argPrintFlag = "--all";
            }
            //this is if --all urlFlag argument is provided
            else if(args[1].equals("-all") || args[1].equals("--all") || args[1].equals("/all")){
                driver.get(args[2]);
                argPrintFlag = "--all";
            }
            //this is if --bad urlFlag argument is provided
            else if(args[1].equals("-bad") || args[1].equals("--bad") || args[1].equals("/bad")){
                driver.get(args[2]);
                argPrintFlag = "--bad";
            }
            //this is if --bad urlFlag argument is provided
            else if(args[1].equals("-good") || args[1].equals("--good") || args[1].equals("/good")){
                driver.get(args[2]);
                argPrintFlag = "--good";
            }
        }
        else{
            driver.quit();
            helpMessage();
        }

        driver.manage().window().maximize();

        List<WebElement> links=driver.findElements(By.tagName("a"));

        boolean exitCode = true;
        for(int i = 0; i < links.size(); i++)
        {
            try {
                WebElement elem = links.get(i);

                String url = elem.getAttribute("href");

                checkLink(url, argPrintFlag);
                exitCode = true;
            } catch (Exception ExceptionExit){
                exitCode = false;
            }
        }
        driver.quit();
        if(exitCode) System.exit(0);
        else System.exit(1);
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

    public static void checkLink(String linkUrl, String argPrintFlag) {
        try
        {
            //check for email links
            if(linkUrl.startsWith("mailto:")){
                linkUrl = linkUrl.replace("mailto:","");
            }
            URL url = new URL(linkUrl);

            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setRequestMethod("HEAD");
            httpURLConnect.connect();
            httpURLConnect.setInstanceFollowRedirects(true);

            //good links
            switch (argPrintFlag) {
                case "--good":
                    if (httpURLConnect.getResponseCode() >= 200 && httpURLConnect.getResponseCode() <= 226) {
                        System.out.println(GREEN + linkUrl + "   ---->   " + "[" + WHITE + GREEN_BACKGROUND + HttpURLConnection.HTTP_OK + RESET + GREEN + "] - " + httpURLConnect.getResponseMessage() + RESET);
                    }
                    break;
                //bad links
                case "--bad":
                    if (httpURLConnect.getResponseCode() >= 400 && httpURLConnect.getResponseCode() <= 420) {
                        System.out.println(RED + linkUrl + "   ---->   " + "[" + WHITE + RED_BACKGROUND + HttpURLConnection.HTTP_NOT_FOUND + RESET + RED + "] - " + httpURLConnect.getResponseMessage() + RESET);
                    }
                    if (httpURLConnect.getResponseCode() >= 500 && httpURLConnect.getResponseCode() <= 599) {
                        System.out.println(RED + linkUrl + "   ---->   " + "[" + WHITE + RED_BACKGROUND + HttpURLConnection.HTTP_INTERNAL_ERROR + RESET + RED + "] - " + httpURLConnect.getResponseMessage() + RESET);
                    }
                    break;
                //all links
                case "--all":
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
                    break;
                default:
                    System.out.println("No Links Found");
                    throw new IllegalStateException("Unexpected value: " + argPrintFlag);
            }
        } catch (IOException ignored) {}
    }
}