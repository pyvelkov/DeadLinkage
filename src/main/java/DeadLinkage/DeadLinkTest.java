package DeadLinkage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;

import static DeadLinkage.CheckLink.checkLinkLogic;
import static DeadLinkage.CheckLink.jsonLinkLogic;
import static DeadLinkage.HelpMessage.helpMessage;
import static DeadLinkage.JsonReader.jsonCheckLink;
import static DeadLinkage.LinkArgumentParser.fileArgParser;
import static DeadLinkage.LinkArgumentParser.urlArgParser;
import static DeadLinkage.LoadIgnoreFile.loadFileIgnore;
import static DeadLinkage.Version.versionMessage;

// import classes

public class DeadLinkTest {

  public static void main(String[] args) {
    String argPrintFlag;
    boolean exitCode = true;
    int ignoreFlag = 0;
    ArrayList<String> ignoreLinks = new ArrayList<String>();

    // headless driver options
    WebDriver driver;
    WebDriverManager.chromedriver().setup();

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--headless");
    driver = new ChromeDriver(chromeOptions);
    // cases
    // 0    1           2               3               4
    // -f    -i          test.html       ignore.txt
    // -f    -i          -all            test.html       ignore.txt
    // -f    -good       test.html
    // -f    test.html

    if (args.length > 1) {
      if ((args[1].equals("-i")
          || args[1].equals("--i")
          || args[1].equals("-ignore")
          || args[1].equals("/i"))) {
        ignoreFlag = 1;
        // get filename for ignore
        String ignoreFile = args[args.length - 1];
          ignoreLinks = loadFileIgnore(ignoreFile);
      }
    }

    if (args.length == 0) {
      driver.quit();
      helpMessage();
    } else if (args[0].equals("-v")) {
      driver.quit();
      versionMessage();
    }
    // check files
    else if (args[0].equals("-f") || args[0].equals("--f") || args[0].equals("/f")) {
      argPrintFlag = fileArgParser(args, driver, ignoreFlag);
      exitCode = checkLinkLogic(ignoreLinks, argPrintFlag, driver);
    }
    // check URLS
    else if (args[0].equals("-u") || args[0].equals("--u") || args[0].equals("/u")) {
      argPrintFlag = urlArgParser(args, driver, ignoreFlag);
      exitCode = checkLinkLogic(ignoreLinks, argPrintFlag, driver);
    } else if (args[0].equals("-telescope")
        || args[0].equals("--telescope")
        || args[0].equals("/telescope")) {
      argPrintFlag = "--all";
      exitCode = jsonLinkLogic(jsonCheckLink(), ignoreLinks, argPrintFlag, driver);
    } else {
      driver.quit();
      helpMessage();
    }
    if (exitCode) System.exit(0);
    else System.exit(1);

    // quit driver in case it was missed somewhere
    driver.quit();
  }
}
