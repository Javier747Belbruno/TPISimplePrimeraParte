/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpiappsimple;

import entities.Term;
import java.util.HashMap;
import java.util.HashSet;
import static tpiappsimple.Main.ii;


public class QueryClass {
    
    
    public void processQuery(String query,Integer r){
        
        Tokenizer t= new Tokenizer();
        PersistenceClass pc = new PersistenceClass();
        RelevantDocumentsClass rd = new RelevantDocumentsClass(r);
        ii.preparePostingList();//prepare postingList for a new Query.
        
        //split a string by space
        String[] splited = query.split("\\s+");
        HashMap<Integer,Double> termHM = new HashMap<>();//First IDTerm, and its idf.
        for (String splitedString : splited) {
           HashSet<String> hst = t.getTerms(splitedString);//CleanAndRemoveStopWords.
            //Find for each term of query its IDNumber in DB
            for (String string : hst) {
            //Busco en dataBase y guardo Objeto.
                Term te = pc.getTerm(string);
                if(te!=null)
                {
                    //Insert
                    ii.setList(pc.getAllPostingByTerm(te.getIdTermino()));
                    //Order
                    ii.OrderPostingLists();
                }
        }
      }
        //YA TENGO TODO ORDERNADO ASIQUE AHORA IR AGARRANDO HASTA R DE CADA LISTA
        ii.doPrintPostingLists();
        rd.addDocuments(r);
        rd.getLD();
    }
}
