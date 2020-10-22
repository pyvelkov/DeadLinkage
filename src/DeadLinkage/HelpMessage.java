package DeadLinkage;

import static DeadLinkage.AnsiColors.*;

public class HelpMessage {
    public static void helpMessage() {
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
}
