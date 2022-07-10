# eventanalyzer

Instructions to setup and run this project:

1)Clone this project from github https://github.com/debiem/eventanalyzer.git
2)Import the project as an existing maven project in your choice of IDE(I have used Eclipse)
3)Clean and build the project using maven, you can do this by opening command prompt and go to the root folder of the project and issue the command "mvn clean install"
4)Open command prompt and go to the root of the project folder i.e the eventanalyzer folder and run the below command to start the HSQL DB Engine, make sure the engine keeps running in a command prompt:
	java -cp hsqldb-2.4.0.jar org.hsqldb.Server --database test
5)Now navigate to the "target" folder within the project from the command prompt and run the below command to run the main class
java -cp uber-eventanalyzer-0.0.1-SNAPSHOT.jar;D:\Projects\Interview\credit_suisse\Codebase\eventanalyzer\hsqldb-2.4.0.jar com.cs.build.server.client.AnalyzerClient

Please note the path to hsqldb-2.4.0.jar can be different in your system, so set the classpath accordingly.
6)After running the above command the program will expect the path of the loginfo.txt file where the event details are stored in JSON format, please provide that path and press enter
7)This should give you the below kind of output where the event details are finally stored in HSQL DB with an Alert flag which is set to true if build duration is more than 4, otherwise set to false

org.hsqldb.jdbc.JDBCPreparedStatement@9f116cc[sql=[SELECT * FROM EventStatistics]]
[main] INFO com.cs.build.server.db.dao.impl.BuildServerDAOImpl - id: scmbstgra, duration: 5, type: APPLICATION_LOG, host: 12345, alert: true
[main] INFO com.cs.build.server.db.dao.impl.BuildServerDAOImpl - id: scmbstgrb, duration: 3, type: , host: , alert: false
[main] INFO com.cs.build.server.db.dao.impl.BuildServerDAOImpl - id: scmbstgrc, duration: 8, type: , host: , alert: true