import edu.duke.*;
/**
 * クラス Tester の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class Tester {
    public void testEncryptLetter(){
        CaesarCipher cc =new CaesarCipher(23);
        System.out.println(cc.encryptLetter('g'));
        
        FileResource fr = new FileResource();
        String text = fr.asString();
        System.out.println(cc.encrypt(text));
        System.out.println("------------------");
        cc.decrypt(cc.encrypt(text));
    }
    public void testCeaserCracker(){
        CaesarCracker cc = new CaesarCracker('a');//The most common latter 'a' for Portuguese
        FileResource fr = new FileResource();
        String text = fr.asString();
        System.out.println("------------------");
        System.out.println(cc.getKey(text));
        System.out.println(cc.decrypt(text));
    }
    public void testVigenereCipher(){
        int[] key ={17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key); 
        FileResource fr = new FileResource();
        String text = fr.asString();
        System.out.println("------------------");
        System.out.println(vc.encrypt(text));
        System.out.println("------------------");
        System.out.println(vc.decrypt(vc.encrypt(text)));
        //System.out.println(vc.decrypt(text));
    }
}
