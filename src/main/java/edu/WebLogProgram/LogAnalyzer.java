package edu.WebLogProgram;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     // initialize records to an empty ArrayList
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();
     }
     
    //  complete the readFile method to create a FileResource and to 
    //  iterate over all the lines in the file. For each line, create a 
    //  LogEntry and store it in the records ArrayList
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
            records.add(WebLogParser.parseEntry(line)) ;
         }
     }

     public int countUniqueIPs(){
        ArrayList<String> uniqueIps = new ArrayList<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!uniqueIps.contains(ip)) {
                uniqueIps.add(ip);
            }
            
        }
        return uniqueIps.size();
     }

     /**
      * write the void method printAllHigherThanNum that has one integer parameter num.
      * This method should examine all the web log entries in records and
      * print those LogEntrys that have a status code greater than num.
      * @param num
      */
     public void printAllHigherThanNum(int num){
        ArrayList<LogEntry> le = new ArrayList<>();
        for (LogEntry logs : records) {
            int statuscode = logs.getStatusCode();
            if (statuscode > num) {
                le.add(logs);
            }
        }

        for (LogEntry logentry: le) {
            System.out.println(logentry);
        }
     }

     /**
      * write the method uniqueIPVisitsOnDay that has one String parameter named someday
      * in the format “MMM DD” where MMM is the first three characters of the month name
      * with the first letter capitalized and the others in lowercase, and DD is the day in two digits
      * This method accesses the web logs in records and returns an ArrayList of Strings of unique IP
      * that had access on the given day.
      */
      public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIpVisits = new ArrayList<>();
        for (LogEntry logentry : records) {
            String date = logentry.getAccessTime().toString();
            if (date.contains(someday)) {
                String ip = logentry.getIpAddress();
                if (!uniqueIpVisits.contains(ip)) {
                    uniqueIpVisits.add(ip);
                }
            }
        }
        return uniqueIpVisits;
      } 

      /**
       * write the method countUniqueIPsInRange that has two integer parameters
       * named low and high. This method returns the number of unique IP addresses in records.
       * that have a status code in the range from low to high, inclusive. 
       * @param low
       * @param high
       * @return
       */
      public ArrayList<String> countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIps = new ArrayList<>();
        for (LogEntry le : records) {
            if (le.getStatusCode()>=low && le.getStatusCode()<=high) {
                String ip = le.getIpAddress();
            if (!uniqueIps.contains(ip)) {
                uniqueIps.add(ip);
            }
            }
        }
        return uniqueIps;
      }
      
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
