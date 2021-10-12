# CaesarCipher

#概要
配列を使用して、シーザー暗号（元のアルファベットから指定したKeyの分ずらす暗号）を作成、解読する。

#Overview
CaesarCipher is a Java program encrypt and decript with Caesar Cipher, which slides an alphabet by a specified key.


https://www.coursera.org/learn/java-programming-arrays-lists-data/supplement/DvNzQ/programming-exercise-implementing-the-caesar-cipher

Programming Exercise: Implementing the Caesar Cipher

# W1学習内容 What's learned


I have learned to be able to:

・Build Strings within a Java program using StringBuilder;
・Use arrays to store and manipulate collections of data; 
・Refactor your programs for improved organization using object-oriented principles; and
・Practice effective algorithm design.

# 発展
・奇数位置と偶数位置のアルファベットを別のKeyで暗号化する
・Keyが分からない前提で、暗号文の再頻出の文字を英語で再頻出の「e」だとして解読する

# 使用技術/概念
・コンストラクタ
・初期化
・Privateフィールド
・new

・StringBuilder
・Array
・for
・if
・substring

・OOPの考え方を適用したコードに書き直す
　上記のプログラムをprivateフィールドとnewでオブジェクトを作り、オブジェクトを指定して実行するように書き直した。
  
# コメント
newでインスタンスを作成して、そのインスタンスについて実行するという概念を、上手くいかない部分を修正しながら理解できた。