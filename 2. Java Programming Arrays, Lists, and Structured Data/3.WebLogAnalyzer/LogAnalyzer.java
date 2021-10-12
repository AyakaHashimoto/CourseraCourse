
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
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     } 
     public void readFile() {
         FileResource fr = new FileResource();
         //("/"+filename+".txt");
        for(String s : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(s);
            records.add(le);
        }
     }  
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        
        for(LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
      }
     public void printAllHigherThanNum(int num){
         //ArrayList<LogEntry> codeHigher = new ArrayList<LogEntry>();
         for(LogEntry le : records){
             int code =le.getStatusCode();
             if(code > num){
                //codeHigher.add(le);
                System.out.println(le);
                }
            }
         
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
          ArrayList<String> uniqueIPs = new ArrayList<String>();
          //[30/Sep/2015:07:47:11 -0400]
        for(LogEntry le : records){
            String ipAddr = le.getIpAddress();
            String dateTime =le.getAccessTime().toString();
            //System.out.println(dateTime);
            String date= dateTime.substring(8,10);
            String month =dateTime.substring(4,7);
            String monthDate = month+" "+date;
            //System.out.println("date>"+date+" month>"+month+" monthDate>"+monthDate);
            if(someday.equals(monthDate)){
                if(!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        
        return uniqueIPs;
        }
     public int countUniqueIPInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records){
            String ipAddr = le.getIpAddress();
            int code =le.getStatusCode();
            //uniqueIPs.add(le);
            if(low<=code && high>=code){
                if(!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
        }
}
