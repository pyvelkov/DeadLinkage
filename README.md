
# Dead Linkage

This program uses the Command Line Interface (CLI) to run the .jar file. Once the jar file is ran it scans for all URLs on a page or a local .html file in the current directory.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

 - [Chrome Web Driver](https://chromedriver.chromium.org/)
 - [Windows Terminal - up to date](https://github.com/lextm/windowsterminal-shell)
 - DeadLinkage.jar file

### Installing

A step by step series of examples that tell you how to get a testing environment running on CLI.

Download .jar file from the repository.

![Repo](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/github%2001.JPG?token=ANEOJ54GEUAD6SINYHCHFFK7OTM4E)
![Repo Path](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/github%2002.JPG?token=ANEOJ57RPQAK6WJGY4JQCJ27OTNA4)

Once you have the .jar file, download the [Chrome Web Driver](https://chromedriver.chromium.org/) for your correct version of Google Chrome.

Once you retrieve the correct chromedriver.exe, an environment variable path must be set.

Place the chromedriver.exe in a new location anywhere on your drive.
i.e.
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/chromedriver%2001.JPG?token=ANEOJ52DGUDODVIIMC6TP6K7OTSUG)
Now copy the path where the chromedriver.exe is located (highlighted above)

1. Right click on "This PC" 
2. Click "properties" on the pop-up window
3. Click "Advanced system settings"
4. A "System Properties" window will appear, click on "Environment Variables..." button on the bottom.
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/chromedriver%2002.JPG?token=ANEOJ5352TCLEECP4KPPIDK7OTT7C)
5. Click on "Path" under the System Variables select box and hit "Edit..."
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/chromedriver%2003.JPG?token=ANEOJ5354SMJW4YKXJDB6427OTUFI)
6. Click "New" and paste the location of chromedriver.exe
![enter image description here](https://raw.githubusercontent.com/pyvelkov/DeadLinkage/master/0.1%20MDassets/chromedriver%2004.JPG?token=ANEOJ52FGLYAC4N3HPBOSH27OTUTW)

Congratulations. You are now set up to use the DeadLinkage tool.

## Running the tests

Once all requirements and installations have been met and completed it is time to run the tool from the CLI.

Open the updated Windows Terminal downloaded from above and navigate to where you saved the .jar file, using the cd (change directory command).


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
java -jar DeadLinkage.jar -u github.com/pyvelkov/DeadLinkage
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
