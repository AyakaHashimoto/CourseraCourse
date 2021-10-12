/**
 * クラス WordInFiles の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;
public class WordInFiles {
    private HashMap<String, ArrayList<String>> fileMap;
    
    
    public WordInFiles(){
        fileMap = new HashMap<>();
        
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        
        for(String w : fr.words()){
             w = w.toLowerCase();
             
             if(!Character.isLetter(w.charAt(0)) && w.length()==1){
                //System.out.println(w+ " is skipped");
                continue;
             }
             if(!Character.isLetter(w.charAt(0))){
                 //System.out.println(w);
                 w= w.substring(1);
                 //System.out.println(">> "+w);
                }
             if(!Character.isLetter(w.charAt(w.length()-1)) ){
                    //System.out.println(w);
                    w= w.substring(0,w.length()-1);
                    //System.out.println(">> "+w);
                }
               
             ArrayList<String> exist = fileMap.get(w);//get ArrayList of key w from the HashMap
            if (exist == null){ //if the ArrayList doesn't exist
               ArrayList<String> fileList = new ArrayList<String>();
               //System.out.println(f.getName());
               fileList.add(f.getName());//get file name and put it into fileList
               fileMap.put(w,fileList);//put the fileList into map
        
             }else {
               exist.add(f.getName());
           }
        }
    }
    private void buildWordFileMap(){
        fileMap.clear();
        //HashMap<String, ArrayList<String>> fileMap = new HashMap<>();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
            
        }
    }
    private int maxNumber(){
        //for(int i=0; i<=fileMap.size(); i++ )
        int max =0;
        String maxWord ="";
        for (String s : fileMap.keySet()) {
            ArrayList<String> list =fileMap.get(s);//Get the HashMap's list associated with s
            int size = list.size();        //the size of the list
            System.out.println("@maxNumber word <"+s +"> occured in "+ size+ " files.");
            if(size > max){
                max = size;
                maxWord = s;
            }
        }
        //System.out.println("@maxNumber "+maxWord +": "+ max);
        return max;
    }
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for (String s : fileMap.keySet()) {
            ArrayList<String> list =fileMap.get(s);
            int size = list.size();
            if(size == number){
                words.add(s);
            }
        }
        return words;
    }
    private void printFilesIn(String word){
        ArrayList<String> list =fileMap.get(word);
        for (String filename : list) {
            System.out.println(filename);
         }
        
    }
    
    public void testAddWordsFromFile(){
        buildWordFileMap();
        for (String s : fileMap.keySet()) {
            ArrayList<String> list =fileMap.get(s);//Get the HashMap's list associated with s
            int size = list.size();        //the size of the list
            for(String file: list){
                System.out.println(s +"\t"+file);
            }
        }
    }
    
    public void testPrintFilesIn(){
        buildWordFileMap();
        System.out.println("Type a word:");
         Scanner scanner = new Scanner(System.in);
          String word = scanner.next();
        
        System.out.println("Files that have"+ word+" in it:");
        printFilesIn(word);
    }
   
    public void testMaxNumber(){
        buildWordFileMap();
        System.out.println("MaxNum is "+maxNumber());
    }
    public void testWordsInNumFiles(){
        //This method returns ArrayList of words that appear exactly <number> files
        buildWordFileMap();
          System.out.println("# of files the word appear:");
          Scanner scanner = new Scanner(System.in);
          int num = scanner.nextInt();
        //int num =5;
        int count =0;
        ArrayList<String> winf = wordsInNumFiles(num);
        System.out.println("Words that appear "+ num+" files:");
        for(String w: winf){
            System.out.println(w);
            count++;
        }
        System.out.println("The# of words appear "+ num+" files:"+count);
    }
    public void tester(){
        buildWordFileMap();
        System.out.println("(maxNumber)The max number of a word appeared: "+maxNumber());
        //System.out.println("Words appeared the most are:");
        
        ArrayList<String> wordList = wordsInNumFiles(maxNumber()); 
        System.out.println("Words that appear "+ maxNumber()+" times:");
        for (String s : wordList){
            System.out.println("From wordList:<"+s+ "> is in ");
            printFilesIn(s);
        }
        
    }
}

/* method addWordsFromFile referred below:
 * https://stackoverflow.com/questions/12134687/how-to-add-element-into-arraylist-in-hashmap
 * 
 * 
 */