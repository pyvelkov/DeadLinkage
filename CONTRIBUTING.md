# Contributing

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
 - [Windows Terminal - up to date](https://github.com/lextm/windowsterminal-shell) is required as it supports ANSI colour codes.
 - [Google Chrome](https://www.google.com/intl/en_ca/chrome/) must be installed as chromedriver relies on the Chrome Browser to send and test Get requests.
 - [Google-Java-Format](https://github.com/google/google-java-format/releases) the latest version must be downloaded in order to maintain the code style.
 - [SpotBugs](https://github.com/spotbugs/spotbugs/releases) the latest version must be downloaded in order to keep code clean and bug-free.

###### Optional
 - [Telescope](https://github.com/Seneca-CDOT/telescope) must be installed in order to use `-telescope` functionality of tool. 

### Installing

 1. `git clone https://github.com/pyvelkov/DeadLinkage.git`
 2. Set the system environment variable PATH variable by doing the following:
	 1. Place the chromedriver.exe located in the DeadLinkage folder in a new location anywhere on your harddrive (optional). For my example I will make a new folder called webdrivers in my User folder.
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/chromedriver%2001.JPG?token=ANEOJ52DGUDODVIIMC6TP6K7OTSUG)
	2. Now copy the path where the chromedriver.exe is located (highlighted above)
	3. Right click on "This PC" 
	4. Click "properties" on the pop-up window
	5. Click "Advanced system settings"
	6. A "System Properties" window will appear, click on "Environment Variables..." button on the bottom.
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/chromedriver%2002.JPG?token=ANEOJ5352TCLEECP4KPPIDK7OTT7C)
	7. Click on "Path" under the System Variables select box and hit "Edit..."
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/chromedriver%2003.JPG?token=ANEOJ5354SMJW4YKXJDB6427OTUFI)
	8. Click "New" and paste the location of chromedriver.exe
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/chromedriver%2004.JPG?token=ANEOJ52FGLYAC4N3HPBOSH27OTUTW)
 3. Type `refreshenv` into Windows Terminal.
 4. `cd DeadLinkage\out\artifacts\DeadLinkage_jar` and run the respective commands listed in the README.
 
#### Enabling Google-Java-Format Plugin for IntelliJ

 1. Go to File->Settings->Plugins
 2. Select Marketplace and type in "Google", the plugin should pop up first.
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/Contributing/intellij-google-format.JPG)
 3. Click on install and restart the IDE

##### Using Google-Java-Format CLI

 1. Open Windows Terminal
 2. Run the formatter with `java -jar /path/to/google-java-format-1.9-all-deps.jar <options> [files...]`
 3. You must run the formatter before making a PR
    1. A specific file: `java -jar google-java-format-1.9-all-deps.jar -i .\Version.java`
    2. All files: `java -jar google-java-format-1.9-all-deps.jar -i .\*.java`

#### Enabling SpotBugs for IntelliJ

 1. Go to File->Settings->Plugins
 2. Select Marketplace and type in "SpotBugs", the plugin should pop up first.
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/Contributing/intellij-spotBugs.JPG)
 3. Click on install and restart the IDE

##### Using SpotBugs CLI/GUI

 1. Open Windows Terminal
 2. Run the SpotBugs GUI (Easier to work with) `java -jar /path/to/spotbugs-4.x.x/lib/spotbugs.jar`
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/Contributing/intellij-spotBugs-gui-from-cli.JPG)
 3. If you do not have SpotBugs installed on your IDE, you must use this to fix potential bugs before making a PR.
    1. [SpotBugs Usage](https://spotbugs.readthedocs.io/en/latest/gui.html)

Congratulations. You are now set up to use the DeadLinkage tool.
