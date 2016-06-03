"1-800-CODING-CHALLENGE" 
=======================

Requirment
----------
Many companies like to list their phone numbers using the letters printed on most
telephones. This makes the number easier to remember for customers. 

An example may be 1-800-FLOWERS

Project Structure:
-----------------
    codingchallenge
    │   pom.xml
    │   README.txt
    │
    ├───resources
    │   ├───dictionary
    │   │       SimpleDictionary
    │   │
    │   └───input
    │           file1
    │           file2
    │
    └───src
        ├───main
        │   └───java
        │       └───com
        │           └───aconex
        │               └───codingchallenge
        │                   │   Client.java
        │                   │
        │                   ├───exception
        │                   │       CHException.java
        │                   │
        │                   └───internal
        │                           Constants.java
        │                           Converter.java
        │                           EnglishDictionary.java
        │                           NumberEncoding.java
        │                           NumberInputHandler.java
        │                           PhoneNumber.java
        │
        └───test
            └───java
                └───com
                    └───aconex
                        └───codingchallenge
                                ConverterTest.java
                                EnglishDictionaryTest.java
                                NumberInputHandlerTest.java
                                PhoneNumberTest.java


How to Build
------------

Prerequisites: 
Maven 3, JDK 1.7, Git
A valid dictonary file -The dictionary is expected to have one word per line.
A directory which contains input files, each input file can have any name and the phone numbers are expected to have one per line.
	inputdir
		|__file1
		|__file2

Steps:

1. Clone the project 
2. Using the commandline/terminal go inside the project root which is "codingchallenge"
3. Run "mvn clean install"


How to Run:
-----------
Prerequisites: JRE 1.7

Run either of the below commands
java -cp target/codingchallenge-1.0.0-SNAPSHOT.jar com.aconex.codingchallenge.Client
(The above will prompt the user to insert a number and it will use the default dictonary)

java -cp target/codingchallenge-1.0.0-SNAPSHOT.jar com.aconex.codingchallenge.Client -i <full_path_to_the_input_files_directory>
(The above will read all the input files from the directory and read the numbers. It will use the default dictonary)

java -cp target/codingchallenge-1.0.0-SNAPSHOT.jar com.aconex.codingchallenge.Client -i <full_path_to_the_input_files_directory> -d <full_path_to_the_dictonary_file>
The above will read all the input files from the directory and read the numbers. It will use the provided dictonary)

Sample Output
-------------

D:\work\github\codingchallenge>java -cp target/codingchallenge-1.0.0-SNAPSHOT.jar com.aconex.codingchallenge.Client -i resources/input
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.Client main
INFO: No valid dictionary found. Using default D:\work\github\codingchallenge\resources\dictionary\SimpleDictionary
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.Client main
INFO: Reading numbers from file resources\input\file1
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.Client invoke
INFO: Looking for conversions for the number 1-800-263464-242553643
1-800-263464-242553643-> 1-800-CODING-CHALLENGE
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.Client invoke
INFO: Looking for conversions for the number 1-800-3569377
1-800-3569377-> 1-800-FLOWERS
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.Client invoke
INFO: Looking for conversions for the number 2255.6388
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.internal.Converter convertToLetters
WARNING: Cannot find a match for 22556388 as it has two adjecent numbers 638 which canot be transformed to letters
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.Client main
INFO: Error occured for number 2255.6388
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.Client main
INFO: Reading numbers from file resources\input\file2
Jun 03, 2016 2:00:19 PM com.aconex.codingchallenge.Client invoke
INFO: Looking for conversions for the number 2255.63
2255.63-> CALL-ME
2255.63-> CALL-OF
2255.63-> BALL-ME
2255.63-> BALL-OF
Jun 03, 2016 2:00:20 PM com.aconex.codingchallenge.Client invoke
INFO: Looking for conversions for the number 1-800
1-800-> 1-800

D:\work\github\codingchallenge>
