import edu.duke.*;
/**
 * クラス TestCaesarCipher の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class TestCaesarCipher {
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
      System.out.println("maxIndex is value "+ max +"at the index of "+ index);
      return index;
   }   
   
   public String breakCaesarCipher(String input){
       int[] freqs = countLetters(input);
       int maxIndex =maxIndex(freqs);
       int dkey = 26 - (maxIndex - 4);//The letter of max freq. should be 'e' and where 'a' is is index of e -4
       if(maxIndex <4){
           dkey = 26 - (4 - maxIndex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);////To use encrypt method in CaesarCipher class
        System.out.println("The decryption key is "+ dkey);
        return cc.encrypt(input);
    }
   
   public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        //CaesarCipher cc = new CaesarCipher(18);
        //System.out.println("Encrypted: "+ cc.encrypt(message));
        //System.out.println("Decrypted: "+ cc.decrypt(cc.encrypt(message)));
        
        //System.out.println("Decrypted: "+ breakCaesarCipher(cc.encrypt(message)));
        System.out.println("Decrypted: "+ breakCaesarCipher(message));
    }
   
}
