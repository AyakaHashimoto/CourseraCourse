import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    private FileResource fr;
    private HashMap<String,HashSet<String>> languages;
    private String[] langName;
    public VigenereBreaker(){
        fr = new FileResource();
        languages = new HashMap<String,HashSet<String>>();
        langName=new String[] {"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        
    }
    
    public void dictionarySetting(){
        
        for(int i=0; i<langName.length; i++){
            FileResource dictionary =new FileResource("dictionaries/"+langName[i]);
            languages.put(langName[i],readDictionary(dictionary));
        }
        
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder allWords =new StringBuilder();
        for(String word:dictionary){
            allWords.append(word);
        }
        CaesarCracker ccr = new CaesarCracker();
        int[] counts =ccr.countLetters(allWords.toString());
        int index = ccr.maxIndex(counts);
        return alph.charAt(index);
    }
    
    public void breakForAllLanguages(String encrypted,HashMap<String,HashSet<String>> langs ){
        //HashMap<String,HashSet<String>> langNtext =new HashMap<String,HashSet<String>>();
        int count =0;
        String language ="";
        String message ="";
        for(String lang:langs.keySet()){
            System.out.println("Chosen language: "+lang);
            String decrypted =breakForLanguage(encrypted, langs.get(lang));
            //Compare the # of matched words to dictionary
            if(count<countWords(decrypted, langs.get(lang))){
                count =countWords(decrypted,langs.get(lang));
                language = lang;
                message = decrypted;
            }
        }
        System.out.println("Language determined: "+language);
        System.out.println(message);
    }    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer =new StringBuilder();
        for(int i=whichSlice; i<message.length(); i+=totalSlices){
            answer.append(message.charAt(i));
            //System.out.println(i);
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker ccr = new CaesarCracker(mostCommon);
        for(int i=0; i<klength; i++){
            String slice =sliceString(encrypted,i,klength);
            key[i]=ccr.getKey(slice);            
        }
        return key;
    }

    public void breakVigenere () {
        VigenereBreaker vb = new VigenereBreaker();
        
        for(int i=0; i<langName.length; i++){
            FileResource dictionary =new FileResource("dictionaries/"+langName[i]);
            languages.put(langName[i],readDictionary(dictionary));
            System.out.println(langName[i]+" is read");
        }
        String message = fr.asString();
        breakForAllLanguages(message,languages);
        /*
        FileResource dic =new FileResource("dictionaries/English");
        HashSet<String> dictionary =readDictionary(dic);
        int keyLength =4;
        int[] key =tryKeyLength(message, keyLength, 'e');
        System.out.println(Arrays.toString(key));
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(message));
        */
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dic = new HashSet<>();
        for(String word:fr.lines()){
            dic.add(word.toLowerCase());
        }
        return dic;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        String[] words = message.split("\\W");
        for(String s: words){
            String word = s.toLowerCase();
            if(dictionary.contains(word)){
                count++;
                //System.out.println(s);
            }
        }
        //System.out.println("Word count: "+ count);
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int count =0;
        String decrypted ="";
        int[] keys ={};
        for(int i =1; i<=100;i++){
            int[] key=tryKeyLength(encrypted,i,mostCommonCharIn(dictionary));//i=key length
            VigenereCipher vc = new VigenereCipher(key);
            String decryptedText =vc.decrypt(encrypted);
            //System.out.println();
            if(count<countWords(decryptedText, dictionary)){
                count =countWords(decryptedText, dictionary);
                decrypted =decryptedText;
                keys = key;
            }
        }
        System.out.println(Arrays.toString(keys));
        System.out.println("Key length: "+keys.length);
        System.out.println("Word count: "+count);
        return decrypted;
    }
    //Test methods below
    public void testMostCommonCharIn(){
        FileResource fr = new FileResource();
        
        System.out.println("The most frequent char is "+mostCommonCharIn(readDictionary(fr)));
    }
    public void testDictionarySetting(){
        dictionarySetting();
        for (String s : languages.keySet()) {
           HashSet<String> setWords = languages.get(s);
           System.out.println(s+" has "+setWords.size()+" words");
           // System.out.println(s);
        }
    }
    public void testBreakForLanguage(){
        FileResource dic =new FileResource("dictionaries/English");
        HashSet<String> dictEn = readDictionary(dic);
        FileResource fr =new FileResource();
        System.out.println(breakForLanguage(fr.asString(), dictEn));
    }
    public void testCountWords(){
        FileResource fr =new FileResource("dictionaries/English");
        HashSet<String> dic = readDictionary(fr);
        FileResource text = new FileResource();
        System.out.println(countWords(text.asString(),dic));
    }
    public void testReadDictionary(){
        FileResource fr =new FileResource();
        HashSet<String> dic = readDictionary(fr);
        int i= 0;
        for(String s:dic){
            
            System.out.println(s);
            i++;
            if(i>10) break;
        }
    }
    public void testSliceString(){
        String message ="abcdefghijklm";
        System.out.println("------------------");
        System.out.println(sliceString(message,0,3));
        System.out.println(sliceString(message,1,3));
        System.out.println(sliceString(message,2,3));
        System.out.println(sliceString(message,0,4));
        System.out.println(sliceString(message,1,4));
        System.out.println(sliceString(message,2,4));
        System.out.println(sliceString(message,3,4));
        System.out.println(sliceString(message,0,5));
        System.out.println(sliceString(message,1,5));
        System.out.println(sliceString(message,2,5));
        System.out.println(sliceString(message,3,5));
        System.out.println(sliceString(message,4,5));
    }
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        String text = fr.asString();
        int[]key =tryKeyLength(text, 38, 'e');
        System.out.println(Arrays.toString(key));
        
        //countWords(String message, HashSet<String> dictionary)
        //for exam
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted =vc.decrypt(text);
        System.out.println(decrypted);
        FileResource dictionaryEn =new FileResource("dictionaries/English");
        HashSet<String> dic = readDictionary(dictionaryEn);
        System.out.println("word counts: "+countWords(decrypted,dic));
    }
}
