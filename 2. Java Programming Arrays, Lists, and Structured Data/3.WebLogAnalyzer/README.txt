#WebLogAnalyzer
 LogAnalyzer.java
 LogEntry.java
 WebLogParser.java

#overview
webサーバログをWebLogParserで読み込み、
LogEntryでログから取得するIPアドレスや時刻、リクエスト、ステータスコードを取得するgetメソッドを準備
LogAnalyzerで条件を指定してユニークIPアドレスや日付ごとの最も訪問回数の多いIPアドレス、訪問が多い日付等を返す

　LogAnalyzerのメソッド
　 countVisitsPerIP	-- This method returns a HashMap<String, Integer> that maps an IP address to the number of times that IP address appears in records.
   mostNumberVisitsByIP	--This method returns the maximum number of visits to this website by a single IP address.
   iPsMostVisits	--This method returns an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website.
   iPsForDays   	--This method returns a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses that occurred on that day (including repeated IP addresses). 
   dayWithMostIPVisits  --This method returns the day that has the most IP address visits. 
   iPsWithMostVisitsOnDay-- This method returns an ArrayList<String> of IP addresses that had the most accesses on the given day.

# W3学習内容 What_s learned
Web Server Logs Module

I have learned :

Read information from a web server log;

Count the number of unique visitors to your website; and

Count the number of times each visitor uses your website.


使用技術/概念
・コンストラクタ
・初期化
・Privateフィールド
・new

・HashMap
・Array
・ArrayList
・for
・if
・while
　他、HashMapやArrayListのメソッド及び本コース用に提供されたクラスのメソッド*（参照　https://www.dukelearntoprogram.com/course3/doc/　）

