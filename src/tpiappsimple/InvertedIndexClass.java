package tpiappsimple;
import entities.Posting;
import entities.Term;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//Instancio la clase como static en el main para que haya una sola instancia todo el programa.
public class InvertedIndexClass {
   
  //all attributes from Inverted Index statics because one instance of attributes.   
 
 private static HashMap<Integer,Double> vocabulary; //-- Key IDTerm -- Value idf = log(N/nr).

 private static List<List<Posting>> postingLists; // Posting List, x Postings by x Terms. Only lives in query time.
 
 private static Long amountDocuments; //How many documents we have in db
 
 
 public InvertedIndexClass() {
     vocabulary = new HashMap<>();
     amountDocuments=0L;//Zero Long type 
     postingLists = new ArrayList<>();
 }
    

 public void doFillVocabulary() {
     PersistenceClass pc = new PersistenceClass();
    
     doCountDocuments();//set amountDocuments
     
     List<Term> lt = pc.getAllTerms();
     for (Term term : lt) {
         double nr = pc.getAmountDocumentsByTerm(term.getIdTermino()).doubleValue();
         double N = amountDocuments.doubleValue();
         double idf = Math.log(N/nr);
         
         vocabulary.put(term.getIdTermino(), idf);
 
     }
}
 

 public boolean IsEmpty() {
     boolean b = false;
     if(vocabulary.isEmpty())
        return true;
     return b;
}

    private void doCountDocuments() {
        PersistenceClass pc = new PersistenceClass();
        amountDocuments = pc.getAmountDocuments();
    }

    public void preparePostingList() {
        postingLists = new ArrayList<>();
    }
 
 public Long getAmountDocuments(){
 return amountDocuments;
 }
 
 //Calculo su indice de relevancia de una Palabra
 public Double getIDF(int TermID){
   Double idf = vocabulary.get(TermID);
 return idf;
 }

 
 public void setList(List<Posting> pl){
     postingLists.add(pl);
 }
 
 public List<List<Posting>> getListofPostingList(){
 return postingLists;
 }
 
 //Hace la magia de ordenar la lista de las listas de posteo por idf de los terminos.
 //Comparamos S2 con S1 y de esa forma nos lo ordena de la forma que queremos.
 public void OrderPostingLists(){
    Collections.sort(postingLists,new Comparator<List<Posting>>(){
                     @Override
                     public int compare(List<Posting> s1,List<Posting> s2){
                        return vocabulary.get(s2.get(0).getPostingPK().getIdTermino()).compareTo(vocabulary.get(s1.get(0).getPostingPK().getIdTermino()));
                      }
                    });
 }
 
 
 public void doPrintPostingLists(){
 
     for (List<Posting> postingList : postingLists) {
         
         System.out.println("Palabra: " + postingList.get(0).getPostingPK().getIdTermino() 
                 + "idf " + vocabulary.get(postingList.get(0).getPostingPK().getIdTermino()));
         
         
         for (Posting posting : postingList) {
             System.out.println(" Documento: " + posting.getPostingPK().getIdDocumento() 
                     + "Frecuencia del termino " + posting.getFrecuencia());
         }
     }
     
 }
}