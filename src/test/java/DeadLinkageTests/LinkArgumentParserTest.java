package DeadLinkageTests;


import DeadLinkage.LinkArgumentParser;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LinkArgumentParserTest {

    WebDriver webdriver;
    LinkArgumentParser lap = new LinkArgumentParser();
    String url = "https://www.google.ca";

    @Before
    public void createMocks() {
        webdriver = mock(WebDriver.class);
    }

    @Test
    public void fileArgParser_noSpecifier() {
        String[] mockFileArgs_noIgnore_noSpecifier = {"-f", "test.html"};
        String[] mockFileArgs_Ignore_noSpecifier = {"-f", "-i", "test.html"};
        String expectedStr = "--all";
        webdriver.get(url);
        verify(webdriver).get(url);

        assertEquals(expectedStr, lap.fileArgParser(mockFileArgs_noIgnore_noSpecifier, webdriver, 0 ));
        assertEquals(expectedStr, lap.fileArgParser(mockFileArgs_Ignore_noSpecifier, webdriver, 1 ));
    }

    @Test
    public void fileArgParser_allLinks() {
        String[] mockFileArgs_noIgnore = {"-f", "--all", "test.html"};
        String[] mockFileArgs_ignore = {"-f", "-i", "--all", "test.html"};
        String expectedStr = "--all";
        webdriver.get(url);
        verify(webdriver).get(url);

        assertEquals(expectedStr, lap.fileArgParser(mockFileArgs_noIgnore, webdriver, 0 ));
        assertEquals(expectedStr, lap.fileArgParser(mockFileArgs_ignore, webdriver, 1 ));
    }

    @Test
    public void fileArgParser_badLinks() {
        String[] mockFileArgs_noIgnore = {"-f", "--bad", "test.html"};
        String[] mockFileArgs_ignore = {"-f", "-i", "--bad", "test.html"};
        String expectedStr = "--bad";
        webdriver.get(url);
        verify(webdriver).get(url);

        assertEquals(expectedStr, lap.fileArgParser(mockFileArgs_noIgnore, webdriver, 0 ));
        assertEquals(expectedStr, lap.fileArgParser(mockFileArgs_ignore, webdriver, 1 ));
    }

    @Test
    public void fileArgParser_goodLinks() {
        String[] mockFileArgs_noIgnore = {"-f", "--good", "test.html"};
        String[] mockFileArgs_ignore = {"-f", "-i", "--good", "test.html"};
        String expectedStr = "--good";
        webdriver.get(url);
        verify(webdriver).get(url);

        assertEquals(expectedStr, lap.fileArgParser(mockFileArgs_noIgnore, webdriver, 0 ));
        assertEquals(expectedStr, lap.fileArgParser(mockFileArgs_ignore, webdriver, 1 ));
    }

    //----------------------------------------------------------------------------------------------------------------
    @Test
    public void urlArgParser_noSpecifier() {
        String[] mockFileArgs_noIgnore_noSpecifier = {"-u", url};
        String[] mockFileArgs_Ignore_noSpecifier = {"-u", "-i", url};
        String expectedStr = "--all";
        webdriver.get(url);
        verify(webdriver).get(url);

        assertEquals(expectedStr, lap.urlArgParser(mockFileArgs_noIgnore_noSpecifier, webdriver, 0 ));
        assertEquals(expectedStr, lap.urlArgParser(mockFileArgs_Ignore_noSpecifier, webdriver, 1 ));
    }

    @Test
    public void urlArgParser_allLinks() {
        String[] mockFileArgs_noIgnore = {"-u", "--all", url};
        String[] mockFileArgs_ignore = {"-u", "-i", "--all", url};
        String expectedStr = "--all";
        webdriver.get(url);
        verify(webdriver).get(url);

        assertEquals(expectedStr, lap.urlArgParser(mockFileArgs_noIgnore, webdriver, 0 ));
        assertEquals(expectedStr, lap.urlArgParser(mockFileArgs_ignore, webdriver, 1 ));
    }

    @Test
    public void urlArgParser_badLinks() {
        String[] mockFileArgs_noIgnore = {"-u", "--bad", url};
        String[] mockFileArgs_ignore = {"-u", "-i", "--bad", url};
        String expectedStr = "--bad";
        webdriver.get(url);
        verify(webdriver).get(url);

        assertEquals(expectedStr, lap.urlArgParser(mockFileArgs_noIgnore, webdriver, 0 ));
        assertEquals(expectedStr, lap.urlArgParser(mockFileArgs_ignore, webdriver, 1 ));
    }

    @Test
    public void urlArgParser_goodLinks() {
        String[] mockFileArgs_noIgnore = {"-u", "--good", url};
        String[] mockFileArgs_ignore = {"-u", "-i", "--good", url};
        String expectedStr = "--good";
        webdriver.get(url);
        verify(webdriver).get(url);

        assertEquals(expectedStr, lap.urlArgParser(mockFileArgs_noIgnore, webdriver, 0 ));
        assertEquals(expectedStr, lap.urlArgParser(mockFileArgs_ignore, webdriver, 1 ));
    }
}