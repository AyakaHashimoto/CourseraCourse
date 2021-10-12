
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
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        //("short-test_log");
        la.printAll();
    }
    public void testUniqueIP(){
        LogAnalyzer la =new LogAnalyzer();
        la.readFile();
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are "+ uniqueIPs +" IPs");
    }
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la =new LogAnalyzer();
        la.readFile();
        System.out.println("----------");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la =new LogAnalyzer();
        la.readFile();
        ArrayList<String> ipList = new ArrayList<String>();
        ipList =la.uniqueIPVisitsOnDay("Mar 24");
        System.out.println("----------");
        System.out.println("Array size:"+ ipList.size());
        for(String ip: ipList){
                System.out.println(ip);
        }
    }
    public void testCountUniqueIPInRange(){
        LogAnalyzer la =new LogAnalyzer();
        la.readFile();
        System.out.println("----------");
        System.out.println(la.countUniqueIPInRange(300,399));
    }
}
