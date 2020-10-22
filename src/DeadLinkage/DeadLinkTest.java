package DeadLinkage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static DeadLinkage.CheckLink.checkLink;
import static DeadLinkage.HelpMessage.helpMessage;
import static DeadLinkage.LinkArgumentParser.*;
import static DeadLinkage.LoadIgnoreFile.loadFileIgnore;
import static DeadLinkage.Version.versionMessage;

//import classes

public class DeadLinkTest {

    public static void main(String[] args) {
        String argPrintFlag = "";
        int ignoreFlag = 0;
        ArrayList<String> ignoreLinks = new ArrayList<>();

        //headless driver options
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        // cases
        // 0    1           2               3               4
        //-f    -i          test.html       ignore.txt
        //-f    -i          -all            test.html       ignore.txt
        //-f    -good       test.html
        //-f    test.html

        if(args.length > 1) {
            if ((args[1].equals("-i") || args[1].equals("--i") || args[1].equals("-ignore") || args[1].equals("/i"))) {
                ignoreFlag = 1;
                //get filename for ignore
                String ignoreFile = args[args.length-1];
                try {
                    ignoreLinks = loadFileIgnore(ignoreFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (args.length == 0) {
            driver.quit();
            helpMessage();
        } else if (args[0].equals("-v")) {
            driver.quit();
            versionMessage();
        }
        //check files
        else if (args[0].equals("-f") || args[0].equals("--f") || args[0].equals("/f")) {
            argPrintFlag = fileArgParser(args, driver, ignoreFlag);
        }
        //check URLS
        else if (args[0].equals("-u") || args[0].equals("--u") || args[0].equals("/u")) {
            argPrintFlag = urlArgParser(args, driver, ignoreFlag);
        } else {
            driver.quit();
            helpMessage();
        }

        driver.manage().window().maximize();

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

        //quit driver in case it was missed somewhere
        driver.quit();

        if (exitCode) System.exit(0);
        else System.exit(1);
    }
}

