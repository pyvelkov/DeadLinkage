package DeadLinkageTests;

public class TestRunner {

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(LoadIgnoreFileTest.class);
    }
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("DeadLinkageTests.LoadIgnoreFileTest", "DeadLinkageTests.CheckLinkTest");

    /* org.junit.runner.JUnitCore.main("junitfaq.SimpleTest");

        Result result = JUnitCore.runClasses(LoadIgnoreFile.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }*/
  }
}