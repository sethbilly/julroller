# julroller

julroller is simple class which extends the java util logging class to enable time and size based file logging rollover.
 
##Table Of Content
- [Background](#background)
- [Install](#install)
- [Usage] (#usage)
- [Api] (#api)
- [Contribution] (#contribution)

##Background

Needed to log application specific activities but the application server running my application had other applications running on it.
The application server logs had other logs the from other application in it, so could not properly keep track of activities per application.
The LogBack implementation of the popular Sl4j configuration on the application needed other libraries loaded and server configurations changed which did
not give the desired result.

I finally had to settle on the java util logging class and extend it to give the desire logging feature needed.

##Install

To run this program as standalone you need to have latest version of java installed and the build tool used is maven.

##Usage

Simple initialize a java.util.logging.logger by calling the getLogger(Class<?> class, String filePath, String fileName, int fileSize, int count) with these parameters.

> 
import java.util.logging.logger;

public class TestJulRollHandler 
{
	private final Logger jul = JulRollHandler.getLogger(TestJulRollHandler.class, null, "jul.log", 1024*1024, 20);
	
	public static void main(String[] args)
	{
		for (int i = 0; i < 100000; i++) 
		{
            jul.log(Level.INFO, "LogTest Info {0}", i);
            jul.log(Level.WARNING, "LogTest Warning {0}", i);
            jul.log(Level.SEVERE, "LogTest Severe {0}", i);
        }
	}
}

##API

JulRollHandler.getLogger() params

class: Class
The class to log

filePath: String
The location to store log files. If none provided, logs are stored in temporary directory of operating system.

fileName: String
The name with extension of file to write the logs to.

fileSize: Integer
Size of file to reach before logs are rolled over to a new log file.

count: Integer

Number of files to be created when file limit size is reached.

##Contribution

This is an public repository. Feel free do clone or download and extend this class to suit your logging features.

