
package tpiappsimple;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/* This class manages one file*/
public class FileClass {
    
 private final String separator = ",";
 
 /** Read one File And Return a String list of Terms
     * @param  filename. 
     * @return HashMap<String,Integer>
     * @throws java.io.IOException*/ 
 public HashMap<String,Integer> readFileAndReturnTerms(String filename) throws IOException {
      
    HashMap<String,Integer> terms = new HashMap<>(); //What I expect from this method.
    
    CreateHashSetStopWords();
    Scanner s = new Scanner(new File(filename));    
    while(s.hasNext ())
    {
      List<String> tokensForEachRow = DivideToken(ClearToken(s.next()));
      tokensForEachRow.forEach((string) -> {
            // LowerCase because has to match with stopwords
            String word = string.toLowerCase();
            if(!hsSW.contains(word)) // Is a stopWord?
            {
                if(terms.containsKey(word)){
                    terms.replace(word, terms.get(word)+1);
                }else{
                terms.put(word,1); //add word in hashtable.
                }
            }  
      });
    }
    /*
    FOR CHECK IF IT GOES WELL
    int sum = 0;
      Iterator it = terms.keySet().iterator();
       while(it.hasNext()){
          String key = (String) it.next();
          System.out.println("Key: " + key + " -> Value: " + terms.get(key));
           sum = sum +  terms.get(key);}
       System.out.println("sum = " + sum);
    */
   return terms; 
 }

/** ClearToken 
  * First: Removes special characters to the input 
  * Second: Prepares for split token adding a comma between each word.*/ 
private String ClearToken(String s) {
    String cleanToken = s;
   for (char ch: "@#$%^?;&*=`():<>~\"][!".toCharArray()) {
       cleanToken =  cleanToken.replace (Character.toString(ch),"");
    }
   for (char ch: ".'-/\\_".toCharArray()) {
       cleanToken =  cleanToken.replace (Character.toString(ch),separator);
    }
    return cleanToken;
}

/** DivideToken detects if a token has a separator 
 * and splits in two or more tokens */ 
private List<String> DivideToken(String s) {
   List<String> partsToken = new ArrayList<>();
     if(s.contains(separator)){
         partsToken = Arrays.asList(s.split(separator));
      }
     else{
         partsToken.add(s);
     }
    return partsToken;
    }


private static HashSet<String> hsSW = new HashSet<String>();
private void CreateHashSetStopWords()
{
    int len= sw.length;
    for(int i=0;i<len;i++){
        hsSW.add(sw[i]);
    }
}
//Stop Words...a lot
private static String[] sw = {"a", "able", "about",
        "across", "after", "all", "almost", "also", "am", "among", "an",
        "and", "any", "are", "as", "at", "b", "be", "because", "been",
        "but", "by", "c", "can", "cannot", "could", "d", "dear", "did",
        "do", "does", "e", "either", "else", "ever", "every", "f", "for",
        "from", "g", "get", "got", "h", "had", "has", "have", "he", "her",
        "hers", "him", "his", "how", "however", "i", "if", "in", "into",
        "is", "it", "its", "j", "just", "k", "l", "least", "let", "like",
        "likely", "m", "may", "me", "might", "most", "must", "my",
        "neither", "n", "no", "nor", "not", "o", "of", "off", "often",
        "on", "only", "or", "other", "our", "own", "p", "q", "r", "rather",
        "s", "said", "say", "says", "she", "should", "since", "so", "some",
        "t", "than", "that", "the", "their", "them", "then", "there",
        "these", "they", "this", "tis", "to", "too", "twas", "u", "us",
        "v", "w", "wants", "was", "we", "were", "what", "when", "where",
        "which", "while", "who", "whom", "why", "will", "with", "would",
        "x", "y", "yet", "you", "your", "z", ""};
}

      
        
        
        
 
