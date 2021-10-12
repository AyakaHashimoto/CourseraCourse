import edu.duke.*;
/**
 * クラス CaesarCipher の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
   public CaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        System.out.println("shiftedAlphabet: "+shiftedAlphabet);
        mainKey = key;
    }
    
   public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i =0; i<sb.length(); i++){
            char c = sb.charAt(i);
            boolean isUpperCase = false;
            int idx = alphabet.indexOf(Character.toLowerCase(c));

            if(idx !=-1){
                if(Character.isUpperCase( c )){
                  c = Character.toUpperCase(shiftedAlphabet.charAt(idx));
                  System.out.println("isUpperCase True at index at "+ i
                  +"(" +sb.charAt(i)+")→" +shiftedAlphabet.charAt(idx));
                  sb.setCharAt(i,c);
                  continue;
                }else{
                  c = shiftedAlphabet.charAt(idx);
                  sb.setCharAt(i,c);
                }
            }
        }
        return sb.toString();
    }
    
    
   public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }


   
    
}
