Group name: Coding 🙋 Sleeping 🙅

--------------------------------------------------------------------------
DOWNLOAD AND INSTALL MYSQL WORKBENCH
--------------------------------------------------------------------------

Visit url to find installer for MySQL	https://dev.mysql.com/downloads/installer/
Download the installer that is correct for your machine
Run installer and follow installation prompts

Please ensure the password you set is: 123456789

--------------------------------------------------------------------------
CREATE LOCAL MYSQL DATABASE AND IMPORT SCHEMA AND DUMMY DATA
--------------------------------------------------------------------------

Start a local instance of your MySQL server

In the Administration tab on the left pane, select Data Import/Restore
Select import from self-contained file and browse to find the SQL file, labelled 'event_master.sql'
Create a new schema and name it: "event_master"
Press Start Import to import the data from the SQL file

You can check the tables were created after refreshing in the Schemas tab on the left navigator pane
You can then check the data was imported by selecting 10 rows from the event table using the following command:
SELECT * FROM event_master.event LIMIT 10;

--------------------------------------------------------------------------
EXECUTE JAR FILE
--------------------------------------------------------------------------

Must use JDK 11 to be sure the jar will run
JDK 11 can download from https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

In the terminal run the following command:
java -jar 'event_master.jar'

Open a browser and visit:
localhost/9999

Github repository link(master branch): https://github.com/unsw-cse-capstone-project/capstone-project-coding-yes-sleeping-no.git 
