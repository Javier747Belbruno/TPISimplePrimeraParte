
package tpiappsimple;

import dal.entities.*;
import entities.*;
import java.util.HashMap;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistenceClass {
    
    protected static EntityManagerFactory emf =
    Persistence.createEntityManagerFactory("Eclipse");
    public static EntityManager entityManagerGlobal = emf.createEntityManager();
    
    
    public void LoadDataInDB(String nameDocument,HashMap<String,Integer> terms){
    
    Document d = new Document(nameDocument);    
    DocumentDao ddao = new DocumentDao();
    int saveIDDoc = ddao.create(d).getIdDocumento();//Create and return the object.
    
     Iterator it = terms.keySet().iterator();
       while(it.hasNext()){
        String key = (String) it.next();
        
        int saveIDterm;
        
        TermDao tdao = new TermDao();
        if(tdao.retrieve(key)!=null){
        saveIDterm = tdao.retrieve(key);  
        }else{
        Term t = new Term(key);    
        saveIDterm = tdao.create(t).getIdTermino();//Create and return the object.
        }
        
        Posting p = new Posting(new PostingPK(saveIDDoc,saveIDterm),terms.get(key));
        PostingDao pdao = new PostingDao();
        pdao.create(p);
        }
    }
}
