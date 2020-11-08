
# Dead Linkage

This program uses the Command Line Interface (CLI) to run the .jar file. Once the jar file is ran it scans for all URLs on a page or a local .html file in the current directory.


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

To run the tool with a specific link status (-all, -good, -bad)
```
java -jar DeadLinkage.jar -u -good https://github.com/pyvelkov
```

To run the tool with a predetermined .txt file containing URLs to ignore
```
java -jar DeadLinkage.jar -f -i test3.html ignore-urls.txt
```

To run the tool on [Telescope](https://github.com/Seneca-CDOT/telescope) 's API
```
java -jar Deadlinkage.jar -telescope
```

## Built With

 - [Selenium](https://www.selenium.dev/) - Web framework used
 - [Intellij](https://www.jetbrains.com/idea/) - IDE used


## Authors

* **Plamen Velkov** - *0.1 release* - [pyvelkov](https://github.com/pyvelkov)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

