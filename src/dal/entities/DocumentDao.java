package dal.entities;
import dal.commons.DaoEclipseLink;
//import javax.enterprise.context.ApplicationScoped;
import entities.Document;


public class DocumentDao  extends DaoEclipseLink<Document, Integer>
{
    
    public DocumentDao() {
        super(Document.class);
    }
    
     public Long getAmountDocuments()
    {
        Long amount = (Long)entityManager.createNamedQuery("Document.getCount").getSingleResult();
        return amount;
    }
    
    

}
