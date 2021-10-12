import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> catList;
    /*
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    */
    private ArrayList<String> checkList;
    private Random myRandom;
    private int countReplace;
    
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";


    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myMap =new HashMap<>();
        catList =new ArrayList<>();
        myRandom = new Random();
        checkList = new ArrayList<>();
        checkList.clear();
        countReplace =0;
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        catList =new ArrayList<>();
        checkList = new ArrayList<>();
        checkList.clear();
        countReplace =0;
    }
    
    private void initializeFromSource(String source) {
        myMap = new HashMap<String, ArrayList<String>>();
        String[] categories ={"adjective","noun","color","country","verb",
            "name","animal","timeframe"};
        for(String s: categories){
            ArrayList<String> list =readIt(source+"/"+s+".txt");
            //System.out.println("CAT:"+s+":"+list.get(0)+" "+list.get(1));
            myMap.put(s,list);
            
        }
        /*
        adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
        */
        checkList= readIt(source+"/check.txt");
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
       if(!catList.contains(label))
            catList.add(label);
       if (label.equals("number"))
            return ""+myRandom.nextInt(50)+5;        
       if(myMap.get(label)==null){
           return "**UNKNOWN**";} 
       return randomFrom(myMap.get(label));
        
        /*
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
        */
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        while(checkList.contains(sub)){
            System.out.println(sub+ " is already used");
            sub = getSubstitute(w.substring(first+1,last));
            countReplace++;
        }
        if(sub !="**UNKNOWN**")
            checkList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    public int totalWordsInMap(){
        int total =0;
        
        
        
        for(String s: myMap.keySet()){

            ArrayList<String> myList = myMap.get(s);
            System.out.println("ArrayList size: "+myList.size());
            
           total = total + myList.size();
        }   
        //System.out.println("Total: "+total);
        return total;
    }
   
    public void makeStory(){
        new GladLibMap();
        initializeFromSource("data");
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("The # of replaced words: "+countReplace);
     }
    
    public int totalWordsConsidered(){
        int total = 0;
        System.out.println("Total # of categories: "+catList.size());
        for (String cat : catList) {
            if (!cat.equals("number")){
                ArrayList<String> myList = myMap.get(cat);
                System.out.println("ArrayList size: "+myList.size());
                total = total + myList.size();  
            }
        }
        return total;
    }
     
    public void testFromTemplate(){
        new GladLibMap();
        initializeFromSource("data");
        String story = fromTemplate("data/madtemplate2.txt");
        System.out.println(story);
        
    }
     
    public void testTotalWordsInMap(){
        //new GladLibMap();
        initializeFromSource("data");
        //totalWordsInMap();
        System.out.println("Total words in map: "+totalWordsInMap());
    } 
    
    public void testTotalWordsConsidered(){
        System.out.println("Total words considered: "+totalWordsConsidered());
    }
    
    public void testReadIt(){//Print all the myMap keys and arrayList items
        initializeFromSource("data");
        System.out.println(myMap.size());
        for(String s: myMap.keySet()){
            System.out.println(s);
            ArrayList<String> myList = myMap.get(s);
            System.out.println(myList.size());
            for (String word :myList){
                System.out.println("*"+word);
            }
        }
        
    }


}
