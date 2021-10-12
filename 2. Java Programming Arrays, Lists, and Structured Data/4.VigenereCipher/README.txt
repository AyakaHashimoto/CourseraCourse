#VigenereProgram
 VigenereBreaker.java
 VigenereCipher.java
 CaesarCipher.java
 CaesarCracker.java
 Tester.java

#overview
ヴィジュネル暗号(Vigenere Cipher)は、元の文字列を入れ替える暗号。
Caesar Cipherとの違いは、Keyが可変長で、すべての文字を同じKeyでずらすわけではない点である。
本プログラムは
・任意の長さのKeyでヴィジュネル暗号を作成
・ヴィジュネル暗号を解読する

　解読のプロセスは、
　　・主要言語の頻出語彙辞書を読み込み；
　　・再頻出のCharを特定し；
　　・暗号分の再頻出Charを特定し；
　　・すべての候補言語でdecryptを試み、単語を辞書と突き合わせる；
　　・最もマッチした単語数の多い辞書の解読文を採用する
　　




