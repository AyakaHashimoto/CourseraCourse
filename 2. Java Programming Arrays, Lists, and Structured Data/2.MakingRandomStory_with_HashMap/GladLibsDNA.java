import edu.duke.FileResource;

import java.util.HashMap;
@SuppressWarnings("unused")
public class GladLibsDNA {
    private HashMap<String, Integer> dnaCountMap;

    public GladLibsDNA(){
        dnaCountMap = new HashMap<>();
        //dnaCountMap.clear();
    }
    public void builtCodonMap(int start, String dna){
        dnaCountMap = new HashMap<>();
        int idx= start;
        dna =dna.toUpperCase();
        for(int i=0;i<dna.length();i++) {
            if(dna.substring(idx).length()>=3){
            String cdn = dna.substring(idx, idx+3);
                if(!dnaCountMap.containsKey(cdn)){
                    dnaCountMap.put(cdn,1);
                }else{
                    dnaCountMap.put(cdn,dnaCountMap.get(cdn)+1);
                }
                idx += 3;
                }
        }
    }
    public String getMostCommonCodon(){
        int count =0;
        String codon = null;
        for(String s: dnaCountMap.keySet()){
            if(count < dnaCountMap.get(s)){
                count = dnaCountMap.get(s);
                codon = s;
            }
        }
        return codon;
    }
    public void printCodonCounts(int start, int end){
        for(String s: dnaCountMap.keySet()){
            int count =dnaCountMap.get(s);
            if(count>=start && count<=end){
                System.out.println(s+"\t"+count);
            }
        }
    }
    public void printAll(){
        for(String s: dnaCountMap.keySet()){
            System.out.println(dnaCountMap.get(s)+"\t"+ s);
        }
    }
    @SuppressWarnings("unused")
    public void testFrames(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        int start =1;
        System.out.println("--------");
        System.out.println("Frame: "+ start);
        System.out.println("The total counts are "+dnaCountMap.size());
        printCodonCounts(0,10) ;
        System.out.println("The most common codon is "+getMostCommonCodon());
    }
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //String dna ="CGTTCAAGTTCAA";
        //int start =0;
            for(int start =0; start<3; start++){
        builtCodonMap(start,dna);
        //printAll();
        System.out.println("--------");
        System.out.println("Frame: "+ start);
        System.out.println("The total counts are "+dnaCountMap.size());
        printCodonCounts(0,10) ;
        System.out.println("The most common codon is "+getMostCommonCodon());
       }


    }
}
