package edu.WebLogProgram;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    //complete the testLogAnalyzer method, which creates a LogAnalyzer object, 
    //calls readFile on the data file short-test_log, and then calls printAll to print all 
    //the web logs
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("src\\main\\java\\edu\\WebLogProgram\\short-test_log");
        la.printAll();
    }

    /*
     * This method should return an integer representing the number of unique IP addresses.
     * It should also assume that the instance variable records already has its ArrayList of Strings 
     * read in from a file, and should access records in computing this value. 
     */

    public void testUniqueIP(){
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("src\\main\\java\\edu\\WebLogProgram\\short-test_log");
        System.out.println(obj.countUniqueIPs()); 
    }

    public void testHighStatusCode() {
        LogAnalyzer obj1 = new LogAnalyzer();
        obj1.readFile("src\\main\\java\\edu\\WebLogProgram\\weblog1_log");
        obj1.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer obj2 = new LogAnalyzer();
        obj2.readFile("src\\main\\java\\edu\\WebLogProgram\\weblog1_log");
        // for (String log2 : obj2.uniqueIPVisitsOnDay("Sep 14")) {
        //     System.out.println(log2);
        // }
        System.out.println(obj2.uniqueIPVisitsOnDay("Mar 17").size()); 
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer obj2 = new LogAnalyzer();
        obj2.readFile("src\\main\\java\\edu\\WebLogProgram\\weblog1_log");
        System.out.println(obj2.countUniqueIPsInRange(300,399));
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("src\\main\\java\\edu\\WebLogProgram\\short-test_log");
        System.out.println(obj.countVisitsPerIP());
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("src\\main\\java\\edu\\WebLogProgram\\weblog1_log");
        System.out.println(obj.mostNumberVisitsByIP(obj.countVisitsPerIP()));
    }

    public void testIPsMostVisits() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("src\\main\\java\\edu\\WebLogProgram\\weblog1_log");
        System.out.println(obj.iPsMostVisits(obj.countVisitsPerIP()));
        
    }

    public void testIPsForDays() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("src\\main\\java\\edu\\WebLogProgram\\weblog3-short_log");
        System.out.println(obj.iPsForDays());
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("src\\main\\java\\edu\\WebLogProgram\\weblog1_log");
        System.out.println(obj.dayWithMostIPVisits(obj.iPsForDays()));
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("src\\main\\java\\edu\\WebLogProgram\\weblog1_log");
        System.out.println(obj.iPsWithMostVisitsOnDay(obj.iPsForDays(), "Mar 17"));
        
    }
}
