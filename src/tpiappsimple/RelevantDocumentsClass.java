/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpiappsimple;


import entities.Posting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import static tpiappsimple.Main.ii;


public class RelevantDocumentsClass {
    
  private LinkedHashMap<Integer,Double> LD; //Document Name - Ir 
  private Integer r; //Number of documents to Retrieve     


  
  
  public RelevantDocumentsClass(){
    this(10); // By default 
  }
  public RelevantDocumentsClass(int r){
      this.r = r; 
      LD = new LinkedHashMap<>();
  }
  
  private void cleanList(){
      LD = new LinkedHashMap<>();
  }
  
  
   public void addDocuments(Integer r) {
   
       cleanList();
       PersistenceClass pc = new PersistenceClass();
    
       List<List<Posting>> llp = ii.getListofPostingList();
       
       for (List<Posting> list : llp) {
           //Agarro de la lista el primer posting ya que cualquiera de los posting tienen el mismo id termino.
           Double idf = ii.getIDF(list.get(0).getPostingPK().getIdTermino());
           for (Posting posting : list) {
               
               if(list.indexOf(posting)< r){
                   Double ir = idf * posting.getFrecuencia();
                   Integer idDoc = posting.getPostingPK().getIdDocumento();
                   
                   if(LD.containsKey(idDoc)){
                       LD.replace(idDoc, LD.get(idDoc) + ir); //Sumo si documento ya existe en la lista.
                   }else{
                    LD.put(idDoc, ir);
                   }
               }else{
                   break;//Corto si ya paso los R documentos
               }
           }
       }
    }
 
 
 public void getLD(){
     
    //Obtener la lista ordenada.
    PersistenceClass pc = new PersistenceClass(); 
   
    List<ListObject> l = new ArrayList<>();
    Iterator it = LD.keySet().iterator();
    int i=0;
       while(it.hasNext() && i < r){
           Integer key = (Integer) it.next();
           String nameDocument = pc.getDocument(key).getNombre();
           ListObject io = new ListObject(nameDocument,LD.get(key));
           l.add(io);
           i++;
           }
 
   OrderList(l);
   
     System.out.println("");
     System.out.println("Lista de " + r + " documentos." );
     for (ListObject listObject : l) {
         System.out.println("Document: -> " + listObject.getDocument() );
         System.out.println("Indice de Relevancia : -> " + listObject.getIr() );
     }
   
 }
 
private void OrderList(List<ListObject> l){
    Collections.sort(l,new Comparator<ListObject>(){
        @Override
        public int compare(ListObject o1, ListObject o2) {
           return o2.getIr().compareTo(o1.getIr());
        }
  });
 }

 
public class ListObject{//Objeto Auxiliar para pasar de LinkedHashMap a una lista Ordernada.

   private String document;
   private Double ir;
   
   public ListObject(String documentP,Double irP){
    this.document=documentP;
    this.ir=irP;
   }
   public String getDocument(){
      return document;
   }
   public Double getIr(){
       return ir;
   }
   
   public void toStringData(){
       System.out.println(" document= " + document + " ,ir; "+ ir);
   }

   }

}
