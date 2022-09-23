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

     /**
      * write the method countVisitsPerIP, which has no parameters. This method returns a
      * HashMap<String, Integer> that maps an IP address to the number of times that IP address 
      * appears in records, meaning the number of times this IP address visited the website.  
      * @return
      */
     public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String,Integer> counts = new HashMap<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip)+1);
            }
        }
        return counts;
     }
    
     /**
      * write the method mostNumberVisitsByIP, which has one parameter, a HashMap<String, Integer> 
      * that maps an IP address to the number of times that IP address appears in the web log file. 
      * This method returns the maximum number of visits to this website by a single IP address.
      * @param map
      * @return
      */

     public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int maxCount = 0;
        for (String key : map.keySet()) {
            int currValue = map.get(key);
            if (currValue > maxCount) {
                maxCount = currValue;
            }
        }

        return maxCount;
     }

     /**
      * write the method iPsMostVisits, which has one parameter, a HashMap<String, Integer>
      * that maps an IP address to the number of times that IP address appears in the web log file.
      * This method returns an ArrayList of Strings of IP addresses that all have the
      * maximum number of visits to this website.
      * @param map
      * @return
      */
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> mostVisitIp = new ArrayList<>();
        int maxCount = mostNumberVisitsByIP(map);
        for (String ip : map.keySet()) {
            if (map.get(ip).equals(maxCount)) {
                mostVisitIp.add(ip);
            }
        }
        return mostVisitIp;
     }

     /**
      * write the method iPsForDays, which has no parameters. This method returns a 
      * HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an 
      * ArrayList of IP addresses that occurred on that day (including repeated IP addresses).
      * @return
      */
     public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> iPsForDaysMap = new HashMap<>();
        for (LogEntry logEntry : records) {
            String time = logEntry.getAccessTime().toString().substring(4,10);
            if (!iPsForDaysMap.containsKey(time)) {
                ArrayList<String> day = new ArrayList<>();
                day.add(logEntry.getIpAddress());
                iPsForDaysMap.put(time, day);
            }
            else 
            {
                ArrayList<String> day = iPsForDaysMap.get(time);
                day.add(logEntry.getIpAddress());
            }
        }
        
        return iPsForDaysMap;
     }

     /**
      * write the method dayWithMostIPVisits, which has one parameter that is a 
      * HashMap<String, ArrayList<String>> that uses records and maps days from 
      * web logs to an ArrayList of IP addresses that occurred on that day. 
      * This method returns the day that has the most IP address visits. If there is a
      * tie, then return any such day.
      * @param dayIps
      * @return
      */
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayIps) {
        String mostIPVisits = null;
        int maxSize = 0;
        for (String days : dayIps.keySet()) {
            int currSize = dayIps.get(days).size();
            if (currSize > maxSize) {
                maxSize = currSize;
                mostIPVisits = days;
            }
        }

        return mostIPVisits;
     }

     /**
      * write the method iPsWithMostVisitsOnDay, which has two parameters—
      * the first one is a HashMap<String, ArrayList<String>> that uses records and
      * maps days from web logs to an ArrayList of IP addresses that occurred on that day,
      * and the second parameter is a String representing a day in the format “MMM DD” 
      * described above. This method returns an ArrayList<String> of IP addresses that had the most 
      * accesses on the given day.
      * @param map
      * @param day
      * @return
      */

      public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
        ArrayList<String> ips = map.get(day);
        HashMap<String, Integer> count = new HashMap<>();
        for (String ip : ips) {
            if (!count.containsKey(ip)) {
                count.put(ip, 1);
            }
            else
            {
               count.put(ip, count.get(ip)+1);
            }
        }
        return iPsMostVisits(count);
        
      }
     
     
}
