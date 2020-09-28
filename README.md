
# Dead Linkage

This program uses the Command Line Interface (CLI) to run the .jar file. Once the jar file is ran it scans for all URLs on a page or a local .html file in the current directory.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
 - [Windows Terminal - up to date](https://github.com/lextm/windowsterminal-shell) is required as it supports ANSI colour codes.
 - [Google Chrome](https://www.google.com/intl/en_ca/chrome/) must be installed as chromedriver relies on the Chrome Browser to send and test Get requests.

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
 4. `cd DeadLinkage\out\artifacts\DeadLinkage_jar` and run the respective commands listed below.

Congratulations. You are now set up to use the DeadLinkage tool.

## Running the tests

Once all requirements and installations have been met and completed it is time to run the tool from the CLI. Running any of the commands will cause Google Chrome to open and the tests to begin in the Windows Terminal.

### Valid arguments

To run the tool and receive help on CLI valid arguments

```
java -jar DeadLinkage.jar
```
To view current version of the tool

```
java -jar DeadLinkage.jar -v
```
To run the tool with a URL 

```
java -jar DeadLinkage.jar -u URL
```
i.e.
```
java -jar DeadLinkage.jar -u https://github.com/pyvelkov/DeadLinkage
```
To run the tool with a local html file located in the same directory as the .jar 

```
java -jar DeadLinkage.jar -f fileName.html
```
## Built With

 - [Selenium](https://www.selenium.dev/) - Web framework used
 - [Intellij](https://www.jetbrains.com/idea/) - IDE used


## Authors

* **Plamen Velkov** - *0.1 release* - [pyvelkov](https://github.com/pyvelkov)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

