package DeadLinkage;

import org.openqa.selenium.WebDriver;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LinkArgumentParser {

    public static String fileArgParser(String[] args, WebDriver driver, int ignoreFlag){
        String argPrintFlag = "--all";
        if (args[1 + ignoreFlag].endsWith(".html")) {//2 if -i
            Path pathFile = Paths.get(args[1 + ignoreFlag]);
            driver.get(pathFile.toUri().toString());
            argPrintFlag = "--all";
        }
        //this is if --all urlFlag argument is provided
        else if (args[1 + ignoreFlag].equals("-all") || args[1 + ignoreFlag].equals("--all") || args[1 + ignoreFlag].equals("/all")) {//2 if -i
            Path pathFile = Paths.get(args[2+ignoreFlag]);//3 if -i
            driver.get(pathFile.toUri().toString());
            argPrintFlag = "--all";
        }
        //this is if --bad urlFlag argument is provided
        else if (args[1 + ignoreFlag].equals("-bad") || args[1 + ignoreFlag].equals("--bad") || args[1 + ignoreFlag].equals("/bad")) {
            Path pathFile = Paths.get(args[2 + ignoreFlag]);
            driver.get(pathFile.toUri().toString());
            argPrintFlag = "--bad";
        }
        //this is if --bad urlFlag argument is provided
        else if (args[1 + ignoreFlag].equals("-good") || args[1 + ignoreFlag].equals("--good") || args[1 + ignoreFlag].equals("/good")) {
            Path pathFile = Paths.get(args[2 + ignoreFlag]);
            driver.get(pathFile.toUri().toString());
            argPrintFlag = "--good";
        }
        return argPrintFlag;
    }

    public static String urlArgParser(String[] args, WebDriver driver, int ignoreFlag){
        String argPrintFlag = "--all";
        //this is the default case if no urlFlag argument is provided
        if (args[1 + ignoreFlag].contains("http")) {
            driver.get(args[1 + ignoreFlag]);
            argPrintFlag = "--all";
        }
        //this is if --all urlFlag argument is provided
        else if (args[1 + ignoreFlag].equals("-all") || args[1 + ignoreFlag].equals("--all") || args[1 + ignoreFlag].equals("/all")) {
            driver.get(args[2 + ignoreFlag]);
            argPrintFlag = "--all";
        }
        //this is if --bad urlFlag argument is provided
        else if (args[1 + ignoreFlag].equals("-bad") || args[1 + ignoreFlag].equals("--bad") || args[1 + ignoreFlag].equals("/bad")) {
            driver.get(args[2 + ignoreFlag]);
            argPrintFlag = "--bad";
        }
        //this is if --bad urlFlag argument is provided
        else if (args[1 + ignoreFlag].equals("-good") || args[1 + ignoreFlag].equals("--good") || args[1 + ignoreFlag].equals("/good")) {
            driver.get(args[2 + ignoreFlag]);
            argPrintFlag = "--good";
        }
        return argPrintFlag;
    }
}
