package DeadLinkage;

import static DeadLinkage.AnsiColors.*;

public class Version {
  public static void versionMessage() {
    System.out.println(
        RED_BACKGROUND
            + "*******************************************************************************"
            + RESET
            + " ");
    System.out.println(
        RED_BACKGROUND
            + "*                                   VERSION                                   *"
            + RESET
            + " ");
    System.out.println(
        RED_BACKGROUND
            + "*******************************************************************************"
            + RESET
            + " ");

    System.out.println(
        RED_BACKGROUND
            + "* Name: Dead Linkage                                                          *"
            + RESET
            + " ");
    System.out.println(
        RED_BACKGROUND
            + "* Version: 0.1.6 + 2 features                                                 *"
            + RESET
            + " ");
    System.out.println(
        RED_BACKGROUND
            + "*******************************************************************************"
            + RESET
            + " ");
    System.out.println();
    System.exit(0);
  }
}
