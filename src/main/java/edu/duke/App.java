package edu.duke;

import java.io.*;

import edu.GladLibs.WordsInFiles;
import edu.WebLogProgram.Tester;

public final class App {
    private App() {
    }
    public static void main(String[] args) {

        /**
         * runs tester method in WordsInFiles.java class
         * created an object of that class and then ran tester method
         */
        
        // WordsInFiles obj = new WordsInFiles();
        // obj.tester();

        // GladLibMap obj = new GladLibMap();
        // obj.makeStory();

        
        Tester ts = new Tester();
        // ts.testLogAnalyzer();
        // ts.testUniqueIP();
        // ts.testHighStatusCode(); 
        // ts.testUniqueIPVisitsOnDay();
        // ts.testCountUniqueIPsInRange();
        // ts.testCountVisitsPerIP();
        // ts.testMostNumberVisitsByIP();
        // ts.testIPsMostVisits();
        // ts.testIPsForDays();
        // ts.testDayWithMostIPVisits();
        ts.testIPsWithMostVisitsOnDay();



        // DirectoryResource dr = new DirectoryResource();
        // for(File file : dr.selectedFiles())
        // {
            // FileResource fr = new FileResource(file);
            // System.out.println(fr.asString());
            // System.out.println(file.getName());
        // }  
    }      
}
