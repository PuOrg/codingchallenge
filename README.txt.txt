"1-800-CODING-CHALLENGE" 
=======================

Requirment
----------
Many companies like to list their phone numbers using the letters printed on most
telephones. This makes the number easier to remember for customers. 

An example may be 1-800-FLOWERS


How to Build
--------

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
