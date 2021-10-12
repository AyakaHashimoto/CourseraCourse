import edu.duke.*;
/**
 * クラス CaesarCipherTwo の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1d;
    private int key2d;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet ="abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 =  alphabet.substring(key1)+
        alphabet.substring(0,key1);
        shiftedAlphabet2 =  alphabet.substring(key2)+
        alphabet.substring(0,key2);
        key1d = key1;
        key2d = key2;
     }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            int idx =alphabet.indexOf(Character.toLowerCase(c)); 
            //Find the index of c in the alphabet (call it idx)
            if(idx !=-1){
                //If the index of the char is even (key1)
                if(i%2 == 0){
                 if(Character.isUpperCase( c )){  
                     c = Character.toUpperCase(shiftedAlphabet1.charAt(idx));
                     //System.out.println("isUpperCase True at index at "+ i
                     //+"(" +encrypted.charAt(i)+")→" +shiftedAlphabet1.charAt(idx));
                     
                     encrypted.setCharAt(i,c);
                     
                 }else{
                     c = shiftedAlphabet1.charAt(idx);
                     encrypted.setCharAt(i,c);
                 }
                 //or the index of the char is odd (key2)
              }else{
                 if(Character.isUpperCase( c )){  
                     c = Character.toUpperCase(shiftedAlphabet2.charAt(idx));
                     //System.out.println("isUpperCase True at index at "+ i
                     //+"(" +encrypted.charAt(i)+")→" +shiftedAlphabet2.charAt(idx));
                     
                     encrypted.setCharAt(i,c);
                     
                 }else{
                     c = shiftedAlphabet2.charAt(idx);
                     encrypted.setCharAt(i,c);
                 }
                }
            }
        }
        return encrypted.toString();
    }
    
   public String decrypt(String input){
       CaesarCipherTwo cct = new CaesarCipherTwo((26-key1d),(26-key2d));
       System.out.println("key is "+ key1d+ " and "+ key2d + "\n" + 
       "Decryption key is "+ (26-key1d)+ " and "+(26-key2d));
       System.out.println("The decrypted text:");
       return cct.encrypt(input);
    }
    
   public int[] countLetters(String message){
        String alph ="abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for(int k=0; k<message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alph.indexOf(ch);//Find index# of alphabet at k
            if(index != -1){ //Count the alphabet 
                counts[index] ++; 
                //System.out.println("countLetters: "+ch+" is counted in index "+ index);
            }
        }
        
        return counts;
    }
    
    public int maxIndex(int[] values){
      int max = 0;
      int index =0;
      for(int i = 0; i < values.length; i++){
        if(max < values[i]) {
            max = values[i];
            index = i;
            //System.out.println("Current max is value "+ i);
        }
        }
      System.out.println("maxIndex is value "+ max +" at the index of "+ index);
      return index;
   }
   
   /* This method return a new String that is every other character from message 
   starting with the start position. */
   
   public String halfOfString(String message, int start){
       StringBuilder half = new StringBuilder();
       for(int i = 0; i < message.length(); i++) {
           if(start%2 == 0){//Even 
               if(i%2 == 0){
               half.append(message.charAt(i));
             }
            }else{//Odd
              if(i%2 == 1){
               half.append(message.charAt(i));
            }
            }
        }
        return half.toString();
    }
   
   public int getKey(String s){//Return index of max frequency alphabet
       int maxIndex = maxIndex(countLetters(s));
       int dkey = maxIndex - 4;
       if(maxIndex <4){
           dkey = 26 - (4 - maxIndex);
        }
       
       return dkey;
       }
       
   public String breakCaesarCipher(String input){
       String even = halfOfString(input, 0);
       String odd = halfOfString(input, 1);
       System.out.println("breakCaesarCipher: String even is" +even);
       System.out.println("breakCaesarCipher: String odd is" +odd);
       int keyEven = getKey(even);
       int keyOdd = getKey(odd);
       System.out.println("breakCaesarCipher: The key for the even letters is "
       + keyEven+ "and the odd letters is" + keyOdd );
       
       
       CaesarCipherTwo cct = new CaesarCipherTwo(26-keyEven, 26-keyOdd);
       return cct.encrypt(input);
    }
    
   public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        
        CaesarCipherTwo cct = new CaesarCipherTwo(18,6);
        System.out.println("The original text: \r\n"+message);
        System.out.println("The encrypted text: \r\n"+cct.encrypt(message));
        System.out.println("The decrypted text: \r\n"+ cct.breakCaesarCipher(encrypt(message)));
        
        //System.out.println(decrypt(message));
        //System.out.println("Original message is: \r\n"+message);
        //System.out.println("Encrypted message is: \r\n"+encrypt(message));
        //System.out.println("Decrypted message is: \r\n"+decrypt(encrypt(message)));
    } 
    
}
