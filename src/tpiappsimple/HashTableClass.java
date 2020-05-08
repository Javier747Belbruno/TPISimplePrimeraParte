/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpiappsimple;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Javier
 */
public class HashTableClass {
   
 private static HashMap<String,Integer> vocabulary = new HashMap<>(); //Static because
 //all objects HashtableClass share same attribute.
    
    
    //Contener la hashtableVocabulary que va a ser cargada en la base de datos.
    //Manejar Merge
 
public void doMerge(HashMap<String,Integer> terms) {
    terms.forEach((key, value) -> vocabulary.merge( key, value, (v1, v2) ->  v1 + v2));
}
public void doPrint(){
    int sum = 0;
      Iterator it = vocabulary.keySet().iterator();
       while(it.hasNext()){
          String key = (String) it.next();
          System.out.println("Key: " + key + " -> Value: " + vocabulary.get(key));
           sum = sum +  vocabulary.get(key);}
       System.out.println("sum = " + sum);
}
 
    




//Merge HashMaps Example
//map 1
public void mergeHTExample(){
HashMap<String,Integer> map1 = new HashMap<>();
 
map1.put("A",1);
map1.put("B",2);
map1.put("C",3);
map1.put("E",4);
 
//map 2
HashMap<String,Integer> map2 = new HashMap<>();
 
map2.put("G",5);   //It will replace the value 'A'
map2.put("B",6);
map2.put("C",8);
map2.put("D",10);   //A new pair to be added
 
//Merge maps
map2.forEach(
    (key, value) -> map1.merge( key, value, (v1, v2) ->  v1 + v2)
);
 
System.out.println(map1);
}
}
