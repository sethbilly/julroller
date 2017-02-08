/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codesample.julroller;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author ICSGH-BILLY
 */
//Java Utils logging extend for time based roll over
public class JulRollHander
{

    static Logger myLogger = null;
    static FileHandler fileHandler = null;

    public static synchronized Logger getLogger(Class<?> c, String filePath, String fileName, int fileSize, int count)
    {
        try
        {
            // Programmatic configuration
            FileHandler fh = initialiseHandler(c, filePath, fileName, fileSize, count);
            myLogger = Logger.getLogger(c.getName());
            myLogger.setLevel(Level.FINEST);
            myLogger.addHandler(fh);
        } catch (Exception e)
        {
            // The runtime won't show stack traces if the exception is thrown
            e.printStackTrace();
        }
        return myLogger;
    }

    private static FileHandler initialiseHandler(Class<?> c, String filePath, String fileName, int fileSize, int count)
    {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = format.format(date);
        try
        {
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");
            if (fileName == null || filePath == null)
            {
                fileHandler = new FileHandler(System.getProperty("java.io.tmpdir") + File.separatorChar + c.getSimpleName() + "_logs" + ".%u.%g._" + dateFormat + ".log",
                        fileSize, count, true);
            } else
            {
                File logFile = Paths.get(filePath).toFile();
                logFile.mkdirs();
                String filename = getFilenameMinusExtenstion(fileName);
                String fileExt = getFileExtension(filename);
                fileHandler = new FileHandler(logFile.toString() + File.separatorChar + filename + ".%u.%g." + "_" + dateFormat + fileExt, fileSize, count, true);
            }
            fileHandler.setLevel(Level.FINEST);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return fileHandler;
    }

    private static String getFileExtension(String fileName)
    {
        String ext = "";

        int lastIndexOfDot = fileName.lastIndexOf(".");

        ext = fileName.substring(lastIndexOfDot);

        return ext;
    }

    private static String getFilenameMinusExtenstion(String fileName)
    {
        String ext = "";

        int lastIndexOfDot = fileName.lastIndexOf(".");

        ext = fileName.substring(lastIndexOfDot);

        ext = fileName.substring(0, fileName.length() - ext.length());

        return ext;
    }
    
    
}
